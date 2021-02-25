package com.example.testerapp.dictionary.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "device")
public class Device {
    @Id
    private Integer deviceId;
    private String description;
}
