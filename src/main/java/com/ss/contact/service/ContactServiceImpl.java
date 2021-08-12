package com.ss.contact.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ss.contact.entity.ContactEntity;
import com.ss.contact.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contRepo;

	@Override
	public boolean saveContact(ContactEntity contact) {
		ContactEntity saveEntity = contRepo.save(contact);
		if(saveEntity != null) {
			return true;
		}
	     
		else
		 return false;
	}

	@Override
	@Cacheable(value = "contact",key = "#contactId")
	public Object getContact(int contactId) {
		Optional<ContactEntity> findById = contRepo.findById(contactId);
		if(findById!=null)
			return findById.get();
		else
			return "Contact Not found";
	}

	@Override
	@Cacheable(value = "contact")
	public List<ContactEntity> getAllContacts() {
		List<ContactEntity> contactList = new ArrayList<ContactEntity>();
		Iterable<ContactEntity> allContacts = contRepo.findAll();
		if(allContacts!=null) {
		for(ContactEntity contact : allContacts) {
			contactList.add(contact);
		}
		return contactList;	
		}
		else
		return null;	
	}

	@Override
	@CacheEvict(value = "contact", key = "#contactId")
	public boolean deleteContact(int contactId) {
		 boolean isExist = contRepo.existsById(contactId);
		if(isExist) {
			contRepo.deleteById(contactId);
	 		return true;
		}
		else
			return false;
	}
	
	@Override
	@CachePut(value = "contact", key = "#contactId")
	 public boolean updateContact(int contactId,ContactEntity contact) {
		Optional<ContactEntity> contact1 = contRepo.findById(contactId);
		if(contact1.isPresent() ) {
	         ContactEntity contactEntity = contact1.get();
	         contactEntity.setContactName(contact.getContactName());
	         contactEntity.setContactNumber(contact.getContactNumber());
	         contRepo.save(contactEntity);
	         return true;
		}
			
			return false;
		
	}

}
