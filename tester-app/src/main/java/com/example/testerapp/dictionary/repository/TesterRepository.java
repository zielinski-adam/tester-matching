package com.example.testerapp.dictionary.repository;

import com.example.testerapp.dictionary.domain.Tester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesterRepository extends JpaRepository<Tester, Integer> {
}
