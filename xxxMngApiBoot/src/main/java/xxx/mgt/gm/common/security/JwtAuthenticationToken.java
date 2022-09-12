package xxx.mgt.gm.common.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class JwtAuthenticationToken extends AbstractAuthenticationToken{

	private final Object principal;
	private final String credentials;
	
	public JwtAuthenticationToken(String principal, String credintials) {
		super(null);
		setAuthenticated(false);
		this.principal = principal;
		this.credentials = credintials;
	}
	
	public JwtAuthenticationToken(Object principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		setAuthenticated(true);
		this.principal = principal;
		this.credentials = credentials;
	}
	
	@Override
	public Object getCredentials() {
		return principal;
	}
	@Override
	public Object getPrincipal() {
		return credentials;
	}
}
