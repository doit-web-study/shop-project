package doit.shop.repository.account;

import doit.shop.repository.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    default Account getById(Long accountId) {
        return this.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 계좌가 존재하지 않습니다."));
    }

    List<Account> findAllByUser(User user);

    default List<Account> getAllByUser(User user) {
        return this.findAllByUser(user);
    }

}
