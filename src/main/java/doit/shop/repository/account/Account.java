package doit.shop.repository.account;

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
public class Account {

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
    public Account(String accountName, String accountNumber, String accountBankName, Integer accountBalance) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBankName = accountBankName;
        this.accountBalance = accountBalance;
    }

}
