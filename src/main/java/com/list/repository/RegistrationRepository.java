package com.list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.list.entity.Registration;
@Repository
public interface RegistrationRepository extends JpaRepository<Registration,String> {
	
	public Registration findByEmail(String email);

}
