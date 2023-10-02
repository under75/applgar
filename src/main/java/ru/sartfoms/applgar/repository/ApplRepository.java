package ru.sartfoms.applgar.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.sartfoms.applgar.entity.Appl;
import ru.sartfoms.applgar.entity.Inspector;

@Repository
public interface ApplRepository extends JpaRepository<Appl, Long> {

	Page<Appl> findByDtApplBetweenAndCdSmo(LocalDate start, LocalDate end, Integer cdSmo, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndSerDoc(LocalDate start, LocalDate end, Integer cdSmo, String serDoc,
			Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndSerDocAndNumDoc(LocalDate start, LocalDate end, Integer cdSmo,
			String serDoc, String numDoc, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmo(LocalDate start, LocalDate end, Integer cdSmo, Integer cdFsmo,
			Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDoc(LocalDate start, LocalDate end, Integer cdSmo,
			Integer cdFsmo, String serDoc, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDoc(LocalDate start, LocalDate end, Integer cdSmo,
			Integer cdFsmo, String serDoc, String numDoc, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndInspector(LocalDate start, LocalDate end, Integer cdSmo,
			Inspector inspector, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndSerDocAndInspector(LocalDate start, LocalDate end, Integer cdSmo,
			String serDoc, Inspector inspector, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndSerDocAndNumDocAndInspector(LocalDate start, LocalDate end,
			Integer cdSmo, String serDoc, String numDoc, Inspector inspector, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndInspector(LocalDate start, LocalDate end, Integer cdSmo,
			Integer cdFsmo, Inspector inspectorp, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndInspector(LocalDate start, LocalDate end,
			Integer cdSmo, Integer cdFsmo, String serDoc, Inspector inspector, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDocAndInspector(LocalDate start, LocalDate end,
			Integer cdSmo, Integer cdFsmo, String serDoc, String numDoc, Inspector inspector, Pageable pageable);

}

