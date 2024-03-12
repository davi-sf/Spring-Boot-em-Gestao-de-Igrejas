package com.example.springboot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.cglib.core.Local;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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


    public boolean isHasWhatsapp() {
        return hasWhatsapp;
    }

    public void setHasWhatsapp(boolean hasWhatsapp) {
        this.hasWhatsapp = hasWhatsapp;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public UUID getIdMember() {
        return idMember;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public void setIdMember(UUID idMember) {
        this.idMember = idMember;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(String age) {
        this.birth = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
