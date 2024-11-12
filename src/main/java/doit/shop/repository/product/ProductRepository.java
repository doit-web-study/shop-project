package doit.shop.repository.product;

import doit.shop.repository.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    default Product getById(Long productId) {
        return findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
    }

    Page<Product> findAllByNameContaining(String name, PageRequest pageable);

    Page<Product> findAllByCategory(Category category, PageRequest pageable);

    Page<Product> findAllByNameContainingAndCategory(String name, Category category, PageRequest pageable);

    default Page<Product> getList(Integer pageNumber, String keyword, Category category) {
        PageRequest pageable = PageRequest.of(pageNumber, 10);

        if (keyword == null && category == null) {
            return findAll(pageable);
        }

        if (category == null) {
            return findAllByNameContaining(keyword, pageable);
        }

        if (keyword == null) {
            return findAllByCategory(category, pageable);
        }

        return findAllByNameContainingAndCategory(keyword, category, pageable);
    }
}
