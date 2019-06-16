package com.zipe.controller;

import com.zipe.vo.UserInfoVO;
import com.zipe.base.controller.BaseController;
import com.zipe.model.UserInfo;
import com.zipe.model.Authority;
import com.zipe.model.PersonalTitle;
import com.zipe.service.IUserService;
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

	@GetMapping("/users")
	public List<UserInfo> users() throws Exception {
		List<UserInfo> userInfoList = userService.findAllUsers();
		return userInfoList;
	}

	@GetMapping("/authorities")
	public List<Authority> authorities() throws Exception {
		List<Authority> authorityList = userService.getAuthorities();
		return authorityList;
	}
	
	@GetMapping("/personalTitles")
	public List<PersonalTitle> personalTitles() throws Exception {
		List<PersonalTitle> personalTitleList = userService.getPersonalTitles();
		return personalTitleList;
	}

	@GetMapping("/user/{loginId}")
	public UserInfo user(@PathVariable String loginId) {
		UserInfo userInfo = userService.findUserByLoginId(loginId);
		return userInfo;
	}

	@GetMapping("/test")
	public Authentication test(Authentication user) {
		return user;
	}

	@PostMapping(value = "/user")
	public String updateAndCreate(@RequestParam("userForm") String userForm) {
		ObjectMapper mapper = new ObjectMapper();
		UserInfoVO userInfoVO;
		try {
			userInfoVO = mapper.readValue(userForm, UserInfoVO.class);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		UserInfo userInfo = userService.findUserByLoginId(userInfoVO.getLoginId());

		if(null == userInfo){
			userInfo = new UserInfo();
		}
		//Ignore null properties
		myCopyProperties(userInfoVO, userInfo);
		Set<Authority> authoritySet = userService.getAuthoritiesByAuthorityId(userInfoVO.getAuthorities());
		userInfo.setAuthorities(authoritySet);
		Set<PersonalTitle> personalTitleSet = userService.getPersonalTitlesByTitleId(userInfoVO.getPersonalTitles());
		userInfo.setPersonalTitle(personalTitleSet);
		
		try {
			userService.saveUser(userInfo);
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	@GetMapping(value = "/users")
	public Authentication update(Authentication user) {
		return user;
	}

	@DeleteMapping("/user/{loginId}")
	public String delete(@PathVariable String loginId) {
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginId(loginId);

		try {
			userService.delUser(userInfo);
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
