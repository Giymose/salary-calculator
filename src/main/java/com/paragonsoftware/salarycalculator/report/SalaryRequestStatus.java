package com.paragonsoftware.salarycalculator.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryRequestStatus {

    private String status;
    private String comment;

}
