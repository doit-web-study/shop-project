package doit.shop.controller.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProductUpdateRequest(
        @Schema(description = "상품 이름", example = "빼빼로")
        String productName,
        @Schema(description = "상품 설명", example = "맛있는 빼빼로")
        String productDescription,
        @Schema(description = "상품 가격", example = "1100")
        Integer productPrice,
        @Schema(description = "상품 재고", example = "50")
        Integer productStock,
        @Schema(description = "상품 이미지", example = "https://www.lotteon.com")
        String productImage,
        @Schema(description = "카테고리 ID", example = "1")
        Long categoryId
) {
}
