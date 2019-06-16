package com.zipe.test.service;

import com.zipe.vo.SysMenuVO;
import com.zipe.service.ISysMenuService;
import com.zipe.test.base.TestBase;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysMenuServiceTest extends TestBase {

	@Autowired
	private ISysMenuService sysMenuService;

	@Test
	public void getSysMenuTreeTest(){
		List<SysMenuVO> sysMenuVOList = sysMenuService.findSysMenuTree();
		Assert.assertNotNull(sysMenuVOList);
	}

}
