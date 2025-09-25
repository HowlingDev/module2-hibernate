package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "created_at", updatable = false)
    private LocalDate created_at;

    public User() {}

    @Override
    public String toString() {
        return "id: " + id + "; username: " + name + "; email: " + email + "; age: " + age;
    }

    //геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public LocalDate getCreated_at() { return created_at; }
    public void setCreated_at(LocalDate created_at) { this.created_at = created_at; }
}