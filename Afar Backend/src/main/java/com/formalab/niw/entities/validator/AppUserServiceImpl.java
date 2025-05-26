package com.formalab.niw.entities.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.formalab.niw.repositories.AppUserRepository;
@Service
public class AppUserServiceImpl  implements AppUserService  {
	  @Autowired
	    private AppUserRepository appUserRepository;
	  @Override
	    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
	        Assert.notNull(fieldName);

	        if (!fieldName.equals("email")) {
	            throw new UnsupportedOperationException("Field name not supported");
	        }

	        if (value == null) {
	            return false;
	        }

	        return this.appUserRepository.existsByEmail(value.toString());
	    }
}
