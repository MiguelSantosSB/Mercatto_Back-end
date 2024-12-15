package br.com.Mercatto.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 11)
    private String number;

    @Column(nullable = false, unique = true, length = 155)
    private String email;

//    @Column(nullable = false, length = 16)
    private String password;

//    @Column(nullable = false, length = 50)
//    private String role;
}
