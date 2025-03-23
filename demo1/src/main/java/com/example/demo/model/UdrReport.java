package com.example.demo.model;

import java.util.List;

public class UdrReport {
    
    private String msisdn; // Номер абонента
    private List<CdrRecord> cdrRecords; // Список CDR записей для данного абонента

    public UdrReport(String msisdn, List<CdrRecord> cdrRecords) {
        this.msisdn = msisdn;
        this.cdrRecords = cdrRecords;
    }

    // Геттеры и сеттеры

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public List<CdrRecord> getCdrRecords() {
        return cdrRecords;
    }

    public void setCdrRecords(List<CdrRecord> cdrRecords) {
        this.cdrRecords = cdrRecords;
    }
}
