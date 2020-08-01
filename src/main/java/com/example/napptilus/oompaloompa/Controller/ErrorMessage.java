package com.example.napptilus.oompaloompa.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder

public class ErrorMessage {
    private String code;
    private List<Map<String,String>> messages;

    public  static String formatMessage (BindingResult result){
        List<Map<String,String>> error = result.getFieldErrors().stream()
                    .map( err ->{
                        Map<String, String> errors = new HashMap();
                        errors.put(err.getField(),err.getDefaultMessage());
                        return errors;
                    }).collect(Collectors.toList());
        return error.toString();
}
}
