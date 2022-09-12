package xxx.mgt.gm.api.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import xxx.mgt.gm.api.notice.vo.NoticeVO;


/***************************************************
 * <ul>
 * <li>업무 그룹명  = 공지사항 업무</li>
 * <li>서브 업무명  = 공지사항 관리 관련</li>
 * <li>설	 명  = 공지사항 관리 mapper</li>
 * <li>작 성 자 = Lee Yun Je</li>
 * <li>작  성  일  = 2021. 09. 06.</li>
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
public interface NoticeMapper {

	  /**
	   * 공지사항 등록
	   * @param NoticeVO
	   */
	  public int noticeInsert(NoticeVO noticeVO);

	  /**
	   * 공지사항 업데이트
	   * @param NoticeVO
	   */
	  public int noticeUpdate(NoticeVO noticeVO);

	  /**
	   * 공지사항 삭제
	   * @param NoticeVO
	   */
	  public int noticeDelete(NoticeVO noticeVO);

	  /**
	   *
	   * 공지사항 목록 조회
	   * @param NoticeVO
	   */
	  public List<Map<String, Object>> noticeList(NoticeVO noticeVO);

	  /**
	   *
	   * 공지사항 목록 조회 카운트
	   * @param NoticeVO
	   */
	  public int noticeListCnt(NoticeVO noticeVO);

	  /**
	   * 공지사항 상세 조회
	   * @param NoticeVO
	   */
	  public NoticeVO noticeView(NoticeVO noticeVO);
	  
	  /**
	   * 공지사항 조회수 증가
	   * @param NoticeVO
	   */
	  public void noticeCntUp(NoticeVO noticeVO);


}

