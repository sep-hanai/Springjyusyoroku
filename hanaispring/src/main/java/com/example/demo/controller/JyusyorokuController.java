package com.example.demo.controller;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Jyusyoroku;
import com.example.demo.form.InputForm;
import com.example.demo.pageWrapper.PageWrapper;
import com.example.demo.service.JyusyorokuService;

@Controller
public class JyusyorokuController {

	@Autowired
	JyusyorokuService jyusyorokuService;

	/**
	 * 住所録一覧表示
	 * ページングあり
	 * @param pageable
	 * @param model
	 * @return 一覧画面
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)

	public String index(@PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {
		 Page<Jyusyoroku> playerPage = jyusyorokuService.getPlayers(pageable);
		 PageWrapper<Jyusyoroku> page = new PageWrapper<Jyusyoroku>(playerPage, "/index");
		 model.addAttribute("page", page);
		 model.addAttribute("jyusyolist", playerPage.getContent());
        model.addAttribute("inputForm", new InputForm());

        return "index";
    }

	/**
	 * ページ選択時
	 */
	@RequestMapping(value="/index", method=RequestMethod.GET)

	public String paging(@PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {

		 Page<Jyusyoroku> playerPage = jyusyorokuService.getPlayers(pageable);
		 PageWrapper<Jyusyoroku> page = new PageWrapper<Jyusyoroku>(playerPage, "/index");
		 model.addAttribute("page", page);
		 model.addAttribute("jyusyolist", playerPage.getContent());
         model.addAttribute("inputForm", new InputForm());

       return "index";
   }

	/**
	 * 新規登録画面へ遷移
	 * @return 登録画面表示
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Model model) {
		model.addAttribute("inputForm", new InputForm());
		return "add";
	}

	/**
	* 確認画面へ遷移
	* @return 確認画面表示
	*/
	@RequestMapping(value = "/addcheck", method = RequestMethod.POST)
	public String addcheck(@ModelAttribute("inputForm") InputForm form, HttpServletRequest request, Model model) {
		//値の受け取り
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
			//serviceからメソッド呼び出し
				JyusyorokuService svc = new JyusyorokuService();
				String errAll[];
				errAll = svc.err(name, address, tel);
			//エラー判定
			//エラー有り
				if (errAll [0] != null || errAll [1] != null || errAll [2] != null) {
				model.addAttribute("errAll", errAll);
				return "add";
				}else {
			//エラー無し
		return "addcheck";
	}}

	/**
	 * 登録処理
	 * @param inputForm
	 * @return 一覧画面表示
	 */
	@PostMapping("/create")
	String regist(@ModelAttribute InputForm inputForm) {
		Jyusyoroku jyusyoroku = new Jyusyoroku();
		BeanUtils.copyProperties(inputForm, jyusyoroku);
		jyusyorokuService.create(jyusyoroku);
		return "redirect:/";
	}

	/**
	 *編集画面へ遷移
	 *@raram jyusyoroku
	 *@return 編集画面表示
	 */
	@PostMapping(path = "edit", params = "edit")
	String edit(@RequestParam Long id, @ModelAttribute InputForm inputForm) {
		Optional<Jyusyoroku> jyusyoOpt = jyusyorokuService.selectById(id);
		Jyusyoroku jyusyoroku = jyusyoOpt.get();
	//Formにコピー
		BeanUtils.copyProperties(jyusyoroku, inputForm);
	//Telを抜き出してハイフンを入れる
		String tel1 = inputForm.getTel();
		String tel = "";
		if (tel1.length() == 11) {
			Pattern p = Pattern.compile("(\\d{3})(\\d{4})(\\d{4})");
			Matcher m = p.matcher(tel1);
			tel = m.replaceAll("$1-$2-$3");
		}
	//ハイフン入りのものをFormに返す
		inputForm.setTel(tel);
		return "edit";
	}

	/**
	 * 確認画面へ遷移
	 * @return 確認画面表示
	 */
	@RequestMapping(value = "/editcheck", method = RequestMethod.POST)
	public String editcheck(@ModelAttribute("inputForm") InputForm form, HttpServletRequest request, Model model) {
	//値の受け取り
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
	//serviceからメソッド呼び出し
		JyusyorokuService svc = new JyusyorokuService();
		String errAll[];
		errAll = svc.err(name, address, tel);
	//エラー判定
	//エラー有り
		if (errAll [0] != null || errAll [1] != null || errAll [2] != null) {
		model.addAttribute("errAll", errAll);
		return "edit";
		}else {
	//エラー無し
		return "editcheck";
	}}

	/**
	 * 編集機能実行
	 */
	@PostMapping(path = "update", params = "regist")
	String regist(@RequestParam Long id, @ModelAttribute InputForm inputForm) {
		/**
		 * 編集 update 処理
		 * 一覧画面へ遷移
		 */
		Jyusyoroku jyusyoroku = new Jyusyoroku();
		BeanUtils.copyProperties(inputForm, jyusyoroku);
		jyusyorokuService.update(jyusyoroku);
		return "redirect:/";
	}

	/**
	 * 削除確認画面へ遷移
	 *@raram jyusyoroku
	 *@return 削除確認画面表示
	 */
	@PostMapping(path = "delete", params = "delete")
	String delete(@RequestParam Long id, @ModelAttribute InputForm inputForm) {
		Optional<Jyusyoroku> jyusyoOpt = jyusyorokuService.selectById(id);
		Jyusyoroku jyusyoroku = jyusyoOpt.get();
    //Formにコピー
		BeanUtils.copyProperties(jyusyoroku, inputForm);
	//Telを抜き出してハイフンを入れる
		String tel1 = inputForm.getTel();
		String tel = "";
		if (tel1.length() == 11) {
			Pattern p = Pattern.compile("(\\d{3})(\\d{4})(\\d{4})");
			Matcher m = p.matcher(tel1);
			tel = m.replaceAll("$1-$2-$3");
		}
	//ハイフン入りのものをFormに返す
		inputForm.setTel(tel);
		return "delete";
	}

	/**
	 * 削除機能実行
	 * 物理的削除
	 */
	@PostMapping(path = "update", params = "delete")
	String delete(@RequestParam Long id, @Validated @ModelAttribute InputForm inputForm, BindingResult result) {

		/**
		 * 削除 update 処理
		 * 一覧画面へ遷移
		 */
		Jyusyoroku jyusyoroku = new Jyusyoroku();
		BeanUtils.copyProperties(inputForm, jyusyoroku);
		jyusyorokuService.update(jyusyoroku);
		return "redirect:/";
	}

	/**
	 * 戻るボタン押下
	 * @return 一覧画面表示
	 */
	@RequestMapping(value = "/back", method = RequestMethod.POST)
	public String back() {
		return "redirect:/";
	}

	/**
	 * 検索機能
	 * 検索結果
	 * @return 一覧画面表示
	 */
	@RequestMapping(value = "/serch", method = RequestMethod.POST)
	String serch(@PageableDefault(page = 0, size = 10) Pageable pageable, InputForm inputForm, HttpServletRequest request, Model model, @RequestParam String address) {
//検索機能
		//ブランクで検索時の処理
		if(address == "") {
			 Page<Jyusyoroku> playerPage = jyusyorokuService.getPlayers(pageable);
			 PageWrapper<Jyusyoroku> page = new PageWrapper<Jyusyoroku>(playerPage, "/index");
			 model.addAttribute("page", page);
			 model.addAttribute("jyusyolist", playerPage.getContent());
	        model.addAttribute("inputForm", new InputForm());

	        return "index";
	    }else {
	    //address入力検索時の処理
		Page<Jyusyoroku> playerPage = jyusyorokuService.selectByName(address, pageable);
		PageWrapper<Jyusyoroku> page = new PageWrapper<Jyusyoroku>(playerPage, "/index");
		model.addAttribute("page", page);
		model.addAttribute("jyusyolist", playerPage.getContent());

		return "index";
	}
	}

}
