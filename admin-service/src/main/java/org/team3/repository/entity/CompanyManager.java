package org.team3.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.team3.repository.enums.Role;

import javax.persistence.*;

@Table(name = "company_manager_table")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String photo;
    String name;
    String lastName;
    String secondName;
    String secondLastname;
    String birthdate;
    String address;
    String phone;
    String mail;
    String password;
    String workStartDate;
    @ManyToOne
    @JoinColumn(name = "company",referencedColumnName = "company_name")
    Company company;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    Role role = Role.USER;


}
