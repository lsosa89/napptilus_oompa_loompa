package com.example.napptilus.oompaloompa.Assemblers;

import com.example.napptilus.oompaloompa.Controller.ControllerOompaLoompa;

import com.example.napptilus.oompaloompa.Entities.OompaLoompa;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OompaLoompaAssembler extends RepresentationModelAssemblerSupport<OompaLoompa,OompaLoompa> {

    public OompaLoompaAssembler() {
        super(ControllerOompaLoompa.class , OompaLoompa.class);
    }


    @Override
    public CollectionModel toCollectionModel(Iterable<? extends OompaLoompa> entities) {
        CollectionModel<OompaLoompa> ompaModel = super.toCollectionModel(entities);
        ompaModel.add(linkTo( methodOn(ControllerOompaLoompa.class).listOomasLoompa(null,null)).withSelfRel()) ;
        return ompaModel;
    }



    @Override
    public OompaLoompa toModel(OompaLoompa entity) {
        entity.add(linkTo(
                methodOn(ControllerOompaLoompa.class)
                        .getOompaLooma(entity.getId()))
                .withSelfRel());
        return entity;
    }



}
