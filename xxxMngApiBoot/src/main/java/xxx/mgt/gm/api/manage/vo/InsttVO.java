package xxx.mgt.gm.api.manage.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xxx.mgt.gm.common.com.CommonVO;

@Setter
@Getter
@ToString
public class InsttVO extends CommonVO {
	//기관관리
	private int insttSn; // 기관 일련번호
	private String insttNm; // 기관 명
	private String insttTyCode; // 기관 유형 코드 
	private String reprsntTelno; // 대표 전화 번호
	private String insttZip; // 기관 우편 번호
	private String insttAdres; // 기관 주소
	private String insttDetailAdres; // 기관 상세 주소
	private String creatDt; // 생성 일시
	private int crtrSn; // 생성자 일련번호
	private String updtDt; // 수정 일시
	private int updusrSn; // 수성자 일련번호
	
	// 검색어 
	private String srchType; // 검색구분 1 : 기관명 / 2 : 유형코드 / 3 : 대표전화 / 4 : 주소 
	private String srchWord; // 검색어
}
