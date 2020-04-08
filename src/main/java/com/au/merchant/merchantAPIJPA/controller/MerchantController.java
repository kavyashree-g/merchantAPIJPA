package com.au.merchant.merchantAPIJPA.controller;

import com.au.merchant.merchantAPIJPA.exception.ResourceNotFoundException;
import com.au.merchant.merchantAPIJPA.models.Merchants;
import com.au.merchant.merchantAPIJPA.repositories.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class MerchantController {

    @Autowired
    MerchantRepository merchantRepository;

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "1";
    }

    @GetMapping("/merchants")
    public List<Merchants> getMerchants(){
        return merchantRepository.findAll();
    }

    @GetMapping("/merchants/byName/{merchantName}")
    public List<Merchants> getMerchantsByName(@PathVariable String merchantName){
        return merchantRepository.findByMerchantName(merchantName);
    }

    @GetMapping("/merchants/{merchantId}")
    public ResponseEntity<Merchants> getMerchantById(@PathVariable Integer merchantId) throws ResourceNotFoundException {
        Merchants merchants=null;
            merchants = merchantRepository.findById(merchantId).orElseThrow(() ->
                    new ResourceNotFoundException("Merchant not found for this ID :: " + merchantId));
            return ResponseEntity.ok().body(merchants);
    }

    @GetMapping("/merchants/byEmail/{merchantName},{emailAddress}/")
    public ResponseEntity<Merchants> getMerchantsByNameAndEmail(@PathVariable String merchantName,
                                                      @PathVariable String emailAddress) throws ResourceNotFoundException {
        Merchants merchants= null;
            merchants = merchantRepository.findByMerchantNameAndEmailAddress(merchantName, emailAddress);
            if (null != merchants) {
                return ResponseEntity.ok(merchants);
            }
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/merchants")
    public ResponseEntity<Merchants> createMerchant(@Valid @RequestBody Merchants merchants){
        Merchants merchants1 = merchantRepository.save(merchants);
        return ResponseEntity.ok().body(merchants1);
    }

    @PutMapping("/merchants/{id}")
    public ResponseEntity<Merchants> updateMerchants(@PathVariable Integer id,
                                                     @Valid @RequestBody Merchants merchants) throws ResourceNotFoundException {
        Merchants merchants1 =merchantRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Merchant id is not found for id : "+id));
        merchants1.setEmailAddress(merchants.getEmailAddress());
        merchants1.setMerchantName(merchants.getMerchantName());
        Merchants updatedMerchants = merchantRepository.save(merchants1);
        return ResponseEntity.ok().body(updatedMerchants);
    }

    @DeleteMapping("/merchants/{id}")
    public Map<String,Boolean> deleteMerchants(@PathVariable Integer id) throws ResourceNotFoundException {
        Merchants merchants = merchantRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Merchant Id not found for Id : "+id));
        merchantRepository.delete(merchants);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", Boolean.TRUE);
        return  result;
    }

    @PutMapping("/merchants/testing/{id}")
    public ResponseEntity<Merchants> updateMerchants1(@PathVariable Integer id,
                                                     @Valid @RequestBody Merchants merchants) {
        Merchants merchants1 =merchantRepository.findById(id).orElseThrow(()-> new NullPointerException());
        merchants1.setEmailAddress(merchants.getEmailAddress());
        merchants1.setMerchantName(merchants.getMerchantName());
        Merchants updatedMerchants = merchantRepository.save(merchants1);
        return ResponseEntity.ok().body(updatedMerchants);
    }
}
