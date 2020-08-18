package com.example.demo.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Jyusyoroku;

/**
 * 住所録repository
 */
@Repository
public interface JyusyorokuRepository extends JpaRepository<Jyusyoroku, Long> {
	/**
	 * Page型で全件取得
	 */
	@Query("SELECT j FROM Jyusyoroku j WHERE j.delete_flg=0 ORDER BY j.id")
	Page<Jyusyoroku> findAllOrderById(Pageable pageable);


	@Query(value = "SELECT * FROM Jyusyoroku2 WHERE delete_flg=0 AND address LIKE %:address% ORDER BY id" , nativeQuery  = true)
	Page<Jyusyoroku> findByName(@Param("address") String address, Pageable pageable);

}