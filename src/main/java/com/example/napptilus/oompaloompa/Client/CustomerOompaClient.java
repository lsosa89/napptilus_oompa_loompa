package com.example.napptilus.oompaloompa.Client;

import com.example.napptilus.oompaloompa.Entities.ShortOompa;


import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface CustomerOompaClient {


    @GetMapping(value = "/oompas/")
    public ResponseEntity<PagedModel<ShortOompa>> listOomasLoompa(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "count", defaultValue = "10", required = false) Integer size);
}