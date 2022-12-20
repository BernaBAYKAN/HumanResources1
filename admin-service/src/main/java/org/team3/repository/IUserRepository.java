package org.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.team3.repository.entity.User;

import java.util.Optional;
@Repository
public interface IUserRepository  extends JpaRepository<User,Long> {

    Optional<User> findByMail(String mail);
    Optional<User> findOptionalById(Long adminid);
    
    Optional<User> findOptionalByMailIgnoreCaseAndPassword(String mail, String password);
    User findByToken(String token);

    User findById(Optional<Long> userId);
}
