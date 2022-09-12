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

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import xxx.mgt.gm.api.support.service.DevGuideService;
import xxx.mgt.gm.api.support.vo.DevGuideVO;
import xxx.mgt.gm.common.file.FileService;
import xxx.mgt.gm.common.utils.StringUtil;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 개발자 가이드 업무</li>
 * <li>서브 업무명  = 개발자 가이드 관련</li>
 * <li>설	 명  = 개발자 가이드 controller</li>
 * <li>작  성  자  = Lee Yun Je</li>
 * <li>작  성  일  = 2021. 09. 08.</li>
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
public class DevGuideController {

	
	private DevGuideService devGuideService;
	
	private FileService fileService;
	
	@Autowired
	public DevGuideController(DevGuideService devGuideService,FileService fileService) {
		this.devGuideService = devGuideService;
	}
	
	/**
	 * 개발자 가이드 목록 조회
	 * 
	 * @param DevGuideVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/devGuideList", method = { RequestMethod.POST })
	public Object devGuideList(@RequestBody  DevGuideVO devGuideVO) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		//개발자 가이드 목록
		List<HashMap<String,Object>> devGuideList = devGuideService.devGuideList(devGuideVO);
		//개발자 가이드 목록 카운트
		int devGuideListCnt = devGuideService.devGuideListCnt(devGuideVO);
		
		result.put("devGuideList", devGuideList);
		result.put("devGuideListCnt", devGuideListCnt);
		return result;
	}
	
	/**
	 * 개발자 가이드 등록/수정
	 * 
	 * @param DevGuideVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/devGuideReg", method = { RequestMethod.POST })
	public Object devGuideReg(@RequestBody  DevGuideVO devGuideVO, HttpServletRequest request) throws Exception {
		/*파라미터 값 찾기(VO버전) */
		Field[] fields = devGuideVO.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			System.err.println(field.getName() + " : " +field.get(devGuideVO));
		}
		Map<String, Object> result = new HashMap<String, Object>();

			devGuideVO.setCrtrSn(12311);
			devGuideVO.setUpdusrSn(12311);
		try {
			if (StringUtil.getString(devGuideVO.getDevlopGuideSn()) == "") {
				
				log.info("devGuideInsert start!!");
				
				// 등록
				devGuideService.devGuideInsert(devGuideVO);
				//등록 후 상세보기를 위한 개발자 가이드 번호 받기
				int NewDevlopGuideSn = devGuideService.getMaxDevlopGuideSn();
				
				result.put("NewDevlopGuideSn", NewDevlopGuideSn);
			} else {

				log.info("devGuideUpdate start!!");
				
				// 업데이트
				devGuideService.devGuideUpdate(devGuideVO);
				
			}

			result.put("code", 200);

		} catch (Exception e) {
			
			log.error("devGuideReg error!");
			e.printStackTrace();

			result.put("code", 401);

			return result;
		}
		return result;

	}

	/**
	 * 개발자 가이드 상세 조회
	 * 
	 * @param DevGuideVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/devGuideView", method = { RequestMethod.POST })
	public Object devGuideView(@RequestBody DevGuideVO devGuideVO) throws Exception {
		// 넘어온 값 확인
		Field[] fields = devGuideVO.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			System.err.println(fields[i].getName() + " : " + fields[i].get(devGuideVO));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		
		//개발자 가이드 목록
		DevGuideVO vo = devGuideService.devGuideView(devGuideVO);
		
		result.put("vo", vo);
		return result;
	}
	
	/**
	 * 개발자 가이드 상세 조회
	 * 
	 * @param DevGuideVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/devGuideDel", method = { RequestMethod.POST })
	public Object devGuideDel(@RequestBody DevGuideVO devGuideVO) throws Exception {
		// 넘어온 값 확인
		Field[] fields = devGuideVO.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			System.err.println(fields[i].getName() + " : " + fields[i].get(devGuideVO));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			
			log.info("devGuideDel start!!");
			
			//개발자 가이드 삭제
			devGuideService.devGuideDel(devGuideVO);

			result.put("code", 200);

		} catch (Exception e) {

			e.printStackTrace();

			result.put("code", 401);

			return result;
		}
		return result;

	}
}
