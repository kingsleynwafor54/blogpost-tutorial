package com.kingsCorner.kingsCorner.security.service;


import com.kingsCorner.kingsCorner.security.config.JwtService;
import com.kingsCorner.kingsCorner.security.dto.AuthenticationRequestDto;
import com.kingsCorner.kingsCorner.security.dto.AuthenticationResponseDto;
import com.kingsCorner.kingsCorner.security.dto.RequestDto;
import com.kingsCorner.kingsCorner.security.model.Role;
import com.kingsCorner.kingsCorner.security.model.User;
import com.kingsCorner.kingsCorner.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements  AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private  final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDto register(RequestDto requestDto) {
        var user= User.builder().
                firstname(requestDto.getFirstname())
                .lastname(requestDto.getLastname())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        // AuthenticationResponseDto authenticationResponseDto=new AuthenticationResponseDto();
//        authenticationResponseDto.setToken(jwtToken);
//         return authenticationResponseDto;
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();

    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.getEmail(),
                        authenticationRequestDto.getPassword()
                )
        );


        var user = userRepository.findByEmail(authenticationRequestDto.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

}
