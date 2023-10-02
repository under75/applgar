package ru.sartfoms.applgar.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UrlPathHelper;

public class LoginPageInterceptor implements HandlerInterceptor {
	UrlPathHelper urlPathHelper = new UrlPathHelper();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (("/login".equals(urlPathHelper.getLookupPathForRequest(request))
				|| "/".equals(urlPathHelper.getLookupPathForRequest(request)))
				&& isAuthenticated()) {
			String encodedRedirectURL = response.encodeRedirectURL(request.getContextPath() + "/appl");
			response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
			response.setHeader("Location", encodedRedirectURL);

			return false;
		} else {
			return true;
		}
	}

	private boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
			return false;
		}
		return authentication.isAuthenticated();
	}
}
