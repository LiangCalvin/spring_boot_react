package com.employee.contact_api.controller;

import com.employee.contact_api.domain.Contact;
import com.employee.contact_api.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api")
public class ContactController {

    private final ContactService _contactService;

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

    @PutMapping("/uploadImage")
    public ResponseEntity<String> uploadImage (@RequestParam("id") String id,
                                               @RequestParam("file") MultipartFile imgUrl) {
        return ResponseEntity.ok().body(_contactService.uploadPhoto(id, imgUrl));
    }

    @GetMapping(path = "/getImage/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getImage(@PathVariable("filename") String filename) throws IOException {
        Path filePath = Paths.get("").toAbsolutePath().resolve(filename);
        return Files.readAllBytes(filePath);
    }

//
//    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
//    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
//        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
//    }
}
