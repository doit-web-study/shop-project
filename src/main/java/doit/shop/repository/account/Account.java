package doit.shop.repository.account;

import doit.shop.controller.account.dto.AccountRegisterRequest;
import doit.shop.controller.account.dto.AccountUpdateRequest;
import doit.shop.repository.BaseEntity;
import doit.shop.repository.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountName;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String accountBankName;

    @Column(nullable = false)
    private Integer accountBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private Account(String accountName, String accountNumber, String accountBankName, Integer accountBalance,
                    User user) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBankName = accountBankName;
        this.accountBalance = accountBalance;
        this.user = user;
    }

    public static Account create(AccountRegisterRequest request, User user) {
        return Account.builder()
                .accountName(request.accountName())
                .accountNumber(request.accountNumber())
                .accountBankName(request.accountBankName())
                .accountBalance(0)
                .user(user)
                .build();
    }

    public void checkOwner(User user) {
        if (!this.user.equals(user)) {
            throw new IllegalArgumentException("본인의 계좌만 조회할 수 있습니다.");
        }
    }

    public void update(AccountUpdateRequest request, User user) {
        checkOwner(user);
        this.accountName = request.accountName();
    }

    public void deposit(Integer amount, User user) {
        checkOwner(user);
        if (amount <= 0) {
            throw new IllegalArgumentException("입금액은 0보다 커야 합니다.");
        }
        this.accountBalance += amount;
    }

    public void withdraw(Integer amount, User user) {
        checkOwner(user);
        if (amount <= 0) {
            throw new IllegalArgumentException("출금액은 0보다 커야 합니다.");
        }
        if (this.accountBalance < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        this.accountBalance -= amount;
    }
}
