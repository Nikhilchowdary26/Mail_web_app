package com.gmail.Gmail.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//users table entity class
@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	private String mailid;
	private String firstName;
	private String lastName;
	private String gender;
	@Lob
	private byte[] profilePic;
	private String mobileNo;
	private String password;
	//setting reference of mails table to here and the name of join column display in mails table with present user
	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
//	@JsonIgnore
//	@JoinColumn(name = "user_id",referencedColumnName = "mailid")
	@JsonIgnoreProperties
	private List<Mail> mails;
	
	//getters and setters
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public byte[] getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Mail> getMails() {
		return mails;
	}
	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return mailid;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	//to string
	@Override
	public String toString() {
		return "User [mailid=" + mailid + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", profilePic=" + Arrays.toString(profilePic) + ", mobileNo=" + mobileNo + ", password=" + password
				+ ", mails=" + mails + "]";
	}
}
