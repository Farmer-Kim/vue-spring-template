package xxx.mgt.gm.api.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import xxx.mgt.gm.api.user.dto.LoginDto;
import xxx.mgt.gm.api.user.dto.SearchUserInfoDto;
import xxx.mgt.gm.api.user.vo.CostomUserDetails;

/**
 * 
 * @author somean
 * 회원 기능 관련
 * 작성일 2021-09-20
 */

@Mapper
@Repository
public interface UserMapper {
	
	/**
	 * 회원 로그인
	 * @param CostomUserDetails
	 */
	public CostomUserDetails getUserById(String userNm);

	/**
	 * @param searchUserInfoDto
	 * @return 
	 * 1 : 일치하는 계정 있음
	 * 0 : 일치하는 계정 없음
	 */
	public int checkIdAndEmail(SearchUserInfoDto searchUserInfoDto);

	/**
	 * 이메일로 회원 아이디 반환하는 sql문
	 * @param searchUserInfoDto
	 * @return String타입 회원 아이디
	 */
	public String getUserIdByEmail(SearchUserInfoDto searchUserInfoDto);

	public int changeTempPassword(SearchUserInfoDto searchUserInfoDto);

	
	
}
