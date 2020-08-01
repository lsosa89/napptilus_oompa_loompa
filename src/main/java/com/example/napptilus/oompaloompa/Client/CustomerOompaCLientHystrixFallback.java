package com.example.napptilus.oompaloompa.Client;

import com.example.napptilus.oompaloompa.Entities.ShortOompa;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerOompaCLientHystrixFallback implements CustomerOompaClient  {


    private static final String url = "http://localhost:9090/oompas?size=10";
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;


    public ResponseEntity<PagedModel<ShortOompa>> failResponse(Integer page, Integer size){
        System.out.println("Calling circuit service");
        return ResponseEntity.ok(PagedModel.empty());
    }

    @Override
    @HystrixCommand(fallbackMethod = "failResponse")
    public ResponseEntity<PagedModel<ShortOompa>> listOomasLoompa(Integer page, Integer size) {
        System.out.println("Calling external service");
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PagedModel<ShortOompa>> response =
                circuitBreaker.run(() -> restTemplate.exchange(url,
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<PagedModel<ShortOompa>>() {
                        }));
        return response;
    }
}
