package iteam.platform.freelancer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Postjob {


    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String jobd;

    private String jtittle;

    private String jskills;

    private String jtype;

    private String jsalary;

    private String jcname;

    private String jcemail;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getJobd() {
        return jobd;
    }

    public void setJobd(String jobd) {
        this.jobd = jobd;
    }

    public String getJtittle() {
        return jtittle;
    }

    public void setJtittle(String jtittle) {
        this.jtittle = jtittle;
    }

    public String getJskills() {
        return jskills;
    }

    public void setJskills(String jskills) {
        this.jskills = jskills;
    }

    public String getJtype() {
        return jtype;
    }

    public void setJtype(String jtype) {
        this.jtype = jtype;
    }

    public String getJsalary() {
        return jsalary;
    }

    public void setJsalary(String jsalary) {
        this.jsalary = jsalary;
    }

    public String getJcname() {
        return jcname;
    }

    public void setJcname(String jcname) {
        this.jcname = jcname;
    }

    public String getJcemail() {
        return jcemail;
    }

    public void setJcemail(String jcemail) {
        this.jcemail = jcemail;
    }



}