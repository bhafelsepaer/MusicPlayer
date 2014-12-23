MusicPlayer Project
===========

I usage in this project technologies: 

Spring MVC
Spring Security
JPA
Hibernate
MySQL
HSQLDB
javascript(jQuery)
CSS
JSP
Junit

This Project demonstrates simple database authorization  where password stored in databases is hashes bcrypt, where password
is encoded/decoded by Spring Security.

We can register new user who will be added to database but it will be not enabled, when we registration new user we can
choice two option - active user by email or sms.
sms - no algorithm, user automatically enabled.
email - we must set in the file emailConfiguration.xml correct email and password to hosted Gmail account,who will be send
the active link to user email.User active account by this link.

In Registration form all field is validated.

In the userProfile we can change password,email,information about us, to change email, password we must provide password.

We can view song by Genres, Album, Artist.

We can play song when we click to link.

We can view,add,update,delete playlist - add song to playlist not implement yet.

All Controller,Service,Dao Layer Are Tested Junit.

Script mysql is in folder db/script.








