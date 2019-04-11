package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
@Component
@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private CustomFilter customFilter;
    @Autowired
    private CustomAuthenticationProvider customauthenticationprovider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
        auth.authenticationProvider(customauthenticationprovider);
//                .withDefaultSchema();
//                .withUser("1").password("1").roles("USER")
//                .and().withUser("2").password("2").roles("ADMIN")
//                .and().withUser("3").password("3").authorities("READ");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().access("@accessControlService.canAccess(request,authentication)");
        http.formLogin();
        // 禁用 H2 控制台的 CSRF 防护
        http.csrf().ignoringAntMatchers("/h2-console/**");
        // 允许来自同一来源的H2 控制台的请求
        http.headers().frameOptions().sameOrigin();
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/css/**", "/js/**", "image/**");
    }
}