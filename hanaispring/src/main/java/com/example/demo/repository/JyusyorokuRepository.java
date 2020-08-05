package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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
	 * 一件取得
	 */
	@Query("SELECT j FROM Jyusyoroku j WHERE j.delete_flg=0 ORDER BY j.id")
	List<Jyusyoroku> findAllOrderById();

	@Query("SELECT j FROM Jyusyoroku j WHERE j.delete_flg=0 AND j.address LIKE %:address% ORDER BY j.id")
	//Optional<Jyusyoroku> findByName(@Param("address") String address);
	List<Jyusyoroku> findByName(String address);

	@Query(value = "SELECT * FROM Jyusyoroku WHERE delete_flg=0 AND address LIKE %:address% ORDER BY id" , nativeQuery  = true)
	//Optional<Jyusyoroku> findByName(@Param("address") String address);
	List<Jyusyoroku> findByName2(@Param("address") String address);

}