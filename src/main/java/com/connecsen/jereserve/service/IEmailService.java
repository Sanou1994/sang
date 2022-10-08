package com.connecsen.jereserve.service;
import javax.mail.MessagingException;

import com.connecsen.jereserve.domaine.Login;
import com.connecsen.jereserve.domaine.MailSend;

public interface IEmailService {
public boolean sendContactEmail(MailSend mail);
public boolean sendEmailToActivateAccount(String nom,String email);
public boolean sendMailUpdatePassword(Login login,String resetPasswordId) throws MessagingException;
public boolean confirmedMessageAccountCreatedSuccess(Login login) throws MessagingException;

}
