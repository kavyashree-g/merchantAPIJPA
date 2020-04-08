package com.au.merchant.merchantAPIJPA.repositories;

import com.au.merchant.merchantAPIJPA.models.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchants,Integer> {

   List<Merchants> findByMerchantName(String merchantName);

    Merchants findByMerchantNameAndEmailAddress(String merchantName,String emailAddress);
}
