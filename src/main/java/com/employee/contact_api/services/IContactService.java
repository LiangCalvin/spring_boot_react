package com.employee.contact_api.services;

import com.employee.contact_api.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IContactService {
        Page<Contact> getAllContactsPerPage(int page, int size);
        List<Contact> getAllContact();
        Contact createContact(Contact contact);
        Contact getContact(String id);
        void deleteContact(Contact contact);
        String uploadPhoto(String id, MultipartFile file);


    }
