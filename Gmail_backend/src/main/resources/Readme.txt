==============================================================================================================
                                    Project: Mail Web app
==============================================================================================================
------------------------------------------------------------------------
Table of Contents
------------------------------------------------------------------------
1. Introduction
2. Tech Stack
3. Key Functionalities
4. Validations
5. Database Design
6. Getting Started
8. Running the Application
-----------------------------------------------------------------------
                          1. Introduction
-----------------------------------------------------------------------

This project aims to create a Gmail-like web application, 
complete with user authentication, email sending and receiving, 
profile management, and more. This README provides an overview 
of the project and instructions on how to set it up and run it locally.
-----------------------------------------------------------------------
                          2. Tech Stack
-----------------------------------------------------------------------
Pre-requisties :
	-node js : install node js and npm to run the frontend
	-postgreSQL database : To store the data
	-JDK : install JDK 8 or higher
	-Eclipse : Tool for writing java code
	-visual studio code : Tool for writing front end code.
Languages/Tools used:
  1.Frontend     : Vue js(version 3)
  2.Backend      : Java(version 8 or higher)
  		-Spring boot(version 3.1.3)
  		Dependencies:
  			-Spring boot starter data jpa
			-Spring boot starter web
			-postgres driver
  3.Database     : PostgreSQL(version 15)
  Tools:
		- Eclipse IDE
		- Visual Studio Code
		- Postman
		- Maven
		- Tomcat
		- npm
------------------------------------------------------------------------
                     3. Key Functionalities
------------------------------------------------------------------------

a)SignUp and SignIn :
   	- Users can sign up for an account.
   	- Existing users can sign in with their username/mailid and password.

b)Forgot Password :
   	- Users can reset their password if password was forgotten.

c)Profile Section :
   	- Users have a profile section with options to change their password, 
      update their profile picture, and sign out.

d)Email Functionality :
	  - Users can send mails to single or multiple recipients.
	  - Receive failure notifications for non-existent email recipients.
	  - View received emails in their inbox.
	  - Unread emails are displayed in bold.
	  - Able to delete multiple/single mails from sent/inbox components.
	  - Deleted mails can be seen in bin folder.
	  - Able to delete mails permanently from bin.
-------------------------------------------------------------------------
                             4. Validations
-------------------------------------------------------------------------

a)SignUp Validation :
   	- Checks email availability while entering data.
   	- Green text indicates the email is available.
   	- Red text indicates the email is taken.
   	- Proper email format validation.
   	- Validation for all other signup fields.

b)SignIn Validation :
   	- Validation for all signin fields.

c)Change Password Validation :
   	- Validation for all fields in the change password form.
-------------------------------------------------------------------------
                           5. Database Design
-------------------------------------------------------------------------
   1.In database the postgres will give default GUI(Graphic user interface) named pgAdmin.
   2.In database i created 2 tables one for storing users and another for storing mails
   Relation :
     1.One user can send and receive multiple mails.So One To Many relation one user many mails.
     2.Multiple mails can sent and received by the one user.So Many To One relation many mails one user.
   3.To establish connection between two tables in mails table foreign key named user_id maintain connection.
   4.The users mailid is stored in mails table foreign key.
   Users Table:
     1.mailid(Primary key):User's mailid
     2.firstName:first name of the user
     3.lastName:last name of the user
     4.gender:Gender of the user
     5.mobileNo:User's mobile number
     6.profilePic:Users profile pic
     7.password:user's account password.
   Mail Table:
     1.id(primary Key):Auto generated.
     2.senderId:store the sender's id of the mail.
     3.receiversId:store the receiver's ids of the mail.
     4.date:Takes the systems local date.
     5.time:Takes the systems local time.
     6.isRead:It is a boolean value.
     7.isImportant:It is a boolean value.
     8.subject:store the subject of the mail.
     9.message:store the body of the mail.
     10.type:string it is one of the key value in the application.
     11.user_id(Foreign key):storesthe user primary key.
   5.The profilePic data type is oid stores the profilepic in bytes.
   This is all about the database further is spring boot functionalities
-------------------------------------------------------------------------
                            6. Getting Started
-------------------------------------------------------------------------

	To get started with this project, follow these steps:
		1.Open the git hub repository https://github.com/buildbottech/Nikhil-Sai-Mandava-Work.git
			and copy the code and paste it in the respective IDE files.
		1.Start the spring boot server from your eclipse IDE.
		2.Start the front end (vue js) server by running vue js 2 - npm run dev
		  vue js 3 - npm run serve from command line interface.
		3.After running the command it will generate the link for your project.Paste the
		  link in chrome and go on using.