package com.zipe.controller;

import com.zipe.base.controller.BaseController;
import com.zipe.model.SysAuthority;
import com.zipe.model.SysUser;
import com.zipe.model.SysUserTitle;
import com.zipe.service.IUserService;
import com.zipe.vo.UserInfoVO;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/userApi")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	@GetMapping("/sysUsers")
	public List<SysUser> users() throws Exception {
		List<SysUser> sysUserList = userService.findAllUsers();
		return sysUserList;
	}

	@GetMapping("/sysAuthorities")
	public List<SysAuthority> authorities() throws Exception {
		List<SysAuthority> sysAuthorityList = userService.getAuthorities();
		return sysAuthorityList;
	}
	
	@GetMapping("/sysUserTitles")
	public List<SysUserTitle> sysUserTitles() throws Exception {
		return userService.getSysUserTitles();
	}

	@GetMapping("/sysUser/{loginId}")
	public SysUser user(@PathVariable String loginId) {
		return userService.findUserByLoginId(loginId);
	}

	@GetMapping("/test")
	public Authentication test(Authentication user) {
		return user;
	}

	@PatchMapping(value = "/sysUser/{loginId}")
	public String update(@PathVariable String loginId, @RequestParam("userForm") String userForm) {
		ObjectMapper mapper = new ObjectMapper();
		UserInfoVO userInfoVO;
		try {
			userInfoVO = mapper.readValue(userForm, UserInfoVO.class);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		SysUser sysUser = userService.findUserByLoginId(userInfoVO.getLoginId());

		if(null == sysUser){
			return "user not found";
		}
		//Ignore null properties
		myCopyProperties(userInfoVO, sysUser);
		Set<SysAuthority> sysAuthoritySet = userService.getSysAuthoritiesByAuthorityId(userInfoVO.getAuthorities());
		sysUser.setAuthorities(sysAuthoritySet);
		Set<SysUserTitle> sysUserTitleSet = userService.getSysUserTitlesByTitleId(userInfoVO.getPersonalTitles());
		sysUser.setSysUserTitle(sysUserTitleSet);

		try {
			userService.saveUser(sysUser);
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	@PostMapping(value = "/sysUser")
	public String create(@RequestParam("userForm") String userForm) {
		ObjectMapper mapper = new ObjectMapper();
		UserInfoVO userInfoVO;
		try {
			userInfoVO = mapper.readValue(userForm, UserInfoVO.class);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		SysUser sysUser = userService.findUserByLoginId(userInfoVO.getLoginId());

		if(null == sysUser){
			sysUser = new SysUser();
		}
		//Ignore null properties
		myCopyProperties(userInfoVO, sysUser);
		Set<SysAuthority> authoritySet = userService.getSysAuthoritiesByAuthorityId(userInfoVO.getAuthorities());
		sysUser.setAuthorities(authoritySet);
		Set<SysUserTitle> sysUserTitleSet = userService.getSysUserTitlesByTitleId(userInfoVO.getPersonalTitles());
		sysUser.setSysUserTitle(sysUserTitleSet);
		
		try {
			userService.saveUser(sysUser);
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

//	@GetMapping(value = "/users")
//	public Authentication update(Authentication user) {
//		return user;
//	}

	@DeleteMapping("/user/{loginId}")
	public String delete(@PathVariable String loginId) {
		SysUser sysUser = new SysUser();
		sysUser.setLoginId(loginId);

		try {
			userService.delUser(sysUser);
		}catch(Exception e) {
			e.printStackTrace();
			return "failure";
		}
		return "success";
	}
	
	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}
	
	public static void myCopyProperties(Object src, Object target) {
	    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}
}
