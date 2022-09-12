package xxx.mgt.gm.api.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xxx.mgt.gm.api.user.dao.UserDAO;
import xxx.mgt.gm.api.user.dto.SearchUserInfoDto;
import xxx.mgt.gm.api.user.vo.CostomUserDetails;

/**
 * Spring Security 에서 유저의 정보를 가져오는 인터페이스
 * @author somean
 *
 */
@Service
public class CostomUserDetailService implements UserDetailsService {

	@Autowired
	private UserDAO userdao;
	

	
	@Override
	public CostomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//유저 정보를 가지고 옴
		CostomUserDetails user = userdao.getUserById(username);
		if (user == null) {
			throw new  UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다.");
		}
		
		return user;
	}
	
	
	public String checkIdAndEmail(SearchUserInfoDto searchUserInfoDto) {
		String username = userdao.checkIdAndEmail(searchUserInfoDto);
		if(username != null) {
			return username;
		}
		return null;
	}


	public void changeTempPassword(SearchUserInfoDto searchUserInfoDto) {
		userdao.changeTempPassword(searchUserInfoDto);
	}
	
	

}
