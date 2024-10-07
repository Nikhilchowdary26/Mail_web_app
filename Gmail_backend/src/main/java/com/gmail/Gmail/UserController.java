package com.gmail.Gmail;

import java.io.IOException;
import java.util.List;

import com.gmail.Gmail.model.User;
import com.gmail.Gmail.service.UserService;
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
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/gmail")
public class UserController {

	@Autowired
	private UserService service;
	

//	@PostMapping("/saveuser")  //registering/saving user in database
//	public ResponseEntity<?> saveUser(@RequestParam("user") String user,@RequestParam("profilePic") MultipartFile file) throws IOException
//	{
//			User user1=new User();
//			ObjectMapper og=new ObjectMapper();
//			user1=og.readValue(user, User.class);
//			user1.setProfilePic(file.getBytes());
//			User user2 = service.saveUser(user1);
//			if(user2!=null) {
//				return ResponseEntity.status(HttpStatus.OK).body("Signup successfull");
//			}
//			else {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already registered with this emailid");
//			}
//	}
	
	@GetMapping("/signin/{mailid}/{password}")
	public ResponseEntity<?> signIn(@PathVariable String mailid,@PathVariable String password) throws IOException {
		User user=service.signIn(mailid,password);
		if(user!=null) {
			return ResponseEntity.status(HttpStatus.OK).body("Signin Successful");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credentials");
		}
	}
	
	@GetMapping("/getall")   //getting all users
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = service.getUsers();
	    if (users.size()!=0) {
	    	return ResponseEntity.status(HttpStatus.OK).body(users);
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(users);
	    }
	}
	
	//getting user with mailid
	@GetMapping("/getuser/{mailid}")  
	public ResponseEntity<?> getUser(@PathVariable String mailid) {
		User user=null;
		try {
			user=service.getUser(mailid);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		if(user!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No user found");
		}
	}
	
	@PutMapping("/updatedetails/{mailid}")//updating user details by taking mailid 
	//and information excluding image
	public ResponseEntity<String> updateUser(@PathVariable String mailid,@RequestParam("user") String user) throws IOException {
		User user1=new User();
		ObjectMapper og=new ObjectMapper();
		user1=og.readValue(user, User.class);
		User user2=service.updateUser(user1, mailid);
		if(user2!=null) {
			return ResponseEntity.status(HttpStatus.OK).body("Details updated");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect email-id entered");
		}
	}
	
	@PutMapping("/forgotpassword/{mailid}/{mobileno}")    //forgot/updating password functionality
	public ResponseEntity<String> forgotPassword(@PathVariable String mailid,@PathVariable String mobileno,@RequestParam("password") String user) throws IOException
	{
		User user1=new User();
		ObjectMapper og=new ObjectMapper();
		user1=og.readValue(user, User.class); 	
		User user2=service.forgotPassword(user1);
		if(user2!=null) {
			return ResponseEntity.status(HttpStatus.OK).body("password updated successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid mailid or mobile number");
		}
	}
	
	@DeleteMapping("/deleteuser/{mailid}")   //deleting user by taking mailid
	public void removeUser(@PathVariable String mailid) {
		service.deleteUser(mailid);
	}
	
	@PutMapping("/updateimage/{mailid}")
	public ResponseEntity<?> updateImage(@PathVariable String mailid,@RequestParam("image") MultipartFile profilePic) throws IOException {
		User user1=new User();
		user1.setProfilePic(profilePic.getBytes());
		User user=service.updateImage(mailid,user1);
		if(user!=null) {
			return ResponseEntity.status(HttpStatus.OK).body("Image updated successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image  not updated successfully");
		}
	}
	
	@PutMapping("/changepassword/{mailid}/{password}")
	public ResponseEntity<?> changePassword(@PathVariable String mailid,@PathVariable String password) {
		User user=service.changePassword(mailid, password);
		if(user!=null) {
			return ResponseEntity.status(HttpStatus.OK).body("Password changed successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Password not changed");
		}
	}
}
