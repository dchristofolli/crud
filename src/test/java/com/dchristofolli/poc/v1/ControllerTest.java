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

import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void createUser_Return_Created() throws Exception {
        given(repository.save(UserStub.entityStubRequest()))
                .willReturn(UserStub.entityStubModel());
        mockMvc.perform(post("/crud/v1/users").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(UserStub.entityStubModel())))
                .andExpect(status().isCreated());
    }

    @Test
    public void showUserById_Return_Ok() throws Exception {
        given(repository.findById("1")).willReturn(Optional.of(UserStub.entityStubRequest()));
        mockMvc.perform(get("/crud/v1/users/id/1")).andExpect(status().isOk());
    }

    @Test
    public void showAllUsers_Return_Ok() throws Exception {
        List<UserEntity> list = new ArrayList<>();
        list.add(UserStub.entityStubRequest());
        given(repository.findAll()).willReturn(list);
        mockMvc.perform(get("/crud/v1/users/")).andExpect(status().isOk());
    }

    @Test
    public void findUserByCpf() throws Exception {
        given(repository.findByCpf("55368778015")).willReturn(Optional.of(UserStub.entityStubRequest()));
        mockMvc.perform(get("/crud/v1/users/cpf/55368778015")).andExpect(status().isOk());
    }

    @Test
    public void findUserByName() throws Exception {
        given(repository.findByName("stubber")).willReturn(Optional.of(UserStub.entityStubRequest()));
        mockMvc.perform(get("/crud/v1/users/username/stubber")).andExpect(status().isOk());
    }

    @Test
    public void findByIdOrCpfOrEmailOrName() throws Exception {
        given(repository.findByIdOrCpfOrEmailOrName("1", "55368778015",
                null, "stubber"))
                .willReturn(Optional.of(UserStub.entityStubRequest()));
        mockMvc.perform(get(
                "/crud/v1/users/query?cpf=55368778015&id=1&name=stubber"))
                .andExpect(status().isOk());
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
        mockMvc.perform(patch("/crud/v1/users/stubber/any"))
                .andExpect(status().isOk());
    }

//    @Test
//    public void deleteUserById() {
//
//    }
}