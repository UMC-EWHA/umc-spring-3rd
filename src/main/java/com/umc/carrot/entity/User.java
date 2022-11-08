package com.umc.carrot.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    private String address;

    @Builder
    public User(String name, int age, String address){
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void update(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
