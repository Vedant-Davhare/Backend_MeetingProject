package com.hackmech.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String mobileNo;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // LEADERSHIP, TEAMLEAD, EXECUTIVE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Meetings this user has created
    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private Set<Meeting> hostedMeetings = new HashSet<>();

    // Meetings this user is attending
    @ManyToMany(mappedBy = "attendees")
    private Set<Meeting> attendingMeetings = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public User() {
    }

    public User(Long id, String name, String email, String mobileNo, String password, Role role, Department department, LocalDateTime createdAt, Set<Meeting> hostedMeetings, Set<Meeting> attendingMeetings) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.password = password;
        this.role = role;
        this.department = department;
        this.createdAt = createdAt;
        this.hostedMeetings = hostedMeetings;
        this.attendingMeetings = attendingMeetings;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Meeting> getHostedMeetings() {
        return hostedMeetings;
    }

    public void setHostedMeetings(Set<Meeting> hostedMeetings) {
        this.hostedMeetings = hostedMeetings;
    }

    public Set<Meeting> getAttendingMeetings() {
        return attendingMeetings;
    }

    public void setAttendingMeetings(Set<Meeting> attendingMeetings) {
        this.attendingMeetings = attendingMeetings;
    }
}
