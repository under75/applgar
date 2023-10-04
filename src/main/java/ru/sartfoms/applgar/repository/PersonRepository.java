package ru.sartfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.sartfoms.applgar.entity.CompositeKey;
import ru.sartfoms.applgar.entity.Person;

public interface PersonRepository extends JpaRepository<Person, CompositeKey> {

	Collection<Person> findByRid(Long rid);
}
