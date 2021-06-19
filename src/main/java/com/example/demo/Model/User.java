package com.example.demo.Model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "TD_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer userId;
    private String username;
    private String password;
    private Integer roleId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp lastLogin;
}
