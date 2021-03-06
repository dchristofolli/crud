package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.repository.UserEntity;
import com.dchristofolli.poc.v1.repository.UserRepository;
import com.dchristofolli.poc.v1.service.UserService;
import com.dchristofolli.poc.v1.service.UserStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = Controller.class)
@ContextConfiguration(classes = {Controller.class,
        ContractFacade.class,
        ImplFacade.class,
        UserService.class})
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository repository;
    @InjectMocks
    private Controller controller;

    @Test(expected = NullPointerException.class)
    public void createUser_Return_Null() throws Exception {
        controller.createUser(UserStub.wrongUserRequestStub());
        mockMvc.perform(post("/crud/v1/users").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(UserStub.entityStubModel())))
                .andExpect(status().isCreated());
    }

    @Test
    public void findByIdOrCpfOrEmailOrName() throws Exception {
        given(repository.findByIdOrCpfOrEmailOrName("1", null,
                null, null))
                .willReturn(Optional.of(UserStub.entityStubRequest()));
        mockMvc.perform(get(
                "/crud/v1/users?id=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdOrCpfOrEmailOrName_listAll() throws Exception {
        given(repository.findAll()).willReturn(UserStub.entityStubList());
        mockMvc.perform(get("/crud/v1/users")).andExpect(status().isOk());
    }

    @Test
    public void updateUsername() throws Exception {
        UserEntity entity = UserStub.entityStubModel();
        given(repository.existsByName(entity.getName())).willReturn(true);
        given(repository.findByName("stubber")).willReturn(Optional.of(UserStub.entityStubModel()));
        entity.setName("any");
        given(repository.save(entity)).willReturn(UserEntity.builder()
                .id("1")
                .name("any")
                .cpf("55368778015")
                .email("stub@teste.com.br")
                .password("123456")
                .build());
        mockMvc.perform(patch("/crud/v1/users/username?newName=any&oldName=stubber"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserById() throws Exception {
        UserEntity entity = UserStub.entityStubModel();
        given(repository.findById(entity.getId())).willReturn(Optional.of(entity));
        mockMvc.perform(delete("/crud/v1/users/1")).andExpect(status().isNoContent());
    }
}