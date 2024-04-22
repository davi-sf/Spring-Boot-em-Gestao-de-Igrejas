package com.example.springboot.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_MEMBERS")
public class MemberModel extends RepresentationModel<MemberModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMember;
    private String name;
    private String birth;
    private String phone;
    private String address;
    private boolean hasWhatsapp;

    private String email;

    @ManyToOne
    @JoinColumn(name = "ministry_id")
    private MinistryModel ministry;

    public UUID getIdMember() {
        return idMember;
    }

    public void setIdMember(UUID idMember) {
        this.idMember = idMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isHasWhatsapp() {
        return hasWhatsapp;
    }

    public void setHasWhatsapp(boolean hasWhatsapp) {
        this.hasWhatsapp = hasWhatsapp;
    }

    public MinistryModel getMinistry() {
        return ministry;
    }

    public void setMinistry(MinistryModel ministry) {
        this.ministry = ministry;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
