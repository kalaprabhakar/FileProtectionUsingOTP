# FileProtectionUsingOTP
- This is the project on file protection using OTP.-
-  The User can encript  their important Files by using OTP which link with Email id.

# Technologies
- Java OOPS
- Java jdbc
- Java Mail System
- MySql
- Maven

# Tables on DataBase
-> For User
-------------
* user table

 Field | Type         | Null | Key | Default | Extra          
 ------|--------------|------|-----|---------|-------
 id    | int          | NO   | PRI | NULL    | auto_increment 
 name  | varchar(100) | YES  |     | NULL    |                
 email | varchar(100) | YES  | UNI | NULL    |                


-> For Data
---------------
* data table
  
 Field    | Type         | Null | Key | Default | Extra          
----------|--------------|------|-----|---------|----------------
 id       | int          | NO   | PRI | NULL    | auto_increment 
 name     | varchar(100) | YES  |     | NULL    |                
 path     | varchar(255) | YES  |     | NULL    |                
 email    | varchar(100) | YES  |     | NULL    |                
 bin_data | blob         | YES  |     | NULL    |                


