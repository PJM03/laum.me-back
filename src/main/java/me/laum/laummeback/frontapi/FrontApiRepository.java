package me.laum.laummeback.frontapi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FrontApiRepository extends JpaRepository<StackEntity, Integer> {
    Optional<StackEntity> findByName(String name);
}
