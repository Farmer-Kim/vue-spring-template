package xxx.mgt.gm.api.support.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import xxx.mgt.gm.api.support.vo.DevGuideVO;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 개발자 가이드 업무</li>
 * <li>서브 업무명  = 개발자 가이드 관련</li>
 * <li>설	 명  = 개발자 가이드 mapper</li>
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
@Mapper
@Repository
public interface DevGuideMapper {

	  /**
	   * 개발자 가이드 목록 조회
	   * @param DevGuideVO
	   */
	  public List<HashMap<String, Object>> devGuideList(DevGuideVO devGuideVO);
	  
	  /**
	   * 개발자 가이드 목록 조회 카운트
	   * @param DevGuideVO
	   */
	  public int devGuideListCnt(DevGuideVO devGuideVO);
	  
	  /**
	   * 개발자 가이드 등록
	   * @param DevGuideVO
	   */
	  public int devGuideInsert(DevGuideVO devGuideVO);
	  
	  /**
	   * 개발자 가이드 최신 순번
	   * @param DevGuideVO
	   */
	  public int getMaxDevlopGuideSn();
	  
	  /**
	   * 개발자 가이드 업데이트
	   * @param DevGuideVO
	   */
	  public int devGuideUpdate(DevGuideVO devGuideVO);
	  
	  /**
	   * 개발자 가이드 삭제
	   * @param DevGuideVO
	   */
	  public int devGuideDel(DevGuideVO devGuideVO);
	  
	  /**
	   * 표준 카테고리 상세 조회
	   * @param stdCtgryVO
	   */
	  public DevGuideVO devGuideView(DevGuideVO devGuideVO);
	  
}

