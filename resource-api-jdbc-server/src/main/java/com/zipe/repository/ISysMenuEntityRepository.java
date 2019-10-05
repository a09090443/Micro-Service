package com.zipe.repository;


import com.zipe.model.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sysMenuEntityRepository")
public interface ISysMenuEntityRepository extends JpaRepository<SysMenu, Long> {

    @Query("SELECT S.menuName FROM SysMenu S WHERE S.parentId = 0")
    public List<SysMenu> findAllByLevel();
}