package com.sample.Jile.Repository;

import com.sample.Jile.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User ,Long> {

    public Optional<User> findByUsername(String username);
}
