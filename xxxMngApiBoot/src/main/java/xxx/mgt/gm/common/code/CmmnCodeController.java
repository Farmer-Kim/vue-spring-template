package xxx.mgt.gm.common.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 공통코드 조회(공통기능)</li>
 * <li>서브 업무명  = 공통코드 조회(공통기능) 관련</li>
 * <li>설	 명  = 공통코드 조회(공통기능) controller</li>
 * <li>작  성  자  = Park Ji Hwan</li>
 * <li>작  성  일  = 2021. 08. 27.</li>
 * </ul>
 * <pre>
 * ======================================
 * 변경자/변경일  =
 * 변경사유/내역  =
 * ======================================
 * </pre>
 ***************************************************/

@Slf4j
@RequestMapping(value = "/common/code")
@RestController
public class CmmnCodeController {
	private CmmnCodeService cmmnCodeService;
	
	public CmmnCodeController(CmmnCodeService cmmnCodeService){
        this.cmmnCodeService = cmmnCodeService;
    }
	
	/**
	 * 공통코드 조회(컴포넌트 사용)
	 * @param cmmnCodeVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmmnCodeList", method = {RequestMethod.POST})
	public @ResponseBody Object cmmnCodeList(@RequestBody CmmnCodeVO cmmnCodeVO, HttpServletRequest request) throws Exception {
		log.info("cmmnCodeList Start !!");

		Map<String, Object> result = new HashMap<String, Object>();
   
		try {
			log.info("cmmnCodeVO : [" + cmmnCodeVO.toString() + "]");
			  
			List<HashMap<String, Object>> list = cmmnCodeService.cmmnCodeList(cmmnCodeVO);
			
			result.put("code", 200);
			result.put("cmmnCodeList", list);

		} catch (Exception e) {
			log.error("cmmnCodeList Exception !!");
			e.printStackTrace();
			
			result.put("code", 401);
			return result;
		}
		
		log.info("cmmnCodeList End !!");
		
		return result;
	}
	

}
