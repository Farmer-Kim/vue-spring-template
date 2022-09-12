package xxx.mgt.gm.api.notice.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xxx.mgt.gm.common.com.CommonVO;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 공지사항 업무</li>
 * <li>서브 업무명  = 공지사항 관리 관련</li>
 * <li>설	 명  = 공지사항 관리 vo</li>
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

@Setter
@Getter
@ToString
public class NoticeVO extends CommonVO{

	//공지사항
	private int noticeSn;			// 공지 일련번호
	private String noticeSj;		// 공지 제목
	private String noticeCn;		// 공지 내용
	private String fileSn;			// 파일 일련번호
	private int noticeRdCnt;		// 공지 조회수
	private String creatDt;		// 생성 일시
	private int crtrSn;			// 생성자 일련번호
	private String updtDt;			// 수정 일시
	private int updusrSn;			// 수정자 일련번호
	
	private String userNm;			// 작성자 이름
	private String fileNm;			// 파일 이름
	private int beforeNoticeSn;	// 이전 공지 번호
	private String beforeNoticeSj;// 이전 제목
	private int nextNoticeSn;		// 다음 공지 번호
	private String nextNoticeSj;	// 다음 제목

	//검색어
	private String srchType;		// 검색구분 1 : 제목 / 2 : 내용
	private String srchWord;		// 검색어

	//리스트
	//private List<Map<String, Object>> notice_List;	// 공지사항 리스트

}
