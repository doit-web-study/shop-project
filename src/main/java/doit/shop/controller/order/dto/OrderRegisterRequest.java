package doit.shop.controller.order.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record OrderRegisterRequest(
        @Schema(description = "주문 상품 목록")
        List<OrderRegisterDetail> orders
) {
}
