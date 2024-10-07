package com.gmail.Gmail.Repository;

import com.gmail.Gmail.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User findByMailidAndPassword(String mailid,String password);
	User findByMailidAndMobileNo(String mailid,String mobileNo);
}
