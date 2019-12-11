package com.abc.springsession.exception;

import static com.abc.springsession.utils.EmployeeUtil.getDateTime;

public enum Message {
    EMAIL_NOT_SENT("Notification email not sent!"),
    INVALID_LOGIN_CREDS("Invalid login credentials!"),
    EMAIL_SUBJECT("Login status"),
    EMAIL_TEXT("You have successfully login to my Employee App at: "+ getDateTime()),
    EMAIL_SENT("Mail Sent"),
    SUCCESS("Successful login. Status sent to login email!"),
    UNAUTHORIZED_ERROR("You are not authorize. Please login by sending a request to /login with email and password"),
    SESSION_KEY("admin"),
    LOGOUT("You have successfully logged out!"),
    RESOURCE_NOT_FOUND("Resource not found!");

    private String message;

    Message(String message){
        this.message = message;
    }

    public String getValue(){
        return this.message;
    }
}
