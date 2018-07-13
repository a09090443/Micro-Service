package com.zipe.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zipe.security.service.UserDetailsService;
import com.zipe.service.impl.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		        .authorizeRequests()
		        .anyRequest() // 對象為所有網址
				.authenticated() // 存取必須通過驗證
				.and().formLogin() // 若未不符合authorize條件，則產生預設login表單
				.and().httpBasic(); // 產生基本表單
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	auth.parentAuthenticationManager(authenticationManager).userDetailsService(userDetailsService)
//        .passwordEncoder(passwordEncoder());
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}