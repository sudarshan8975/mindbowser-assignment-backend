package com.list.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.list.service.implemantation.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println("token--"+requestTokenHeader);
		String userName=null;
		String jwtToken=null;
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Mind ")) {
		
			jwtToken=requestTokenHeader.substring(5);
			try {
				System.out.println("jwtToken--"+jwtToken);
			     userName=jwtUtils.extractUsername(jwtToken);
			     System.out.println("userName--"+userName);
			}
			catch(ExpiredJwtException e) {
				e.printStackTrace();
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		else {
			System.out.println("Invalid Token");
		}
		
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(userName);
			
			if(jwtUtils.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);;
			}
		
		}
		else {
			System.out.println("Token Is Not Valid");
		}
		
		filterChain.doFilter(request, response);
	}

}
