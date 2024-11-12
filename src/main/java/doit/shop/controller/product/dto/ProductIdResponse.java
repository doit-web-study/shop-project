package doit.shop.controller.product.dto;

import doit.shop.repository.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ProductIdResponse(
        @Schema(description = "상품 ID", example = "1")
        Long productId
) {
    public static ProductIdResponse from(Product product) {
        return ProductIdResponse.builder()
                .productId(product.getId())
                .build();
    }
}
