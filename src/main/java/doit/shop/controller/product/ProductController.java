package doit.shop.controller.product;

import doit.shop.controller.ListWrapper;
import doit.shop.controller.PageResponse;
import doit.shop.controller.product.dto.ProductIdResponse;
import doit.shop.controller.product.dto.ProductInfoResponse;
import doit.shop.controller.product.dto.ProductRegisterRequest;
import doit.shop.controller.product.dto.ProductUpdateRequest;
import doit.shop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController implements ProductControllerDocs {

    private final HttpSession httpSession;
    private final ProductService productService;

    @PostMapping
    public ProductIdResponse registerProduct(@RequestBody ProductRegisterRequest productRegisterRequest) {
        Long userId = (Long) httpSession.getAttribute("userId");
        return productService.registerProduct(productRegisterRequest, userId);
    }

    @PutMapping("/{productId}")
    public ProductIdResponse updateProduct(@PathVariable Long productId,
                                           @RequestBody ProductUpdateRequest productUpdateRequest) {
        Long userId = (Long) httpSession.getAttribute("userId");
        return productService.updateProduct(productId, productUpdateRequest, userId);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        Long userId = (Long) httpSession.getAttribute("userId");
        productService.deleteProduct(productId, userId);
    }

    @GetMapping("/{productId}")
    public ProductInfoResponse getProduct(@PathVariable Long productId) {
        return productService.getProductInfo(productId);
    }

    @GetMapping
    public PageResponse<ProductInfoResponse> getProductList(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                            @RequestParam(required = false) String keyword,
                                                            @RequestParam(required = false) Long categoryId) {
        return productService.getProductList(pageNumber, keyword, categoryId);
    }
}
