package com.kingsCorner.service;


import com.kingsCorner.config.JwtService;
import com.kingsCorner.dto.AuthenticationRequestDto;
import com.kingsCorner.dto.AuthenticationResponseDto;
import com.kingsCorner.dto.RequestDto;
import com.kingsCorner.model.Role;
import com.kingsCorner.model.User;
import com.kingsCorner.repository.UserRepository;
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
