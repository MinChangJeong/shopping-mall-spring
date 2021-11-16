package com.project.shop.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.shop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    // update query
    @Modifying
    @Transactional
    @Query("UPDATE User SET username = :username, email = :email, phoneNumber = :phoneNumber WHERE id = :userId")
    void updateUser(@Param("userId") Long userId, @Param("username") String username, 
    				@Param("email") String email, @Param("phoneNumber") String phoneNumber);

}