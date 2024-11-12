package doit.shop.controller;

import java.util.List;
import lombok.Builder;

public record ListWrapper<T>(
        List<T> result
) {
    public static <T> ListWrapper<T> of(List<T> result) {
        return new ListWrapper<T>(result);
    }
}
