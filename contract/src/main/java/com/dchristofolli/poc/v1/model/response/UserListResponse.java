package com.dchristofolli.poc.v1.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserListResponse {
    List<UserResponse> list;
    Integer quantity;
}
