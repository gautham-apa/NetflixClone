package com.hari.netflix.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hari.netflix.auth.UserAuthRole;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid = 1;
    @NaturalId
    @Column(nullable = false, length = 100)
    private String email;
    @JsonIgnore
    private String password;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "auth_role")
    @Enumerated(EnumType.STRING)
    private UserAuthRole authRole;
    private String address;
    private Integer age;

    public User(String email, String password, String name, String phoneNumber, UserAuthRole authRole, String address, Integer age) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.authRole = authRole;
        this.address = address;
    }
    public User() {}

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserAuthRole getAuthRole() {
        return authRole;
    }

    public void setAuthRole(UserAuthRole authRole) {
        this.authRole = authRole;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
