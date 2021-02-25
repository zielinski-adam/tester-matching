package com.example.testerapp.dictionary.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TesterResponseDto {
    private String firstName;
    private String lastName;
    private String country;
    private Map<String, Long> bugs = new HashMap<>();
    private Integer experience;
}
