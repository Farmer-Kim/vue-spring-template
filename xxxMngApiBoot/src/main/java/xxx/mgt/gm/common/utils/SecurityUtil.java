package xxx.mgt.gm.common.utils;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityUtil {

	private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
	
	public SecurityUtil() {
		// TODO Auto-generated constructor stub
	}
	
	//권한 정보에 담겨져 있는 유저 이름을 반환하는 메소드
	public Optional<String> getCurrentUsername(){
		
		//SecurityContextHolder에서 권한 정보들을 뽑아옴
		final Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null) {
			logger.debug("SecurityContext 에 인증 정보가 없습니다.");
			return Optional.empty();
		}
		String username = null;
		
		if(authentication.getPrincipal() instanceof UserDetails) {
			UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
			username = springSecurityUser.getUsername();
		}
		else if(authentication.getPrincipal() instanceof String) {
			username = (String) authentication.getPrincipal();
		}
		
		return Optional.ofNullable(username);
	}
}
