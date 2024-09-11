package com.kingsCorner.kingsCorner.security.controller;

import com.kingsCorner.kingsCorner.security.dto.RequestDto;
import com.kingsCorner.kingsCorner.security.service.AuthenticationServiceImpl;
import com.kingsCorner.kingsCorner.security.dto.AuthenticationRequestDto;
import com.kingsCorner.kingsCorner.security.dto.AuthenticationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationServiceImpl;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(authenticationServiceImpl.register(requestDto));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        return ResponseEntity.ok(authenticationServiceImpl.authenticate(authenticationRequestDto));
    }

}
