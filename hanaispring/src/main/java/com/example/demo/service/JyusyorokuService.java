package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Jyusyoroku;
import com.example.demo.repository.JyusyorokuRepository;

/**
 * 住所録一覧 Service
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class JyusyorokuService {
/**
 * 住所録Repository
 */
 @Autowired
 JyusyorokuRepository jyusyorokuRepository;

 /**
  * 全件検索
  * @return 検索結果
  */
 public List<Jyusyoroku> searchAll(){
	 return jyusyorokuRepository.findAll();
 }


	/**
	 * 新規登録
	 * @parem jyusyoroku 登録情報
	 */
	public void create(Jyusyoroku jyusyoroku) {
		jyusyorokuRepository.save(CreateUser(jyusyoroku));
	}

 /**
  * 新規登録
  * @param jyusyo
  * @return entity
  */
	private Jyusyoroku CreateUser(Jyusyoroku jyusyoroku) {

		Jyusyoroku user = new Jyusyoroku();
		user.setName(jyusyoroku.getName());
		user.setAddress(jyusyoroku.getAddress());
		user.setTel(jyusyoroku.getTel());
		user.setDelete_flg(jyusyoroku.getDelete_flg());
		return user;
 }
}
