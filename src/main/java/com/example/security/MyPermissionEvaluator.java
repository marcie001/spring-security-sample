/**
 * 
 */
package com.example.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.example.entity.Article;
import com.example.entity.User;
import com.example.repository.ArticleRepository;

/**
 * @author marcie
 *
 */
public class MyPermissionEvaluator implements PermissionEvaluator {

	private ArticleRepository service;

	public MyPermissionEvaluator(ArticleRepository service) {
		this.service = service;
	}

	/**
	 * 記事に対する権限があるか判定する。
	 * 
	 * @see org.springframework.security.access.PermissionEvaluator#hasPermission(org.springframework.security.core.Authentication,
	 *      java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if (!(targetDomainObject instanceof Article)) {
			return false;
		}
		Article target = (Article) targetDomainObject;
		Permission p = Permission.valueOf((String) permission);
		if (p == Permission.READ) {
			// READ 権限は常にtrue
			return true;
		}
		// WRITE 権限は作成者のみ
		return target.getUserId().equals(((User) authentication.getPrincipal()).getId());
	}

	/**
	 * 記事に対する権限があるか判定する。
	 * 
	 */
	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		if ("article".equals(targetType)) {
			Integer id;
			if (targetId instanceof String) {
				id = Integer.valueOf((String) targetId);
			} else if (targetId instanceof Number) {
				id = ((Number) targetId).intValue();
			} else {
				return false;
			}
			Article article = service.findOne(Integer.valueOf(id));
			return ((User) authentication.getPrincipal()).getId().equals(article.getUserId());
		}
		return false;
	}

}
