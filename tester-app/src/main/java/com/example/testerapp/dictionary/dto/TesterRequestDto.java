package com.example.testerapp.dictionary.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ToString
public class TesterRequestDto {
    @NotEmpty
    private List<String> countries;
    @NotEmpty
    private List<String> devices;
}
