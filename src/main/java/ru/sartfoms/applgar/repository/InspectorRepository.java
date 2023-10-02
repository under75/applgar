package ru.sartfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.sartfoms.applgar.entity.Inspector;


@Repository
public interface InspectorRepository extends JpaRepository<Inspector, Long> {

	Collection<Inspector> findByCdSmoAndLoginIsNotNullOrderByFioInsp(Integer cdSmo);

	Collection<Inspector> findByCdSmoAndCdFsmoAndLoginIsNotNullOrderByFioInsp(Integer cdSmo, Integer cdFsmo);

}

