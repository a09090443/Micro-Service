package com.zipe.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.zipe.model.Authority;

public interface IAuthorityRepository extends JpaRepository<Authority, Long> {

	public List<Authority> findAll();

//	@Query("SELECT a FROM Authority a WHERE a.authorityId in :authorityId")
	public Set<Authority> findAuthoritiesByAuthorityIdIn(@Param("authorityId") List<String> authorityId);

}
