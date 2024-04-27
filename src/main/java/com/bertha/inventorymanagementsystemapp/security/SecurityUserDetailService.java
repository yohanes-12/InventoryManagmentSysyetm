package com.bertha.inventorymanagementsystemapp.security;

import com.bertha.inventorymanagementsystemapp.domain.User;
import com.bertha.inventorymanagementsystemapp.dto.UserDetail;
import com.bertha.inventorymanagementsystemapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailService implements UserDetailsService {
    UserRepository userRepository;

    public SecurityUserDetailService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email).orElse(null);
        System.out.println("---user by email---");
        System.out.println(user);

        if (user == null) return null;

        return new UserDetail(
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
