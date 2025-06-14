package iteam.platform.freelancer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

//@Entity
@Getter
@Setter
public class ProjectApplications {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String projectname;


    private String projectcompany;

    private String projectcompanyemail;

    private String status;


    private String candidatename;


    private String candidateemail;


    private String candidateresume;

    private UUID cdid;
}
