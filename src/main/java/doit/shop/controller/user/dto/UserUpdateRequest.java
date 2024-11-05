package doit.shop.controller.user.dto;

import lombok.Builder;

@Builder
public record UserUpdateRequest(
        String userNickname,
        String userPhoneNumber
) {
}
