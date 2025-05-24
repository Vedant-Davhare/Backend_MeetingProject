package com.hackmech.config;

import com.hackmech.entity.Department;
import com.hackmech.entity.Role;
import com.hackmech.entity.User;
import com.hackmech.repository.DepartmentRepository;
import com.hackmech.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository userRepository, DepartmentRepository departmentRepository) {
        return args -> {
            if (!userRepository.findByEmail("admin@example.com").isPresent()) {
//                Department dept = new Department((long)2, "HR", 25, null);
//                departmentRepository.save(dept);
                Department department = departmentRepository.findById(1L)
                        .orElseThrow(() -> new RuntimeException("Department not found"));

                User user = new User();
                user.setName("Admin User");
                user.setEmail("admin@example.com");
                user.setPassword("123456"); // plain-text for demo
                user.setMobileNo("1234567891");
                user.setRole(Role.valueOf("LEADERSHIP")); // or TEAMLEAD, EXECUTIVE
                user.setDepartment(department);

                userRepository.save(user);
                System.out.println("Sample user inserted.");
            }
        };
    }
}