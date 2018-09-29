package com.zipe.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.zipe.model.PersonalTitle;

public interface IPersonalTitleRepository extends JpaRepository<PersonalTitle, Long> {

	public List<PersonalTitle> findAll();

//	@Query("SELECT pt FROM PersonalTitle pt WHERE pt.titleId in :titleId")
	public Set<PersonalTitle> findPersonalTitleByTitleIdIn(@Param("titleId") List<String> titleId);

}
