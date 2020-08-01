package com.example.napptilus.oompaloompa.Assemblers;

import com.example.napptilus.oompaloompa.Controller.ControllerOompaLoompa;
import com.example.napptilus.oompaloompa.Entities.OompaLoompa;
import com.example.napptilus.oompaloompa.Entities.ShortOompa;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ShortOompaLoompaAssembler extends RepresentationModelAssemblerSupport<OompaLoompa,ShortOompa> {

    public ShortOompaLoompaAssembler() {
        super(ControllerOompaLoompa.class , ShortOompa.class);
    }


    @Override
    public CollectionModel toCollectionModel(Iterable<? extends OompaLoompa> entities) {
        CollectionModel<ShortOompa> ompaModel = super.toCollectionModel(entities);
        ompaModel.add(linkTo( methodOn(ControllerOompaLoompa.class).listOomasLoompa(null,null)).withSelfRel()) ;
        return ompaModel;
    }



    @Override
    public ShortOompa toModel(OompaLoompa entity) {
        ShortOompa so = new ShortOompa();
        so.setId(entity.getId());
        so.setName(entity.getName());
        so.setJob(entity.getJob());
        so.setAge(entity.getAge());

        so.add(linkTo(
                methodOn(ControllerOompaLoompa.class)
                        .getOompaLooma(entity.getId()))
                .withSelfRel());
        return so;
    }



}
