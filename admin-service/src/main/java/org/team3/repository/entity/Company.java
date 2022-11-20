package org.team3.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Transient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "company_table")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    Long id;

    @Column(name = "company_name")
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

    //String domain;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    List<CompanyManager> companyManagerList;


}
