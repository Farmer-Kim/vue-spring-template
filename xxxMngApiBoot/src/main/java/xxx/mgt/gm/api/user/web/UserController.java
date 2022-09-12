package xxx.mgt.gm.api.user.web;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xxx.mgt.gm.api.user.dto.SearchUserInfoDto;
import xxx.mgt.gm.api.user.service.CostomUserDetailService;
import xxx.mgt.gm.api.user.service.MailService;
import xxx.mgt.gm.common.security.TokenProvider;
import xxx.mgt.gm.common.utils.RamdomNumUtil;
/**
 * 유저 관련 컨트롤러
 * @author somean
 * 작업 시작일 : 2021.9.26
 */
@RequestMapping(value = "api/user")
@RestController
public class UserController {

	private CostomUserDetailService costomUserDetailService;
	private TokenProvider tokenProvider;
	private RamdomNumUtil numberUtil;
	
	@Autowired
	private MailService mailService;
	
	public UserController(
			TokenProvider tokenProvider,
			MailService mailService,
			RamdomNumUtil numberUtil,
			CostomUserDetailService costomUserDetailService
		) {
		this.tokenProvider = tokenProvider;
		this.mailService = mailService;
		this.numberUtil = numberUtil;
		this.costomUserDetailService = costomUserDetailService;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getInfo", method = RequestMethod.POST)
	public ResponseEntity<Authentication> getUserInfo(@RequestBody String token) {
		Authentication authentication = tokenProvider.getAuthentication(token);
		
		return new ResponseEntity<Authentication>(authentication, null, HttpStatus.OK);
	}
	
	
	/**
	 * 아이디 찾기 메소드
	 * @param userInfo
	 * @return boolean type, true 반환 시 홈페이지에서 card3_popup창을 띄움
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value = "/searchId", method = RequestMethod.POST)
	public boolean searchId(@RequestBody String userInfo) throws ParseException {
		
		//json형태의 String값을 parse하기 위해서 사용
		JSONParser jp = new JSONParser();
		JSONObject userInfoToJson = (JSONObject) jp.parse(userInfo);
		
		//각각의 키값을 통해서 value를 String자료형의 변수에 담음
		String nickname = userInfoToJson.get("nickname").toString();
		String userEmail = userInfoToJson.get("userEmail").toString();
		
		//객체 형태로 넘기기 위해서 해당 속성을 가진 DTO클래스 생성 후 값을 넣음
		SearchUserInfoDto searchUserInfoDto = new SearchUserInfoDto();
		searchUserInfoDto.setNickname(nickname);
		searchUserInfoDto.setUserEmail(userEmail);
		
		//회원이름과 닉네임을 통해서 회원 아이디를 얻음
		String username = costomUserDetailService.checkIdAndEmail(searchUserInfoDto);
		if(username != null) {
			//회원 아이디가 null이 아닐 경우 실행
			mailService.mailSendWithNewPw(username, nickname, userEmail);
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * 비밀번호 찾기 메소드
	 * 새로운 비밀번호를 회원의 이메일로 보내는 형식
	 * @param userInfo
	 * @return boolean type, true 반환 시 홈페이지에서 card3_popup창을 띄움
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value = "/searchPw", method = RequestMethod.POST)
	public boolean searchPw(@RequestBody String userInfo) throws ParseException {
		//json형태의 String값을 parse하기 위해서 사용
		JSONParser jp = new JSONParser();
		JSONObject userInfoToJson = (JSONObject) jp.parse(userInfo);
		
		//각각의 키값을 통해서 value를 String자료형의 변수에 담음
		String nickname = userInfoToJson.get("nickname").toString();
		String userEmail = userInfoToJson.get("userEmail").toString();
		
		String ramdomNum = numberUtil.getKey(true, 12);
		
		//객체 형태로 넘기기 위해서 해당 속성을 가진 DTO클래스 생성 후 값을 넣음
		SearchUserInfoDto searchUserInfoDto = new SearchUserInfoDto();
		searchUserInfoDto.setNickname(nickname);
		searchUserInfoDto.setUserEmail(userEmail);
		searchUserInfoDto.setTempPassword(ramdomNum);
		
		if(costomUserDetailService.checkIdAndEmail(searchUserInfoDto)!= null) {
			//해당하는 아이디가 있을 때 true가 반환되므로 아래의 코드들이
			//실행이 됨
			costomUserDetailService.changeTempPassword(searchUserInfoDto);
			mailService.mailSendWithNewPw(ramdomNum, nickname, userEmail);
			return true;
		}
		else {
			return false;
		}
	}
}
