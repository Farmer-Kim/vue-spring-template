package xxx.mgt.gm.api.manage.service;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xxx.mgt.gm.api.manage.mapper.InsttMapper;
import xxx.mgt.gm.api.manage.vo.InsttVO;

@Service
@Transactional(rollbackFor = Exception.class)
public class InsttService {
	
	private InsttMapper insttMapper;
	
	@Autowired
	public InsttService(InsttMapper insttMapper) {
		this.insttMapper = insttMapper;
	}
	
	/*
	 * 기관관리 목록 조회
	 * @param insttVO
	 * @return List<HashMap<String,Object>>
	 */
	public List<Map<String, Object>> manageList(InsttVO manageVO) {
		return insttMapper.insttManageList(manageVO);
	}

	public int manageListCnt(InsttVO manageVO) {
		// TODO Auto-generated method stub
		return insttMapper.insttManageListCnt(manageVO);
	}

	public void insttInsert(InsttVO insttVO) {
		// TODO Auto-generated method stub
		insttMapper.insttInsert(insttVO);
		
	}

}
