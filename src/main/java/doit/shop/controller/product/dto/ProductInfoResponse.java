package doit.shop.controller.product.dto;

import doit.shop.repository.category.CategoryType;
import doit.shop.repository.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ProductInfoResponse(
        @Schema(description = "상품 ID", example = "1")
        Long productId,
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
        @Schema(description = "상품 생성일시", example = "2021-07-01T00:00:00")
        LocalDateTime productCreatedAt,
        @Schema(description = "상품 수정일시", example = "2021-07-01T00:00:00")
        LocalDateTime productModifiedAt,
        @Schema(description = "카테고리 ID", example = "1")
        Long categoryId,
        @Schema(description = "카테고리 타입", example = "FOOD")
        CategoryType categoryType,
        @Schema(description = "사용자 ID", example = "1")
        Long userId,
        @Schema(description = "사용자 닉네임", example = "둘리")
        String userNickname
) {
    public static ProductInfoResponse from(Product product) {
        return ProductInfoResponse.builder()
                .productId(product.getId())
                .productName(product.getName())
                .productDescription(product.getDescription())
                .productPrice(product.getPrice())
                .productStock(product.getStock())
                .productImage(product.getImage())
                .productCreatedAt(product.getCreatedAt())
                .productModifiedAt(product.getModifiedAt())
                .categoryId(product.getCategory().getId())
                .categoryType(product.getCategory().getType())
                .userId(product.getUser().getId())
                .userNickname(product.getUser().getNickname())
                .build();
    }
}