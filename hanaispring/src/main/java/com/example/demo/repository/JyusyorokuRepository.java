package com.example.demo.repository;

import java.util.List;

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
	public Page<Jyusyoroku> findAll(Pageable pageable);

	/**
	 * 全件取得
	 */
	@Query("SELECT j FROM Jyusyoroku j WHERE j.delete_flg=0 ORDER BY j.id")
	List<Jyusyoroku> findAllOrderById();

//	@Query("SELECT j FROM Jyusyoroku j WHERE j.delete_flg=0 AND j.address LIKE %:address% ORDER BY j.id")
//	List<Jyusyoroku> findByName(@Param("address") String address);

//	@Query("SELECT j FROM Jyusyoroku j WHERE j.delete_flg=0 AND j.address LIKE %:address% ORDER BY j.id")
//	Page<InputForm> findByName(@Param("address") String address, Pageable pageble);


	@Query(value = "SELECT * FROM Jyusyoroku WHERE delete_flg=0 AND address LIKE %:address% ORDER BY id" , nativeQuery  = true)
	Page<Jyusyoroku> findByName(@Param("address") String address, Pageable pageable);

}