package com.us.junu.project.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.us.junu.project.model.Gender;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = "true")
public class DonorDto {

    private String fullName;

    private Gender gender;

    private String address;

    private String contactNumber;

    private String group;

    private String age;

    private String email;

}
