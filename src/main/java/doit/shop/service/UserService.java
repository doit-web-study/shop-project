package doit.shop.service;


import doit.shop.controller.user.dto.UserIdResponse;
import doit.shop.controller.user.dto.UserInfoResponse;
import doit.shop.controller.user.dto.UserLoginRequest;
import doit.shop.controller.user.dto.UserSignUpRequest;
import doit.shop.controller.user.dto.UserUpdateRequest;
import doit.shop.repository.user.User;
import doit.shop.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void checkDuplicateId(String loginId) {
        if (userRepository.existsByLoginId(loginId)) {
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }
    }

    public void createUser(UserSignUpRequest request) {
        checkDuplicateId(request.userLoginId());
        userRepository.save(request.toEntity());
    }

    public UserIdResponse login(UserLoginRequest request) {
        User user = userRepository.getByLoginIdAndPassword(request.userLoginId(), request.userPassword());
        return UserIdResponse.from(user);
    }

    public UserInfoResponse getUserInfo(Long userId) {
        User user = userRepository.getById(userId);
        return UserInfoResponse.from(user);
    }

    public UserIdResponse updateUserInfo(Long userId, UserUpdateRequest request) {
        User user = userRepository.getById(userId);
        user.update(request.userNickname(), request.userPhoneNumber());
        userRepository.save(user);
        return UserIdResponse.from(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
