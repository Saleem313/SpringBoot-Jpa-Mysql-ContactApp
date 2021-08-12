package com.ss.contact.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name="contact_master")
@AllArgsConstructor
@NoArgsConstructor
public class ContactEntity implements Serializable {
  @Id
  @Column(name="Contact_Id")
 // @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int contactId;
  @Column(name="Contact_Name")
  private String contactName;
  @Column(name="Contact_Number")
  private long contactNumber;
  
}
