package xxx.mgt.gm.api.manage.mapper;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import xxx.mgt.gm.api.manage.vo.InsttVO;

@Mapper
@Repository
public interface InsttMapper {
	/**
	 * 기관관리 목록 조회
	 * @param insttVO
	 */
	public List<Map<String,Object>> insttManageList(InsttVO insttManageVO);
	
	/**
	 * 기관관리 목록 조회 카운트
	 * @param insttVO
	 */
	public int insttManageListCnt(InsttVO insttManageVO);
	

  /**
   * 기관 등록
   * @param insttVO
   */
	public int insttInsert(InsttVO insttVO);
	
}
