package com.capgemini.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.application.exception.RecordNotFoundException;
import com.capgemini.application.model.ContactEntity;
import com.capgemini.application.repository.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	ContactRepository repository;
	
	public List<ContactEntity> getAllContact() {
		
		List<ContactEntity> contactList = repository.findAll();
		
		if(contactList.size()>0)
		{
			return contactList;
		}
		else {
			return new ArrayList<ContactEntity>();
		}
	}
	
	public ContactEntity getContactById(Long id)  throws RecordNotFoundException {
		
		Optional<ContactEntity> contact = repository.findById(id);
		
		if(contact.isPresent()) {
			return contact.get();
		}
		else
		{
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}
	
	public ContactEntity createOrUpdateContact(ContactEntity entity) throws RecordNotFoundException 
    {
        Optional<ContactEntity> contact = repository.findById(entity.getId());
         
        if(contact.isPresent()) 
        {
        	ContactEntity newEntity = contact.get();
        	newEntity.setName(entity.getName());
            newEntity.setEmail(entity.getEmail());
            newEntity.setAddress(entity.getAddress());
            newEntity.setTelephone(entity.getTelephone());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
	
	public void deleteContactById(Long id) throws RecordNotFoundException 
    {
        Optional<ContactEntity> contact = repository.findById(id);
         
        if(contact.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    } 

}
