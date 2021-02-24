package com.ss.contact.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="contact_master")
public class ContactEntity {
  @Id
  @Column(name="Contact_Id")
 // @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int contactId;
  @Column(name="Contact_Name")
  private String contactName;
  @Column(name="Contact_Number")
  private long contactNumber;
  
}
