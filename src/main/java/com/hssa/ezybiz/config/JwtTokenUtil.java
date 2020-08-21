package com.hssa.ezybiz.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author KPIT
 *
 */
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_AUDIENCE = "audience";
    static final String CLAIM_KEY_CREATED = "created";
    static final String CLAIM_KEY_USER_ID = "userId";
    static final String CLAIM_KEY_USER_TYPE = "userType";

    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";
    
    // Added by Mohsin for RetailPos
    private static final String APPLICATION = "application";
    
    /**
     * 
     */
    private static final String USER_ROLE = "userRole";

	private static final String USER_ID = "userId";
	
	private static final String COMAPNY_ID = "companyId";
	private static final String SUPER_USER_ID = "superUserId";
	

    private String secret="mySecret";

    private Long expiration = 604800L;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    
    public List<LinkedHashMap<String, String>> getUserRoleFromToken(String token) {
    	List<LinkedHashMap<String, String>> authorites;
        try {
            final Claims claims = getClaimsFromToken(token);
            authorites = (List<LinkedHashMap<String, String>>)claims.get(USER_ROLE);
        } catch (Exception e) {
        	authorites = null;
        }
        return authorites;
    }
    
    /**
     * getUserRoleFromToken (user id)
     * @param token
     * @return
     */
    public Integer getUserIdFromToken(String token) {
    	Integer userId = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = (Integer)claims.get(USER_ID);
        } catch (Exception e) {
        	userId = null;
        }
        return userId;
    }
    
    // Added by Mohsin for RetailPos
    public String getApplicationFromToken(String token){
    	String application = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            application = (String)claims.get(APPLICATION);
        } catch (Exception e) {
        	application = null;
        }
        return application;
    }
    
    /**
     * 
     * @param token
     * @return
     */
    public Integer getCompanyIdFromToken(String token) {
    	Integer companyId = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            companyId = (Integer)claims.get(COMAPNY_ID);
        } catch (Exception e) {
        	companyId = null;
        }
        return companyId;
    }
    /**
     * 
     * @param token
     * @return
     */
    public String getUserTypeFromToken(String token) {
    	String userType = "";
    	try {
    		final Claims claims = getClaimsFromToken(token);
    		userType = (String)claims.get(CLAIM_KEY_USER_TYPE);
    	} catch (Exception e) {
    		userType = null;
    	}
    	return userType;
    }
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = getClaimsFromToken(token);
            audience = (String) claims.get(CLAIM_KEY_AUDIENCE);
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /*private String generateAudience(Device device) {
        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = AUDIENCE_WEB;
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;
        }
        return audience;
    }*/

    private Boolean ignoreTokenExpiration(String token) {
        String audience = getAudienceFromToken(token);
        return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
    }
    
    // Added by Mohsin for RetailPos
    public String generateTokenForRetailPos(JwtRetailerUser userDetails){
    	Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(USER_ROLE, userDetails.getAuthorities());
        claims.put(USER_ID, userDetails.getUserId());
        claims.put(COMAPNY_ID, userDetails.getCompanyid());
        claims.put(APPLICATION, "retailPos");
        return Jwts.builder()
            .setClaims(claims)                
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    public String generateToken(JwtUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(USER_ROLE, userDetails.getAuthorities());
        claims.put(USER_ID, userDetails.getUserId());
        claims.put(COMAPNY_ID, userDetails.getCompanyid());
        claims.put(SUPER_USER_ID, userDetails.getSuperUserId());
        claims.put(CLAIM_KEY_USER_TYPE, userDetails.getUserType());
        return generateToken(claims);
    }
    
    
    /**
	 * Added this method to generate tokens for integration applications - Mohsin
	 *  
	 */
    public String generateTokenForApplication(JwtUser userDetails, String applicationName){
    	Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(USER_ROLE, userDetails.getAuthorities());
        claims.put(USER_ID, userDetails.getUserId());
        claims.put(COMAPNY_ID, userDetails.getCompanyid());
        claims.put(APPLICATION, applicationName);
        return Jwts.builder()
            .setClaims(claims)                
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
        return (
                username.equals(user.getUsername())
                        && !isTokenExpired(token)
                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
    }
}