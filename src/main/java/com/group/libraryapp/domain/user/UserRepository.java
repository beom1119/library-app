package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);

    boolean existsByName(String name);


}
