package com.fastpay.jam.emailservice.service;

import com.fastpay.jam.emailservice.exception.FPGenericException;
import com.fastpay.jam.emailservice.model.EmailRequest;
import com.fastpay.jam.emailservice.model.EmailResponse;

/**
 * Backend service to send email
 */
public interface EmailService {
    public EmailResponse sendEmail(EmailRequest email) throws FPGenericException;
}
