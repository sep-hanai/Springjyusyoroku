package com.example.demo.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class InputForm implements Serializable{
	/**
	 * name
	 */
	private String name;

	/**
	 * address
	 */
	private String address;

	/**
	 * tel
	 */
	private String tel;

	/**
	 * categoryid
	 */
	private String categoryid;

	/**
	 * delete_flg
	 */
	private String delete_flg;

}
