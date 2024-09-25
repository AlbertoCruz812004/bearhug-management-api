package com.bearhug.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "empleado")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID folio;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, targetEntity = PersonEntity.class)
    @JoinColumn(name = "id_persona", unique = true, nullable = false)
    private PersonEntity person;
}
