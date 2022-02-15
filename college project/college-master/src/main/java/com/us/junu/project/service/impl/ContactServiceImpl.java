package com.us.junu.project.service.impl;

import com.us.junu.project.model.Contact;
import com.us.junu.project.repository.ContactRepository;
import com.us.junu.project.service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void save(Contact contact) {
        contactRepository.save(contact);
    }
}
