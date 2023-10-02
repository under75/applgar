package ru.sartfoms.applgar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.sartfoms.applgar.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
