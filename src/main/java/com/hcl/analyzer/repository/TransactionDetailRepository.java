package com.hcl.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.analyzer.entity.TransactionDetail;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {

	@Query(value = "SELECT * FROM transaction_detail  WHERE customer_id = :customer", nativeQuery = true)
	List<TransactionDetail> findByCustomerDetails(@Param("customer") Long customer);
	
	@Query(value = "select * from transaction_detail where customer_id = :customerId AND MONTH(transaction_date) = :month AND YEAR(transaction_date) =:year",nativeQuery = true)
	public List<TransactionDetail> findByMonthAndYear(@Param("customerId") Long customerId, @Param("month") String month, @Param("year") String year);
}
