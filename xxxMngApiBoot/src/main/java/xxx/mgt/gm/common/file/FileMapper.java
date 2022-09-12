package xxx.mgt.gm.common.file;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 파일 업무</li>
 * <li>서브 업무명  = 파일 관리 관련</li>
 * <li>설	 명  = 파일 관리 mapper</li>
 * <li>작  성  자  = Lee Yun Je</li>
 * <li>작  성  일  = 2021. 08. 25.</li>
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
public interface FileMapper {

	/**
	 * 파일 리스트 조회
	 *
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getFileList(Map<String, Object> params);

	/**
	 * 지식 파일 html 조회
	 *
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getFileKnwlg(Map<String, Object> params);

	/**
	 * 파일 상세정보 조회
	 *
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<HashMap<String, Object>> getFileDetail(FileVO params);
	
	/**
	 * 파일 ATACH_SEQ 최대값 조회
	 *
	 * @param params
	 * @return
	 * @throws Exception
	 */
	int getMaxAtachSeq(String atach_id);
	
	/**
	 * 파일 상세정보 등록
	 *
	 * @param filelist
	 * @return
	 * @throws Exception
	 */
	int insertFileInfo(Map<String, Object> params);
	
	/**
	 * 파일 ATACH_ID 정보 변경
	 *
	 * @param filelist
	 * @return
	 * @throws Exception
	 */
	int updateFileDetailAtachId(Map<String, Object> params);
	
	/**
	 * 파일 상세정보 삭제
	 *
	 * @param filelist
	 * @return
	 * @throws Exception
	 */
	int deleteFileDetail(Map<String, Object> params);
	
	
	/**
	 * 파일 상세정보 삭제
	 *
	 * @param filelist
	 * @return
	 * @throws Exception
	 */
	List<HashMap<String, Object>> fileInfoList(FileVO params);
	
	/**
	 * 파일ID 생성
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	String getAtachIdSeq();
}
