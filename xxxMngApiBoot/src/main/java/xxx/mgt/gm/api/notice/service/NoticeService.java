package xxx.mgt.gm.api.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xxx.mgt.gm.api.notice.mapper.NoticeMapper;
import xxx.mgt.gm.api.notice.vo.NoticeVO;


/***************************************************
 * <ul>
 * <li>업무 그룹명  = 공지사항 업무</li>
 * <li>서브 업무명  = 공지사항 관리 관련</li>
 * <li>설	 명  = 공지사항 관리 service</li>
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
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService {

	private NoticeMapper noticeMapper;

	@Autowired
	public NoticeService(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	/**
	 * 공지사항 등록
	 *
	 * @param NoticeVO
	 */
	public void noticeInsert(NoticeVO noticeVO) {
		noticeMapper.noticeInsert(noticeVO);
	}

	/**
	 * 공지사항 업데이트
	 *
	 * @param NoticeVO
	 */
	public void noticeUpdate(NoticeVO noticeVO) {
		noticeMapper.noticeUpdate(noticeVO);
	}

	/**
	 * 공지사항 삭제
	 *
	 * @param NoticeVO
	 */
	public void noticeDelete(NoticeVO noticeVO) {
		noticeMapper.noticeDelete(noticeVO);
	}

	/**
	 * 공지사항 목록 조회
	 *
	 * @param NoticeVO
	 * @return List<HashMap<String, Object>>
	 */
	public List<Map<String, Object>> noticeList(NoticeVO noticeVO) {
		return noticeMapper.noticeList(noticeVO);
	}
	
	/**
	 * 공지사항 목록 조회 카운트
	 *
	 * @param NoticeVO
	 * @return int
	 */
	public int noticeListCnt(NoticeVO noticeVO) {
		return noticeMapper.noticeListCnt(noticeVO);
	}

	/**
	 * 공지사항 상세 조회
	 *
	 * @param NoticeVO
	 * @return List<HashMap<String, Object>>
	 */
	public NoticeVO noticeView(NoticeVO noticeVO) {
		// 공지사항 조회수 증가
		noticeCntUp(noticeVO);
		return noticeMapper.noticeView(noticeVO);
	}
	
	/**
	 * 공지사항 조회수 증가
	 *
	 * @param NoticeVO
	 * @return List<HashMap<String, Object>>
	 */
	public void noticeCntUp(NoticeVO noticeVO) {
		noticeMapper.noticeCntUp(noticeVO);
	}
}
