package com.example.demo.controller;

import com.example.demo.model.UdrReport;
import com.example.demo.service.CdrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/cdr")
public class CdrController {

    @Autowired
    private CdrService cdrService;

    // Эндпоинт для генерации CDR записей
    @PostMapping("/generate")
    public ResponseEntity<String> generateCdrRecords() {
        cdrService.generateCdrRecords(); // Генерация записей
        return ResponseEntity.ok("CDR records generated successfully!"); // Ответ
    }

    // Эндпоинт для получения UDR отчета
    @GetMapping("/udr/{msisdn}")
    public ResponseEntity<UdrReport> getUdrReport(
            @PathVariable String msisdn,
            @RequestParam String start,
            @RequestParam String end) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(start, formatter); // Парсинг времени начала
        LocalDateTime endTime = LocalDateTime.parse(end, formatter); // Парсинг времени окончания
        
        UdrReport report = cdrService.getUdrReport(msisdn, startTime, endTime); // Получение отчета
        return ResponseEntity.ok(report); // Ответ с отчетом
    }
}
