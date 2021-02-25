package com.example.testerapp.dictionary.service;

import com.example.testerapp.dictionary.dto.TesterRequestDto;
import com.example.testerapp.dictionary.dto.TesterResponseDto;

import java.util.List;

public interface TesterService {

    List<TesterResponseDto> getTesters(TesterRequestDto testerRequestDto);
}
