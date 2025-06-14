package iteam.platform.freelancer.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class JobApplicationDto {

    @NotBlank
    private String companyname;

    @NotBlank
    private String position;

    @NotBlank(message = "Candidate name is required")
    private String candidatename;

    @Email(message = "Invalid email")
    @NotBlank(message = "Candidate email is required")
    private String candidateemail;

    private MultipartFile candidateresume;

    @NotBlank
    private String cid;

    @NotBlank
    private String companyemail;

    private String status = "Send";


    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
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

    public MultipartFile getCandidateresume() {
        return candidateresume;
    }

    public void setCandidateresume(MultipartFile candidateresume) {
        this.candidateresume = candidateresume;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
