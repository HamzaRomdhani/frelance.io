package iteam.platform.freelancer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "job_applications")
public class JobApplications {


    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String companyname;

    private String companyemail;

    private String position;

    private String candidatename;

    private String candidateemail;

    private String candidateresume;

    private String status;

    private UUID cid;

    public UUID getCid() {
        return cid;
    }

    public void setCid(UUID cid) {
        this.cid = cid;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCandidatename() {
        return candidatename;
    }

    public void setCandidatename(String candidatename) {
        this.candidatename = candidatename;
    }

    public String getCandidateemail() {
        return candidateemail;
    }

    public void setCandidateemail(String candidateemail) {
        this.candidateemail = candidateemail;
    }

    public String getCandidateresume() {
        return candidateresume;
    }

    public void setCandidateresume(String candidateresume) {
        this.candidateresume = candidateresume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
