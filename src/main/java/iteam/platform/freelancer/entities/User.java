package iteam.platform.freelancer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class User implements Serializable {

    @Column(nullable = false)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String password;

    @Column
    private String cpassword;


    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public String getName() {
        return name;
    }


}