package de.smava.homework.authentication.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.smava.homework.authentication.model.LoanDTO;
import de.smava.homework.authentication.model.PersonDTO;
import de.smava.homework.authentication.model.RegistrationRequestDTO;
import de.smava.homework.authentication.model.RegistrationResponseVO;
import de.smava.homework.authentication.model.UserDTO;
import de.smava.homework.authentication.response.JWTTokenResponse;
import de.smava.homework.authentication.service.AuthenticationService;
import de.smava.homework.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<RegistrationResponseVO> register(
            @RequestBody @Valid RegistrationRequestDTO registrationRequest) {

        String tempPassword = registrationRequest.getUser().getPassword();
        UserDTO userDTODB = userService.create(registrationRequest.getUser());
        JWTTokenResponse jWTTokenResponse = authenticationService.generateJWTToken(userDTODB.getUsername(),
                tempPassword);
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + jWTTokenResponse.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        registrationRequest.setUser(userDTODB);

        HttpEntity<RegistrationRequestDTO> entity = new HttpEntity<>(registrationRequest, headers);
        log.info("About to call service loanapplications through gateway proxy");
        ResponseEntity<LoanDTO> loanResponse = restTemplate.exchange("http://localhost:9080/api/loanapplications/",
                HttpMethod.POST, entity, LoanDTO.class);
        log.info("About to call service customers through gateway proxy");
        ResponseEntity<PersonDTO> customerResponse = restTemplate.exchange("http://localhost:9080/api/customers/",
                HttpMethod.POST, entity, PersonDTO.class);
        log.info("Building response... Ok");
        return ResponseEntity.ok(new RegistrationResponseVO(userDTODB.getId(),
                customerResponse.getBody().getFirstName(), customerResponse.getBody().getLastName(),
                loanResponse.getBody().getId(), loanResponse.getBody().getStatus()));

    }

}
