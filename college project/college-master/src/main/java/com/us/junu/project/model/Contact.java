package com.us.junu.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "contact")
@Data
@JsonIgnoreProperties(value = "true")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private int id;

    private String name;

    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    private String message;
}
