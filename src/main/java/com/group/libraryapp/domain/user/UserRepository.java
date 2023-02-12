package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);

    boolean existsByName(String name);


}
