package xxx.mgt.gm.api.user.dto;

import com.google.auto.value.AutoValue.Builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchUserInfoDto {

	private String nickname;
	private String userEmail;
	private String tempPassword;
}
