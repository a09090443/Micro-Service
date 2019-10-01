package com.zipe.repository;

import com.zipe.model.SysUserTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ISysUserTitleRepository extends JpaRepository<SysUserTitle, Long> {

    public List<SysUserTitle> findAll();

    //	@Query("SELECT pt FROM PersonalTitle pt WHERE pt.titleId in :titleId")
    public Set<SysUserTitle> findSysUserTitleByTitleIdIn(@Param("titleId") List<String> titleId);

}
