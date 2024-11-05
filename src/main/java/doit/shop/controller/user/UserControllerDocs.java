package doit.shop.controller.user;

import doit.shop.controller.user.dto.UserIdResponse;
import doit.shop.controller.user.dto.UserInfoResponse;
import doit.shop.controller.user.dto.UserLoginRequest;
import doit.shop.controller.user.dto.UserSignUpRequest;
import doit.shop.controller.user.dto.UserUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "유저 관련 API")
interface UserControllerDocs {

    @Operation(summary = "아이디 중복 확인", description = "회원가입 시 아이디 중복을 확인한다.")
    @ApiResponse(responseCode = "200", description = "아이디 중복 확인 성공")
    @ApiResponse(responseCode = "400", description = "아이디 중복 확인 실패")
    void checkDuplicateId(
            @Schema(description = "중복 확인할 아이디", example = "testId")
            String id
    );

    @Operation(summary = "회원가입", description = "회원가입을 한다.")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    @ApiResponse(responseCode = "400", description = "회원가입 실패")
    void signUp(
            @Schema(description = "회원가입 정보", implementation = UserSignUpRequest.class)
            UserSignUpRequest userSignUpRequest
    );

    @Operation(summary = "로그인", description = "로그인을 한다.")
    @ApiResponse(responseCode = "200", description = "로그인 성공", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "로그인 실패")
    UserIdResponse login(
            @Schema(description = "로그인 정보", implementation = UserLoginRequest.class)
            UserLoginRequest userLoginRequest
    );

    @Operation(summary = "로그아웃", description = "로그아웃을 한다.")
    @ApiResponse(responseCode = "200", description = "로그아웃 성공")
    @ApiResponse(responseCode = "400", description = "로그아웃 실패")
    void logout();

    @Operation(summary = "본인 정보 조회", description = "유저 정보를 조회한다.")
    @ApiResponse(responseCode = "200", description = "유저 정보 조회 성공", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "유저 정보 조회 실패")
    UserInfoResponse getUserInfo();

    @Operation(summary = "다른 유저 정보 조회", description = "다른 유저 정보를 조회한다.")
    @ApiResponse(responseCode = "200", description = "다른 유저 정보 조회 성공", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "다른 유저 정보 조회 실패")
    UserInfoResponse getOtherUserInfo(
            @Schema(description = "조회할 유저 ID", example = "1")
            Long userId
    );

    @Operation(summary = "본인 정보 수정", description = "유저 정보를 수정한다.")
    @ApiResponse(responseCode = "200", description = "유저 정보 수정 성공")
    @ApiResponse(responseCode = "400", description = "유저 정보 수정 실패")
    UserIdResponse updateUserInfo(
            @Schema(description = "수정할 유저 정보", implementation = UserUpdateRequest.class)
            UserUpdateRequest userUpdateRequest
    );

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴를 한다.")
    @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공")
    @ApiResponse(responseCode = "400", description = "회원 탈퇴 실패")
    void deleteUser();
}
