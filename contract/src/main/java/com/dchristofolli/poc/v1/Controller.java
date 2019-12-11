package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.model.RequestModel;
import com.dchristofolli.poc.v1.model.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
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
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "An error occurred on the server")
    })
    @PostMapping
    public ResponseModel createUser(@Valid @RequestBody RequestModel model) {
        return facade.createUser(model);
    }

    @ApiOperation("Displays a list containing the names for all users.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List displayed successfully"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @GetMapping("/")
    public List<ResponseModel> findAllUsers() {
        return facade.findAllUsers();
    }

    @ApiOperation("Find a user by id, cpf, e-mail address or name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @GetMapping("/query")
    public List<ResponseModel> findUser(@RequestParam(required = false) String id,
                                        @RequestParam(required = false) String cpf,
                                        @RequestParam(required = false) String email,
                                        @RequestParam(required = false) String name) {
        return facade.findByIdOrCpfOrEmailOrName(id, cpf, email, name);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete a user")
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Bad server")})
    @DeleteMapping("{id}")
    public ResponseModel deleteUserById(@PathVariable String id) {
        return facade.delete(id);
    }

    @ApiOperation("Update username")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Username updated"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @PatchMapping("/{oldName}/{newName}")
    public ResponseModel updateUsername(@PathVariable String oldName,
                                        @PathVariable String newName) {
        return facade.updateUsername(oldName, newName);
    }
}

