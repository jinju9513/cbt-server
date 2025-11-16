package com.jinju.cbtserver.controller.user;

import com.jinju.cbtserver.dto.common.ApiResponse;
import com.jinju.cbtserver.dto.user.SignupRequest;
import com.jinju.cbtserver.dto.user.SignupResponse;
import com.jinju.cbtserver.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(@Valid @RequestBody SignupRequest request) {

        userService.signup(request);

        SignupResponse response = new SignupResponse(
                request.getEmail(),
                request.getName()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("회원가입이 완료되었습니다.", response));
    }
}
