
package com.ezen.security.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

	// private MemberService memberService;
	private final SecurityUserService memberService;

	// 비밀번호 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 무시하는 경로
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		// static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
		return (web) -> web.ignoring().antMatchers("/templates/include/**", "/bootstrap/css/**", "/bootstrap/js/**",
				"/bootstrap/images/**", "/bootstrap/fonts/**", "/img_file/**", "/join_img_file/**");
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 페이지 권한 설정
		http.authorizeRequests()
				.antMatchers("/board/**", "/").permitAll()
				.antMatchers("/member/**").hasRole("MEMBER")
				.antMatchers("/admin/**").hasRole("ADMIN");
		
				 http.formLogin().loginPage("/Login") // 로그인 페이지 이동
//				.formLogin().loginPage("/main/Login") // 로그인 페이지 이동
				.usernameParameter("memberId") // 로그인 Form Input ID Name
				.passwordParameter("memberPw") // 로그인 Form Input PW Name
				.loginProcessingUrl("/login/action") // 로그인Form Action Url
				.defaultSuccessUrl("/") // 로그인성공 시 이동
				.failureUrl("/Login") // 로그인실패 시 이동
				.permitAll();
//				.and() // 로그아웃
				 http.logout()
				.logoutUrl("/logout")// 로그아웃 처리 URL
				.logoutSuccessUrl("/Login") // 로그아웃 성공 후 이동페이지
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "remember-me");
//				.and() // 403 예외처리 핸들링
//				.exceptionHandling().accessDeniedPage("/main/denied");
//				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/main/logout"))
//				.logoutSuccessUrl("/main/logout/result")
//				.invalidateHttpSession(true)
				

		return http.build();
	}

}