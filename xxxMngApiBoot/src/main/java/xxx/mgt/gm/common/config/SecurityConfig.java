package xxx.mgt.gm.common.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import xxx.mgt.gm.common.security.JwtAccessDeniedHandler;
import xxx.mgt.gm.common.security.JwtAuthenticationEntryPont;
import xxx.mgt.gm.common.security.JwtFilter;
import xxx.mgt.gm.common.security.JwtSecurityConfig;
import xxx.mgt.gm.common.security.TokenProvider;

@Configuration
@EnableWebSecurity	//Spring Security를 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	    private final TokenProvider tokenProvider;
	    private final JwtFilter jwtFilter;
	    private final JwtAuthenticationEntryPont authenticationEntryPont;
	    private final JwtAccessDeniedHandler accessDeniedHandler;

	    public SecurityConfig(
	        TokenProvider tokenProvider,
	        JwtFilter jwtFilter,
	        JwtAuthenticationEntryPont authenticationEntryPont,
	        JwtAccessDeniedHandler accessDeniedHandler){
	        
	        this.tokenProvider = tokenProvider;
	        this.jwtFilter = jwtFilter;
	        this.authenticationEntryPont = authenticationEntryPont;
	        this.accessDeniedHandler = accessDeniedHandler;
	    }

	    //service에서 비밀번호를 암호화 할 수 있도록 bean으로 등록
	    @Bean
	    public PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	    

	    //js, css, image 설정은 보안설정의 영향 밖에 있도록 만듬
	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web .ignoring()
	            .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http.csrf().disable()   //csrf : 자신의 의지와는 무관하게 특정 웹사이트에(수정, 삭제, 등록 등) 요청하는 행위
	                                //토큰 방식이기 때문에 설정을 안함
	        	.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

	            .exceptionHandling()    //exceptin을 핸들링 할 때 각각의 상황이 발생하면 해당 메소드를 실행
	            .authenticationEntryPoint(authenticationEntryPont)
	            .accessDeniedHandler(accessDeniedHandler)

	            .and()
	            .sessionManagement()    //세션을 사용하지 않기 때문에 세션 설정을 stateless로
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

	            .and()
	            .authorizeRequests()    //HttpServletRequest를 사용하는 요청들에 대한 접근 제한
	            .antMatchers("/").permitAll()  //해당 주소의 요청은 인증없이 접근을 허용하겠다는 의미
	            .antMatchers("/api/user/**").permitAll()
	            .antMatchers("/manager/**").authenticated()  //해당들에 대해서는 모두 인증을 받아야 한다는 의미 
	            .antMatchers("/admin/**").authenticated()

	            .and()
	            .apply(new JwtSecurityConfig(tokenProvider));
	    }
}