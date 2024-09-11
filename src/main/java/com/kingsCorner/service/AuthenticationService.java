package com.kingsCorner.kingsCorner.security.service;


import com.kingsCorner.kingsCorner.security.dto.AuthenticationRequestDto;
import com.kingsCorner.kingsCorner.security.dto.AuthenticationResponseDto;
import com.kingsCorner.kingsCorner.security.dto.RequestDto;

public interface AuthenticationService {
//    Here we're registering the user
    AuthenticationResponseDto register(RequestDto requestDto);
//    Login to the application
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);


}
