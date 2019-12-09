package com.dchristofolli.poc.v1;

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
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 409, message = "User already exists"),
            @ApiResponse(code = 500, message = "An error occurred on the server")
    })
    @PostMapping
    public ResponseModel createUser(@Valid @RequestBody RequestModel model) {
        return facade.createUser(model);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Find a user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found. Try again"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @GetMapping("/id/{id}")
    public ResponseModel showUserById(@PathVariable(value = "id") String id) {
        return facade.showUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Displays a list containing the names for all users.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List displayed successfully"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @GetMapping("/")
    public List<ResponseModel> showAllUsers() {
        return facade.showAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Find a user by CPF")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseModel findUserByCpf(@PathVariable String cpf) {
        return facade.findUserByCpf(cpf);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Find a user by name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @GetMapping("/username/{username}")
    public ResponseModel findUserByName(@PathVariable String username) {
        return facade.findUserByName(username);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Find a user by id, cpf, e-mail address or name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @GetMapping("/query")
    public ResponseModel findByIdOrCpfOrEmailOrName(@RequestParam(required = false) String id,
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
    public void deleteUserById(@PathVariable String id) {
        facade.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update username")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Username updated"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Bad server")
    })
    @PatchMapping("/{oldName}/{newName}")
    public ResponseModel updateUsername(@PathVariable String oldName,
                                        @PathVariable String newName){
        return facade.updateUsername(oldName, newName);
    }
}

