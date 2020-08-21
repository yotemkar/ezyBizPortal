package com.hssa.ezybiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableScheduling
@EnableAsync
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@PropertySource("classpath:customerportal.properties")
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new CustomePasswordEncoder();
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		//web.ignoring().antMatchers("/customerportal-webservices/controller/paymentController/transResult");

		httpSecurity.headers().frameOptions().disable();
		httpSecurity.headers().xssProtection();

		httpSecurity.csrf().disable()

				// we don't need CSRF because our token is invulnerable
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

				// don't create session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.authorizeRequests()
				// .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

				// allow anonymous resource requests
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/controller/UserLoginBeanService/getAuthenticatedUser").permitAll()
				.antMatchers(HttpMethod.OPTIONS,
						"/customerportal-webservices/controller/cmsIncategoryService/getAllCmsIncategoryList")
				.permitAll()
				.antMatchers(HttpMethod.OPTIONS,
						"/customerportal-webservices/controller/cmsInsubcategoryService/getAllCmsInsubcategoryList")
				.permitAll()
				.antMatchers(HttpMethod.OPTIONS,
						"/customerportal-webservices/controller/cmsChaildsubcategoryService/getAllCmsChaildsubcategoryList")
				.permitAll().antMatchers("/controller/userPasswordService/forgotPassword").permitAll()
				.antMatchers("/UserLoginBeanService/login").permitAll()
				.antMatchers("/controller/menuService/downloadUserManual/**").permitAll()
				.antMatchers("/controller/userRegistrationService/registerUser").permitAll()
				.antMatchers("/controller/userRegistrationService/getLookupDetailsListFromLookupMasterName").permitAll()
				.antMatchers("/controller/UserLoginBeanService/getHomePageImage").permitAll()
				.antMatchers("/controller/UserLoginBeanService/getLoginPageContent").permitAll()
				.antMatchers("/controller/commonService/getCaptcha").permitAll()
				// Added by Mohsin for RetailPos
				.antMatchers("/controller/retailPos/login").permitAll()
				.antMatchers("/controller/retailPos/posActivation").permitAll()
				.antMatchers("/controller/retailPos/posValidateOtp").permitAll()
				.antMatchers("/controller/retailPos/posLeadCapture").permitAll()
				.antMatchers("/controller/retailPos/posBannerDetails").permitAll()
				.antMatchers("/controller/customerLoyaltyPoints/addPoints").permitAll()
				.antMatchers("/controller/UserLoginBeanService/getVersionByCompanyIdAndPlatform").permitAll()
				.antMatchers("/controller/UserLoginBeanService/mobileActivation").permitAll()
				.antMatchers("/controller/UserLoginBeanService/mobileOTPLogin").permitAll()
				.antMatchers("/controller/UserLoginBeanService/userOtpValidation").permitAll()
				.antMatchers("/controller/UserLoginBeanService/mobileLogin").permitAll()
				.antMatchers("/controller/ledgerService/getLedgerExcel").permitAll()
				.antMatchers("/controller/ledgerService/putLedgerLineItems").permitAll()
				.antMatchers("/controller/customerService/searchForRegisteration").permitAll()
				.antMatchers("/controller/userRegistrationService/registerUserApplication").permitAll()
				.antMatchers("/controller/userRegistrationService/registerUserApplicationOTP").permitAll()
				.antMatchers("/controller/userRegistrationService/getUniqeLoginIDByLoginId").permitAll()
				.antMatchers("/controller/userRegistrationService/getUserByEmailId").permitAll()
				.antMatchers("/controller/contactUsDetailService/insertContactUsDetails").permitAll()
				.antMatchers("/controller/employeeService/findEmployeeByCode").permitAll()
				.antMatchers("/controller/paymentController/transactionResult").permitAll()



				// .antMatchers("/controller/UserLoginBeanService/getAuthenticatedUser").authenticated();
				.anyRequest().authenticated();

		// Custom JWT based security filter
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		httpSecurity.headers().cacheControl();
	}

}