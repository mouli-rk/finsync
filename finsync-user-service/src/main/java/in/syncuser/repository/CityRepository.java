package in.syncuser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import in.syncuser.entity.City;

public interface CityRepository extends JpaRepository<City, Long>{
	
	@EntityGraph(attributePaths = { "district", "district.state", "district.state.country" })
	Optional<City> findById(Long id);

}
