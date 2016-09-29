package com.example.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The persistent class for the users database table.<br>
 * Spring Security 用に {@link UserDetails} を実装する
 * 
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USERS_ID_GENERATOR", sequenceName = "USERS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_ID_GENERATOR")
	private Integer id;

	@Column(length = 255, nullable = false)
	private String account;

	@Column(length = 255, nullable = false)
	private String fullname;

	@Column(length = 60, nullable = false, columnDefinition = "bpchar")
	private String password;

	@Column(length = 10, nullable = false)
	private String role;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * 権限を返す
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(getRole()));
	}

	/**
	 * ログインに使うユーザ名を返す
	 */
	@Override
	public String getUsername() {
		return getAccount();
	}

	/**
	 * アカウントが期限切れのときは false を返す。このサンプルでは期限の概念がないので常に true を返す
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * アカウントがロックされているときは false を返す。このサンプルではロックの概念がないので常に true を返す
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * パスワードが期限切れのときは false を返す。このサンプルではパスワード期限切れの概念がないので常に true を返す
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * アカウントが無効のときは false を返す。このサンプルではアカウント無効の概念がないので常に true を返す
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}