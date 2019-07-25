package com.hcl.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.analyzer.entity.CustomerDetails;


@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails,Long >{

}
