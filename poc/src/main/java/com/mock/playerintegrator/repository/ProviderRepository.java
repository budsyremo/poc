package com.mock.playerintegrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mock.playerintegrator.dto.Providers;

@Repository
public interface ProviderRepository extends JpaRepository<Providers, Long> {
	
	Providers findByProviderName(String provider_id);
	
	@Query(value="SELECT max(id) FROM Providers ",  nativeQuery = true)
	int maxId();
}
