package com.example.demo.service;

import com.example.demo.model.CdrRecord;
import com.example.demo.model.UdrReport;
import com.example.demo.repository.CdrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class CdrService {

    @Autowired
    public CdrRepository cdrRepository;

    // Генерация случайных CDR записей
    public void generateCdrRecords() {
        Random random = new Random();
        LocalDateTime now = LocalDateTime.now();
        
        for (int i = 0; i < 10; i++) {
            CdrRecord cdrRecord = new CdrRecord();
            cdrRecord.setCallType(random.nextBoolean() ? "OUTGOING" : "INCOMING"); // Случайный выбор типа звонка
            cdrRecord.setCallerNumber("123456789" + random.nextInt(10)); // Генерация номера звонящего
            cdrRecord.setReceiverNumber("987654321" + random.nextInt(10)); // Генерация номера принимающего
            cdrRecord.setStartTime(now.minusMinutes(random.nextInt(60))); // Время начала звонка (в прошлом)
            cdrRecord.setEndTime(cdrRecord.getStartTime().plusMinutes(random.nextInt(5) + 1)); // Время окончания звонка
            
            cdrRepository.save(cdrRecord); // Сохранение записи в базе данных
        }
    }

    // Получение UDR отчета
    public UdrReport getUdrReport(String msisdn, LocalDateTime start, LocalDateTime end) {
        List<CdrRecord> cdrRecords = cdrRepository.findByStartTimeBetween(start, end);
        return new UdrReport(msisdn, cdrRecords); // Формирование отчета
    }
}
