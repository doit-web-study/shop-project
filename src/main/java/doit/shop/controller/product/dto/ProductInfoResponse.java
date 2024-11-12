package doit.shop.controller.product.dto;

public record ProductInfoResponse(
        Long productId,
        String productName,
        String productDescription,
        Integer productPrice,
        Integer productStock,
        String productImage,
        String productCreatedAt,
        String productModifiedAt,
        Long categoryId,
        Long userId,
        String userNickname
) {
}