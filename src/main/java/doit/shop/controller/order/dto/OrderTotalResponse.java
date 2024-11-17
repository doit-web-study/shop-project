package doit.shop.controller.order.dto;

import doit.shop.controller.product.dto.ProductInfoResponse;
import doit.shop.repository.order.Order;
import doit.shop.repository.product.Product;
import lombok.Builder;

@Builder
public record OrderTotalResponse(
        OrderInfoResponse order,
        ProductInfoResponse product
) {
    public static OrderTotalResponse from(Order order, Product product) {
        return OrderTotalResponse.builder()
                .order(OrderInfoResponse.from(order))
                .product(ProductInfoResponse.from(product))
                .build();
    }
}
