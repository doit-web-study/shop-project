package doit.shop.controller;

import doit.shop.controller.product.dto.ProductInfoResponse;
import java.util.List;

public record PageResponse <T> (
        List<T> data,
        int totalPage,
        int currentPage
) {
    public static PageResponse<ProductInfoResponse> from(List<ProductInfoResponse> products, Integer currentPageNumber,
                                                         Integer totalPageNumber) {
        return new PageResponse<>(products, totalPageNumber, currentPageNumber);
    }
}
