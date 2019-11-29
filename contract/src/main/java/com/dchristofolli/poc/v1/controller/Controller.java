package com.dchristofolli.poc.v1.controller;

import com.dchristofolli.poc.v1.ContractFacade;
import com.dchristofolli.poc.v1.model.RequestModel;
import com.dchristofolli.poc.v1.model.ResponseModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
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
    public ResponseModel createUser(@RequestBody RequestModel model){
        return facade.createUser(model);
    }
}

