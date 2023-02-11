package com.rodrigowsky.aquarius.services;

import com.rodrigowsky.aquarius.dto.RegisterDTO;
import com.rodrigowsky.aquarius.entities.Department;
import com.rodrigowsky.aquarius.entities.Teacher;
import com.rodrigowsky.aquarius.entities.User;
import com.rodrigowsky.aquarius.model.CustomUserDetails;
import com.rodrigowsky.aquarius.repositories.DepartmentRepository;
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

    @Autowired
    private DepartmentRepository dptRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Problem during authentication!");

        User u = userRepository.findUserByUsername(username).orElseThrow(s);

        return new CustomUserDetails(u);
    }

    public void registerUser(User user, RegisterDTO registerDto) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        Department dpt = new Department();
        dpt.setFull_name("Hello_department2");


        User user2 = userRepository.save(user);
        System.out.println("CHECK!!!!!:");
        System.out.println(user2 instanceof User);
        Teacher teacher = new Teacher();
        teacher.setFirstName(registerDto.getFirstName());
        teacher.setLastName(registerDto.getLastName());
        teacher.setPhoneNumber(registerDto.getPhoneNumber());
        teacher.setEmail(registerDto.getEmail());

        teacher.setDateOfBirth(registerDto.getDateOfBirth());

        System.out.println(user2);
        System.out.println(user2.getId());
        teacher.setUser(user2);


        try {
            Teacher tch = teacherRepository.save(teacher);
            dpt.setTeacher(tch);
            dptRepo.save(dpt);
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

