package com.eco.base.repository;

import com.eco.base.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAndPhoneNum(String email, String phoneNum);

    User findByEmail(String email);
}
