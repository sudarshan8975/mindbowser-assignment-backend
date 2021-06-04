package com.list.service.implemantation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.list.entity.Registration;
import com.list.repository.RegistrationRepository;
import com.list.service.RegistrationService;
import com.list.utility.Constant;
@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public String createUser(Registration registration) {
		Registration reg = null;
		
		
		String retVal="";
		try {
				reg = registrationRepository.findByEmail(registration.getEmail());
				if(reg != null) {
					retVal=Constant.ALREDY_EXIST;
				}
				else {
					String pass=registration.getPassword();
					registration.setPassword(this.bCryptPasswordEncoder.encode(pass));
					reg=registrationRepository.save(registration);
					if(reg != null) {
						retVal=Constant.SUCCESS;
					}
					else {
						retVal=Constant.FAIL;
					}
				}
			}
		catch(Exception e) {
			e.printStackTrace();
			retVal=Constant.FAIL;
		}
		// TODO Auto-generated method stub
		return retVal;
	}

}
