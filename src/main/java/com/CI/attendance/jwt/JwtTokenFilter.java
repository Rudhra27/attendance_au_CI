package com.CI.attendance.jwt;

import java.io.IOException;
import java.security.Key;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import com.CI.attendance.Model.Role;
import com.CI.attendance.Model.User;
import com.CI.attendance.Service.UserDetailsImpl;
import com.CI.attendance.Service.UserDetailsServiceImpl;

import io.jsonwebtoken.*;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	 @Autowired
	  private UserDetailsServiceImpl userDetailsService;
	 
	 @Value("${app.jwt.secret}")
		private String SECRET_KEY;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
				HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!hasAuthorizationBearer(request)) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = getAccessToken(request);

		if (!jwtUtil.validateAccessToken(token)) {
			filterChain.doFilter(request, response);
			return;
		}

		setAuthenticationContext(token, request);
		filterChain.doFilter(request, response);
	}

	private boolean hasAuthorizationBearer(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
			return false;
		}

		return true;
	}

	private String getAccessToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		String token = header.split(" ")[1].trim();
		return token;
	}

	private void setAuthenticationContext(String token, HttpServletRequest request) {
		
		//UserDetailsImpl userPrincipal = (UserDetailsImpl)getUserDetails(token);
		 Claims claims = jwtUtil.parseClaims(token);
		 String username = claims.getSubject();
		 String[] jwtSubject = username.split(",");
		 String roles = (String) claims.get("roles");
		 System.out.println("roles: " +  roles); 
		 roles = roles.replace("[", "").replace("]", ""); 
		 String[] roleNames = roles.split(",");
		 
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtSubject[1]);
		 System.out.println("roles:authorities " +   userDetails.getAuthorities()); 
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
		//UserDetails userDetails = getUserDetails(token);

		/*
		 * UsernamePasswordAuthenticationToken authentication = new
		 * UsernamePasswordAuthenticationToken(userPrincipal, null,
		 * userPrincipal.getAuthorities());
		 */

		authentication.setDetails(
				new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	 

		  public String getUserNameFromJwtToken(String token) {
			  String jwt = token.replace("Bearer", "");
			 return Jwts.parser()
	                    .setSigningKey(SECRET_KEY)
	                    .parseClaimsJws(jwt)
	                    .getBody()
	                    .getSubject();
		  }

			/*
			 * private UserDetails getUserDetail(String token) { User userDetails = new
			 * User(); Claims claims = jwtUtil.parseClaims(token); String subject = (String)
			 * claims.get(Claims.SUBJECT); String roles = (String) claims.get("roles");
			 * 
			 * System.out.println("SUBJECT: " + subject); System.out.println("roles: " +
			 * roles); roles = roles.replace("[", "").replace("]", ""); String[] roleNames =
			 * roles.split(",");
			 * 
			 * for (String aRoleName : roleNames) { userDetails.addRole(new
			 * Role(aRoleName)); }
			 * 
			 * String[] jwtSubject = subject.split(",");
			 * 
			 * userDetails.setId(Integer.parseInt(jwtSubject[0]));
			 * userDetails.setUsername(jwtSubject[1]);
			 * 
			 * return userDetails; }
			 */
	
}
