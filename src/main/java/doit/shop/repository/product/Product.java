package doit.shop.repository.product;

import doit.shop.controller.product.dto.ProductRegisterRequest;
import doit.shop.controller.product.dto.ProductUpdateRequest;
import doit.shop.repository.BaseEntity;
import doit.shop.repository.category.Category;
import doit.shop.repository.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String image;

    private Integer price;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private Product(String name, String description, String image, Integer price, Integer stock, Category category,
                    User user) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.user = user;
    }


    public static Product create(ProductRegisterRequest request, User user, Category category) {
        return Product.builder()
                .name(request.productName())
                .description(request.productDescription())
                .image(request.productImage())
                .price(request.productPrice())
                .stock(request.productStock())
                .category(category)
                .user(user)
                .build();
    }

    public void update(ProductUpdateRequest request, User user, Category category) {
        checkOwner(user);
        this.name = request.productName();
        this.description = request.productDescription();
        this.image = request.productImage();
        this.price = request.productPrice();
        this.stock = request.productStock();
        this.category = category;
    }

    public void checkOwner(User user) {
        if (!Objects.equals(this.user.getId(), user.getId())) {
            throw new IllegalArgumentException("해당 상품의 소유자가 아닙니다.");
        }
    }

    public void decreaseStock(Integer numberOfProduct) {
        if (stock < numberOfProduct) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        stock -= numberOfProduct;
    }

    public void addStock(Integer orderedStock) {
        stock += orderedStock;
    }
}
