package com.m6findjobbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "company", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        }),
})
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeCompany;
    private String name;
    private String avatar;
    private String description;
    private String address;
    private int employeeQuantity;
    @ManyToOne(targetEntity = City.class)
    private City city;
    private String linkMap;
    private String phone;
    private Status statusCompany;
    @OneToOne
    private Account account;
    @OneToMany(targetEntity = RecuitmentNew.class, mappedBy = "company")
    @JsonIgnore
    private List<RecuitmentNew> recuitmentNews;

    public Company(Long id, String name, String avatar, String description, String address, int employeeQuantity, City city, String linkMap, String phone, Status statusCompany, Account account) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.description = description;
        this.address = address;
        this.employeeQuantity = employeeQuantity;
        this.city = city;
        this.linkMap = linkMap;
        this.phone = phone;
        this.statusCompany = statusCompany;
        this.account = account;
    }
}
