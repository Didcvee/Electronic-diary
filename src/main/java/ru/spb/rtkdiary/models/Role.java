package ru.spb.rtkdiary.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "e_role")
    private ERole ERole;

    public Role() {
    }
    public Role(ERole name){
        this.ERole=name;
    }
}
