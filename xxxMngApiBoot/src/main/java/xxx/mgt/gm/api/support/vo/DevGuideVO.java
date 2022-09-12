package xxx.mgt.gm.api.support.vo;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xxx.mgt.gm.common.com.CommonVO;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 개발자 가이드 업무</li>
 * <li>서브 업무명  = 개발자 가이드 관련</li>
 * <li>설	 명  = 개발자 가이드 vo</li>
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
@Setter
@Getter
@ToString
public class DevGuideVO extends CommonVO{
	
	//저장 방식
	private String saveType;
	
	//개발자가이드
	private List<Map<String,Object>> stdCtgryList;			// 표준 카테고리 리스트
	private String devlopGuideSn; 							// 개발 가이드 일련번호
	private String devlopGuideSj;							// 개발 가이드 제목
	private String devlopGuideCn; 							// 개발 가이드 내용
	private String devlopGuideVerValue; 					// 개발 가이드 버전 값
	private int fileSn; 									// 첨부 파일 일련번호
	
	private String beforeDevGuideSn;
	private String beforeDevGuideSj;
	private String nextDevGuideSn;
	private String nextDevGuideSj;
	
	//검색조건
	private String srchWord;
	
}
