package com.example.napptilus.oompaloompa;

import com.example.napptilus.oompaloompa.Entities.OompaLoompa;
import com.example.napptilus.oompaloompa.Repository.OompaLoompaRepository;
import com.example.napptilus.oompaloompa.Service.OompaLoompaService;
import com.example.napptilus.oompaloompa.Service.OompaLoompaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class testOompaLoompaService {

    @Mock
    private OompaLoompaRepository oompaLoompaRepository;

    private OompaLoompaService oompaLoompaService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        oompaLoompaService = new OompaLoompaServiceImpl(oompaLoompaRepository);


        OompaLoompa ompa = new OompaLoompa().builder()
                .id(1l)
                .age(10)
                .description("description")
                .height(1.93f)
                .job("some job")
                .name("OMPA2")
                .weight(60.0f)
                .build();
        List<OompaLoompa> listOmpas = new ArrayList<>();
        listOmpas.add(ompa);

        Page<OompaLoompa> pageOmpas = new PageImpl<OompaLoompa>(listOmpas);

        Pageable paging = PageRequest.of(1, 10);

        Mockito.when(oompaLoompaRepository.findAll(paging)).thenReturn(pageOmpas);
        Mockito.when(oompaLoompaRepository.findAll()).thenReturn(pageOmpas);
        Mockito.when(oompaLoompaRepository.save(ompa)).thenReturn(ompa);
        Mockito.when(oompaLoompaRepository.findById(1l)).thenReturn(Optional.of(ompa));
    }


    @Test
    public void testFindAllOompas_Service(){
        Page<OompaLoompa> listOmpas = oompaLoompaService.listAllOompaLoompa(1,10);
        Assertions.assertEquals(1,listOmpas.getSize());
        //check that only name , age and job are returned
        listOmpas.stream().forEach( o -> Assertions.assertTrue(checkAttributesWhenListing(o)));
    }

    private boolean checkAttributesWhenListing(OompaLoompa ompa){
        return  !Objects.isNull(ompa.getName()) && !Objects.isNull(ompa.getAge())  && !Objects.isNull(ompa.getJob());
    }


    // TODO implement this @Test
    public void testAddOompa_service(){

        OompaLoompa ompa = new OompaLoompa().builder()
                .age(10)
                .description("description")
                .height(1.93f)
                .job("some job")
                .name("OMPA2")
                .weight(60.0f)
                .build();
        OompaLoompa ompa2 = oompaLoompaService.create(ompa).get();
        assertThat(ompa).isEqualToComparingFieldByField(ompa2);

    }


    @Test
    public void testUpdatingOompa(){

        Optional<OompaLoompa> ompaOpt = oompaLoompaRepository.findById(1l);
        Assertions.assertTrue(ompaOpt.isPresent());
        OompaLoompa oompa = ompaOpt.get();
        oompa.setName("Hello");
        oompa.setAge(50);
        oompa.setWeight(2.2f);
        oompa.setHeight(5.5f);
        oompa.setDescription("Description");
        OompaLoompa oompaUpd = oompaLoompaService.update(oompa).get();
        Assertions.assertEquals(oompa.getName(),"Hello");
        Assertions.assertEquals(oompa.getAge(),oompaUpd.getAge());
        Assertions.assertEquals(oompa.getWeight(),oompaUpd.getWeight());
        Assertions.assertEquals(oompa.getHeight(),oompaUpd.getHeight());
        Assertions.assertEquals(oompa.getDescription(),oompaUpd.getDescription());

    }






}
