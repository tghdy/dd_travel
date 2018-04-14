package com.dd.filter;

import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Filter")
public class Filter implements javax.servlet.Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		String val = httpRequest.getHeader("Origin");
		
		httpResponse.addHeader("Access-Control-Allow-Origin", val);
		httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		httpResponse.setHeader("Access-Control-Allow-Methods", "GET, PUT, DELETE, POST");
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
		//httpResponse.setHeader("A?ccept", "text/html");

		
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {

	}

}