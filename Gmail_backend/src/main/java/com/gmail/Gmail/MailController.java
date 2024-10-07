package com.gmail.Gmail;

import java.io.IOException;
import java.util.List;

import com.gmail.Gmail.model.Mail;
import com.gmail.Gmail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/gmail")
public class MailController {
	
	@Autowired
	private MailService service;
	
	@PostMapping("/sendmail")        //to send mail by taking sender mail id and mail body
	public ResponseEntity<?> sendMail(@RequestParam("mail") String mail) throws IOException {
		Mail mail1=new Mail();
		ObjectMapper og=new ObjectMapper();
		mail1=og.readValue(mail, Mail.class);
		Mail mail2=service.sendMail(mail1);
		if(mail2!=null) {
			return ResponseEntity.status(HttpStatus.OK).body("Mail sent successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mail not sented");
		}
	}
//	@GetMapping(value = "/getimage/{id}",produces = org.springframework.http.MediaType.ALL_VALUE)
//    public ResponseEntity<?> getImage(@PathVariable Integer id) {
//        Mail imageEntity = repo.findById(id).orElse(null);
//
//        if (imageEntity != null) {
//            return ResponseEntity.ok(imageEntity.getAttachments());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No image");
//        }
//    }
	
	@GetMapping("/getsentmails/{type}/{senderid}")
	public ResponseEntity<?> getSentMails(@PathVariable String senderid,@PathVariable String type) {
		List<Mail> mails=service.findSentMails(type,senderid);
		if(mails.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Sent mails");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(mails);
		}
	}
	
	@GetMapping("/getreceivedmails/{type}/{rmailid}")
	public ResponseEntity<?> getReceivedMails( @PathVariable String type,@PathVariable String rmailid) {
		List<Mail> mails=service.findReceivedMails(type,rmailid);
		if(mails.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No mails received");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(mails);
		}
	}
	
	@PutMapping("/deletemail")
	public ResponseEntity<?> deleteMail(@RequestParam("ids") List<Integer> selectedIds)
	{
		boolean mail=service.deleteMail(selectedIds);
		if(mail==true) {
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not deleted");
		}
	}
	
	@PutMapping("/setimportant")
	public ResponseEntity<?> setImportantMail(@RequestParam("important") boolean important,@RequestParam("id") int id) throws IOException {
		Mail mail1=new Mail();
//		ObjectMapper og=new ObjectMapper();
		mail1.setImportant(important);
		mail1.setId(id);
		Mail mail2=service.setImportant(mail1);
		if(mail2!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(mail2);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation is not done");
		}
	}
	
	@GetMapping("/getimportantmails/{important}/{mailid}")
	public ResponseEntity<?> getImportantMails(@PathVariable boolean important,@PathVariable String mailid)
	{
		List<Mail> mails=service.findImportantMails(important, mailid);
		if(mails.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No mails found");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(mails);
		}
	}
	
	@GetMapping("/getmail/{id}")
	public ResponseEntity<?> getMail(@PathVariable int id) {
		Mail mail=service.findMail(id);
		if(mail!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(mail);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 NOT FOUND");
		}
	}
	
	@PutMapping("/setread/{id}")
	public ResponseEntity<?> setRead(@PathVariable int id) {
		Mail mail=service.setRead(id);
		if(mail!=null) {
			return ResponseEntity.status(HttpStatus.OK).body("Mail read");
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
		}
	}
	
	@GetMapping("/getbinmails/{type}/{mailid}")
	public ResponseEntity<?> getDeletedMails(@PathVariable String type,@PathVariable String mailid) {
		List<Mail> mails=service.findDeltedMails(type, mailid);
		if(mails.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No mails in bin folder");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(mails);
		}
	}
	
	@DeleteMapping("/deletepermanently/{selectedIds}")
	public ResponseEntity<?> deletePermanently(@PathVariable List<Integer> selectedIds)
	{
		boolean mail=service.permanentDeleteMail(selectedIds);
		if(mail==true) {
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not deleted");
		}
	}
}
