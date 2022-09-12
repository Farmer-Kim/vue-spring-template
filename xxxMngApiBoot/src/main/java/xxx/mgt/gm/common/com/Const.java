package xxx.mgt.gm.common.com;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 공통 상수 Class 업무</li>
 * <li>서브 업무명  = 공통 상수 관련</li>
 * <li>설	 명  = 공통 상수 Class 구현(메세지 아이디, 시스템 구분 등등)</li>
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
@Component
@RequiredArgsConstructor
public class Const {

	/** 메세지 아이디 정보 */
	public static class Message {
		public static final String COMMON_MESSAGE_NO_DATA 			= "common.message.no.data";			// 데이터가 없습니다.
		public static final String COMMON_MESSAGE_SEARCH 			= "common.message.search";			// 데이터가 없습니다.
		public static final String COMMON_MESSAGE_INSERT 			= "common.message.insert";			// 정상적으로 등록 하였습니다.
		public static final String COMMON_MESSAGE_UPDATE 			= "common.message.update";			// 정상적으로 수정 하였습니다.
		public static final String COMMON_MESSAGE_DELETE 			= "common.message.delete";			// 정상적으로 삭제 하였습니다.
		public static final String COMMON_MESSAGE_SAVE 				= "common.message.save";			// 정상적으로 저장 하였습니다
		public static final String COMMON_MESSAGE_NO_DELETE 		= "common.message.no.delete";		// 삭제할 데이터가 없습니다.
		public static final String COMMON_MESSAGE_NOT_MOV 			= "common.message.not.mov";			// 이동이 불가능 합니다
		public static final String COMMON_MESSAGE_REQUIRED_DATA 	= "common.message.required.data";	// {0}는/은 필수 입력값 입니다.

		public static final String COMMON_MESSAGE_ERROR 			= "common.message.error"; 			// '에러가 발생하였습니다. 관리자에게 문의하여 주십시오.'
		public static final String CODE_MESSAGE_UPPR_NO_EXIST_ERROR = "code.message.uppr.no.exist.error"; // '에러가 발생하였습니다. 상위 코드가 존재하지 않습니다.'
		public static final String CODE_MESSAGE_LOWR_EXIST_ERROR 	= "code.message.lowr.exist.error"; 	//  '에러가 발생하였습니다. 하위 코드가 존재합니다.'
		public static final String CODE_MESSAGE_DUP_ERROR 			= "code.message.dup.error"; 		// '에러가 발생하였습니다. 중복되는 코드입니다.'
		public static final String ORDER_MESSAGE_NO_EXIST_ERROR 	= "order.message.no.exist.error"; 	//  '주문이 존재하지 않습니다. 관리자에게 문의하여 주십시오.'
		public static final String AUTH_MESSAGE_MENU_NO_DATA 		= "auth.message.menu.no.data"; 		// '해당 권한에 메뉴가 존재하지 않습니다. 관리자에게 문의하여 주십시오.'
		public static final String DATE_MESSAGE_APPL_ERROR 			= "date.message.appl.error"; 		// '선택된 적용일자를 사용할 수 없습니다. 관리자에게 문의하여 주십시오.'
		public static final String EXCEL_MESSAGE_NOT_DOC_ERROR 		= "excel.message.not.doc.error"; 	// '추출 할 Excel 문서가 없습니다.'
		public static final String EXCEL_MESSAGE_NOT_COLUMN_ERROR 	= "excel.message.not.column.error"; // '추출 할 항목명들이 존재 하지 않습니다.'
		public static final String EXCEL_MESSAGE_NOT_SHEET_ERROR 	= "excel.message.not.sheet.error"; 		// '추출 할 {0}번째 시트가 존재 하지 않습니다.'
		public static final String EXCEL_MESSAGE_NOT_MBSH_ERROR 	= "excel.message.not.mbsh.error"; 	// '추출 대상이 회원 데이터와 일치 하지 않습니다.'

		public static final String COMMON_MESSAGE_PARAMETER_ERROR	= "common.message.parameter.error";	// 파라메터 오류입니다.

	}

	/** 케릭터셋 */
	public static class CharacterSet {
		public static final String EUC_KR 	= "euc-kr";	 // euc-kr
		public static final String UTF_8 	= "utf-8";	 // utf-8
	}
	/**
	 * 표준 메시지
	 */
	public static final String GENERIC_MESSAGE = "_GENERIC_MESSAGE";
	/**
	 * 로그 제외 파라미터명
	 */
	public static final String IGNORE_LOGGING = "__ignoreLogging";
	/**
	 * "/" 슬래시 변수
	 */
	public static final String SLASH = "/";
	/**
	 * "EUC-KR" 변수
	 */
	public static final String EUC_KR = "EUC-KR";
	/**
	 * "html" 변수
	 */
	public static final String HTML = "html";
	/**
	 * 엑셀 다운로드 Map 헤더정보 key 명
	 */
	public static final String EXCEL_DOWNLOAD_MAP_HEADERS = "headers";
	/**
	 * 엑셀 다운로드 Map 헤더 머지 정보 key 명
	 */
	public static final String EXCEL_DOWNLOAD_MAP_HEADERS_MERGE = "headers_merges";
	/**
	 * 엑셀 다운로드 Map 바디 정보 key 명
	 */
	public static final String EXCEL_DOWNLOAD_MAP_BODY = "body";
	/**
	 * 지식 관리자 메인 URI
	 */
	public static final String KNWLG_ADMR_HOME_URI_ENDPOINT = "/knwlg/admr";
	/**
	 * 지식 사용자 메인 URI
	 */
	public static final String KNWLG_USER_HOME_URI_ENDPOINT = "/knwlg/user";
	/**
	 * 통합검색 URI
	 */
	public static final String ENGINE_INTERGRATION_FIND = "/engin/search";

}
