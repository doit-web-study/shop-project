package doit.shop.controller.account.dto;

import doit.shop.repository.account.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AccountIdResponse(
        @Schema(description = "계좌 식별 ID", example = "1")
        Long accountId
) {
    public static AccountIdResponse from(Account savedAccount) {
        return AccountIdResponse.builder()
                .accountId(savedAccount.getId())
                .build();
    }
}
