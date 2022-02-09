package com.afklm.offertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afklm.offertest.entity.User;

@Repository
public interface UserRepositoryI extends JpaRepository<User, Long> {

}
