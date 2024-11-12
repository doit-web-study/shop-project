package doit.shop.service;

import doit.shop.controller.account.dto.AccountIdResponse;
import doit.shop.controller.account.dto.AccountInfoResponse;
import doit.shop.controller.account.dto.AccountRegisterRequest;
import doit.shop.controller.account.dto.AccountUpdateRequest;
import doit.shop.repository.account.Account;
import doit.shop.repository.account.AccountRepository;
import doit.shop.repository.user.User;
import doit.shop.repository.user.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountIdResponse registerAccount(AccountRegisterRequest request, Long userId) {
        User user = userRepository.getById(userId);

        Account newAccount = Account.create(request,user);
        Account savedAccount = accountRepository.save(newAccount);

        return AccountIdResponse.from(savedAccount);
    }

    public AccountInfoResponse getAccountInfo(Long accountId, Long userId) {
        User user = userRepository.getById(userId);
        Account account = accountRepository.getById(accountId);
        account.checkOwner(user);

        return AccountInfoResponse.from(account);
    }

    public List<AccountInfoResponse> getUsersAccountList(Long userId) {
        User user = userRepository.getById(userId);
        List<Account> accountList = accountRepository.getAllByUser(user);

        return accountList.stream()
                .map(AccountInfoResponse::from)
                .toList();
    }

    public AccountIdResponse updateAccountInfo(Long accountId, AccountUpdateRequest request, Long userId) {
        User user = userRepository.getById(userId);

        Account account = accountRepository.getById(accountId);
        account.update(request, user);
        accountRepository.save(account);

        return AccountIdResponse.from(account);
    }

    public void deposit(Long accountId, Integer amount, Long userId) {
        User user = userRepository.getById(userId);
        Account account = accountRepository.getById(accountId);
        account.deposit(amount, user);

        accountRepository.save(account);
    }

    public void withdraw(Long accountId, Integer amount, Long userId) {
        User user = userRepository.getById(userId);
        Account account = accountRepository.getById(accountId);
        account.withdraw(amount, user);

        accountRepository.save(account);
    }
}