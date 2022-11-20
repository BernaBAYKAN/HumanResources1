package org.team3.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.team3.repository.enums.Gender;
import org.springframework.data.annotation.Transient;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "user_table")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;
    String photo;
    String name;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "second_name")
    String secondName;
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
    @Column(unique = true)
    String mail;
    String password;

    @Transient
    String token;

    //@Column(columnDefinition = "TIMESTAMP")
    @Transient
    private LocalDateTime tokenCreationDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role" ,referencedColumnName = "role")
    UserRole userRole;
    @OneToOne(mappedBy = "user")
    CompanyManager companyManager;
}
