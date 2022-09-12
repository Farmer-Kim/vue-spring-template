package xxx.mgt.gm.api.user.web;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import xxx.mgt.gm.api.user.dto.TokenDto;
import xxx.mgt.gm.api.user.service.CostomUserDetailService;
import xxx.mgt.gm.api.user.vo.CostomUserDetails;
import xxx.mgt.gm.common.security.JwtFilter;
import xxx.mgt.gm.common.security.TokenProvider;

/*	로그인 기능
 * 	작업 시작일 2021-9-19
 */

@RequestMapping(value = "api/user")
@RestController
public class LoginController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//자바 객체를 json형태로 변환하는 클래스
	@Autowired
	private ObjectMapper objectMapper;
	
	private TokenProvider tokenProvider;
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	private CostomUserDetailService costomUserDetailService;
	
	
	
	public LoginController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder,
			CostomUserDetailService costomUserDetailService) {
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.costomUserDetailService = costomUserDetailService;
	}

	/**
	 * 
	 * @param userVO
	 * @return 
	 * 토큰과 유저 데이터를 넘겨줘야 함
	 * @throws ParseException 
	 * @throws JsonProcessingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<TokenDto> login(@RequestBody String user) throws ParseException, JsonProcessingException {
		
		//{ username : 아이디,
		//	password : 비밀번호
		//}
		//형태로 넘어오기 때문에 jsonparsing
		JSONParser jp = new JSONParser();
		JSONObject jo = (JSONObject) jp.parse(user);
		
		String username = (String) jo.get("username");
		String password = (String) jo.get("password");
		CostomUserDetails CostomUser = costomUserDetailService.loadUserByUsername(username);
		
//		String encodedPassword = passwordEncoder.encode(password);	비밀번호 인코딩 되었는지 확인할 때 사용
//		System.out.println(encodedPassword);
		
		//httpHeader에 json형식으로 보내기 위해서 변환하는 메소드
		String convertToJson = objectMapper.writeValueAsString(CostomUser);
		
		System.out.println(convertToJson);
		
		//해당하는 아이디가 존재하고 DB의 비밀번호가 일치할 경우 실행
		if(CostomUser != null) {
			String DBPw = CostomUser.getPassword();
			
			if(passwordEncoder.matches(password, DBPw)) {
				//회원 아이디와 비밀번호를 이용해서 authenticationToken 객체 생성
				UsernamePasswordAuthenticationToken authenticationToken = 
						new UsernamePasswordAuthenticationToken(username, password); 
				
				Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				//인증 정보를 바탕으로 토큰 생성
				String jwt = tokenProvider.createToken(authentication);
				
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "bearer " + jwt);
				httpHeaders.add("user", convertToJson);
				
				
				//ResponseEntity 객체로 반환을 하게 되면 HTTP 정보들이 JSON으로 변환되어 프론트엔드 단으로 전달된다.
				return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
			}
		}
		
		
			return null;
		
		
		
		
		
	}
}
