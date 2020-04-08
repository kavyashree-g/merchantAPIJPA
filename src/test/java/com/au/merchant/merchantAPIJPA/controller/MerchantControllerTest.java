package com.au.merchant.merchantAPIJPA.controller;

import com.au.merchant.merchantAPIJPA.MerchantApijpaApplication;
import com.au.merchant.merchantAPIJPA.exception.ResourceNotFoundException;
import com.au.merchant.merchantAPIJPA.models.Merchants;
import com.au.merchant.merchantAPIJPA.repositories.MerchantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= MerchantApijpaApplication.class)
@SpringBootTest
public class MerchantControllerTest {

    @Mock
    private MerchantRepository merchantRepositoryMock;

    @Mock
    private Merchants merchantsMock;

    @InjectMocks
    private MerchantController merchantControllerMock;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createMerchantTest(){
        Mockito.when(merchantRepositoryMock.save(merchantsMock)).thenReturn(merchantsMock);
        ResponseEntity<Merchants> response =  merchantControllerMock.createMerchant(merchantsMock);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateMerchantTest() throws ResourceNotFoundException {
        Mockito.when(merchantRepositoryMock.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(merchantsMock));
        Mockito.doNothing().when(merchantsMock).setEmailAddress(Mockito.anyString());
        Mockito.doNothing().when(merchantsMock).setMerchantName(Mockito.anyString());
        Mockito.when(merchantRepositoryMock.save(merchantsMock)).thenReturn(merchantsMock);
        ResponseEntity<Merchants>  response =   merchantControllerMock.updateMerchants1(Mockito.anyInt(), merchantsMock);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test(expected = NullPointerException.class)
    public void updateMerchantTest_Error()  {
        Mockito.when(merchantRepositoryMock.findById(Mockito.anyInt())).thenThrow(new NullPointerException());
        ResponseEntity<Merchants>  response =   merchantControllerMock.updateMerchants1(Mockito.anyInt(), merchantsMock);
    }
}
