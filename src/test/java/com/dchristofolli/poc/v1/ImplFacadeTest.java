package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.exception.ApiException;
import com.dchristofolli.poc.v1.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ImplFacadeTest {
    @Mock
    UserService service;
    @InjectMocks
    ImplFacade facade;

    @Test(expected = ApiException.class)
    public void updateUserName_user_does_not_exists() {
        when(service.userDoesNotExistsByName(any())).thenReturn(true);
        when(service.newNameIsEqualsOldName(any(), any())).thenReturn(true);
        facade.updateUserName("oldName", "newName");
    }

    @Test(expected = ApiException.class)
    public void updateUserName_new_name_is_equals_old_name() {
        when(service.userDoesNotExistsByName(any())).thenReturn(false);
        when(service.newNameIsEqualsOldName(any(), any())).thenReturn(true);
        facade.updateUserName("oldName", "newName");
    }


    @Test
    public void findAllUsers_Ok() {
        when(service.repositoryIsEmpty()).thenReturn(false);
        facade.findAllUsers();
        Assert.assertTrue(service.findAllUsers().isEmpty());
    }

    @Test(expected = ApiException.class)
    public void findAllUsers_NotFound() {
        when(service.repositoryIsEmpty()).thenReturn(true);
        facade.findAllUsers();
        Assert.assertTrue(service.findAllUsers().isEmpty());
    }
}