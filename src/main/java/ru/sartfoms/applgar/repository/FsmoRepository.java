package ru.sartfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.sartfoms.applgar.entity.Fsmo;
import ru.sartfoms.applgar.entity.FsmoId;

public interface FsmoRepository extends JpaRepository<Fsmo, FsmoId> {

	Collection<Fsmo> findByCdSmo(Integer smo);

	Fsmo getByCdSmoAndCdFsmo(Integer smo, Integer fSmo);

	Collection<Fsmo> findByCdSmoOrderByName(Integer smo);

}

