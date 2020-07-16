package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Jyusyoroku;

/**
 * 住所録repository
 */
@Repository
public interface JyusyorokuRepository extends JpaRepository<Jyusyoroku, Long>{

}
