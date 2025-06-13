package iteam.platform.freelancer.dtos;

import java.util.UUID;

public class ShowJob {

    private UUID id;
    private String jobd;
    private String jtittle;
    private String jskills;
    private String jtype;
    private String jsalary;
    private String jcname;
    private String jcemail;
    private String profileimg;
    private String about;

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

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public ShowJob(UUID id, String jobd, String jtittle, String jskills, String jtype, String jsalary,
                   String jcname, String jcemail, String profileimg, String about) {
        this.id = id;
        this.jobd = jobd;
        this.jtittle = jtittle;
        this.jskills = jskills;
        this.jtype = jtype;
        this.jsalary = jsalary;
        this.jcname = jcname;
        this.jcemail = jcemail;
        this.profileimg = profileimg;
        this.about = about;
    }


}
