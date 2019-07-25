package com.hcl.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.analyzer.entity.CustomerDetails;
import com.hcl.analyzer.entity.MonthyStatement;
import com.hcl.analyzer.entity.TransactionDetail;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {

	/*
	 * @Query(value =
	 * "select * from transaction_detail where customer_id = :customerId AND MONTH(transaction_date) = :month AND YEAR(transaction_date) =:year"
	 * , nativeQuery = true) public List<TransactionDetail>
	 * findByMonthAndYear(@Param("customerId") Long customerId,
	 * 
	 * @Param("month") String month, @Param("year") String year);
	 */

	
	
	@Query("select new com.hcl.analyzer.entity.MonthyStatement(SUM(t.transactionAmount) , MONTH(t.transactionDate), MAX(t.availableBalance)) from TransactionDetail t where t.customerDetails=:customerId AND t.transactionType=:transactionType group by MONTH(t.transactionDate)")
	public List<MonthyStatement> findByMonthAndYear(@Param("customerId") CustomerDetails customerId, @Param("transactionType") String transactionType);
	/*
	 * @Query("SELECT " +
	 * "    new com.path.to.SurveyAnswerStatistics(v.answer, COUNT(v)) " + "FROM " +
	 * "    Survey v " + "GROUP BY " + "    v.answer")
	 */
	
	/*
	 * @Query("SELECT new com.hcl.analyzer.dto.MonthlyStatementDto() ") public
	 * List<MonthlyStatementDto> findByMonthAndYear2(@Param("customerId")
	 * CustomerDetails customerId, @Param("transactionType") String
	 * transactionType);
	 * 
	 * SELECT DATE_FORMAT(date, "%m-%Y") Month,customer_id, SUM(COALESCE(CASE WHEN
	 * payment_type = 'credit' THEN amount END,0)) total_credits, SUM(COALESCE(CASE
	 * WHEN payment_type = 'debit' THEN amount END,0)) total_debits FROM
	 * ingfinance.transaction where customer_id = 1 GROUP BY DATE_FORMAT(date,
	 * "%m-%Y");
	 */  
}
