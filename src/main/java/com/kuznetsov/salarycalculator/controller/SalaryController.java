package com.kuznetsov.salarycalculator.controller;

import com.kuznetsov.salarycalculator.model.Salary;
import com.kuznetsov.salarycalculator.report.SalaryRequestStatus;
import com.kuznetsov.salarycalculator.repository.SalaryRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryRepository salaryRepository;

    @PostMapping("/v1/create")
    @ApiOperation(
            value = "Add information about salary into DB.",
            response = SalaryRequestStatus.class
    )
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Information about salary was added into DB!", response = SalaryRequestStatus.class)}
    )
    public ResponseEntity<SalaryRequestStatus> create(@RequestBody Salary salary) {
        log.info("Adding information about salary.....");
        BigDecimal balance = salary.getAmount().subtract(salary.getCredit().add(salary.getReserve()));
        salary.setBalance(balance);
        salaryRepository.save(salary);
        return ResponseEntity.ok(new SalaryRequestStatus("SUCCESS", "Information about salary was added into DB!"));
    }

    @ResponseBody
    @GetMapping("/v1/getAll")
    public ResponseEntity<List<Salary>> getAllSalaries() {
        log.info("Getting information about salaries.....");
        List<Salary> all = salaryRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @ResponseBody
    @GetMapping("/v1/get/{id}")
    public ResponseEntity<Salary> getSalary(@ApiParam @PathVariable Long id) {
        log.info("Getting information about salary.....");
        Salary salary = salaryRepository.findSalaryById(id);
        return ResponseEntity.ok(salary);
    }

    @ResponseBody
    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<SalaryRequestStatus> deleteSalary(@ApiParam @PathVariable Long id) {
        log.info("Deleting information about salary.....");
        salaryRepository.deleteSalaryById(id);
        return ResponseEntity.ok(new SalaryRequestStatus("SUCCESS", "Information about salary was deleted from DB!"));
    }

    private Date getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.AUGUST, 1, 23, 23, 23);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-u H:m:s");
        return calendar.getTime();
    }

}
