package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.model.request.UserQueryRequest;
import com.dchristofolli.poc.v1.model.request.UserRequest;
import com.dchristofolli.poc.v1.model.response.UserListResponse;
import com.dchristofolli.poc.v1.model.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@Api(value = "Users Crud", tags = "v1")
@RequestMapping(path = "/crud/v1/users")
public class Controller {
    private ContractFacade facade;

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a new user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User successfully registered"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "An error occurred on the server")
    })
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest model) {
        return facade.createUser(model);
    }

    @ApiOperation("Find a user by id, cpf, e-mail address or name. Returns all users if request is empty")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @GetMapping
    public UserListResponse getUser(@Valid UserQueryRequest userQueryRequest) {
        return facade.find(userQueryRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete a user")
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Bad server")})
    @DeleteMapping("{id}")
    public UserResponse deleteUserById(@PathVariable String id) {
        return facade.delete(id);
    }

    @ApiOperation("Update username")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Username updated"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @PatchMapping("/username")
    public UserResponse updateUsername(String oldName,
                                       String newName) {
        return facade.updateUsername(oldName, newName);
    }
}