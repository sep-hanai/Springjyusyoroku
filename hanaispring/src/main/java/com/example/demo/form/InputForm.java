package com.example.demo.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class InputForm implements Serializable{
	/**
	 * id
	 */
	private long id;

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
	 * delete_flg
	 */
	private String delete_flg;

}
