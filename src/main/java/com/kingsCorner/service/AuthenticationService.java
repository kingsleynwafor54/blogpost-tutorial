package com.kingsCorner.service;


import com.kingsCorner.dto.AuthenticationRequestDto;
import com.kingsCorner.dto.AuthenticationResponseDto;
import com.kingsCorner.dto.RequestDto;

public interface AuthenticationService {
//    Here we're registering the user
    AuthenticationResponseDto register(RequestDto requestDto);
//    Login to the application
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);


}
