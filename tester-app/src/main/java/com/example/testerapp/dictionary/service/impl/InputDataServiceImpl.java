package com.example.testerapp.dictionary.service.impl;

import com.example.testerapp.dictionary.domain.Device;
import com.example.testerapp.dictionary.domain.Tester;
import com.example.testerapp.dictionary.repository.DeviceRepository;
import com.example.testerapp.dictionary.repository.TesterRepository;
import com.example.testerapp.dictionary.service.InputDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InputDataServiceImpl implements InputDataService {

    private static final String ALL = "ALL";
    private final DeviceRepository deviceRepository;
    private final TesterRepository testerRepository;

    @Override
    public Set<String> getDevices() {
        List<Device> devices = deviceRepository.findAll();
        Set<String> collect = devices
                .stream()
                .map(Device::getDescription)
                .collect(Collectors.toSet());
        collect.add(ALL);

        return collect;
    }

    @Override
    public Set<String> getCountries() {
        List<Tester> testers = testerRepository.findAll();
        Function<Tester, String> getCountry = Tester::getCountry;
        Set<String> countries = testers.stream()
                .map(getCountry)
                .collect(Collectors.toSet());
        countries.add(ALL);

        return countries;
    }

}
