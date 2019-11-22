package com.capgemini.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.application.exception.RecordNotFoundException;
import com.capgemini.application.model.ContactEntity;
import com.capgemini.application.service.ContactService;

@RestController
@RequestMapping("/contcat")
public class ContactController {
	
	@Autowired
	ContactService service;
	
	@GetMapping
    public ResponseEntity<List<ContactEntity>> getAllContact() {
        List<ContactEntity> list = service.getAllContact();
 
        return new ResponseEntity<List<ContactEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	 @GetMapping("/{id}")
	    public ResponseEntity<ContactEntity> getContactById(@PathVariable("id") Long id) 
	                                                    throws RecordNotFoundException {
		 ContactEntity entity = service.getContactById(id);
	 
	        return new ResponseEntity<ContactEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	 @PostMapping
	    public ResponseEntity<ContactEntity> createOrUpdateEmployee(ContactEntity contact)
	                                                    throws RecordNotFoundException {
		 ContactEntity updated = service.createOrUpdateContact(contact);
	        return new ResponseEntity<ContactEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	 @DeleteMapping("/{id}")
	    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id) 
	                                                    throws RecordNotFoundException {
	        service.deleteContactById(id);
	        return HttpStatus.FORBIDDEN;
	    }

}

