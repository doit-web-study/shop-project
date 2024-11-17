package doit.shop.controller.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record OrderRegisterDetail(
        @Schema(description = "상품 ID", example = "1")
        Long productId,
        @Schema(description = "상품 개수", example = "1")
        Integer numberOfProduct
) {
}
