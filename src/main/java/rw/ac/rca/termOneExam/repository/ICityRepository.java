package rw.ac.rca.termOneExam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ac.rca.termOneExam.domain.City;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

	boolean existsByName(String name);

}
