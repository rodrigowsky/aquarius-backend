package com.rodrigowsky.aquarius.services;

import com.rodrigowsky.aquarius.dto.RegisterDTO;
import com.rodrigowsky.aquarius.entities.Role;
import com.rodrigowsky.aquarius.entities.User;
import com.rodrigowsky.aquarius.model.UserRoles;
import com.rodrigowsky.aquarius.repositories.nosql.OtpRepository;
import com.rodrigowsky.aquarius.repositories.sql.AuthorityRepository;
import com.rodrigowsky.aquarius.repositories.sql.RoleRepository;
import com.rodrigowsky.aquarius.repositories.sql.UserRepository;
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

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private OtpService otpService;




    public User createUser(RegisterDTO registerDto) {
        // gets the info from the client with the RegisterDTO class
        // created a new user with this string
        // the registration process always start with the creation of an user, after that you create either a student or a teacher and assign its foreign key to the respective new user, so each user is associated with either
        // a teacher or a student, expect admins which are simply users;

        System.out.println(registerDto.toString());
        User user = new User();
        String prefix = assignPrefix(registerDto.getUserRole());
        user.setUsername(prefix + "_" + registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        user.setEnabled(true);
        Role roleModel = new Role();
        //find by role model if not create a new one
        roleModel.setName(registerDto.getUserRole());

        user.setRole(roleModel);
        jpaUserService.registerUser(user,registerDto);


        return user;
    }

    public String assignPrefix(UserRoles userRole) {
        // method to facilitate username clarity with a distinct role prefix
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

//    public void addUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }


    //used to get code only
    //consider moving it to auth provider ????????
//    public void auth(User user) {
//        //serch for user in db
//        Optional<User> o =
//                userRepository.findUserByUsername(user.getUsername());
//
//        //id user exists verifies password
//        if(o.isPresent()) {
//            User u = o.get();
//            //if pw is correct generates a new otp code
//            //?????????????
//            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
//                otpService.renewOtp(u);
//            } else {
//                //if incorrect throw bad credentials exception
//                throw new BadCredentialsException("Bad credentials.");
//            }
//        } else {
//            throw new BadCredentialsException("Bad credentials.");
//        }
//    }




}
