package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * ユーザー情報 Entity
 */
@Entity
@Data
@Table(name="jyusyoroku2")

public class Jyusyoroku implements Serializable{
/**
 * ID
 */
@Id
@Column(name="id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

/**
 * 名前
 */
@Column(name="name")
private String name;

/**
 * 住所
 */
@Column(name="address")
private String address;

/**
 * 電話番号
 */
@Column(name="tel")
private String tel;

/**
 * delete_flg
 */
@Column(name="delete_flg")
private String delete_flg;

}
