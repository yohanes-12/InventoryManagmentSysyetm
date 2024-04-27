package com.bertha.inventorymanagementsystemapp.security;

import com.bertha.inventorymanagementsystemapp.dto.user.UserDTO;
import com.bertha.inventorymanagementsystemapp.dto.user.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    @GetMapping
//    public ResponseEntity<UserDTO> findAll() {
//        List<UserDTO> userDTOS = this.authService.findAll();
//        return ResponseEntity.ok(new ResponseDataList(userDTOS));
//    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(
            @RequestBody UserRequest userRequest
    ) {
        UserDTO userDTO = this.authService.signup(userRequest);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest authRequest
    ) {
        AuthResponse authResponse = this.authService.login(authRequest);
        return ResponseEntity.ok(authResponse);
    }
}
