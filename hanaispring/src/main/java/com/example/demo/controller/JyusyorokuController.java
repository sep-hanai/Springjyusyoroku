package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.service.JyusyorokuService;

@Controller
public class JyusyorokuController {

	@Autowired
	JyusyorokuService jyusyorokuService;

	/**
	 * 住所録一覧表示
	 * @param model
	 * @return 住所録一覧表示
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String helloWorld(Model model) {

		List<Jyusyoroku> jyusyolist = jyusyorokuService.searchAll();

		model.addAttribute("jyusyolist", jyusyolist);
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
	public String addcheck(@ModelAttribute("inputForm")InputForm form) {
		return "addcheck";
	}

	/**
	 * 登録処理
	 * @param inputForm
	 * @return 一覧画面表示
	 */
//	@RequestMapping(value="/create", method=RequestMethod.POST)
//	public String create(@ModelAttribute InputForm inputForm, Model model) {
//		// ユーザー情報の登録
//				jyusyorokuService.create(inputForm);
//				return "redirect:/";
//		}
	 @PostMapping("create")
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
		BeanUtils.copyProperties(jyusyoroku, inputForm);
        return "edit";
	}

	   /**
	    * 確認画面へ遷移
	    * @return 確認画面表示
	    */
		@RequestMapping(value = "/editcheck", method = RequestMethod.POST)
		public String editcheck(@ModelAttribute("inputForm")InputForm form) {
			return "editcheck";
		}

	/**
	 * 編集機能実行
	 */
	@PostMapping(path = "update", params = "regist")
	String regist(@RequestParam Long id, @Validated @ModelAttribute InputForm inputForm, BindingResult result) {
//        if (result.hasErrors()) {
//            return edit(id, inputForm);
//        }

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
		BeanUtils.copyProperties(jyusyoroku, inputForm);
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
	@RequestMapping(value="/back", method=RequestMethod.POST)
	public String back() {
				return "redirect:/";
	}
}
