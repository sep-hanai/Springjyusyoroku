package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Jyusyoroku;

/**
 * 住所録repository
 */
@Repository
public interface JyusyorokuRepository extends JpaRepository<Jyusyoroku, Long>{
	/**
	 * 一件取得
	 */
    @Query("SELECT j FROM Jyusyoroku j ORDER BY j.id")
    List<Jyusyoroku> findAllOrderById();
}

