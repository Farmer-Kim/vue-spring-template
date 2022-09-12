package xxx.mgt.gm.api.lab.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xxx.mgt.gm.api.lab.mapper.QuillEditorMapper;
import xxx.mgt.gm.api.lab.vo.QuillEditorVO;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 실험실 관리 업무</li>
 * <li>서브 업무명  = Quill Editor 관리</li>
 * <li>설     명  = Quill Editor 관리 service</li>
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
@Service
@Transactional
public class QuillEditorService {
	
	private QuillEditorMapper quillEditorMapper;

	@Autowired
	public QuillEditorService(QuillEditorMapper quillEditorMapper) {
		this.quillEditorMapper = quillEditorMapper;
	}
	
	/**
	 * Quill Editor Content 목록
	 * 
	 * @param List<HashMap<String, Object>>
	 * @return Object
	 */
	public Object getQuillEditorList(QuillEditorVO quillEditorVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		// Quill Editor Content 목록
		List<QuillEditorVO> quillEditorList = quillEditorMapper.getQuillEditorList(quillEditorVO);

		result.put("content", quillEditorList.get(0).getContent());
		result.put("code", 200);
		
		return result;
	}
	
	/**
	 * Quill Editor Content 등록
	 * 
	 * @param cmmnCdVO
	 * @return Object
	 */
	public Object regQuillEditorInfo(QuillEditorVO quillEditorVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		UUID id = UUID.randomUUID();
		
		quillEditorVO.setId(id.toString());
		
		quillEditorMapper.regQuillEditorInfo(quillEditorVO);
		
		result.put("code", 200);
		
		return result;
	}

}
