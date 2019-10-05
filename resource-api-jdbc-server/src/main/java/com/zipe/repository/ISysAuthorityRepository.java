package com.zipe.repository;

import com.zipe.model.SysAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ISysAuthorityRepository extends JpaRepository<SysAuthority, Long> {

	public List<SysAuthority> findAll();

//	@Query("SELECT a FROM Authority a WHERE a.authorityId in :authorityId")
	public Set<SysAuthority> findSysAuthoritiesByAuthorityIdIn(@Param("authorityId") List<String> authorityId);

}