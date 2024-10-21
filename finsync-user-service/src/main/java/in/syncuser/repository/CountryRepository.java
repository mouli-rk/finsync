package in.syncuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.syncuser.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

}
