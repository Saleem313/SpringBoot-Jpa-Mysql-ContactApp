package com.ss.contact.controller;


import java.util.Iterator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.contact.entity.ContactEntity;
import com.ss.contact.service.ContactServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/contact")
public class ContactController {
	
  private List<ContactEntity> contacts; 
	
	
  @Autowired
  private ContactServiceImpl service;
  
  

  @RequestMapping(method = RequestMethod.POST, value = "/saveContact")
  public ResponseEntity<String> saveContact(@RequestBody ContactEntity contact) {
	  boolean saveContact = false;
	 contacts = service.getAllContacts();
	 if(contacts!=null) {
		 Iterator<ContactEntity> itr = contacts.iterator();
	     while(itr.hasNext()) {
	    	 ContactEntity contact1 = itr.next();
	    	 if(contact.getContactId()==contact1.getContactId()) 
	    		saveContact=false; 	
	    	 else
	    		 saveContact = service.saveContact(contact);
	     }
	    	 
	 }
	 return saveContact ? new ResponseEntity<String>("Contact Saved successfully", HttpStatus.OK):
		                  new ResponseEntity<String>("Contact already exist",HttpStatus.EXPECTATION_FAILED);
		                  
  }
  
  @RequestMapping(method = RequestMethod.GET, value="/getContact/{contactId}")
  public ResponseEntity<?> getContactById(@PathVariable (value = "contactId") int contactId){
	  System.out.println("getContactById");
	  ContactEntity contact = null;
	  boolean flag = false;
	  contacts = service.getAllContacts();
	  if(contacts!=null) {
		  Iterator<ContactEntity> itr = contacts.iterator();
		  while(itr.hasNext()) {
			  ContactEntity contact1 = itr.next();
			  if(contact1.getContactId()==contactId) {
				 contact = (ContactEntity) service.getContact(contactId);
				 flag = true;		 
		  }
		  }
	  }
		  return flag ? new ResponseEntity<ContactEntity>(contact, HttpStatus.OK):
                            new ResponseEntity<String>("Contact not exist",HttpStatus.EXPECTATION_FAILED); 
  }
  
  @RequestMapping(method = RequestMethod.GET, value="/getAllContacts")
  public ResponseEntity<?> getAllContacts(){
	contacts = service.getAllContacts();
	  return new ResponseEntity<List<ContactEntity>>(contacts,HttpStatus.OK);
  }
  
  @RequestMapping(method = RequestMethod.DELETE, value = "/deleteContact/{contactId}") 
  public ResponseEntity<String> deleteContact(@PathVariable(value="contactId")int contactId){
	  boolean deleteContact = service.deleteContact(contactId);
	  if(deleteContact == true)
		  return new ResponseEntity<String>("Contact deleted successfully",HttpStatus.OK);
	  else
		  return new ResponseEntity<String>("Contact not available to delete", HttpStatus.NOT_FOUND);
  }
  
  @RequestMapping(method = RequestMethod.PUT, value = "/updateContact/{contactId}") 
  public ResponseEntity<?> updateContact(@PathVariable(value = "contactId")int contactId,@RequestBody ContactEntity contact){
	  System.out.println("updateContact");
	  boolean updateContact = false;
	  contacts = service.getAllContacts();
	 Iterator<ContactEntity> itr = contacts.iterator();
	 while(itr.hasNext()) {
		 ContactEntity contact1 = itr.next();
		 if(contact1.getContactId() == contact.getContactId()) 
			 updateContact = service.updateContact(contactId, contact);
			  
	 
	 }
	 return updateContact ? new ResponseEntity<ContactEntity>(contact, HttpStatus.OK) :
		                    new ResponseEntity<String>("Contact not available to update",HttpStatus.NOT_FOUND);
  
  
  }
}
