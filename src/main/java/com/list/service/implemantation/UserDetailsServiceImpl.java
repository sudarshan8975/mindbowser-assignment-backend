package com.list.service.implemantation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.list.entity.Registration;
import com.list.repository.RegistrationRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private RegistrationRepository registrationRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Registration reg = registrationRepository.findByEmail(email);
		return new User(reg.getEmail(), reg.getPassword(),new ArrayList<>());
		
		/*
		 * if(reg==null) { throw new UsernameNotFoundException("User Not Found"); }
		 * System.out.println("reg--11"+reg.getEmail()); return reg;
		 */
	}

}
