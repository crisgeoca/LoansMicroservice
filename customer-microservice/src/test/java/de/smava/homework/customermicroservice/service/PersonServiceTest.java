package de.smava.homework.customermicroservice.service;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.smava.homework.customermicroservice.entity.PersonEntity;
import de.smava.homework.customermicroservice.entity.UserEntity;
import de.smava.homework.customermicroservice.model.PersonDTO;
import de.smava.homework.customermicroservice.model.UserDTO;
import de.smava.homework.customermicroservice.repository.PersonRepository;
import de.smava.homework.customermicroservice.repository.UserRepository;

public class PersonServiceTest {
	
	private static final String USER_ID = "ed46e1c1-4ab8-43dd-82f7-36bd88af6a46";
	
	
	@InjectMocks
	private PersonService personService;
	
	@Mock
	private PersonRepository personRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private PersonEntity personEntity;
	
	@Mock
	private PersonDTO personDTO;
	
	@Before
    public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void create() {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(USER_ID);
		UserEntity userEntity = new UserEntity();
		userEntity.setId(USER_ID);
		Mockito.when(userRepository.findById(userDTO.getId())).thenReturn(Optional.of(userEntity));
		Mockito.when(personEntity.getId()).thenReturn(USER_ID);
		
		personService.create(personDTO, userDTO);
        Mockito.verify(personRepository).save(Mockito.anyObject());
	}

};
