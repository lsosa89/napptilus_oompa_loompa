package com.example.napptilus.oompaloompa.Service;


import com.example.napptilus.oompaloompa.Entities.OompaLoompa;

import com.example.napptilus.oompaloompa.Entities.ShortOompa;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

public interface OompaLoompaService {

    /*
     * Return a list of OompaLoompa
     * */
    public Page<OompaLoompa> listAllOompaLoompa(Integer pageNo, Integer pageSize);

    /*
    * Return a specific Oompa loompa
    * */
    public Optional<OompaLoompa> getOompaLoompa(long id);

    /*
    *Update an Oompa Loompa
    * */
    public Optional<OompaLoompa> update(OompaLoompa ompa);

    /*
    * Create a new Oompa Loompa
    **/
    public Optional<OompaLoompa> create(OompaLoompa ompa);

    /*
     * Create a new Oompa Loompa
     **/
    public ResponseEntity<PagedModel<ShortOompa>> externalOompas(Integer page, Integer size);





}
