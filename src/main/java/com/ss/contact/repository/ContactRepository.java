package com.ss.contact.repository;

import org.springframework.data.repository.CrudRepository;

import com.ss.contact.entity.ContactEntity;

public interface ContactRepository extends CrudRepository<ContactEntity, Integer> {

}
