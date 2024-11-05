package doit.shop.controller.user.dto;

import doit.shop.repository.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserIdResponse(
        @Schema(description = "유저 식별 ID", example = "1")
        Long userId
) {
    public static UserIdResponse from(User user) {
        return UserIdResponse.builder()
                .userId(user.getId())
                .build();
    }
}
