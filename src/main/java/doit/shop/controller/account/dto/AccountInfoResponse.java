package doit.shop.controller.account.dto;

import doit.shop.repository.account.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record AccountInfoResponse(
        @Schema(description = "계좌 식별 ID", example = "1")
        Long accountId,

        @Schema(description = "계좌 애칭", example = "나의 작고 소중한 계좌")
        String accountName,

        @Schema(description = "계좌 번호", example = "123-456-789")
        String accountNumber,

        @Schema(description = "은행 이름", example = "국민은행")
        String accountBankName,

        @Schema(description = "계좌 잔액", example = "100000")
        Integer accountBalance,

        @Schema(description = "계좌 생성일시", example = "2021-01-01T00:00:00")
        LocalDateTime createdAt,

        @Schema(description = "계좌 수정일시", example = "2021-01-01T00:00:00")
        LocalDateTime modifiedAt
) {
    public static AccountInfoResponse from(Account account) {
        return AccountInfoResponse.builder()
                .accountId(account.getId())
                .accountName(account.getAccountName())
                .accountNumber(account.getAccountNumber())
                .accountBankName(account.getAccountBankName())
                .accountBalance(account.getAccountBalance())
                .createdAt(account.getCreatedAt())
                .modifiedAt(account.getModifiedAt())
                .build();
    }
}
