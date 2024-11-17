package doit.shop.repository.user;

import doit.shop.repository.BaseEntity;
import doit.shop.repository.account.Account;
import doit.shop.repository.order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "\"user\"")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    private User(String name, String nickname, String loginId, String password, String phoneNumber) {
        this.name = name;
        this.nickname = nickname;
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void update(String nickname, String phoneNumber) {
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public void refundFromOrderCancel(Order order) {
        account.deposit(order.getTotalPrice(), this);
    }

    public void registerAccount(Account savedAccount) {
        this.account = savedAccount;
    }
}
