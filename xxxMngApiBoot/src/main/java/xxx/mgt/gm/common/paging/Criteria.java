package xxx.mgt.gm.common.paging;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 공통 업무</li>
 * <li>서브 업무명  = 공통 페이징 관련</li>
 * <li>설	 명  = 페이징 계산식 class</li>
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
public class Criteria {
	
	// 특정 페이지 조회를 위한 클래스
	private int page; // 현재 페이지 번호
	private int perPageNum; // 페이지당 보여줄 게시글의 개수

	
	public int getPageStart() {
		// 특정 페이지의 범위를 정하는 구간, 현재 페이지의 게시글 시작 번호
		// 0 ~ 10 , 10 ~ 20 이런식으로
		return (this.page -1) * perPageNum;
	}

	public Criteria() {
		// 기본 생성자 : 최초 게시판에 진입시 필요한 기본값
		this.page = 1;
		this.perPageNum = 10;
	}

	// 현재 페이지 번호 page : getter, setter
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			
		} else {
			this.page = page;
		}	
	}

	
	// 페이지당 보여줄 게시글의 개수 perPageNum : getter, setter
	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		int cnt = this.perPageNum;
		
		if(perPageNum != cnt) {
			this.perPageNum = perPageNum;	
		} else {
			this.perPageNum = cnt;
		}
		
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
}
