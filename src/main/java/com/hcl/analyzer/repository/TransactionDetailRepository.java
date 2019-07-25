package com.hcl.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.analyzer.dto.MonthyStatement;
import com.hcl.analyzer.entity.CustomerDetails;
import com.hcl.analyzer.entity.TransactionDetail;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {	
	
	@Query("select new com.hcl.analyzer.entity.MonthyStatement(SUM(t.transactionAmount) , MONTH(t.transactionDate), MAX(t.availableBalance)) from TransactionDetail t where t.customerDetails=:customerId AND t.transactionType=:transactionType group by MONTH(t.transactionDate)")
	public List<MonthyStatement> findByMonthAndYear(@Param("customerId") CustomerDetails customerId, @Param("transactionType") String transactionType);
	
	@Query(value = "SELECT * FROM transaction_detail  WHERE customer_id = :customer", nativeQuery = true)
	List<TransactionDetail> findByCustomerDetails(@Param("customer") Long customer);
}
