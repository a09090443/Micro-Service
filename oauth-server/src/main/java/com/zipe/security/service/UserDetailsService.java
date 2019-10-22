package com.zipe.security.service;

import com.zipe.exception.UserNotActivatedException;
import com.zipe.model.SysAuthority;
import com.zipe.model.SysUser;
import com.zipe.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Custom authentication workflow (based on OAuth2) used by Spring security
 * layer.
 *
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired
	private IUserService userService;

	/**
	 * Checks if user exists in DB and is activated.
	 * 
	 * @param login
	 *            username used on login
	 * @return {@link UserDetails}
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login) {

		log.debug("Authenticating {}", login);
		String lowercaseLogin = login.toLowerCase();

		SysUser userFromDatabase;
		if (lowercaseLogin.contains("@")) {
			userFromDatabase = getUserService().findUserByEmail(lowercaseLogin);
		} else {
			userFromDatabase = getUserService().findUserByLoginId(login);
		}

		if (userFromDatabase == null) {
			throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database.");
		} else if (!userFromDatabase.isActivated()) {
			throw new UserNotActivatedException("User " + lowercaseLogin + " is not activated.");
		}

		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (SysAuthority sysAuthority : userFromDatabase.getAuthorities()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysAuthority.getName());
			grantedAuthorities.add(grantedAuthority);
		}

		return new org.springframework.security.core.userdetails.User(userFromDatabase.getLoginId(),
				userFromDatabase.getPassword(), grantedAuthorities);
	}

	public IUserService getUserService() {
		return userService;
	}
}
