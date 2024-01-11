package com.loncark.langoapp.repository;

import com.loncark.langoapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    Optional<User> findById(Long id);

    void deleteById(Long id);

    User save(User user);

    Optional<User> findByName(String name);

    List<User> findByLanguagesContaining(String language);

    List<User> findByIdIn(List<Long> idList);
}
