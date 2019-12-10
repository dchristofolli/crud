package com.dchristofolli.poc.v1.service;

import com.dchristofolli.poc.v1.exception.ApiException;
import com.dchristofolli.poc.v1.repository.UserEntity;
import com.dchristofolli.poc.v1.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository repository;
    @InjectMocks
    UserService service;

    @Test(expected = ApiException.class)
    public void emptyListValidator_return_NotFound() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        service.emptyListChecker();
    }

    @Test
    public void userDoesNotExistsByName() {
        when(repository.existsByName("joao")).thenReturn(false);
        service.userDoesNotExistsByName("joao");
        Assert.assertTrue(service.userDoesNotExistsByName("joao"));
    }

    @Test
    public void delete() {
        UserEntity entity = UserStub.entityStubModel();
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        service.delete(entity.getId());
        verify(repository).delete(entity);
    }
}