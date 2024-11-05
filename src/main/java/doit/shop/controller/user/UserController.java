package doit.shop.controller.user;

import doit.shop.controller.user.dto.UserIdResponse;
import doit.shop.controller.user.dto.UserInfoResponse;
import doit.shop.controller.user.dto.UserLoginRequest;
import doit.shop.controller.user.dto.UserSignUpRequest;
import doit.shop.controller.user.dto.UserUpdateRequest;
import doit.shop.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements UserControllerDocs {
    private final HttpSession session;
    private final UserService userService;

    @PostMapping("/validate")
    public void checkDuplicateId(@RequestParam String id) {
        userService.checkDuplicateId(id);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody UserSignUpRequest userSignUpRequest) {
        userService.createUser(userSignUpRequest);
    }

    @PostMapping("/login")
    public UserIdResponse login(@RequestBody UserLoginRequest userLoginRequest) {
        UserIdResponse response = userService.login(userLoginRequest);
        session.setAttribute("userId", response.userId());
        return response;
    }

    @PostMapping("/logout")
    public void logout() {
        session.removeAttribute("userId");
    }

    @GetMapping("/info")
    public UserInfoResponse getUserInfo() {
        Long loginUserId = (Long) session.getAttribute("userId");

        if (loginUserId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        return userService.getUserInfo(loginUserId);
    }

    @GetMapping("/{userId}/info")
    public UserInfoResponse getOtherUserInfo(@PathVariable Long userId) {
        Long loginUserId = (Long) session.getAttribute("userId");

        if (loginUserId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        return userService.getUserInfo(userId);
    }

    @PutMapping("/info")
    public UserIdResponse updateUserInfo(@RequestBody UserUpdateRequest userUpdateRequest) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        return userService.updateUserInfo(userId, userUpdateRequest);
    }

    @DeleteMapping("/info")
    public void deleteUser() {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        session.removeAttribute("userId");

        userService.deleteUser(userId);
    }
}
