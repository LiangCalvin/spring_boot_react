package com.employee.contact_api.services;

import com.employee.contact_api.domain.Contact;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IContactService {
        Page<Contact> getAllContactsPerPage(int page, int size);
        List<Contact> getAllContact();


    }
