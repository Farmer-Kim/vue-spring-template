package xxx.mgt.gm.api.lab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xxx.mgt.gm.api.lab.service.QuillEditorService;
import xxx.mgt.gm.api.lab.vo.QuillEditorVO;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 실험실 관리 업무</li>
 * <li>서브 업무명  = Quill Editor 관리</li>
 * <li>설     명  = Quill Editor 관리 controller</li>
 * <li>작  성  자  = Lee Min gu</li>
 * <li>작  성  일  = 2021. 08. 31.</li>
 * </ul>
 * <pre>
 * ======================================
 * 변경자/변경일  =
 * 변경사유/내역  =
 * ======================================
 * </pre>
 ***************************************************/
@RequestMapping(value = "/api/lab")
@RestController
public class QuillEditorController {
	
	private QuillEditorService quillEditorService;
	
	@Autowired
	public QuillEditorController(QuillEditorService quillEditorService) {
		this.quillEditorService = quillEditorService;
	}
	
	/**
	 * Quill Editor Content 조회
	 * 
	 * @param quillEditorVO
	 * @return Object
	 */
	@RequestMapping(value = "/quillEditorSearch", method = { RequestMethod.POST })
	public @ResponseBody Object cmmnCdList(@RequestBody QuillEditorVO quillEditorVO) throws Exception {
		return quillEditorService.getQuillEditorList(quillEditorVO);
	}
	
	/**
	 * Quill Editor Content 등록
	 * 
	 * @param quillEditorVO
	 * @return Object
	 */
	@RequestMapping(value = "/quillEditorInsert", method = { RequestMethod.POST })
	public @ResponseBody Object quillEditorInfo(@RequestBody QuillEditorVO quillEditorVO) throws Exception {
		return quillEditorService.regQuillEditorInfo(quillEditorVO);
	}
}
