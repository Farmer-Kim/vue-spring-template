package xxx.mgt.gm.api.support.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import xxx.mgt.gm.api.support.vo.DevInquryVO;

@Mapper
@Repository
public interface DevInquryMapper {

	public List<HashMap<String, Object>> devInquryList(DevInquryVO devInquryVO);

	public int devInquryListCnt(DevInquryVO devInquryVO);

	public DevInquryVO devInquryView(DevInquryVO devInquryVO);

	public void devInquryInsert(DevInquryVO devInquryVO);

	public void devInquryUpdate(DevInquryVO devInquryVO);

	public void devInquryDel(DevInquryVO devInquryVO);

}
