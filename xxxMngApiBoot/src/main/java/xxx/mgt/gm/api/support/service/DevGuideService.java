package xxx.mgt.gm.api.support.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import xxx.mgt.gm.api.support.mapper.DevGuideMapper;
import xxx.mgt.gm.api.support.vo.DevGuideVO;
import xxx.mgt.gm.common.utils.StringUtil;


/***************************************************
 * <ul>
 * <li>업무 그룹명  = 개발자 가이드 업무</li>
 * <li>서브 업무명  = 개발자 가이드 관련</li>
 * <li>설	 명  = 개발자 가이드 service</li>
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
@Service
@Transactional(rollbackFor = Exception.class)
public class DevGuideService {

	private DevGuideMapper devGuideMapper;

	@Autowired
	public DevGuideService(DevGuideMapper devGuideMapper) {
		this.devGuideMapper = devGuideMapper;
	}

	
	/**
	 * 개발자 가이드 목록 조회
	 * 
	 * @param DevGuideVO
	 * @return List<HashMap<String, Object>>
	 */
	public List<HashMap<String, Object>> devGuideList(DevGuideVO devGuideVO) {
		return devGuideMapper.devGuideList(devGuideVO);
	}
	
	/**
	 * 개발자 가이드 목록 조회 카운트
	 * 
	 * @param DevGuideVO
	 * @return int
	 */
	public int devGuideListCnt(DevGuideVO devGuideVO) {
		return devGuideMapper.devGuideListCnt(devGuideVO);
	}
	
	/**
	 * 개발자 가이드 등록
	 * 
	 * @param DspyCtgryVO
	 */
//	public void devGuideRegist(DevGuideVO devGuideVO) {
//		
//		if() {
//			devGuideMapper.devGuideInsert(devGuideVO);		
//		}else {
//			devGuideMapper.devGuideInsert(devGuideVO);
//		}
//	}
	
	/**
	 * 개발자 가이드 등록
	 * 
	 * @param DspyCtgryVO
	 */
	public void devGuideInsert(DevGuideVO devGuideVO) {
		devGuideMapper.devGuideInsert(devGuideVO);		
	}
	/**
	 * 개발자 가이드 등록
	 * 
	 * @param DspyCtgryVO
	 */
	public int getMaxDevlopGuideSn() {
		return devGuideMapper.getMaxDevlopGuideSn();		
	}

	/**
	 * 개발자 가이드 업데이트
	 * 
	 * @param DspyCtgryVO
	 * @throws Exception 
	 */
	public void devGuideUpdate(DevGuideVO devGuideVO) throws Exception {
		devGuideMapper.devGuideUpdate(devGuideVO);
	}
	
	/**
	 * 개발자 가이드 삭제
	 * 
	 * @param DspyCtgryVO
	 * @throws Exception 
	 */
	public void devGuideDel(DevGuideVO devGuideVO) throws Exception {
		devGuideMapper.devGuideDel(devGuideVO);
	}

	
	/**
	 * 개발자 가이드 상세 조회
	 * 
	 * @param DevGuideVO
	 * @return DevGuideVO
	 */
	public DevGuideVO devGuideView(DevGuideVO devGuideVO) {
		return devGuideMapper.devGuideView(devGuideVO);
	}

}
