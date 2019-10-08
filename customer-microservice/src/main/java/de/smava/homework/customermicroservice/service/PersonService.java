package de.smava.homework.customermicroservice.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.smava.homework.customermicroservice.entity.PersonEntity;
import de.smava.homework.customermicroservice.entity.UserEntity;
import de.smava.homework.customermicroservice.exception.UserNotFoundException;
import de.smava.homework.customermicroservice.model.PersonDTO;
import de.smava.homework.customermicroservice.model.UserDTO;
import de.smava.homework.customermicroservice.repository.PersonRepository;
import de.smava.homework.customermicroservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {
    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final UserRepository userRepository;

    @Transactional
    public PersonDTO create(PersonDTO personDTO, UserDTO userDTO) {
        Optional<UserEntity> optionalUser = userRepository.findById(userDTO.getId());
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }
        PersonEntity person = new PersonEntity();
        BeanUtils.copyProperties(personDTO, person);
        person.setUser(optionalUser.get());
        personRepository.save(person);
        log.info("Saved person with id: {}", person.getId());
        personDTO.setId(person.getId());
        return personDTO;
    }
}
