//package com.au.merchant.merchantAPIJPA.controller;
//
//import com.au.merchant.merchantAPIJPA.MerchantApijpaApplication;
//import com.au.merchant.merchantAPIJPA.models.Merchants;
//import com.au.merchant.merchantAPIJPA.repositories.MerchantRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestContext;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestContext.class, MerchantApijpaApplication.class})
//@WebAppConfiguration
//public class MerchantControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private MerchantRepository merchantRepositoryMock;
//
//    @Mock
//    private Merchants merchantsMock;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @Before
//    public void setUp(){
//        this.mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
//    }
//
//    @Test
//    public void createMerchantTest() throws Exception {
//        Mockito.when(merchantRepositoryMock.save(merchantsMock)).thenReturn(merchantsMock);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/merchants").accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
//        System.out.println(result.getResponse());
//    }
//}
