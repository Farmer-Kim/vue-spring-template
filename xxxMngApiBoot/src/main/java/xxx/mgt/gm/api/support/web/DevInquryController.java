package xxx.mgt.gm.api.support.web;

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

import com.google.api.client.util.SecurityUtils;

import lombok.extern.slf4j.Slf4j;
import xxx.mgt.gm.api.notice.vo.NoticeVO;
import xxx.mgt.gm.api.support.service.DevInquryService;
import xxx.mgt.gm.api.support.vo.DevGuideVO;
import xxx.mgt.gm.api.support.vo.DevInquryVO;
import xxx.mgt.gm.common.file.FileService;
import xxx.mgt.gm.common.utils.StringUtil;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = Q&A 업무</li>
 * <li>서브 업무명  = Q&A 관련</li>
 * <li>설	 명  = Q&A controller</li>
 * <li>작  성  자  = Si Yeon Han</li>
 * <li>작  성  일  = 2021. 09. 25.</li>
 * </ul>
 * <pre>
 * ======================================
 * 변경자/변경일  =
 * 변경사유/내역  =
 * ======================================
 * </pre>
 ***************************************************/
@Slf4j
@RequestMapping(value = "/api/support")
@RestController
public class DevInquryController {
	
	private DevInquryService devInquryService;
	
	private FileService fileService;
	
	@Autowired
	public DevInquryController(DevInquryService devInquryService,FileService fileService) {
		this.devInquryService = devInquryService;
	}
	
	/**
	 * Q&A 목록 조회
	 * 
	 * @param DevGuideVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/devInquryList", method = { RequestMethod.POST })
	public Object devInquryList(@RequestBody  DevInquryVO devInquryVO) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		//개발자 가이드 목록
		List<HashMap<String,Object>> devInquryList = devInquryService.devInquryList(devInquryVO);
		//개발자 가이드 목록 카운트
		int devInquryListCnt = devInquryService.devInquryListCnt(devInquryVO);
		
		result.put("devInquryList", devInquryList);
		result.put("devInquryListCnt", devInquryListCnt);
		return result;
	}
	
	/**
	 * Q&A 상세 조회
	 * 
	 * @param DevInquryVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/devInquryView", method = { RequestMethod.POST })
	public Object devInquryView(@RequestBody DevInquryVO devInquryVO) throws Exception {
		// 넘어온 값 확인
		Field[] fields = devInquryVO.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			System.err.println(fields[i].getName() + " : " + fields[i].get(devInquryVO));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		
		//@&A 목록
		DevInquryVO vo = devInquryService.devInquryView(devInquryVO);
		
		result.put("devInquryVO", vo);
		return result;
	}
	
	/**
	 * Q&A 등록/수정
	 *
	 * @param DevInquryVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/devInquryRegist", method = { RequestMethod.POST })
	public Object devInquryRegist(@RequestBody DevInquryVO devInquryVO, HttpServletRequest request) throws Exception {
		/*파라미터 값 찾기(VO버전) */
		Field[] fields = devInquryVO.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			System.err.println(field.getName() + " : " +field.get(devInquryVO));
		}
		Map<String, Object> result = new HashMap<>();
		System.out.println(devInquryVO.toString());
		try {
			
			if(devInquryVO.getSecretAt().equals("false")) {
				devInquryVO.setSecretAt("N");
			} else {
				devInquryVO.setSecretAt("Y");
			}
			
			if (StringUtil.getString(devInquryVO.getInqrySn()) == "" || devInquryVO.getInqrySn() == 0) {
				// 등록
				devInquryVO.setInqryUserSn(4);
				
				devInquryService.devInquryInsert(devInquryVO);
				
				result.put("resultSn", devInquryVO.getInqrySn());
			} else {
				// 업데이트

				devInquryService.devInquryUpdate(devInquryVO);

				result.put("resultSn", devInquryVO.getInqrySn());
			}

			result.put("code", 200);

		} catch (Exception e) {

			e.printStackTrace();

			result.put("code", 401);

			return result;
		}
		return result;

	}
	
	/**
	 * Q&A 삭제
	 * 
	 * @param DevInquryVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/devInquryDelete", method = { RequestMethod.POST })
	public Object devGuideDel(@RequestBody DevInquryVO devInquryVO) throws Exception {
		// 넘어온 값 확인
		Field[] fields = devInquryVO.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			System.err.println(fields[i].getName() + " : " + fields[i].get(devInquryVO));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			
			log.info("devGuideDel start!!");
			
			//개발자 가이드 삭제
			devInquryService.devInquryDel(devInquryVO);

			result.put("code", 200);

		} catch (Exception e) {

			e.printStackTrace();

			result.put("code", 401);

			return result;
		}
		return result;

	}
}
