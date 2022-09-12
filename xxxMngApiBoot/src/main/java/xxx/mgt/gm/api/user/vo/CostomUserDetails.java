package xxx.mgt.gm.api.user.vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Spring Security에서 유저 정보를 담는 클래스
 * @author somean
 *
 */



@SuppressWarnings("serial")
public class CostomUserDetails implements UserDetails{
	
	private int userId; //유저 고유 아이디(pk)
	private String username; //유저 이름
	private String password; //유저 비밀번호
	private String nickname; //유저 벌명, 이름
	private String userEmail; //유저 이메일
	private String userPhone; //유저 핸드폰 번호
	private String teamName; //소속팀
	private String userKind; //회원 구분
	private boolean enable;	//회원 상태
	private String authority;	//회원 권한
	
	
	
	//계정이 가지고 있는 권한 목록을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(authority));
		return auth;
	}
	
	

	public int getUserId() {
		return userId;
	}

	
	@Override
	public String getUsername() {
		return username;
	}
	
	
	@Override
	public String getPassword() {
		return password;
	}
	

	public String getNickname() {
		return nickname;
	}



	public String getUserEmail() {
		return userEmail;
	}



	public String getUserPhone() {
		return userPhone;
	}



	public String getTeamName() {
		return teamName;
	}



	public String getUserKind() {
		return userKind;
	}






	/**
	 * 계정 활성화 여부
	 * true : 활성화
	 * false : 비활성화
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		return enable;
	}

	/**
	 * 계정 만료 여부
	 * true : 만료 안됨
	 * false : 만료됨
	 * @return
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	/**
	 * 계정 잠김 여부
	 * true : 잠기지 않음
	 * false : 잠김
	 * @return
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	/**
	 * 비밀번호 만료 여부
	 * true : 만료 안됨
	 * false : 만료됨
	 * @return
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
