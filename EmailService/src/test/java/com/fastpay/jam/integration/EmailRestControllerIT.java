package com.fastpay.jam.integration;

import com.fastpay.jam.emailservice.config.AppConfig;
import com.fastpay.jam.emailservice.config.FastPayAWSBean;
import com.fastpay.jam.emailservice.config.PropertySourcesConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.annotation.Resource;

/**
 * Integration test for EmailRestController
 */
@ContextConfiguration(classes = {AppConfig.class,FastPayAWSBean.class,PropertySourcesConfig.class})
@WebAppConfiguration
public class EmailRestControllerIT extends AbstractTestNGSpringContextTests {
    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeTest
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void sendEmailTest() {
        Assert.assertNotNull(mockMvc);
    }
}
