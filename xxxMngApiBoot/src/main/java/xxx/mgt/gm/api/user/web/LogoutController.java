package xxx.mgt.gm.api.user.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "api/user")
@RestController
public class LogoutController {
	
	
	@ResponseBody
	@RequestMapping(value = "/logout")
	public boolean logout(@RequestBody String token) {
		return true;
	}
}
