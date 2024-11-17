package doit.shop.controller;

import java.util.List;

public record PageResponse<T>(
        List<T> data,
        int totalPage,
        int currentPage
) {
    public static <T> PageResponse<T> from(List<T> data, Integer currentPage, Integer totalPage) {
        return new PageResponse<>(data, totalPage, currentPage);
    }
}
