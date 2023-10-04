package ru.sartfoms.applgar.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.sartfoms.applgar.entity.MergeAncessorOip;

public interface MergeAncessorOipRepository extends JpaRepository<MergeAncessorOip, Long> {

	Page<MergeAncessorOip> findByUserAndDtInsBetweenOrderByDtInsDesc(String userName, LocalDateTime atStartOfDay,
			LocalDateTime atStartOfDay2, PageRequest pageRequest);

	Page<MergeAncessorOip> findByUserAndDtInsAfterOrderByDtInsDesc(String userName, LocalDateTime atStartOfDay,
			PageRequest pageRequest);

	Page<MergeAncessorOip> findByUserAndDtInsBeforeOrderByDtInsDesc(String userName, LocalDateTime atStartOfDay,
			PageRequest pageRequest);

	Page<MergeAncessorOip> findByUserOrderByDtInsDesc(String userName, PageRequest pageRequest);

}