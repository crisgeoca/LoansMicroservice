package de.smava.homework.loanapplicationmicroservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.smava.homework.loanapplicationmicroservice.entity.LoanEntity;
import de.smava.homework.loanapplicationmicroservice.entity.UserEntity;
import de.smava.homework.loanapplicationmicroservice.exception.UserNotFoundException;
import de.smava.homework.loanapplicationmicroservice.model.LoanDTO;
import de.smava.homework.loanapplicationmicroservice.model.LoanStatus;
import de.smava.homework.loanapplicationmicroservice.model.UserDTO;
import de.smava.homework.loanapplicationmicroservice.repository.LoanRepository;
import de.smava.homework.loanapplicationmicroservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanService {
    @Autowired
    private final LoanRepository loanRepository;
    @Autowired
    private final UserRepository userRepository;

    public LoanDTO create(LoanDTO loanDTO, UserDTO userDTO) {
        Optional<UserEntity> optionalUser = userRepository.findById(userDTO.getId());
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }
        loanDTO.setId(UUID.randomUUID().toString());
        loanDTO.setStatus(LoanStatus.CREATED);
        LoanEntity loan = new LoanEntity();
        BeanUtils.copyProperties(loanDTO, loan);
        loan.setUser(optionalUser.get());
        loanRepository.save(loan);
        log.info("Saved loan with id: {}", loan.getId());
        return loanDTO;
    }

    public List<LoanDTO> find(String userId) {
        List<LoanDTO> loanDTOList = new ArrayList<>();
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }
        Optional<List<LoanEntity>> optionalLoan = loanRepository.findByUser(optionalUser.get());
        optionalLoan.get().stream().forEach(loanEntity -> {
            LoanDTO loanDTO = new LoanDTO();
            loanDTO.setAmount(loanEntity.getAmount());
            loanDTO.setDuration(loanEntity.getDuration());
            loanDTO.setId(loanEntity.getId());
            loanDTO.setStatus(loanEntity.getStatus());
            loanDTOList.add(loanDTO);
        });

        return loanDTOList;
    }
}
