package de.smava.homework.loanapplicationmicroservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.smava.homework.loanapplicationmicroservice.model.LoanDTO;
import de.smava.homework.loanapplicationmicroservice.model.RegistrationRequestDTO;
import de.smava.homework.loanapplicationmicroservice.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/loanapplications")
    public ResponseEntity<LoanDTO> register(@RequestBody @Valid RegistrationRequestDTO registrationRequest) {
        return ResponseEntity.ok()
                .body(loanService.create(registrationRequest.getLoan(), registrationRequest.getUser()));
    }

    @GetMapping("/loanapplications")
    public ResponseEntity<List<LoanDTO>> getLoansById(@RequestParam String userId) {
        log.info("Finding user {}", userId);
        List<LoanDTO> loansList = loanService.find(userId);
        if (loansList.isEmpty()) {
            ResponseEntity.ok("The user has no loans registered.");
        }
        return ResponseEntity.ok().body(loansList);
    }
}
