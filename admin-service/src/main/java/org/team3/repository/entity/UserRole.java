package org.team3.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.team3.repository.enums.Role;

import javax.persistence.*;

@Table(name = "user_role")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    User userId;

    @Enumerated(EnumType.STRING)
    Role role;

}
