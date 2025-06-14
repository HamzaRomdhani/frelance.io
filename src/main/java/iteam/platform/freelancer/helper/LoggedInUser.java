package iteam.platform.freelancer.helper;

public class LoggedInUser {
    private String name;
    private String email;
    private String role;
    private String profilec;

    public LoggedInUser(String name, String email, String role, String profilec) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.profilec = profilec;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getProfilec() { return profilec; }
}