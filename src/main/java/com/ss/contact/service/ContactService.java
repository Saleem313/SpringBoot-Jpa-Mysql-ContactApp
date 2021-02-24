package com.ss.contact.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.ss.contact.entity.ContactEntity;


public interface ContactService {
   public boolean saveContact(ContactEntity contact);
   public Object getContact(int contactId);
   public List<ContactEntity> getAllContacts();
   public boolean deleteContact(int contactId);
   public boolean updateContact(int contactId,ContactEntity contact);
}
