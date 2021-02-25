package com.example.testerapp.dictionary.service.impl;

import com.example.testerapp.dictionary.domain.Device;
import com.example.testerapp.dictionary.domain.Tester;
import com.example.testerapp.dictionary.dto.TesterRequestDto;
import com.example.testerapp.dictionary.dto.TesterResponseDto;
import com.example.testerapp.dictionary.enums.FetchType;
import com.example.testerapp.dictionary.repository.TesterRepository;
import com.example.testerapp.dictionary.service.TesterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TesterServiceImpl implements TesterService {

    private final TesterRepository testerRepository;

    @Override
    public List<TesterResponseDto> getTesters(TesterRequestDto testerRequestDto) {
        log.info("Starting calculating for: {}", testerRequestDto.toString());
        boolean allDevices = testerRequestDto.getDevices().contains(FetchType.ALL.name());
        boolean allCountries = testerRequestDto.getCountries().contains(FetchType.ALL.name());
//      TODO
//      For huge volumes of data better option is preparing dedicated queries and strategy pattern
        List<Tester> testers = testerRepository.findAll();

        if (!allCountries) {
            testers = testers.stream()
                    .filter(tester -> testerRequestDto.getCountries().contains(tester.getCountry()))
                    .collect(Collectors.toList());
        }

        if (!allDevices) {
            testers = testers.stream()
                    .filter(tester -> isContainsDevice(testerRequestDto, tester))
                    .collect(Collectors.toList());
        }

        List<TesterResponseDto> testerResponseDtos = testers.stream()
                .map(tester -> getTesterResponseDto(tester, testerRequestDto))
                .sorted(Comparator.comparing(TesterResponseDto::getExperience).reversed())
                .collect(Collectors.toList());

        return testerResponseDtos;
    }

    private TesterResponseDto getTesterResponseDto(Tester tester, TesterRequestDto testerRequestDto) {
        boolean allDevices = testerRequestDto.getDevices().contains(FetchType.ALL.name());

        TesterResponseDto testerResponseDto = new TesterResponseDto();
        testerResponseDto.setCountry(tester.getCountry());
        testerResponseDto.setFirstName(tester.getFirstName());
        testerResponseDto.setLastName(tester.getLastName());

        Map<String, Long> bugsForDevice = tester.getBugs()
                .stream()
                .map(Device::getDescription)
                .filter(device -> testerRequestDto.getDevices().contains(device) || allDevices)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        testerResponseDto.setBugs(bugsForDevice);

        Integer experience = bugsForDevice.values().stream().mapToInt(Long::intValue).sum();
        testerResponseDto.setExperience(experience);

        return testerResponseDto;
    }

    private boolean isContainsDevice(TesterRequestDto testerRequestDto, Tester tester) {
        return tester.getDevices()
                .stream()
                .anyMatch(device -> testerRequestDto.getDevices().contains(device.getDescription()));
    }
}
