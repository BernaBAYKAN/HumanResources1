package org.team3.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "company_table")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String companyName;
    String title;
    String taxNo;
    String commercialRegistryNo;
    String employeeNumber;
    String address;
    String phone;
    String mail;
    String logo;
    String foundingDate;


}
