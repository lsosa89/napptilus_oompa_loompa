package com.example.napptilus.oompaloompa.Controller;


import com.example.napptilus.oompaloompa.Assemblers.OompaLoompaAssembler;
import com.example.napptilus.oompaloompa.Assemblers.ShortOompaLoompaAssembler;
import com.example.napptilus.oompaloompa.Client.CustomerOompaClient;
import com.example.napptilus.oompaloompa.Entities.OompaLoompa;
import com.example.napptilus.oompaloompa.Entities.ShortOompa;
import com.example.napptilus.oompaloompa.Service.OompaLoompaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;




@RestController
@RequestMapping(value = "/oompas")
public class ControllerOompaLoompa {

    @Autowired
    private OompaLoompaService oompaLoompaService;

    Logger log =  LoggerFactory.getLogger(ControllerOompaLoompa.class);

    @Autowired
    private OompaLoompaAssembler oompaLoompaAssembler;

    @Autowired
    private ShortOompaLoompaAssembler shortOompaLoompaAssembler;

    @Autowired
    PagedResourcesAssembler<OompaLoompa> assembler;

    @Autowired
    PagedResourcesAssembler<OompaLoompa> assemblerShort;







    @GetMapping
    public ResponseEntity<PagedModel> listOomasLoompa(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "count", defaultValue = "10", required = false) Integer size

    ){
        Page<OompaLoompa> ompas = oompaLoompaService.listAllOompaLoompa(page,size);
        PagedModel<ShortOompa>  pages =  assemblerShort.toModel(ompas, shortOompaLoompaAssembler);
        return  ResponseEntity.ok(pages);
    }





    @GetMapping(value="/{id}")
    public ResponseEntity<OompaLoompa> getOompaLooma(@PathVariable("id") long id){

        Optional<OompaLoompa> ompa = oompaLoompaService.getOompaLoompa( id);


        if(ompa.isPresent()){
            OompaLoompa oompaResult = oompaLoompaAssembler.toModel(ompa.get());
            return ResponseEntity.ok(oompaResult);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OompaLoompa> createOompaLooma(@Valid @RequestBody OompaLoompa ompa , BindingResult result){
        if(result.hasErrors()){
            String errorMsg = ErrorMessage.formatMessage(result);
            log.error(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  errorMsg);
        }

        Optional<OompaLoompa> createdOompa = oompaLoompaService.create(ompa);
        if(createdOompa.isPresent()){
            OompaLoompa oompaResult = oompaLoompaAssembler.toModel(createdOompa.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(oompaResult);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createdOompa.get());
        }
    }

    @PutMapping
    public ResponseEntity<OompaLoompa> updateOompaLooma(@Valid @RequestBody OompaLoompa ompa , BindingResult result){
        if(result.hasErrors()){
            String errorMsg = ErrorMessage.formatMessage(result);
            log.error(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  errorMsg);
        }
        Optional<OompaLoompa> updatedOompa = oompaLoompaService.update(ompa);
        if(updatedOompa.isPresent()){
            OompaLoompa oompaResult = oompaLoompaAssembler.toModel(updatedOompa.get());
            return ResponseEntity.ok().body(oompaResult);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @RequestMapping("externalOompa")
    public ResponseEntity<PagedModel<ShortOompa>> listOomasLoompaClient(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "count", defaultValue = "10", required = false) Integer size

    ){
        return oompaLoompaService.externalOompas (page,size);
    }




}
