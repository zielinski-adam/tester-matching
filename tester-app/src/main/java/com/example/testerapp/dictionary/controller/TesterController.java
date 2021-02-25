package com.example.testerapp.dictionary.controller;

import com.example.testerapp.common.BaseController;
import com.example.testerapp.dictionary.dto.TesterRequestDto;
import com.example.testerapp.dictionary.dto.TesterResponseDto;
import com.example.testerapp.dictionary.service.InputDataService;
import com.example.testerapp.dictionary.service.TesterService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class TesterController extends BaseController {

    private final TesterService testerService;
    private final InputDataService inputDataService;

    @PostMapping("/testers")
    public ResponseEntity<List<TesterResponseDto>> getTesters(@RequestBody @NotNull @Validated TesterRequestDto testerRequestDto) {
        return ResponseEntity.ok(testerService.getTesters(testerRequestDto));
    }

    @GetMapping("/devices")
    public ResponseEntity<Set<String>> getDevices() {
        return ResponseEntity.ok(inputDataService.getDevices());
    }

    @GetMapping("/countries")
    public ResponseEntity<Set<String>> getCounties() {
        return ResponseEntity.ok(inputDataService.getCountries());
    }

}
