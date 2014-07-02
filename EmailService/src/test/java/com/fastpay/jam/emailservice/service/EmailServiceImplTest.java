package com.fastpay.jam.emailservice.service;

import com.fastpay.jam.emailservice.exception.FPGenericException;
import com.fastpay.jam.emailservice.model.AttachmentData;
import com.fastpay.jam.emailservice.model.EmailNameValue;
import com.fastpay.jam.emailservice.model.EmailRequest;
import com.fastpay.jam.emailservice.model.EmailResponse;
import com.fastpay.jam.emailservice.service.EmailServiceImpl;
import com.fastpay.jam.emailservice.service.helper.impl.AmazonSESEmailHelperImpl;
import com.google.common.collect.Lists;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmailServiceImplTest {
    private static final String MESSAGE_ID = "12345xyz";

    @Mock(name = "sesHelper")
    private AmazonSESEmailHelperImpl sesEmailHelperMock;

    @InjectMocks
    private EmailServiceImpl emailService;

    private EmailRequest emailRequest;
    private EmailResponse emailResponse;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        emailRequest = setupEmailRequest();
    }

    @Test
    public void testSendEmail() throws Exception {
        when(sesEmailHelperMock.send(anyListOf(EmailNameValue.class), anyListOf(EmailNameValue.class), anyListOf(EmailNameValue.class),
                any(EmailNameValue.class), anyString(), anyString(), anyListOf(AttachmentData.class))).thenReturn(MESSAGE_ID);
        emailResponse = emailService.sendEmail(emailRequest);
        Assert.assertNotNull(emailResponse);
        Assert.assertEquals(emailResponse.getMessageId(), MESSAGE_ID);
        verify(sesEmailHelperMock).send(anyListOf(EmailNameValue.class), anyListOf(EmailNameValue.class), anyListOf(EmailNameValue.class),
                any(EmailNameValue.class), anyString(), anyString(), anyListOf(AttachmentData.class));
    }

    @Test(expectedExceptions = FPGenericException.class)
    public void testSendEmailNoMessageIdError() throws Exception {
        when(sesEmailHelperMock.send(anyListOf(EmailNameValue.class), anyListOf(EmailNameValue.class), anyListOf(EmailNameValue.class),
                any(EmailNameValue.class), anyString(), anyString(), anyListOf(AttachmentData.class))).thenReturn(null);
        emailResponse = emailService.sendEmail(emailRequest);
        Assert.assertNotNull(emailResponse);
        Assert.assertEquals(emailResponse.getMessageId(), null);
        verify(sesEmailHelperMock).send(anyListOf(EmailNameValue.class), anyListOf(EmailNameValue.class), anyListOf(EmailNameValue.class),
                any(EmailNameValue.class), anyString(), anyString(), anyListOf(AttachmentData.class));
    }

    private EmailRequest setupEmailRequest() {
        emailRequest = new EmailRequest();
        EmailNameValue nameValue = new EmailNameValue();
        emailRequest.setSubject("TEST Subject");
        emailRequest.setBody("Blah Blah Blah");
        emailRequest.setFrom(nameValue);
        nameValue.setEmail("you@fastpay.com");
        nameValue.setName("you");
        emailRequest.setTo(Lists.newArrayList(nameValue));
        return emailRequest;
    }
}