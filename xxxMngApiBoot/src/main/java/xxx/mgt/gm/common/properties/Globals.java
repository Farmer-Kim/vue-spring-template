package xxx.mgt.gm.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import xxx.mgt.gm.common.utils.StringUtil;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 프로퍼티 업무</li>
 * <li>서브 업무명  = 서버별 프로퍼티 관리</li>
 * <li>설	 명  = 서버별 프로퍼티 정보 class</li>
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
@PropertySources(value = { 
		@PropertySource(value = { "classpath:globals/globals-${spring.profiles.active:local}.properties" }) 
})
public class Globals {
	private String serverProfile;
	@Value("${server.profile}")
	public void setServerProfile(String serverProfile) {
		this.serverProfile = serverProfile;
	}
	private String filePath;
	@Value("${file.path}")
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	private String filePathResourcePatterns;
	@Value("${file.path.resource.patterns}")
	public void setFilePathResourcePatterns(String filePathResourcePatterns) {
		this.filePathResourcePatterns = filePathResourcePatterns;
	}
	private String filePathUrl;
	@Value("${file.path.url}")
	public void setFilePathUrl(String filePathUrl) {
		this.filePathUrl = filePathUrl;
	}
	private String fileExclusiveExtList;
	@Value("${file.exclusive.ext.list}")
	public void setFileExclusiveExtList(String fileExclusiveExtList) {
		this.fileExclusiveExtList = fileExclusiveExtList;
	}
	private double fileMaxSize;
	@Value("${file.max.size}")
	public void setFileMaxSize(double fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}

	/**
	 * globals.properties - server.profile 값을 반환
	 *
	 * @return
	 */
	public String getServerProfile() {
		return this.serverProfile;
	}
	public boolean isServerLocal() {
		return StringUtil.equals(this.serverProfile, "local");
	}
	public boolean isServerDev() {
		return StringUtil.equals(this.serverProfile, "dev");
	}
	public boolean isServerProd() {
		return StringUtil.equals(this.serverProfile, "prod");
	}
	/**
	 * 파일 업로드 경로
	 *
	 * @return
	 */
	public String getFilePath() {
		return this.filePath;
	}
	/**
	 * 파일 업로드 리소스 패턴
	 *
	 * @return
	 */
	public String getFilePathResourcePatterns() {
		return this.filePathResourcePatterns;
	}
	/**
	 * 파일 업로드 URL 경로
	 *
	 * @return
	 */
	public String getFilePathUrl() {
		return this.filePathUrl;
	}
	/**
	 * 파일 업로드 불가 확장자 리스트
	 *
	 * @return
	 */
	public String getFileExclusiveExtList() {
		return this.fileExclusiveExtList;
	}
	/**
	 * 파일 업로드 최대 사이즈 (MB 기준)
	 *
	 * @return
	 */
	public double getFileMaxSize() {
		return this.fileMaxSize;
	}
	
}
