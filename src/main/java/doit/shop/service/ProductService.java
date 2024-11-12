package doit.shop.service;

import doit.shop.controller.PageResponse;
import doit.shop.controller.product.dto.ProductIdResponse;
import doit.shop.controller.product.dto.ProductInfoResponse;
import doit.shop.controller.product.dto.ProductRegisterRequest;
import doit.shop.controller.product.dto.ProductUpdateRequest;
import doit.shop.repository.category.Category;
import doit.shop.repository.category.CategoryRepository;
import doit.shop.repository.product.Product;
import doit.shop.repository.product.ProductRepository;
import doit.shop.repository.user.User;
import doit.shop.repository.user.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductIdResponse registerProduct(ProductRegisterRequest request, Long userId) {
        User user = userRepository.getById(userId);
        Category category = categoryRepository.getById(request.categoryId());

        Product newProduct = Product.create(request, user, category);
        Product savedProduct = productRepository.save(newProduct);

        return ProductIdResponse.from(savedProduct);
    }

    public ProductIdResponse updateProduct(Long productId, ProductUpdateRequest request, Long userId) {
        Product product = productRepository.getById(productId);
        User user = userRepository.getById(userId);
        Category category = categoryRepository.getById(request.categoryId());

        product.update(request, user, category);
        Product savedProduct = productRepository.save(product);

        return ProductIdResponse.from(savedProduct);
    }


    public void deleteProduct(Long productId, Long userId) {
        Product product = productRepository.getById(productId);
        User user = userRepository.getById(userId);

        product.checkOwner(user);

        productRepository.deleteById(productId);
    }

    public ProductInfoResponse getProductInfo(Long productId) {
        Product product = productRepository.getById(productId);

        return ProductInfoResponse.from(product);
    }

    public PageResponse<ProductInfoResponse> getProductList(Integer pageNumber, String keyword, Long categoryId) {
        Category category = categoryId == null ? null : categoryRepository.getById(categoryId);
        Page<Product> productPage = productRepository.getList(pageNumber, keyword, category);

        List<ProductInfoResponse> products = productPage.get().map(ProductInfoResponse::from).toList();
        Integer currentPageNumber = productPage.getPageable().getPageNumber();
        Integer totalPageNumber = productPage.getTotalPages()-1;

        return PageResponse.from(products, currentPageNumber, totalPageNumber);
    }
}
