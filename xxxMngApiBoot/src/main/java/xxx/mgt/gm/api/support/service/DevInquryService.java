package xxx.mgt.gm.api.support.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xxx.mgt.gm.api.support.mapper.DevInquryMapper;
import xxx.mgt.gm.api.support.vo.DevGuideVO;
import xxx.mgt.gm.api.support.vo.DevInquryVO;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = Q&A 업무</li>
 * <li>서브 업무명  = Q&A 관련</li>
 * <li>설	 명  = Q&A service</li>
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
@Service
@Transactional(rollbackFor = Exception.class)
public class DevInquryService {
	
	private DevInquryMapper devInquryMapper;
	
	@Autowired
	public DevInquryService(DevInquryMapper devInquryMapper) {
		this.devInquryMapper = devInquryMapper;
	}

	public List<HashMap<String, Object>> devInquryList(DevInquryVO devInquryVO) {
		// TODO Auto-generated method stub
		return devInquryMapper.devInquryList(devInquryVO);
	}

	public int devInquryListCnt(DevInquryVO devInquryVO) {
		// TODO Auto-generated method stub
		return devInquryMapper.devInquryListCnt(devInquryVO);
	}

	public DevInquryVO devInquryView(DevInquryVO devInquryVO) {
		return devInquryMapper.devInquryView(devInquryVO);
	}

	public void devInquryInsert(DevInquryVO devInquryVO) {
		devInquryMapper.devInquryInsert(devInquryVO);
		
	}

	public void devInquryUpdate(DevInquryVO devInquryVO) {
		devInquryMapper.devInquryUpdate(devInquryVO);
		
	}

	public void devInquryDel(DevInquryVO devInquryVO) {
		devInquryMapper.devInquryDel(devInquryVO);
		
	}

}
