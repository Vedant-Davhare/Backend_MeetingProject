package com.hackmech.dto;

public class DepartmentDTO {
    private Long id;
    private String name;
    private int totalMembers;

    // Constructor, Getters and Setters

    public DepartmentDTO() {
    }

    public DepartmentDTO(Long id, String name, int totalMembers) {
        this.id = id;
        this.name = name;
        this.totalMembers = totalMembers;
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

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }
}
