package com.example.springboot.repositories;

import com.example.springboot.models.MinistryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MinistryRepository extends JpaRepository<MinistryModel, UUID> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário
}
