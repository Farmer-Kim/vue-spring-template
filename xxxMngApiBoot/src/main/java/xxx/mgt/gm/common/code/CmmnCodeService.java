package xxx.mgt.gm.common.code;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 공통코드 조회(공통기능)</li>
 * <li>서브 업무명  = 공통코드 조회(공통기능) 관련</li>
 * <li>설	 명  = 공통코드 조회(공통기능) service</li>
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

@Service
public class CmmnCodeService {

	private CmmnCodeMapper cmmnCodeMapper;

	public CmmnCodeService(CmmnCodeMapper cmmnCodeMapper) {
		this.cmmnCodeMapper = cmmnCodeMapper;
	}
	
	/**
	 * 공통코드 조회(컴포넌트 사용)
	 * @param cmmnCodeVO
	 * @return List<HashMap<String, Object>>
	 */
	public List<HashMap<String, Object>> cmmnCodeList(CmmnCodeVO cmmnCodeVO) {
		return cmmnCodeMapper.cmmnCodeList(cmmnCodeVO);
	}
	
}
