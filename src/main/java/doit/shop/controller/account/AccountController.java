package doit.shop.controller.account;

import doit.shop.controller.account.dto.AccountIdResponse;
import doit.shop.controller.account.dto.AccountInfoResponse;
import doit.shop.controller.account.dto.AccountRegisterRequest;
import doit.shop.controller.account.dto.AccountUpdateRequest;
import doit.shop.service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController implements AccountControllerDocs {
    private final HttpSession session;
    private final AccountService accountService;

    @PostMapping
    public AccountIdResponse registerAccount(@RequestBody AccountRegisterRequest request) {
        Long userId = (Long) session.getAttribute("userId");
        return accountService.registerAccount(request, userId);
    }

    @GetMapping
    public AccountInfoResponse getMyAccount() {
        Long userId = (Long) session.getAttribute("userId");
        return accountService.getMyAccountInfo(userId);
    }

    @PutMapping("/{accountId}")
    public AccountIdResponse updateAccountInfo(@PathVariable Long accountId,
                                               @RequestBody AccountUpdateRequest request) {
        Long userId = (Long) session.getAttribute("userId");
        return accountService.updateAccountInfo(accountId, request, userId);
    }

    @PostMapping("/{accountId}/deposit")
    public void depositAccount(@PathVariable Long accountId, @RequestParam Integer amount) {
        Long userId = (Long) session.getAttribute("userId");
        accountService.deposit(accountId, amount, userId);
    }

    @PostMapping("/{accountId}/withdraw")
    public void withdrawAccount(@PathVariable Long accountId, @RequestParam Integer amount) {
        Long userId = (Long) session.getAttribute("userId");
        accountService.withdraw(accountId, amount, userId);
    }
}
