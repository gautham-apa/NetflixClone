//package com.hari.netflix.pojo;
//
//import javax.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "address")
//@Entity
//public class Address {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int addrid;
//    private String addressLine;
//    @ManyToOne
//    @JoinColumn(name="fkuser", referencedColumnName = "userid")
//    private User user;
//}
