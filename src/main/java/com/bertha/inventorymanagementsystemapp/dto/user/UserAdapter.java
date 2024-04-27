package com.bertha.inventorymanagementsystemapp.dto.user;

import com.bertha.inventorymanagementsystemapp.domain.User;

public class UserAdapter {
    public static UserDTO getUserDTOFromUser(User user) {
        if (user == null) return null;

        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );

        return userDTO;
    }

    public static User getUserFromRequest(UserRequest userRequest) {
//        String email, String password, String firstName, String lastName
        User user = new User(
                userRequest.email(),
                userRequest.password(),
                userRequest.firstName(),
                userRequest.lastName()
        );

        return user;
    }
}
