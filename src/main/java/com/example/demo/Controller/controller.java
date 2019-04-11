package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Users;
import com.example.demo.service.BankService;

@RestController
public class controller {
	@Autowired
	private BankService bankService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/aa")
	public String main() {
		String s = bankService.aaaaaaaa();
		return "main"+s;
	}
	@GetMapping("/admin")
	public String admin() {
		return "admin!!";
	}
	@GetMapping("/user")
	public Users user() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if("anonymousUser".equals(principal)) {
			return null;
		}else {
			Users user = (Users)principal;
			System.out.println(user);
			return user;
		}
	}
}
