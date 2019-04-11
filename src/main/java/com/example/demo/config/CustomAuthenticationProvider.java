package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Authorities;
import com.example.demo.entity.Users;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		// 1.查询用户User
		String username=(String) authentication.getPrincipal();
		String password=(String) authentication.getCredentials();
		String sql = "select username,password,phone,email,enabled from users where username='"+username+"' and password='"+password+"' ";
		Users users = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Users.class));
		String authoritiesSql = "select a.username,a.authority from users u left join authorities a on u.username=a.username where u.username='"+username+"' and a.password='"+password+"' ";
		List<Authorities> authoritiesList = jdbcTemplate.query(authoritiesSql, new BeanPropertyRowMapper(Authorities.class));
		// 2.把用户User转换成Authentication对象返回
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(users,
				users.getPassword(),authoritiesList);
		result.setDetails(authentication.getDetails());
		return result;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		// TODO Auto-generated method stub
		return true;
	}

}
