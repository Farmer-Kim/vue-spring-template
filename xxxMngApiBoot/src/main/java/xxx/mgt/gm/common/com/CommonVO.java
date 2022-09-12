package xxx.mgt.gm.common.com;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xxx.mgt.gm.common.paging.Paging;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 공통 업무</li>
 * <li>서브 업무명  = 공통 페이징 관련</li>
 * <li>설	 명  = rownumber vo</li>
 * <li>작  성  자  = Lee Yun Je</li>
 * <li>작  성  일  = 2021. 08. 25.</li>
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
public class CommonVO extends Paging{
	
	private String rowNumber;	// 행번호
	private String regDt;		// 등록일시
	private String regId;		// 등록ID
	private String regUserNm;	// 등록자명
	private String regIp;		// 등록IP
	private String updtDt;		// 수정일시
	private String updtId;		// 수정ID
	private String updtUserNm;	// 수정자명
	private String updtIp;		// 수정IP
	
	private String creatDt;		// 생성 일시
	private int crtrSn;			// 생성자 일련번호
	//private String updtDt;		// 수정 일시
	private int updusrSn;		// 수정자 일련번호

}
