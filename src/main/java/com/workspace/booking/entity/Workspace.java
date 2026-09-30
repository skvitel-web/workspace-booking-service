package com.workspace.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "workspace")
@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor

public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "zone", nullable = false)
    private String zone;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "active")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;
}
