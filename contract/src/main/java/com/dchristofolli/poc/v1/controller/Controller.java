package com.dchristofolli.poc.v1.controller;

import com.dchristofolli.poc.v1.ContractFacade;
import com.dchristofolli.poc.v1.exception.ApiException;
import com.dchristofolli.poc.v1.model.CrudModel;
import com.dchristofolli.poc.v1.model.RequestModel;
import com.dchristofolli.poc.v1.model.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@Api("Users Crud")
@RequestMapping(path = "/crud/v1/users")
public class Controller {
    private ContractFacade facade;

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a new user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User successfully registered"),
            @ApiResponse(code = 404, message = "Invalid data"),
            @ApiResponse(code = 500, message = "An error occurred on the server")
    })
    @PostMapping
    public ResponseModel createUser(@Valid @RequestBody RequestModel model) {
        return facade.createUser(model);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Displays a user's basic information")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found. Try again", response = ApiException.class),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @GetMapping("/{id}")
    public ResponseModel showUserById(@PathVariable(value = "id") String id) {
        return facade.showUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Displays a list containing basic data for all users.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List displayed successfully"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @GetMapping("/")
    public List<CrudModel> showAllUsers() {
        return facade.showAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update user data")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User updated"),
            @ApiResponse(code = 404, message = "Invalid data"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @PatchMapping("/{id}")
    public ResponseModel updateUser(@RequestBody RequestModel user, @PathVariable String id) {
        return facade.update(id, user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Bad server")})
    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable String id) {
        facade.delete(id);
    }
}

