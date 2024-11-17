package doit.shop.repository.order;

import doit.shop.repository.BaseEntity;
import doit.shop.repository.account.Account;
import doit.shop.repository.product.Product;
import doit.shop.repository.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "\"order\"")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderedStock;

    private Integer totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    private Order(Integer orderedStock, Integer totalPrice, Product product, User user, OrderStatus status) {
        this.orderedStock = orderedStock;
        this.totalPrice = totalPrice;
        this.product = product;
        this.user = user;
        this.status = status;
    }

    public static Order create(Integer numberOfProduct, User user, Product product, Account account) {
        product.decreaseStock(numberOfProduct);
        account.decreaseBalance(product.getPrice() * numberOfProduct);
        return Order.builder()
                .orderedStock(numberOfProduct)
                .totalPrice(product.getPrice() * numberOfProduct)
                .user(user)
                .product(product)
                .status(OrderStatus.ORDERED)
                .build();
    }

    public void checkOwner(User user) {
        if (!Objects.equals(this.user.getId(), user.getId())) {
            throw new IllegalArgumentException("주문자가 일치하지 않습니다.");
        }
    }

    public void cancel(User user) {
        checkOwner(user);
        product.addStock(this.orderedStock);
        user.refundFromOrderCancel(this);
        this.status = OrderStatus.CANCELED;
    }
}
