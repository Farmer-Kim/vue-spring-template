package xxx.mgt.gm.api.user.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xxx.mgt.gm.api.user.dto.SearchUserInfoDto;
import xxx.mgt.gm.api.user.mapper.UserMapper;
import xxx.mgt.gm.api.user.vo.CostomUserDetails;

@Repository
public class UserDAO {

	private static final Logger Logger = LoggerFactory.getLogger(UserDAO.class);
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 회원 아이디로 유저 정보를 얻어온다
	 * @author somean
	 * @param userNm
	 * @return {@link CostomUserDetails}
	 */
	public CostomUserDetails getUserById(String username) {
		CostomUserDetails user = userMapper.getUserById(username);
		
		return user;
		
	}

	
	/**
	 * 입력한 아이디와 이메일이 둘 다 일치하는지 체크하는 메소드
	 * @param searchUserInfoDto
	 * @return boolean
	 */
	public String checkIdAndEmail(SearchUserInfoDto searchUserInfoDto) {
		if(userMapper.checkIdAndEmail(searchUserInfoDto) == 1) {
			String username = userMapper.getUserIdByEmail(searchUserInfoDto);
			return username;
		}
		else {
			Logger.debug("해당하는 아이디가 존재하지 않습니다, 유저 이름 : %s 이메일 : %s", 
					searchUserInfoDto.getNickname(), searchUserInfoDto.getUserEmail());
			return null;
		}
		
	}


	/**
	 * 비밀번호 찾기, 임시 비밀번호로 변경하는 메소드
	 * @param searchUserInfoDto
	 */
	public void changeTempPassword(SearchUserInfoDto searchUserInfoDto) {
		if(userMapper.changeTempPassword(searchUserInfoDto) == 1) {
			Logger.debug("비밀번호 변경 성공");
		}
		else {
			Logger.debug("비밀번호 변경 실패");
		}
	}
	


}
