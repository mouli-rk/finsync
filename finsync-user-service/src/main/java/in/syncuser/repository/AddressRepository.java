package in.syncuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.syncuser.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}