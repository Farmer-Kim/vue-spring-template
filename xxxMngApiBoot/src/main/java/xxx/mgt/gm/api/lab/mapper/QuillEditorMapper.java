package xxx.mgt.gm.api.lab.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
@Mapper
@Repository
public interface QuillEditorMapper {
	
	/**
	 * Quill Editor Content 목록
	 * @param cmmnCdVO
	 */
	List<QuillEditorVO> getQuillEditorList(QuillEditorVO quillEditorVO);
	
	/**
	 * Quill Editor Content 목록 카운트
	 * @param cmmnCdVO
	 */
	int getQuillEditorListCnt(QuillEditorVO quillEditorVO);
	
	/**
	 * Quill Editor Content 등록
	 * @param cmmnCdVO
	 */
	int regQuillEditorInfo(QuillEditorVO quillEditorVO);
}
