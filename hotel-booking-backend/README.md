UI should send encrypted password over the network

have to send an email for the user entered email, when user click on that an UI should pop up
to enter new password and confirm password.
once user click on submit button it should call the below endpoint 

##################### API 1
POST
http://localhost:8088/api/v1/user/login?userLoginType=FORGET_PASSWORD_LOGIN
Header:
"Authorization":"Bearer dsfsdferGSDGSGARARDfdgDAGTADAAGR"  <-this token should include Constants.USER_ONE_TIME_AUTH_KEY

payload
{
   "userName":"David@gmail.com", <-UI can get this from invitation token set it back to backend request;(replace label in form to "Email")
   "password":"EWESADRFDSFGDSDGSDG"
}

##################### API 2
PUT
http://localhost:8088/api/v1/user/forget-password?email=abc@gmail.com


Response
200 OK
invitation email will be sent
#####################
To-Do
create end point to encrypt and decrypt a given string



####################
in future
password strength validation