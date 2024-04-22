package com.example.springboot.repositories;


import com.example.springboot.models.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface MemberRepository extends JpaRepository<MemberModel, UUID> {
    List<MemberModel> findByBirthBetween(String startDate, String endDate);
    // Outros métodos de repositório...
}