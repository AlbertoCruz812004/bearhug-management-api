package com.bearhug.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "persona")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "curp", nullable = false, length = 18, unique = true)
    private String curp;
    @Column(name = "nombre_persona", nullable = false, length = 50)
    private String name;
    @Column(name = "apellidos", nullable = false, length = 50)
    private String lastname;
    @Column(name = "edad", nullable = false)
    private Byte age;

    @OneToOne(cascade = CascadeType.REMOVE, targetEntity = UserEntity.class)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private UserEntity user;
}
