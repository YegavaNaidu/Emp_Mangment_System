package com.sample.Jile.Repository;

import com.sample.Jile.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User ,Long> {

}
