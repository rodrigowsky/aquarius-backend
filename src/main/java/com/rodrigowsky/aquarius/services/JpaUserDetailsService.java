package com.rodrigowsky.aquarius.services;

import com.rodrigowsky.aquarius.dto.RegisterDTO;
import com.rodrigowsky.aquarius.entities.Student;
import com.rodrigowsky.aquarius.entities.User;
import com.rodrigowsky.aquarius.model.CustomUserDetails;
import com.rodrigowsky.aquarius.repositories.StudentRepository;
import com.rodrigowsky.aquarius.repositories.TeacherRepository;
import com.rodrigowsky.aquarius.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Problem during authentication!");

        User u = userRepository.findUserByUsername(username).orElseThrow(s);

        return new CustomUserDetails(u);
    }

    public void registerUser(User user, RegisterDTO registerDto) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);


        User user2 = userRepository.save(user);
        System.out.println("CHECK!!!!!:");
        System.out.println(user2 instanceof User);
        Student student = new Student();
        student.setFirstName(registerDto.getFirstName());
        student.setLastName(registerDto.getLastName());
        student.setPhoneNumber(registerDto.getPhoneNumber());
        student.setEmail(registerDto.getEmail());

        student.setDateOfBirth(registerDto.getDateOfBirth());

        System.out.println(user2);
        System.out.println(user2.getId());
        student.setUser(user2);

        try {
            studentRepository.save(student);
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public boolean existsByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Problem during authentication!");

        User u = userRepository.findUserByUsername(username).orElseThrow(s);

        return true;
    }

}

