package com.gmail.Gmail.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.gmail.Gmail.Repository.MailRepository;
import com.gmail.Gmail.Repository.UserRepository;
import com.gmail.Gmail.model.Mail;
import com.gmail.Gmail.model.User;
import com.gmail.Gmail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImplementation implements MailService {

	@Autowired
	private MailRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override  //getting sent mails by using fk and type
	public List<Mail> findSentMails(String type, String senderid) {
//		List<Mail> mails=new ArrayList<>();
		User user=null;
		try {
			user=userRepo.findById(senderid).get();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		List<Mail> mails=repo.findByTypeAndUser(type, user);
		return mails; 
	}

	@Override      //sending mail by taking mail body and senders id
	public Mail sendMail(Mail mail) {
		User user=null;             //This is for storing sender's object
		Mail mail1=null;            //This is for storing sender's mail after saving
		List<String> senderid=new ArrayList<>(); //storing sender's id for sending mail to that sender 
		List<String> rMailids=mail.getReceiversId();     //storing receivers id's 
		List<String> verifiedMailid=new ArrayList<>();   //storing existed receiver id's
		List<String> wrongMailid=new ArrayList<>();
		try {                                            //taking senders user object
			user=userRepo.findById(mail.getSenderId()).get();
		}
		catch(Exception e) {
		}
		senderid.add(mail.getSenderId());
		mail.setSenderId(mail.getSenderId());    //This is for sending mail for senders sent box
		mail.setDate();               //setting mail time and date and other details
		mail.setTime();
		mail.setType("sent");
		mail.setUser(user);
		mail1=repo.save(mail);
		for (String string : rMailids) {   //if receiver id is not present it will stop and sends failure notification
			User user1=null;
			try {
				user1=userRepo.findById(string).get();         //getting each receiver object
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			if(user1==null) {   //if receiver is not present the application will send default failure mail
				wrongMailid.add(string);
			}
			else
			{                                      //sending mails for the receiver
				verifiedMailid.add(string);
			}
		}
		for (String string : verifiedMailid) {     //sending mails for verified receivers
			User user1=null;
			try {
				user1=userRepo.findById(string).get();
			}
			catch (Exception e) {
			}
			Mail mail2=new Mail();
			mail2.setSenderId(mail.getSenderId());
			mail2.setReceiversId(mail.getReceiversId());
			mail2.setSubject(mail.getSubject());
			mail2.setMessage(mail.getMessage());
			mail2.setDate();
			mail2.setTime();
			mail2.setType("inbox");
			mail2.setUser(user1);
			repo.save(mail2);                      //storing mails in their inbox
		}
		if(wrongMailid.size()!=0) {
			Mail mail2=new Mail();
			mail2.setSenderId("mailSecurityTeam@gmail.com");
			mail2.setReceiversId(senderid);
			mail2.setSubject("Mail not sent successfully");
			mail2.setMessage("Entered "+ wrongMailid +" is not there. Kindly please check it and send to that users only");
			mail2.setDate();
			mail2.setTime();
			mail2.setType("inbox");
			mail2.setUser(user);
			repo.save(mail2);
		}
		return mail1;
	}

	@Override     //to get mails from receivers mailid
	public List<Mail> findReceivedMails(String type,String rmailid) {
		User user=null;
		try {
			user=userRepo.findById(rmailid).get();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		List<Mail> rmails=repo.findByTypeAndUser(type, user);
		return rmails;
	}

	@Override
	public boolean deleteMail(List<Integer> id) {
		boolean isDeleted=false;
		for (Integer integer : id) {
			Mail mail= repo.findById(integer).get();;
					mail.setType("deleted");
					mail.setImportant(false);
					repo.save(mail);
					isDeleted=true;
		}
		return isDeleted;
	}

	@Override   //getting important mails
	public List<Mail> findImportantMails(boolean important,String mailid) {
		User user=null;
		try {
			user=userRepo.findById(mailid).get();
		}
		catch (Exception e) {
		}
		List<Mail> mails=repo.findByIsImportantAndUser(important, user);
		return mails;
	}

	@Override    //setting mail as important one
	public Mail setImportant(Mail mail) {
		Mail mail1=null;
		try {
			mail1=repo.findById(mail.getId()).get();
		}
		catch (Exception e) {
		}
		if(mail.getisImportant()==true) {
			mail1.setImportant(mail.getisImportant());
			return repo.save(mail1);
		}
		else if(mail.getisImportant()==false){
			mail1.setImportant(mail.getisImportant());
			return repo.save(mail1);
		}
		else {
			return null;
		}
	}

	@Override
	public Mail findMail(int id) {
		Mail mail=null;
		try {
			mail=repo.findById(id).get();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return mail;
	}

	@Override
	public Mail setRead(int id) {
		Mail mail=null;
		Mail mail1=null;
		try {
			mail=repo.findById(id).get();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		if(mail!=null) {
			mail.setRead(true);
			mail1=repo.save(mail);
		}
		return mail1;
	}

	@Override
	public List<Mail> findDeltedMails(String type, String mailid) {
		User user=null;
		try {
			user=userRepo.findById(mailid).get();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		List<Mail> binMails=repo.findByTypeAndUser(type, user);
		return binMails;
	}

	@Override
	public boolean permanentDeleteMail(List<Integer> id) {
		boolean isDeleted=false;
		for (Integer integer : id) {
			repo.deleteById(integer);
			isDeleted=true;
		}
		return isDeleted;
	}
}