package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Jyusyoroku;
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
		model.addAttribute("jyusyoroku", new Jyusyoroku());
		return "add";
	}

   /**
    * 確認画面へ遷移
    * @return 確認画面表示
    */
	@RequestMapping(value = "/addcheck", method = RequestMethod.POST)
	public String addcheck(Jyusyoroku jyusyorku) {
		return "addcheck";
	}

	/**
	 * 戻るボタン押下
	 * @return 一覧画面表示
	 */
	@RequestMapping(value="/back", method=RequestMethod.POST)
	public String back() {
				return "redirect:/";
	}

	/**
	 * 登録処理
	 * @param jyusyoroku
	 * @return 住所録一覧表示
	 */
		@RequestMapping(value="/create", method=RequestMethod.POST)
		public String create(@ModelAttribute Jyusyoroku jyusyoroku) {
			// ユーザー情報の登録
					jyusyorokuService.create(jyusyoroku);
					return "redirect:/";
		}
}
