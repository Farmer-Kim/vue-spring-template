package xxx.mgt.gm.api.notice.web;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;

import lombok.extern.slf4j.Slf4j;
import xxx.mgt.gm.api.notice.service.NoticeService;
import xxx.mgt.gm.api.notice.vo.NoticeVO;
import xxx.mgt.gm.common.excel.PoiExcelView;
import xxx.mgt.gm.common.file.FileService;
import xxx.mgt.gm.common.utils.StringUtil;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 공지사항 업무</li>
 * <li>서브 업무명  = 공지사항 관리 관련</li>
 * <li>설	 명  = 공지사항 관리 Controller</li>
 * <li>작 성 자 = Lee Yun Je</li>
 * <li>작  성  일  = 2021. 09. 06.</li>
 * </ul>
 * <pre>
 * ======================================
 * 변경자/변경일  =
 * 변경사유/내역  =
 * ======================================
 * </pre>
 ***************************************************/
@Slf4j
@RequestMapping(value = "/api/notice")
@RestController
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	private FileService fileService;

	@Autowired
	public NoticeController(NoticeService noticeService, FileService fileService) {
		this.noticeService = noticeService;
		this.fileService = fileService;
	}

	/**
	 * 공지사항 등록/수정
	 *
	 * @param noticeVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/noticeRegist", method = { RequestMethod.POST })
	public Object noticeRegist(@RequestBody NoticeVO noticeVO, HttpServletRequest request) throws Exception {
		/*파라미터 값 찾기(VO버전) */
		Field[] fields = noticeVO.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			System.err.println(field.getName() + " : " +field.get(noticeVO));
		}
		Map<String, Object> result = new HashMap<>();
		System.out.println(noticeVO.toString());
		try {
			if (StringUtil.getString(noticeVO.getNoticeSn()) == "" || noticeVO.getNoticeSn() == 0) {
				// 등록
				noticeVO.setRegId("admin");
				noticeVO.setRegIp(request.getRemoteAddr());
				noticeVO.setUpdtId("admin");
				noticeVO.setUpdtIp(request.getRemoteAddr());

				noticeService.noticeInsert(noticeVO);
				
				result.put("resultSn", noticeVO.getNoticeSn());
			} else {
				// 업데이트

				noticeVO.setUpdtId("admin");
				noticeVO.setUpdtIp(request.getRemoteAddr());

				noticeService.noticeUpdate(noticeVO);

				result.put("resultSn", noticeVO.getNoticeSn());
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
	 * 공지사항 삭제
	 *
	 * @param noticeVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/noticeDelete", method = { RequestMethod.POST })
	public Object noticeDelete(@RequestBody NoticeVO noticeVO, HttpServletRequest request) throws Exception {

		Map<String, Object> result = new HashMap<>();

		try {
			noticeVO.setUpdtId("admin");
			noticeVO.setUpdtIp(request.getRemoteAddr());

			noticeService.noticeDelete(noticeVO);

			result.put("code", 200);

		} catch (Exception e) {

			e.printStackTrace();

			result.put("code", 401);

			return result;
		}
		return result;

	}

	/**
	 * 공지사항 관리 상세 조회
	 *
	 * @param noticeVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/noticeView", method = { RequestMethod.POST })
	public Object noticeView(@RequestBody NoticeVO noticeVO) throws Exception {

		Map<String, Object> result = new HashMap<>();

		try {
			// 공지사항 기본 정보 조회
			NoticeVO vo = noticeService.noticeView(noticeVO);
			
			result.put("noticeVO", vo);
			result.put("code", 200);

		} catch (Exception e) {

			log.error("noticeView Exception!!");
			e.printStackTrace();

			result.put("code", 401);
			return result;
		}

		return result;
	}
	/**
	 * 공지사항 목록 조회
	 *
	 * @param noticeVO
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/noticeList", method = { RequestMethod.POST })
	public Object noticeList(@RequestBody NoticeVO noticeVO) throws Exception {

		Map<String, Object> result = new HashMap<>();
		
		try {
			// 공지사항 목록
			List<Map<String, Object>> noticeList = noticeService.noticeList(noticeVO);

			// 공지사항 카운트
			int noticeListCnt = noticeService.noticeListCnt(noticeVO);
			
			result.put("noticeList", noticeList);
			result.put("noticeListCnt", noticeListCnt);
			result.put("code", 200);
			
		} catch (Exception e) {

			log.error("noticeList Exception!!");
			e.printStackTrace();

			result.put("code", 401);
			return result;
		}

		return result;
	}

	/***
	 * 공지사항 엑셀 다운로드
	 *
	 * @param NoticeVO, HttpServletRequest, HttpServletResponse, ModelMap
	 */
	@ResponseBody
	@RequestMapping(value = "/noticeXslDnld", method = { RequestMethod.POST })
	public View noticeXslDnld(@RequestBody NoticeVO noticeVO, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		// 공지사항 목록
		List<Map<String, Object>> noticeList = noticeService.noticeList(noticeVO);

		model.addAttribute("contentList", noticeList);

		return new PoiExcelView(new SimpleDateFormat("yyyyMMdd").format(new Date()) + "공지사항_목록.xlsx");
	}

}
