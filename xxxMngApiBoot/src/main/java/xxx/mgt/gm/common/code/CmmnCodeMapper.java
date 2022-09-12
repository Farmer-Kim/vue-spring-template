package xxx.mgt.gm.common.code;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 공통코드 조회(공통기능)</li>
 * <li>서브 업무명  = 공통코드 조회(공통기능) 관련</li>
 * <li>설	 명  = 공통코드 조회(공통기능) mapper</li>
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

@Mapper
@Repository
public interface CmmnCodeMapper {

	/**
	 * 공통코드 조회(컴포넌트 사용)
	 * @param commCodeVO
	 * @return List<HashMap<String, Object>>
	 */
	public List<HashMap<String, Object>> cmmnCodeList(CmmnCodeVO commCodeVO);
	
}
