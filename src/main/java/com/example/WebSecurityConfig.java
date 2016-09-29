package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * セキュリティ設定を行うには、 {@link WebSecurityConfigurerAdapter} を継承する
 * 
 * @author marcie001
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * パスごとのセキュリティの設定
	 */
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// /admin/ 以下には ADMIN 権限が必要
				.antMatchers("/admin/**").hasRole("ADMIN")
				// /user/ 以下には ADMIN か USER 権限が必要
				.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
				// 認証方法はフォーム認証（ログインページを用意して認証）
				.and().formLogin()
				// ログインページの設定
				.loginPage("/").permitAll()
				// ログイン後の挙動の設定
				.successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						// /user にリダイレクトさせる
						response.sendRedirect("/user");
					}
				})
				// ログアウトの設定
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(10));
	}

}
