package xxx.mgt.gm.common.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;


//GenericFilterBean를 상속받으면 doFilter만 오버라이딩 해도 된다.
//Spring security에서 제공하는 다른 필터는 init, destory도 구현해야 한다.
@Configuration
public class JwtFilter extends GenericFilterBean{
    
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private TokenProvider tokenProvider;

    //생성자 주입
    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    //실제 필터링 로직이 들어가는 메소드
    //토큰의 인증 정보를 현재 실행 중인 SecurityContext에 저장하는 역할 수행
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            
            String jwt = resolveToken(httpServletRequest); //토큰을 받아서 할당
            System.out.println(jwt);
            String requestURI = httpServletRequest.getRequestURI(); //요청이 들어온 URI
            
            
            
            //토큰이 문자열이고 유효성 검증을 했을 때 true이면 실행
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            	
            	//토큰이 유효할 경우 토큰으로부터 유저 정보를 가지고 온다. 인증 객체 생성
                Authentication authentication = tokenProvider.getAuthentication(jwt);
                
                //securityContext에 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("Security Context에 '{}' 인증 정보를 저장하였습니다, uri : []", authentication.getName(), requestURI);
            }
            else {
                logger.info("유효한 JWT 토큰이 없습니다., uri : {}", requestURI);
            }
            chain.doFilter(servletRequest, servletResponse);


        
    }

    //토큰 정보를 꺼내오기 위한 메소드
    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            //StringUtils.hasText() : 값이 문자열인지 아닌지 확인해서 true 혹은 false를 리턴
            //bearerToken이 "bearer" 로 시작하면 true를 리턴, 아니면 false를 리턴
            return bearerToken.substring(7);
        }
        return null;
    }
    
}
