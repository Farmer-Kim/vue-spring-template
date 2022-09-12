package xxx.mgt.gm.common.code;

import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 공통코드 조회(공통기능)</li>
 * <li>서브 업무명  = 공통코드 조회(공통기능) 관련</li>
 * <li>설	 명  = 공통코드 조회(공통기능) vo</li>
 * <li>작  성  자  = Park Ji Hwan</li>
 * <li>작  성  일  = 2021. 08. 27.</li>
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
public class CmmnCodeVO {
	private String groupId;
	private String groupNm;
	private String groupUseYn;
	private String codeId;
	private String codeNm;
	private String sortSn;
	private String codeUseYn;
	
	private List<HashMap<String, Object>> codeList;
	
	private String searchGroupId;
	
}
