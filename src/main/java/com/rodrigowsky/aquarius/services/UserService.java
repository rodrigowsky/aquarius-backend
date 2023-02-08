package com.rodrigowsky.aquarius.services;

import com.rodrigowsky.aquarius.dto.RegisterDTO;
import com.rodrigowsky.aquarius.entities.Role;
import com.rodrigowsky.aquarius.entities.User;
import com.rodrigowsky.aquarius.model.UserRoles;
import com.rodrigowsky.aquarius.repositories.AuthorityRepository;
import com.rodrigowsky.aquarius.repositories.RoleRepository;
import com.rodrigowsky.aquarius.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.rodrigowsky.aquarius.model.UserRoles.*;

@Service
public class UserService {

    @Autowired
    JpaUserDetailsService jpaUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;


    @Autowired
    public AuthorityRepository authorityRepository;


    public User createUser(RegisterDTO registerDto) {
        User user = new User();
        UserRoles prefix = registerDto.getUserRole();
        user.setUsername(prefix.toString() + user.getId());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        user.setEnabled(true);
        Role roleModel = new Role();
        //find by role model if not create a new one
        roleModel.setName(registerDto.getUserRole());

        user.setRole(roleModel);
        jpaUserService.registerUser(user);


        return user;
    }

    public String assignPrefix(UserRoles userRole) {
        if(userRole.equals(ROLE_ADMIN)){
            return "ADMIN";
        }
        if(userRole.equals(ROLE_TEACHER)){
            return "t";
        }
        if(userRole.equals(ROLE_STUDENT)){
            return "a";
        }
        throw new RuntimeException("Role not found");
    }



}
