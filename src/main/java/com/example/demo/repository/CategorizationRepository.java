package com.example.demo.repository;

import com.example.demo.model.Categorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategorizationRepository extends JpaRepository<Categorization, Long> {

    @Transactional
    Integer removeById(Long id);
}
