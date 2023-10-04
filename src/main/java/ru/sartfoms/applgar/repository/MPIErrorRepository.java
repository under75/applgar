package ru.sartfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.sartfoms.applgar.entity.CompositeKey;
import ru.sartfoms.applgar.entity.MPIError;

public interface MPIErrorRepository extends JpaRepository<MPIError, CompositeKey> {

	Collection<MPIError> findAllByRid(Long rid);

}
