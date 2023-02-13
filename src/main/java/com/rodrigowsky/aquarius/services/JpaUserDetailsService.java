package com.rodrigowsky.aquarius.services;

import com.rodrigowsky.aquarius.dto.RegisterDTO;
import com.rodrigowsky.aquarius.entities.*;
import com.rodrigowsky.aquarius.model.CustomUserDetails;
import com.rodrigowsky.aquarius.repositories.*;
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

    @Autowired
    private CourseRepository crsRepo;


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

        Course crs = new Course();
        crs.setName("CS101");
        Student student = new Student();
        student.setFirstName(registerDto.getFirstName()+ "student");
        student.setLastName(registerDto.getLastName()+"student");
        student.setEmail(registerDto.getEmail()+ "@");
        student.setPhoneNumber(registerDto.getPhoneNumber() + "1234");
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
        student.setUser(user2);
        Student student1 = studentRepository.save(student);

        try {
            Teacher tch = teacherRepository.save(teacher);
            dpt.setTeacher(tch);
            Department dptBack = dptRepo.save(dpt);
            System.out.println(dptBack);
            crs.setDepartment(dptBack);
            crs.setTeacher(tch);
            crs.setStudent(student1);
            crsRepo.save(crs);
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

