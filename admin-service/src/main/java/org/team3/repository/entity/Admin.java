package org.team3.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.team3.repository.enums.Gender;

import javax.persistence.*;

@Table(name = "admin_table")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String photo;
    String name;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "second_name")
    String secondName;
    @Column(name = "second_name")    String secondName;
    @Column(name = "second_last_name")
    String secondLastname;
    @Enumerated(EnumType.STRING)
    Gender gender;
    String department;
    String birthdate;
    @Column(name = "work_start_date")
    String workStartDate;
    String address;
    @Column(name = "phone_number")
    String phoneNumber;
    String mail;


}
