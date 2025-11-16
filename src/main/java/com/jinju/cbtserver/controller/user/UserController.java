package com.jinju.cbtserver.controller.user;

import com.jinju.cbtserver.dto.user.SignupRequest;
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
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest request) {

        userService.signup(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
