package ru.sartfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.sartfoms.applgar.entity.CompositeKey;
import ru.sartfoms.applgar.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, CompositeKey> {

	Collection<Policy> findAllByRid(Long rid);

}
