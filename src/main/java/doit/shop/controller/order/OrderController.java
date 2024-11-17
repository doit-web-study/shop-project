package doit.shop.controller.order;

import doit.shop.controller.ListWrapper;
import doit.shop.controller.PageResponse;
import doit.shop.controller.order.dto.OrderRegisterRequest;
import doit.shop.controller.order.dto.OrderTotalResponse;
import doit.shop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController implements OrderControllerDocs{
    private final HttpSession session;
    private final OrderService orderService;

    @PostMapping
    public void registerOrder(@RequestBody OrderRegisterRequest orderRegisterRequest) {
        Long userId = (Long) session.getAttribute("userId");
        orderService.registerOrder(orderRegisterRequest, userId);
    }

    @PostMapping("/{orderId}/cancel")
    public void cancelOrder(@PathVariable Long orderId) {
        Long userId = (Long) session.getAttribute("userId");
        orderService.cancelOrder(orderId, userId);
    }

    @GetMapping("/{orderId}")
    public OrderTotalResponse getOrder(@PathVariable Long orderId) {
        Long userId = (Long) session.getAttribute("userId");
        return orderService.getOrder(orderId, userId);
    }

    @GetMapping
    public PageResponse<OrderTotalResponse> getOrderList(@RequestParam Integer pageNumber) {
        Long userId = (Long) session.getAttribute("userId");
        return orderService.getOrderList(userId, pageNumber);
    }
}
