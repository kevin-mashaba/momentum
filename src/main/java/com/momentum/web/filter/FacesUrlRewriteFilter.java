package com.momentum.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class FacesUrlRewriteFilter implements Filter {

	private final String facesExtension;

	public FacesUrlRewriteFilter(final String facesExtension) {
		this.facesExtension = facesExtension;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		final String requestUri = httpServletRequest.getRequestURI();

		if (isUrlExtensionless(requestUri)) {
			RequestDispatcher requestDispatcher = httpServletRequest
					.getRequestDispatcher(requestUri.concat(facesExtension));
			requestDispatcher.forward(servletRequest, servletResponse);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}

	}

	private boolean isUrlExtensionless(final String requestUri) {
		final int lastIndexOfForwardSlash = requestUri.lastIndexOf("/");
		final String urlPrefix = requestUri.substring(lastIndexOfForwardSlash + 1);
		return !urlPrefix.contains(".");
	}
}
