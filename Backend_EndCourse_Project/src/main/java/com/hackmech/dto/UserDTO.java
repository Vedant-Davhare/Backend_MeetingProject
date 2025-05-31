package com.hackmech.dto;

import com.hackmech.entity.Department;
import com.hackmech.entity.Meeting;
import com.hackmech.entity.Role;
import com.hackmech.entity.User;

import java.time.LocalDateTime;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String mobileNo;
    private Role role;
    private String departmentName;
    private LocalDateTime createdAt;

    public UserDTO() {}

    public UserDTO(Long id, String name, String email, String mobileNo, Role role, String departmentName, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.role = role;
        this.departmentName = departmentName;
        this.createdAt = createdAt;
    }

    public UserDTO(Long id, String email, String mobileNo, String name, Role role, String departmentName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.role = role;
        this.departmentName = departmentName;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
