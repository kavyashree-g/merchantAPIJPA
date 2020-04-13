package com.au.merchant.merchantAPIJPA.controller;

import com.au.merchant.merchantAPIJPA.models.Merchants;
import com.au.merchant.merchantAPIJPA.repositories.MerchantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MerchantControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MerchantRepository merchantRepositoryMock;

    @Mock
    private Merchants merchantsMock;

    @Mock
    private List<Merchants> merchantsList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void healthCheckTest() throws Exception {
        this.mockMvc.perform(get("/v1/healthCheck")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void getMerchantByNameTest() throws Exception {
        Mockito.when(merchantRepositoryMock.findByMerchantName(Mockito.any())).thenReturn(merchantsList);
        this.mockMvc.perform(get("/v1/merchants/byName/{merchantName}", "Harsha")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void createMerchant_IntegrationTest() throws Exception {
     Mockito.when(merchantRepositoryMock.save(Mockito.any())).thenReturn(merchantsMock);
       Merchants merchants= new Merchants(9,"DJ","DJ@cba.com");
       ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(merchants);
        this.mockMvc.perform(post("/v1/merchants")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void getMerchantByIdTest() throws Exception {
        Mockito.when(merchantRepositoryMock.findById(Mockito.any())).thenReturn(java.util.Optional.of(merchantsMock));
        this.mockMvc.perform(get("/v1/merchants/{merchantId}", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMerchantsTest() throws Exception {

        Mockito.when(merchantRepositoryMock.findById(Mockito.any())).thenReturn(java.util.Optional.of(merchantsMock));
        Mockito.doNothing().when(merchantsMock).setEmailAddress(Mockito.anyString());
        Mockito.doNothing().when(merchantsMock).setMerchantName(Mockito.anyString());
        Mockito.when(merchantRepositoryMock.save(merchantsMock)).thenReturn(merchantsMock);

        Merchants merchants = new Merchants(2, "Harsha Kodandaramaiah", "krHarsha@cba.com");
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(merchants);
        this.mockMvc.perform(put("/v1/merchants/{id}", "2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andExpect(status().isOk());


    }
    @Test
    public void updateMerchants_IntegrationTest() throws Exception {

        Merchants merchants = new Merchants(2, "Harsha Kodandaramaiah", "krHarsha@cba.com");
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(merchants);

        this.mockMvc.perform(put("/v1/merchants/{id}", "2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andExpect(status().isOk());
    }
}
