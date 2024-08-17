package org.szekai.paymentvalidationsimple.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.szekai.paymentvalidationsimple.config.RulesConfig;
import org.szekai.paymentvalidationsimple.exception.ValidationException;
import org.szekai.paymentvalidationsimple.model.Transaction;
import org.szekai.paymentvalidationsimple.service.ValidationEngineService;
import org.szekai.paymentvalidationsimple.service.ValidationResult;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/validate")
public class ValidationController {

    @Autowired
    private ValidationEngineService validationEngineService;

    @Autowired
    private RulesConfig rulesConfig;

    @PostMapping("/normal")
    public ResponseEntity<ValidationResult> validateTransaction(@RequestBody Transaction transaction,
                                                                @RequestParam(defaultValue = "false") boolean parallel) {
        try {
            ValidationResult result = validationEngineService.validateTransaction(transaction, parallel);
            if (result.isValid()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }
        } catch (ValidationException e) {
            var result = new ValidationResult();
            result.addFailedRule("Unknown", e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

//    @PostMapping("/react")
//    public Mono<ResponseEntity<String>> validateTransactionReact(@RequestBody Transaction transaction) {
//        try {
//            validationEngineService.validateTransaction(transaction);
//            return Mono.just(ResponseEntity.ok("Transaction is valid"));
//        } catch (ValidationException e) {
//            return Mono.just(ResponseEntity.badRequest().body(e.getMessage()));
//        }
//    }

    @GetMapping("/rules")
    public Map<String, List<String>> getRules() {
        return rulesConfig.getTypes();
    }
}
