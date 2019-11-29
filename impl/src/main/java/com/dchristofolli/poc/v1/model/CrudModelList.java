package com.dchristofolli.poc.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CrudModelList {
    List<CrudModel> list;

    public void add(CrudModel m) {
        list.add(m);
    }
}
