package doit.shop.repository.order;

import doit.shop.repository.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    default Order getById(Long orderId) {
        return findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));
    }

    Page<Order> findAllByUser(User user, PageRequest pageable);

    default Page<Order> getAllByUser(User user, Integer pageNumber) {
        PageRequest pageable = PageRequest.of(pageNumber, 10);

        return findAllByUser(user, pageable);
    }
}
