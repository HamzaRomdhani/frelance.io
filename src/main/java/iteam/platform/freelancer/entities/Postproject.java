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
public class Postproject {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String projectd;

    private String projectf;

    private String projectb;

    private String projectt;

    private String projects;

    private String projectc;

    private String projecte;
}
