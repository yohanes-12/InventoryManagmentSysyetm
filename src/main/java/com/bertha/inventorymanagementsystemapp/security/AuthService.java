package com.bertha.inventorymanagementsystemapp.security;

import com.bertha.inventorymanagementsystemapp.domain.User;
import com.bertha.inventorymanagementsystemapp.dto.UserDetail;
import com.bertha.inventorymanagementsystemapp.dto.user.UserAdapter;
import com.bertha.inventorymanagementsystemapp.dto.user.UserDTO;
import com.bertha.inventorymanagementsystemapp.dto.user.UserRequest;
import com.bertha.inventorymanagementsystemapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthService {
    UserRepository userRepository;
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;
    PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO signup(UserRequest userRequest) {
        System.out.println("---- userRequest");
        System.out.println(userRequest);

        User user = UserAdapter.getUserFromRequest(userRequest);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user = this.userRepository.save(user);

        System.out.println("---- user saved");
        System.out.println(user);
        return UserAdapter.getUserDTOFromUser(user);
    }

    public AuthResponse login(AuthRequest loginRequest) {
        System.out.println("---- loginRequest");
        System.out.println(loginRequest);

        Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        if (auth.isAuthenticated()) {
            System.out.println("---- auth");
            System.out.println(auth);
            UserDetail user = (UserDetail) auth.getPrincipal();
            String token = jwtUtil.getToken(user.getEmail());
            return new AuthResponse(token);
        }
        System.out.println("---- no auth");
        return null;
    }
}
