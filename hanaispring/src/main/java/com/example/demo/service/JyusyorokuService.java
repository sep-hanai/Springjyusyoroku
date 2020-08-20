package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	 * Page型で全件取得
	 */
	    public Page<Jyusyoroku> getPlayers(Pageable pageable) {
	        return jyusyorokuRepository.findAllOrderById(pageable);
	    }

	/**
	 * 新規登録
	 * @parem jyusyoroku 登録情報
	 */
	public void create(Jyusyoroku jyusyoroku) {
		jyusyorokuRepository.save(jyusyoroku);
	}

	/**
	 * 編集,削除 update 処理
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
//	public Optional<Jyusyoroku> selectById(long id) {
//		return jyusyorokuRepository.findById(id);
//	}

	public Optional<Jyusyoroku> selectById(Long id) {
		return jyusyorokuRepository.findById(id);
	}
	/**
	 * 検索 serch 処理
	 */

	public Page<Jyusyoroku> selectByName(String address, Pageable pageable) {
		return jyusyorokuRepository.findByName(address, pageable);
	}

	/**
	 * エラーチェック
	 * @return errmsg
	 */
	public String[] err(String name, String address, String tel) {

		String errmsg = "";
		String errmsg2 = "";
		String errmsg3 = "";
		try {
			if (name.getBytes("Shift_JIS").length > 40) {
				errmsg = "名前は全角20文字以内で入力してください";
			}
			if (name.getBytes("Shift_JIS").length == 0) {
				errmsg = "名前は必須項目です";
			}
			if (address.getBytes("Shift_JIS").length > 80) {
				errmsg2 = "住所は全角40文字以内で入力してください";
			}
			if (address.getBytes("Shift_JIS").length == 0) {
				errmsg2 = "住所は必須項目です";
			}

			int t = tel.getBytes("Shift_JIS").length;
			if (tel == "") {
			} else if (tel.matches(".*^0[0-9]{2}-[0-9]{4}-[0-9]{4}.*") != true || 13 < t || (1 <= t && 13 > t)) {
				errmsg3 = "電話番号は「000-0000-0000」の形式で入力してください";
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//			String errAll[] = {errmsg, errmsg2, errmsg3};
		//配列を用意、エラーがあれば要素に追加
		String errAll[] = new String[3];
		if (errmsg != "") {
			errAll[0] = errmsg;
		}
		if (errmsg2 != "") {
			errAll[1] = errmsg2;
		}
		if (errmsg3 != "") {
			errAll[2] = errmsg3;
		}
		return errAll;
	}
}
