package com.gmail.Gmail.service;

import com.gmail.Gmail.model.Mail;

import java.util.List;

public interface MailService {
	List<Mail> findSentMails(String type, String senderid);
	Mail sendMail(Mail mail);
	List<Mail> findReceivedMails(String type,String rmailid);
	boolean deleteMail(List<Integer> id);
	Mail setImportant(Mail mail);
	List<Mail> findImportantMails(boolean important,String mailid);
	Mail findMail(int id);
	Mail setRead(int id);
	List<Mail> findDeltedMails(String type,String mailid);
	boolean permanentDeleteMail(List<Integer> id);
}
