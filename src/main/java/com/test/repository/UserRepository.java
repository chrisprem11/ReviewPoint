package com.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.test.model.Person;

@Repository
public interface UserRepository extends DataTablesRepository<Person, Long> {
	
	Optional<Person> findOneByEmail(String email);

}
