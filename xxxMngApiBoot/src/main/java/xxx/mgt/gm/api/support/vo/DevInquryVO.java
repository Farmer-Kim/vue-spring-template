package xxx.mgt.gm.api.support.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xxx.mgt.gm.common.com.CommonVO;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = Q&A 업무</li>
 * <li>서브 업무명  = Q&A 관련</li>
 * <li>설	 명  = Q&A vo</li>
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
@Setter
@Getter
@ToString
public class DevInquryVO extends CommonVO {
	private int InqrySn;
	private int InqryUserSn;
	private int fileSn;
	private String inqryCn;
	private String secretAt;
	private String answerAt;
	private String answerCn;
	private int answerUserSn;
	private String answerDt;
	private String creatDt;
	private int crtrSn;
	private String updtDt;
	private int updusrSn;
	private String inqrySj;
	private String srchWord;
	private String srchType;
	private String userId;
	private int beforeInqrySn;
	private String beforeInqrySj;
	private int nextInqrySn;
	private String nextInqrySj;
	private int inqryIndex;
	private String answerUserId;
}
