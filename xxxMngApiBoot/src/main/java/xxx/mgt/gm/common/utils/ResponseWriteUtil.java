package xxx.mgt.gm.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 공통 업무</li>
 * <li>서브 업무명  = 공통 유틸 관리</li>
 * <li>설	 명  = 응답 메시지 관련 유틸 class</li>
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
public class ResponseWriteUtil {
  
  /**
   * Alert창을 띄움
   * 
   * @param response
   * @throws IOException
   */
  public static void alert(HttpServletResponse response, String msg) throws IOException {
    msg = toJS(msg);
    StringBuffer sb = new StringBuffer();
    sb.append("<script> alert(\"").append(msg).append("\");</script>");
    
    callPage(response, sb.toString());
  }
	
	/**
	 * Alert창을 띄운 후, 팝업을 닫음
	 * 
	 * @param response
	 * @throws IOException
	 */
	public static void adminAlertAndPopupClose(HttpServletResponse response, String msg) throws IOException {
		msg = toJS(msg);
		StringBuffer sb = new StringBuffer();
		sb.append("<script> alert(\"").append(msg).append("\"); this.close();</script>");

		callPage(response, sb.toString());
	}
	
	/**
	 * Alert창을 띄운 후, 이전페이지로 이동
	 * 
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	public static void adminAlertAndHistoryBack(HttpServletResponse response, String msg) throws IOException {
		msg = toJS(msg);
		StringBuffer sb = new StringBuffer();
		sb.append("<script> alert(\"").append(msg).append("\"); history.go(-1);</script>");
		callPage(response, sb.toString());
	}

	
	/**
	 * Alert창을 띄운 후, 로그인 페이지로 이동
	 * 
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	public static void adminAlertAndLoginBack(HttpServletResponse response, String msg) throws IOException {
		msg = toJS(msg);
		StringBuffer sb = new StringBuffer();
		sb.append("<script> alert(\"").append(msg).append("\"); location.href='/login';</script>");
		callPage(response, sb.toString());
	}
	
	private static void callPage(HttpServletResponse res, String msg) throws IOException {
		res.setContentType(MediaType.TEXT_HTML_VALUE + "; charset=" + StandardCharsets.UTF_8.name());
		PrintWriter pw = res.getWriter();
		
		try {
			pw.println(msg);
		} finally {
			if (pw != null)
				pw.close();
		}
	}
	
	public static void print(HttpServletRequest request, HttpServletResponse response, String mapperString) throws IOException {
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.getWriter().print(mapperString);
        response.getWriter().flush();
	}
	
	/**
	 * 다음과 같은 변환을 한다. <li>\n : \\n <li>\"; : \\\"" <li>\'; : \\\' <br>
	 * str을 javascript의 "" 안에 들어갈 수 있도록 encode한다.<br>
	 * javascript의 '' 안에는 들어갈 수 없다. '' 는 특수 문자를 허용하지 않기 때문이다.
	 */
	public static String toJS(String str) {
		if (str == null)
			return "";
		StringBuffer sb = new StringBuffer(str.length() * 3 / 2);
		for ( int i = 0 ; i < str.length() ; ++i ) {
			char c = str.charAt(i);
			switch (c) {
				case '\n' :
					sb.append("\\n");
					break;
				case '\"' :
					sb.append("\\\"");
					break;
				case '\'' :
					sb.append("\\\'");
					break;
				default :
					sb.append(c);
					break;
			}
		}
		return sb.toString();
	}
}
