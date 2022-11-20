package org.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.team3.repository.entity.UserRole;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole,Long> {
}
