/**
 * 
 */
package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

/**
 * 認証を行うサービス
 * 
 * @author marcie001
 *
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	protected UserRepository repository;

	/**
	 * Spring Security 用のメソッド。
	 */
	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		User user = repository.findByAccount(account);
		if (user == null) {
			throw new UsernameNotFoundException(account + " is not found.");
		}
		return user;
	}

}
