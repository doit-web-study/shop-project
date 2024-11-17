package doit.shop.controller.order.dto;

import doit.shop.repository.order.Order;
import doit.shop.repository.order.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record OrderInfoResponse(
        @Schema(description = "주문 ID", example = "1")
        Long orderId,
        @Schema(description = "주문 생성일시", example = "2021-07-01T00:00:00")
        LocalDateTime orderCreatedAt,
        @Schema(description = "주문 총 가격", example = "1100")
        Integer orderTotalPrice,
        @Schema(description = "주문 상품 개수", example = "1")
        Integer numberOfProduct,
        @Schema(description = "주문 상태", example = "ORDERED")
        OrderStatus orderStatus
) {
        public static OrderInfoResponse from(Order order) {
                return OrderInfoResponse.builder()
                        .orderId(order.getId())
                        .orderCreatedAt(order.getCreatedAt())
                        .orderTotalPrice(order.getTotalPrice())
                        .numberOfProduct(order.getOrderedStock())
                        .orderStatus(order.getStatus())
                        .build();
        }
}
