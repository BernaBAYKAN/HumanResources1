package org.team3.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;


@Table(name = "company_manager_table")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyManager implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    @JoinColumn(name = "company_id" , referencedColumnName = "company_id")
    Company company;
    @OneToOne
    @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    User user;

}
