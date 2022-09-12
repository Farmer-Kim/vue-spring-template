package xxx.mgt.gm.common.file;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 파일 업로드 업무</li>
 * <li>서브 업무명  = 파일 업로드 구분 ENUM</li>
 * <li>설	 명  = 파일 업로드 구분 ENUM class</li>
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
public enum FileUploadType {
	/**
	 * 일반
	 */
	GENERIC(null),
	/**
	 * 지식문서
	 */
	KNOWLEDGE_DOCUMENT("html"),
	/**
	 * 지식문서
	 */
	KNOWLEDGE_GENERIC(null),
	/**
	 * 웹에디터
	 */
	WEB_EDITOR(null),
	;

	FileUploadType(String extension) {
		this.extension = extension;
	}

	private String extension;

	public String extension() {
		return extension;
	}
}
