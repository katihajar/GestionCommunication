package com.example.PFEproject.rest;

import com.example.PFEproject.bean.RefreshToken;
import com.example.PFEproject.bean.User;
import com.example.PFEproject.dto.LoginDTO;
import com.example.PFEproject.dto.TokenDTO;
import com.example.PFEproject.jwt.JwtHelper;
import com.example.PFEproject.repo.RefreshTokenRepository;
import com.example.PFEproject.repo.UserRepository;
import com.example.PFEproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/auth")
public class AuthREST {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login( @RequestBody LoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setOwner(user);
        User user1 = userRepository.findUserById(user.getId());
        String accessToken = jwtHelper.generateAccessToken(user);
        String refreshTokenString = jwtHelper.generateRefreshToken(user, refreshToken);
        return ResponseEntity.ok(new TokenDTO(user1, accessToken, refreshTokenString));
    }

//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestBody TokenDTO dto,HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        String refreshTokenString = dto.getRefreshToken();
//        if (refreshTokenString != null && jwtHelper.validateRefreshToken(refreshTokenString) && refreshTokenRepository.existsById(jwtHelper.getTokenIdFromRefreshToken(refreshTokenString))) {
//            refreshTokenRepository.deleteById(jwtHelper.getTokenIdFromRefreshToken(refreshTokenString));
//            return ResponseEntity.ok().build();
//        }
//
//        throw new BadCredentialsException("Invalid or missing refresh token");
//    }
    @PostMapping("/logoutAll")
    public ResponseEntity<?> logoutAll() {
            // valid and exists in db
            refreshTokenRepository.deleteAll();
            return ResponseEntity.ok().build();
    }

    @GetMapping("/logout-success")
    public void sucess(){
        System.out.println("success");
    }
    @PostMapping("access-token")
    public ResponseEntity<?> accessToken(@RequestBody TokenDTO dto) {
        String refreshTokenString = dto.getRefreshToken();
        if (jwtHelper.validateRefreshToken(refreshTokenString) && refreshTokenRepository.existsById(jwtHelper.getTokenIdFromRefreshToken(refreshTokenString))) {
            // valid and exists in db
            User user = userService.findUserByUsername(jwtHelper.getUserUsernameFromRefreshToken(refreshTokenString));
            String accessToken = jwtHelper.generateAccessToken(user);
            return ResponseEntity.ok(new TokenDTO(user, accessToken, refreshTokenString));
        }

        throw new BadCredentialsException("invalid token");
    }

    @PostMapping("refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenDTO dto) {
        String refreshTokenString = dto.getRefreshToken();
        if (jwtHelper.validateRefreshToken(refreshTokenString) ) {
            // valid and exists in db
            User user = userService.findUserByUsername(jwtHelper.getUserUsernameFromRefreshToken(refreshTokenString));
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setOwner(user);
            String accessToken = jwtHelper.generateAccessToken(user);
            String newRefreshTokenString = jwtHelper.generateRefreshToken(user, refreshToken);
            return ResponseEntity.ok(new TokenDTO(user, accessToken, newRefreshTokenString));
        }

        throw new BadCredentialsException("invalid token");
    }
}
