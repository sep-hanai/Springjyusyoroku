package com.example.demo.service;

import java.util.List;
import java.util.Optional;

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
	 return jyusyorokuRepository.findAllOrderById();
 }


	/**
	 * 新規登録
	 * @parem jyusyoroku 登録情報
	 */
	public void create(Jyusyoroku jyusyoroku) {
		jyusyorokuRepository.save(jyusyoroku);
	}

 /**
  * 新規登録
  * @param jyusyo
  * @return entity
  */
//	private Jyusyoroku CreateUser(InputForm inputForm) {
//
//		Jyusyoroku user = new Jyusyoroku();
//		user.setName(inputForm.getName());
//		user.setAddress(inputForm.getAddress());
//		user.setTel(inputForm.getTel());
//		user.setDelete_flg(inputForm.getDelete_flg());
//		return user;
// }

	/**
	 * 編集 update 処理
	 * @param jyusyoroku
	 */
    public void update(Jyusyoroku jyusyoroku) {
        jyusyorokuRepository.save(jyusyoroku);
    }

    /**
     * 一件取得
     * @param id
     * @return
     */
	public Optional<Jyusyoroku> selectById(long id) {
        return jyusyorokuRepository.findById(id);}
}
