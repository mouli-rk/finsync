package in.syncaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.syncaccount.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Account findTopByOrderByIdDesc();
}
