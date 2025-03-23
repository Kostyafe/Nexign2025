package com.example.demo.service;

import com.example.demo.model.CdrRecord;
import com.example.demo.model.UdrReport;
import com.example.demo.repository.CdrRepository;
import com.example.demo.service.CdrService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class Test1 {

    private CdrService cdrService;
    private CdrRepository cdrRepository;

    @BeforeEach
    void setUp() {
        cdrRepository = mock(CdrRepository.class);
        cdrService = new CdrService();
        cdrService.cdrRepository = cdrRepository; // Внедрение мока
    }

    @Test
    void testGenerateCdrRecords() {
        cdrService.generateCdrRecords();

        // Проверяем, что метод save был вызван 10 раз
        verify(cdrRepository, times(10)).save(any(CdrRecord.class));

        // Проверяем, что запись была сохранена с корректными данными (по желанию)
        ArgumentCaptor<CdrRecord> captor = ArgumentCaptor.forClass(CdrRecord.class);
        verify(cdrRepository, times(10)).save(captor.capture());

        List<CdrRecord> savedRecords = captor.getAllValues();
        for (CdrRecord record : savedRecords) {
            assertEquals(10, record.getCallerNumber().length()); // Проверяем длину номера
            assertEquals(10, record.getReceiverNumber().length()); // Проверяем длину номера
        }
    }

    @Test
    void testGetUdrReport() {
        String msisdn = "1234567890";
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();

        List<CdrRecord> mockRecords = new ArrayList<>();
        mockRecords.add(new CdrRecord());
        mockRecords.add(new CdrRecord());

        when(cdrRepository.findByStartTimeBetween(start, end)).thenReturn(mockRecords);

        UdrReport report = cdrService.getUdrReport(msisdn, start, end);

        // Проверяем, что отчет сформирован корректно
        assertEquals(msisdn, report.getMsisdn());
        assertEquals(2, report.getCdrRecords().size()); // Должно быть 2 записи в отчете
        verify(cdrRepository).findByStartTimeBetween(start, end); // Проверяем, что метод был вызван
    }
}

