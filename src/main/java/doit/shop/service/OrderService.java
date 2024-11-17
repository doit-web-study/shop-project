package doit.shop.service;

import doit.shop.controller.PageResponse;
import doit.shop.controller.order.dto.OrderRegisterRequest;
import doit.shop.controller.order.dto.OrderTotalResponse;
import doit.shop.repository.account.Account;
import doit.shop.repository.account.AccountRepository;
import doit.shop.repository.order.Order;
import doit.shop.repository.order.OrderRepository;
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
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    public void registerOrder(OrderRegisterRequest request, Long userId) {
        User user = userRepository.getById(userId);
        Account account = accountRepository.getByUser(user);

        request.orders().forEach(order -> {
            Product product = productRepository.getById(order.productId());
            Integer numberOfProduct = order.numberOfProduct();
            Order newOrder = Order.create(numberOfProduct, user, product, account);

            orderRepository.save(newOrder);
        });
    }

    public void cancelOrder(Long orderId, Long userId) {
        Order order = orderRepository.getById(orderId);
        User user = userRepository.getById(userId);
        order.cancel(user);
    }

    public OrderTotalResponse getOrder(Long orderId, Long userId) {
        User user = userRepository.getById(userId);
        Order order = orderRepository.getById(orderId);

        Product product = order.getProduct();
        order.checkOwner(user);

        return OrderTotalResponse.from(order, product);
    }

    public PageResponse<OrderTotalResponse> getOrderList(Long userId, Integer pageNumber) {
        User user = userRepository.getById(userId);
        Page<Order> ordersPage = orderRepository.getAllByUser(user, pageNumber);

        List<OrderTotalResponse> orders = ordersPage.get().map(order -> {
            Product product = order.getProduct();
            return OrderTotalResponse.from(order, product);
        }).toList();
        Integer currentPage = ordersPage.getNumber();
        Integer totalPage = ordersPage.getTotalPages();

        return PageResponse.from(orders, currentPage, totalPage);
    }
}
