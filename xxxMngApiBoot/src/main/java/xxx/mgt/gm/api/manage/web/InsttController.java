package xxx.mgt.gm.api.manage.web;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import xxx.mgt.gm.api.manage.service.InsttService;
import xxx.mgt.gm.api.manage.vo.InsttVO;
import xxx.mgt.gm.api.notice.vo.NoticeVO;
import xxx.mgt.gm.common.utils.StringUtil;

@Slf4j
@RequestMapping(value = "/api/manage")
@RestController
public class InsttController {
	@Autowired
	private InsttService insttService;
	
	@Autowired
	public InsttController(InsttService insttService) {
		this.insttService = insttService;
	}
	
	/***
	 *  기관 관리 목록 조회
	 *  @param insttVO
	 *  @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/insttManageList", method = { RequestMethod.POST })
	public Object insttManageList(@RequestBody InsttVO insttManageVO) throws Exception {
		Map<String,Object> result = new HashMap<>();
		try {
			// 기관관리 목록
			List<Map<String,Object>> insttManageList = insttService.manageList(insttManageVO);
			// 기관관리 카운트 
			int insttManageListCnt = insttService.manageListCnt(insttManageVO);
			result.put("insttManageList", insttManageList);
			result.put("insttManageListCnt", insttManageListCnt);
			result.put("code", 200);
			
		} catch(Exception e) {
			log.error("insttManageList Exception!!");
			e.printStackTrace();

			result.put("code", 401);
			return result;
		}
		return result;
	}
	
	/**
	 * 기관 등록
	 *
	 * @param insttVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/insttRegist", method = { RequestMethod.POST })
	public Object insttRegist(@RequestBody InsttVO insttVO, HttpServletRequest request) throws Exception {
		/*파라미터 값 찾기(VO버전) */
		Field[] fields = insttVO.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			System.err.println(field.getName() + " : " +field.get(insttVO));
		}
		Map<String, Object> result = new HashMap<>();
		System.out.println(insttVO.toString());
		try {
			if (StringUtil.getString(insttVO.getInsttSn()) == "" || insttVO.getInsttSn() == 0) {
				// 등록
				insttVO.setRegId("admin");
				insttVO.setRegIp(request.getRemoteAddr());
				insttVO.setUpdtId("admin");
				insttVO.setUpdtIp(request.getRemoteAddr());

				insttService.insttInsert(insttVO);
				
				result.put("resultSn", insttVO.getInsttSn());
			}
			
			result.put("code", 200);

		} catch (Exception e) {

			e.printStackTrace();

			result.put("code", 401);

			return result;
		}
		return result;

	}
	
}
