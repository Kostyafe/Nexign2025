package com.example.demo.repository;

import com.example.demo.model.CdrRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CdrRepository extends JpaRepository<CdrRecord, Long> {
    
    // Метод для получения CDR записей по времени
    List<CdrRecord> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
