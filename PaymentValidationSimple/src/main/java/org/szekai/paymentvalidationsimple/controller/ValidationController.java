package org.szekai.paymentvalidationsimple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.szekai.paymentvalidationsimple.exception.ValidationException;
import org.szekai.paymentvalidationsimple.model.Transaction;
import org.szekai.paymentvalidationsimple.service.ValidationEngineService;

@RestController
@RequestMapping("/validate")
public class ValidationController {

    @Autowired
    private ValidationEngineService validationEngineService;

    @PostMapping
    public ResponseEntity<String> validateTransaction(@RequestBody Transaction transaction) {
        try {
            validationEngineService.validateTransaction(transaction);
            return ResponseEntity.ok("Transaction is valid");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
