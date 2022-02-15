package com.us.junu.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "donor")
@Data
@JsonIgnoreProperties(value = "true")
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "donor_id")
    private int id;

    @Column(name = "fullname")
    private String fullName;

    private Gender gender;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "blood_group")
    private String group;

    private String age;
}
