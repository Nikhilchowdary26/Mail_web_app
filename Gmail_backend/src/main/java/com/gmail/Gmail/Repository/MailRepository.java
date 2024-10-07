package com.gmail.Gmail.Repository;

import java.util.List;

import com.gmail.Gmail.model.Mail;
import com.gmail.Gmail.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Integer>{
	List<Mail> findByTypeAndUser(String type, User user);
	List<Mail> findByIsImportantAndUser(boolean isimportant,User user);
}