package com.example.napptilus.oompaloompa.Service;

import com.example.napptilus.oompaloompa.Client.CustomerOompaClient;
import com.example.napptilus.oompaloompa.Entities.CustomOmpaLoompa;
import com.example.napptilus.oompaloompa.Entities.OompaLoompa;
import com.example.napptilus.oompaloompa.Entities.ShortOompa;
import com.example.napptilus.oompaloompa.Repository.OompaLoompaRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.*;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
@NoArgsConstructor
public class OompaLoompaServiceImpl implements OompaLoompaService {

    @Autowired
    OompaLoompaRepository oompaLoompaRepository;


    @Autowired
    CustomerOompaClient customerOompaClient;

    public OompaLoompaServiceImpl(OompaLoompaRepository oompaLoompaRepository){
        this.oompaLoompaRepository = oompaLoompaRepository;
    }





    @Override
    public Page<OompaLoompa> listAllOompaLoompa(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<OompaLoompa> pagedResult =  oompaLoompaRepository.findAll(paging);
        if(!Objects.isNull(pagedResult) && pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return Page.empty();
        }

    }



    @Override
    public Optional<OompaLoompa> getOompaLoompa(long id) {
        return     oompaLoompaRepository.findById(id);
    }

    @Override
    public Optional<OompaLoompa> update(OompaLoompa ompa) {
        if(ompa == null){
            return Optional.empty();
        }

        Optional<OompaLoompa> oompaLoompaOpt = oompaLoompaRepository.findById(ompa.getId());
        if(!oompaLoompaOpt.isPresent()){
            return Optional.empty();
        }

        OompaLoompa oompaLoompa = oompaLoompaOpt.get();
        oompaLoompa.setAge(ompa.getAge());

        oompaLoompa.setDescription(ompa.getDescription());
        oompaLoompa.setHeight(ompa.getHeight());
        oompaLoompa.setJob(ompa.getJob());
        oompaLoompa.setName(ompa.getName());
        oompaLoompa.setWeight(ompa.getWeight());
        oompaLoompaRepository.save(oompaLoompa);
        return Optional.of(oompaLoompa);
    }

    @Override
    public Optional<OompaLoompa> create(OompaLoompa ompa) {
        if(ompa == null){
            return Optional.empty();
        }
        OompaLoompa o = oompaLoompaRepository.save(ompa);
        return  Optional.ofNullable(o);
    }

    @Override
    public ResponseEntity<PagedModel<ShortOompa>> externalOompas(Integer page, Integer size) {
        return customerOompaClient.listOomasLoompa(page,size);
    }


}
