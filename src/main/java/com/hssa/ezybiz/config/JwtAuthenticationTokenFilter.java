package com.hssa.ezybiz.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hssa.ezybiz.dto.ApplicationToken;
import com.hssa.ezybiz.services.ApplicationTokenBO;
import com.hssa.ezybiz.services.RetailerBo;
import com.hssa.ezybiz.utils.CustomerPortalConstants;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    //@Autowired
   // private RetailerBo retailerBO ;
    
    @Autowired
    private ApplicationTokenBO applicationTokenBO; 

    /**
     * All the request to be authorized through this filter
     * If success, this method sets the authentication context till the log in session expires
     * 
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authToken = request.getHeader(CustomerPortalConstants.AUTHORIZATION);
        String path = request.getPathInfo();
        // authToken.startsWith("Bearer ")
        // String authToken = header.substring(7);
        
       /* if(authToken!= null && authToken.equals("dddydaW5kaWFaefeF9kZdDYDW1vOmeNlbWVudDAwDDasseMA")){ // add this if condition to allow Navendor application to access services.
        	JwtUser user = new JwtUser(1, "retailPos", "", "", "", "", null, true, null, null, 1, 1, "");
        	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));            
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        else{ */// moved below code to else condition to allow Navendor application to access services.
        	String username = jwtTokenUtil.getUsernameFromToken(authToken);
            String application = jwtTokenUtil.getApplicationFromToken(authToken); // this value will only come for token generated for integration apps. 
            logger.info("checking authentication für user " + username);       

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            	
            	if(application != null){ // this will be true for integration apps like Navendor.
            		ApplicationToken tokenDetails = new ApplicationToken();
            		tokenDetails.setApplicationName(application);
            		tokenDetails.setApplicationToken(authToken);
            		tokenDetails.setServiceUrl(path);
            		int access = applicationTokenBO.validateApplicationTokenAndURL(tokenDetails);
            		if (access > 0){ // if access is greater than zero than incoming token is allowed to access the requested service.
            			JwtUser user = new JwtUser(1, application, "", "", "", "", null, true, null, null, 1, 1, ""); // Dummy user is created for integration apps to allow then access the services, since they have a valid token.
                    	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));            
                        SecurityContextHolder.getContext().setAuthentication(authentication);
            		}
            	}
            	
            	else{ // control will enter into else loop only for Dealer Connect generated token
            		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
                    // the database compellingly. Again it's up to you ;)
                    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        logger.info("authenticated user " + username + ", setting security context");
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
            	}        	
                // It is not compelling necessary to load the use details from the database. You could also store the information
                // in the token and read it from it. It's up to you ;)
                
            }
        //}
        
        
        if(request.getMethod().equalsIgnoreCase("Options")){
        	response.addHeader("Allow","GET, POST"); 
        }
        chain.doFilter(request, response);
    }
}