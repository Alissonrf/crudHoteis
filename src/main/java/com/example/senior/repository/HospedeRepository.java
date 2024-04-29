package com.example.senior.repository;

import com.example.senior.entity.HospedeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedeRepository extends JpaRepository<HospedeEntity, Long> {

}
