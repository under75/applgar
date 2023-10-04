package ru.sartfoms.applgar.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.sartfoms.applgar.entity.PersonData;

public interface PersonDataRepository extends JpaRepository<PersonData, Long> {

	Page<PersonData> findByUserOrderByDtInsDesc(String name, Pageable pageable);

	Page<PersonData> findByUserAndDtInsBetweenOrderByDtInsDesc(String name, LocalDate start, LocalDate end, Pageable pageable);

	Page<PersonData> findByUserAndDtInsAfterOrderByDtInsDesc(String name, LocalDate start, Pageable pageable);

	Page<PersonData> findByUserAndDtInsBeforeOrderByDtInsDesc(String name, LocalDate end, Pageable pageable);

}

