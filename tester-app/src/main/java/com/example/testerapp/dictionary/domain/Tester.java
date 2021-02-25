package com.example.testerapp.dictionary.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tester")
public class Tester {
    @Id
    private Integer testerId;
    private String firstName;
    private String lastName;
    private String country;

    @Column(name = "last_login", columnDefinition = "TIMESTAMP")
    private LocalDateTime lastLogin;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tester_device",
            joinColumns = @JoinColumn(name = "testerId", referencedColumnName = "testerId"),
            inverseJoinColumns = @JoinColumn(name = "deviceId", referencedColumnName = "deviceId")
    )
    private List<Device> devices;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bug",
            joinColumns = @JoinColumn(name = "testerId", referencedColumnName = "testerId"),
            inverseJoinColumns = @JoinColumn(name = "deviceId", referencedColumnName = "deviceId")
    )
    private List<Device> bugs;

}
