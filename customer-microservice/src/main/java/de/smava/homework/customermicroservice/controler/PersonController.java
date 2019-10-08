package de.smava.homework.customermicroservice.controler;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.smava.homework.customermicroservice.model.PersonDTO;
import de.smava.homework.customermicroservice.model.RegistrationRequestDTO;
import de.smava.homework.customermicroservice.service.PersonService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PersonController {

    private final PersonService personService;

    @PostMapping("/customers")
    public ResponseEntity<PersonDTO> register(@RequestBody @Valid RegistrationRequestDTO registrationRequest) {
        return ResponseEntity.ok()
                .body(personService.create(registrationRequest.getPerson(), registrationRequest.getUser()));
    }

}
