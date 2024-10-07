package com.gmail.Gmail.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.gmail.Gmail.Repository.UserRepository;
import com.gmail.Gmail.model.User;
import com.gmail.Gmail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository repo;
	
//	 public String convertImageBytesToBase64(byte[] data) {       //this is for changing the bytes to base 64
//	        return Base64.getEncoder().encodeToString(data);
//	    }
	
//	@Override     //to add user into database
//	public User saveUser(User user) {
//		User user1=null;
//		try {
//			user1=repo.findById(user.getMailid()).get();
//		}
//		catch(Exception e) {
//		}
//		if(user1==null) {
//			User user2 = repo.save(user);
//			return user2;
//		}
//		else {
//			return null;
//		}
//	}

	@Override   //to get all users
	public List<User> getUsers() {
		List<User> users=new ArrayList<>();
		try {
		users = repo.findAll();
	    } 
		catch (Exception e) { 
	    }
		if(users.size()!=0) {
	        return users;
		 }
		 else {
			 return null; 
		 }
	}

	@Override
	public User getUser(String mailid) {
		User user1=null;
		try {
			user1=repo.findById(mailid).get();			
		}
		catch (Exception e) {
		}
		return user1;
	}

	@Override
	public User updateUser(User user, String mailid) {
		User user1=null;
		try {
			user1=repo.findById(mailid).get();
		}
		catch(Exception e) {
			
		}
		if(user1==null) {
			return null;
		}
		else {
			if(user.getFirstName()!=null) {
				user1.setFirstName(user.getFirstName());
			}
			if(user.getLastName()!=null) {
				user1.setLastName(user.getLastName());
			}
			if(user.getGender()!=null) {
				user1.setGender(user.getGender());
			}
			if(user.getMobileNo()!=null) {
				user1.setMobileNo(user.getMobileNo());
			}
			User user2=repo.save(user1);
			return user2;
		}
	}

	@Override
	public void deleteUser(String mailid) {
		repo.deleteById(mailid);
	}

	@Override                                    //for signin operation
	public User signIn(String mailid,String password) {
		User user1=new User();
		try {
			user1=repo.findByMailidAndPassword(mailid, password);
		}
		catch(Exception e) {
		}
		return user1;
	}

	@Override   //for forgot password operation
	public User forgotPassword(User user) {
		User user1=null;
		try {
			String mailid=user.getMailid();
			String mobileno=user.getMobileNo();
			user1=repo.findById(mailid).get();
			if(user1!=null) {
				if(user1.getMobileNo()!=mobileno) {
					
				}
			}
		}
		catch(Exception e) {
		}
		if(user1!=null) {
			user1.setPassword(user.getPassword());
			System.out.println(user1);
			User user2=repo.save(user1);
			return user2;
		}
		else {
			return null;
		}
	}

	@Override       //This is for updating image only
	public User updateImage(String mailid,User user) {
		User user1=null;
		try {
			user1=repo.findById(mailid).get();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		if(user1!=null) {
			user1.setProfilePic(user.getProfilePic());
			User user2=repo.save(user1);
			return user2;
		}
		else {
			return null;
		}
	}

	@Override
	public User changePassword(String mailid, String password) {
		User user1=null;
		User user=null;
		try {
			user=repo.findById(mailid).get();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		if(user!=null) {
			user.setPassword(password);
			user1=repo.save(user);
		}
		return user1;
	}
}