package ru.sartfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.sartfoms.applgar.entity.ASY23MPIError;

public interface ASY23MPIErrorRepository extends JpaRepository<ASY23MPIError, Long> {

	Collection<ASY23MPIError> findAllByRid(Long rid);

}
