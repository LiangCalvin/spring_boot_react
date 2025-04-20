package com.employee.contact_api.controller;

import com.employee.contact_api.domain.Contact;
import com.employee.contact_api.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {

    private ContactService _contactService;

    @Autowired
    public ContactController(ContactService _contactService) {
        this._contactService = _contactService;
    }

    @GetMapping("/getAllContactPerPage")
    public ResponseEntity<Page<Contact>> getAllContactsPerPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(_contactService.getAllContactsPerPage(page, size));
    }

    @GetMapping("/getAllContact")
    public ResponseEntity<List<Contact>> getAllContact() {
        return ResponseEntity.ok().body(_contactService.getAllContact());
    }

    @PostMapping("/createContact")
    public ResponseEntity<Contact> createContact2(@RequestBody Contact contact){
        Contact createdContact = _contactService.createContact(contact);
        URI location = URI.create("/getContactsById/" + createdContact.getId());
        return ResponseEntity.created(location).body(createdContact);
    }

    @GetMapping("/getContactsById/{id}")
    public ResponseEntity<Contact> getContactById (@PathVariable String id) {
        return ResponseEntity.ok().body(_contactService.getContact(id));
    }




//    @GetMapping("/{id}")
//    public ResponseEntity<Contact> getContactById(@PathVariable(value = "id") String id) {
//        return ResponseEntity.ok().body(contactService.getContact(id));
//    }
//
//    @PutMapping("/photo")
//    public ResponseEntity<String> uploadPhoto(@RequestParam("id") String id, @RequestParam("file")MultipartFile file) {
//        return ResponseEntity.ok().body(contactService.uploadPhoto(id, file));
//    }
//
//    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
//    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
//        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
//    }
}
