package com.dchristofolli.poc.v1.service;

import com.dchristofolli.poc.v1.exception.ApiException;
import com.dchristofolli.poc.v1.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository repository;
    @InjectMocks
    UserService service;

//    @Test
//    void create() {
//    }
//
//    @Test
//    void registrationChecker() {
//    }
//
//    @Test
//    void showUserById() {
//
//    }

    @Test(expected = ApiException.class)
    public void emptyListValidator_return_NotFound() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        service.emptyListValidator();
    }

    @Test
    public void userExistsByName() {
    }

    @Test
    public void updateUsername() {
    }

//    @Test
//    void delete() {
//    }
//
//    @Test
//    void updatePassword() {
//    }
//
//    @Test
//    void passwordIsValid() {
//    }
//
//    @Test
//    void findUserByCpf() {
//    }
//
//    @Test
//    void findUserByName() {
//    }
//
//    @Test
//    void findUserByIdOrCpfOrEmailOrName() {
//    }
}