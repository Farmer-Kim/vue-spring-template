package xxx.mgt.gm.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import xxx.mgt.gm.common.com.Const;
import xxx.mgt.gm.common.properties.Globals;
import xxx.mgt.gm.common.utils.CalendarUtil;
import xxx.mgt.gm.common.utils.ServletUtil;
import xxx.mgt.gm.common.utils.StringUtil;

/***************************************************
 * <ul>
 * <li>업무 그룹명  = 파일 업무</li>
 * <li>서브 업무명  = 파일 관리 관련</li>
 * <li>설	 명  = 파일 관리 service</li>
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
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FileService {

	@Autowired
	private Globals globals;
	@Autowired
	private FileMapper fileMapper;

	/**
	 * 파일 업로드
	 *
	 * @param mphsr
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> upload(MultipartHttpServletRequest mphsr) throws Exception {
		return this.saveFileInfo(null, mphsr, FileUploadType.GENERIC, null, null, null);
	}
	
	/**
	 * 파일 업로드
	 *
	 * @param mphsr
	 * @param brforeAtachId : 이전 파일 id (ex : 공지사항 내용 수정 시 이전 atach_id) 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> upload(MultipartHttpServletRequest mphsr, String brforeAtachId) throws Exception {
		return this.saveFileInfo(brforeAtachId, mphsr, FileUploadType.GENERIC, null, null, null);
	}
	
	/**
	 * 파일 업로드
	 *
	 * @param mphsr
	 * @param brforeAtachId : 이전 파일 id (ex : 공지사항 내용 수정 시 이전 atach_id) 
	 * @param inputName : jsp input file name
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> upload(MultipartHttpServletRequest mphsr, String brforeAtachId, String inputName) throws Exception {
		return this.saveFileInfo(brforeAtachId, mphsr, FileUploadType.GENERIC, null, null, inputName);
	}
	

	/**
	 * 파일 저장
	 *
	 * @param brforeAtachId : 이전 파일 id (ex : 공지사항 내용 수정 시 이전 atach_id)
	 * @param mphsr
	 * @param fileUploadType
	 * @param selKnwlgTypeId
	 * @param newKnwlgId
	 * @param inputName
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> saveFileInfo(String brforeAtachId, MultipartHttpServletRequest mphsr, FileUploadType fileUploadType, String selKnwlgTypeId, String newKnwlgId, String inputName) throws Exception {
		Iterator<String> iterator = mphsr.getFileNames();
		brforeAtachId= "A001";
		List<MultipartFile> multipartFileList = null;
		Map<String, Object> subParams = null;
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String originalFileName = null;
		String storedFileName = null;
		String defaultFilePath = globals.getFilePath();
		String storedFilePath = this.getFileUploadPath(fileUploadType, selKnwlgTypeId, newKnwlgId);
		String fullFilePath = defaultFilePath + storedFilePath;

		log.debug("fullFilePath======>" + fullFilePath);

		// filePathBlackList() 저장경로 변경체크.
		File file = new File(filePathBlackList(fullFilePath));

		// 파일 저장경로 생성..
		this.createFolder(file);

		while (iterator.hasNext()) {
			//mybatis dml result
			int rs = 0;
			//파일 seq
			int atachSeq = 1;
			// 파일ID 생성
			String atachId = "A001";
			returnMap.put("atachId", atachId);
			multipartFileList = mphsr.getFiles(iterator.next());
			for(int i=0; i<multipartFileList.size(); i++) {
				MultipartFile multipartFile = multipartFileList.get(i);
				
				//input file name 있으면 패스
				if(StringUtil.isNotEmpty(inputName)
						&& ! inputName.equals(multipartFile.getName()) && inputName != null) {
					continue;
				}

				//이전 파일 ID가 있으면
				if(i == 0 && StringUtil.isNotEmpty(brforeAtachId)) {
					//파일 seq = 파일 ATACH_SEQ 최대값 조회결과 + 1
					atachSeq = fileMapper.getMaxAtachSeq(brforeAtachId) + 1;
					//파일 ATACH_ID 정보 변경
					subParams = new HashMap<>();
					subParams.put("new_atach_id", atachId);
					subParams.put("atach_id", brforeAtachId);
					rs = fileMapper.updateFileDetailAtachId(subParams);
					//XXX : 파일 업로드 후 파일이 전부 삭제 된 경우 0건으로 처리되는 경우도 있으므로 주석 처리
					////KzoneAssert.state(rs > 0, "이전 파일정보 ID 변경을 실패하였습니다.", Exception.class);
				}
				
				// 파일이 있는 경우만 수행
				if (multipartFile.isEmpty() == false) {
					//파일 정보는 atachId 기준 1개씩 반환
					if(i == 0) {
						returnMap.put(multipartFile.getName(), atachId);
					}
					
					// 파일명 획득.
					originalFileName = multipartFile.getOriginalFilename();
					// --------------------------------------
					// 원 파일명이 없는 경우 처리
					// (첨부가 되지 않은 input file type)
					// --------------------------------------
					if (StringUtil.isEmpty(originalFileName)) {
						continue;
					}
					//// ------------------------------------
					
					// 파일 확장자 및 사이즈 체크 ...(확장자리스트, 업로드 사이즈 property 참조.)
					validateFile(fileUploadType, multipartFile);
					
					// 파일의 저장파일명 랜덤생성.
					storedFileName = this.getFileUploadNm();
					
					//FIXME - 파일을 LOOP로 여러번 저장 할 경우 에러발생하여 업로드 방식을 아래로 변경
					//// 파일생성.
					//file = new File(this.filePathBlackList(fullFilePath + storedFileName + "." + this.getFileExt(fileUploadType, multipartFile.getOriginalFilename())));
					//// 파일 저장.
					//multipartFile.transferTo(file);

					//파일저장
					Path copyOfLocation = Paths.get(this.filePathBlackList(fullFilePath + storedFileName + "." + this.getFileExt(fileUploadType, multipartFile.getOriginalFilename())));
					Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
					
					// 파일 정보 DB 저장 위래 정보 return.
					subParams = this.getFileMap(fileUploadType, atachSeq, multipartFile, storedFilePath, storedFileName);
					subParams.put("atach_id", atachId);
					if(fileUploadType == FileUploadType.WEB_EDITOR) {
						returnMap.put("file_name", subParams.get("file_name"));
						returnMap.put("url", subParams.get("url"));
					} 
					
					// 파일 정보 DB 저장
					rs = fileMapper.insertFileInfo(subParams);
					//KzoneAssert.state(rs > 0, "파일정보 DB 저장을 실패하였습니다.", Exception.class);
					
					atachSeq++;
				}
			}
		}
		return returnMap;
	}
	
	/**
	 * 폴더 생성
	 *
	 * @param file
	 * @throws Exception
	 */
	private void createFolder(File file) throws Exception {
		if (!file.exists() || file.isFile()) {
			// 시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			if (! file.mkdirs()) {
				throw new Exception("폴더 생성을 실패하였습니다.");
			}
		}
	}
	
	/**
	 * 메소드 상세 설명
	 *
	 * @param fileUploadType : 파일업로드 구분 ENUM
	 * @param atachSeq : 파일 seq
	 * @param multipartFile : 업로드 파일
	 * @param storedFilePath : 저장 경로
	 * @param storedFileName : 저장 파일명
	 * @return
	 * @throws Exception 
	 */
	private Map<String, Object> getFileMap(FileUploadType fileUploadType, int atachSeq, MultipartFile multipartFile, String storedFilePath, String storedFileName) throws Exception {
		String originalFileName = multipartFile == null ? "" : multipartFile.getOriginalFilename();
		if(originalFileName.indexOf("\\") > -1) {
			String[] aFileName = originalFileName.split("\\\\");
			originalFileName = aFileName[aFileName.length-1];
		}
		String fileEtsionExptName = this.getEtsionExptNm(fileUploadType, originalFileName, storedFileName);
		String etsionName = this.getFileExt(fileUploadType, originalFileName);
		String fileName = fileUploadType == FileUploadType.KNOWLEDGE_DOCUMENT ? fileEtsionExptName : originalFileName; 
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("atach_seq", atachSeq++);
		listMap.put("file_name", fileName);
		listMap.put("file_store_name", storedFileName);
		listMap.put("file_store_path", storedFilePath);
		listMap.put("file_etsion_expt_name", fileEtsionExptName);
		listMap.put("etsion_name", etsionName);
		listMap.put("file_size", multipartFile == null ? 0 : multipartFile.getSize());
		listMap.put("url", globals.getFilePathUrl() + storedFilePath + storedFileName + "." + etsionName);
		return listMap;
	}

	private String filePathBlackList(String value) {

		if (StringUtil.isEmpty(value)) {
			return "";
		}

		String returnValue = StringUtil.getString(value);

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\

		return returnValue;

	}
	
	private String getEtsionExptNm(FileUploadType fileUploadType, String fname, String storedFileName) throws Exception {
		switch(fileUploadType) {
			//일반
			case GENERIC:
			//웹에디터
			case WEB_EDITOR:
			//지식일반
			case KNOWLEDGE_GENERIC:
				if (StringUtil.isNotEmpty(fname) && fname != null) {
					int lstIn = fname.lastIndexOf('.');
					return fname.substring(0, lstIn);
				}
			//지식문서
			case KNOWLEDGE_DOCUMENT:
				return storedFileName;
			default:
				throw new Exception("정의되지 않은 경로");
		}
	}

	/**
	 * 파일 확장자 및 사이즈 검사
	 * 
	 * @param file
	 * @param fileUploadType, file
	 * @throws Exception 
	 */
	private void validateFile(FileUploadType fileUploadType, MultipartFile file) throws Exception {
		log.debug("file ori name, {} 파일업로드불가확장자리스트." + globals.getFileExclusiveExtList());
		log.debug("file ori name, {} 파일사이즈체크." + globals.getFileMaxSize());
		log.debug("file ori name, {} 파일체크");

		String fileExt = getFileExt(fileUploadType, file.getOriginalFilename());
		//CommonUtils.encodingTest(file.getOriginalFilename());
		String originalFileName = new String(file.getOriginalFilename().getBytes(StandardCharsets.UTF_8));
		log.debug("file ori name, {} 파일명 : ===>" + originalFileName);

		if ((originalFileName.indexOf("\0") > -1) || (originalFileName.indexOf(";") > -1)
				|| (originalFileName.indexOf("./") > -1) || (originalFileName.indexOf(".\\") > -1)) {
			//사용자에게 알림을 주기 위해 Exception 사용
			//throw new Exception("업로드가 불가한 파일명입니다.");
			throw new Exception("업로드가 불가한 파일명입니다.");
		}

		String[] arrExclusiveExtList = globals.getFileExclusiveExtList().split(",");
		boolean isValidExt = false;
		for (String ext : arrExclusiveExtList) {
			if(! StringUtil.equals(ext, fileExt.toLowerCase())) {
				isValidExt = true;
				log.debug("isValidExt0 ==== |{}|" + isValidExt);
				break;
			}
		}

		if (!isValidExt) {
			log.debug("isValidExt2 ==== |{}|" + isValidExt);
			//사용자에게 알림을 주기 위해 Exception 사용
			//throw new Exception("업로드 할 수 없는 파일유형입니다.");
			throw new Exception("업로드가 불가한 파일유형입니다.");
		}

		if (file.getSize() / 1024 / 1024 > globals.getFileMaxSize()) {
			//사용자에게 알림을 주기 위해 Exception 사용
			//throw new Exception("파일 사이즈를 초과하였습니다.");
			throw new Exception("최대 파일 사이즈 " + globals.getFileMaxSize() + "mb를 초과하였습니다.");
		}
	}

	/**
	 * 파일의 확장자 추출
	 *
	 * @param fileUploadType
	 * @param fname
	 * @return
	 * @throws Exception 
	 */
	private String getFileExt(FileUploadType fileUploadType, String fname) throws Exception {
		switch(fileUploadType) {
			//일반
			case GENERIC:
			//웹에디터
			case WEB_EDITOR:
			//지식일반
			case KNOWLEDGE_GENERIC:
				if (StringUtil.isNotEmpty(fname) && fname != null) {
					int lstIn = fname.lastIndexOf('.');
					String ext = fname.substring(lstIn + 1);
					return ext.toLowerCase();
				}
			//지식문서
			case KNOWLEDGE_DOCUMENT:
				return "html";
			default:
				throw new Exception("정의되지 않은 경로");
		}
	}

	/**
	 * 업로드 파일경로 반환
	 *
	 * @param fileUploadType
	 * @param selKnwlgTypeId
	 * @param newKnwlgId
	 * @return
	 * @throws Exception 
	 */
	private String getFileUploadPath(FileUploadType fileUploadType, String selKnwlgTypeId, String newKnwlgId) throws Exception {
		switch(fileUploadType) {
			//일반
			case GENERIC:
			//웹에디터
			case WEB_EDITOR:
				return CalendarUtil.getCurrentSimpleDate() + Const.SLASH;
			//지식문서
			case KNOWLEDGE_DOCUMENT:
			//지식일반
			case KNOWLEDGE_GENERIC:
				//return selKnwlgTypeId + "_2" + Const.SLASH + newKnwlgId + Const.SLASH + newKnwlgId + "_10";
				return selKnwlgTypeId + "_2" + Const.SLASH + newKnwlgId + "_10"+ Const.SLASH ;
			default:
				throw new Exception("정의되지 않은 경로");
		}
	}
	
	/**
	 * 업로드 파일명 반환
	 *
	 * @return
	 */
	private String getFileUploadNm() {
		// 파일명
		// 시간으로만 파일명 생성시 중복발생하여 random 숫자 추가
		String tmpName1 = String.valueOf(Math.round(Math.random() * 1000));
		String tmpName = convertFormat(tmpName1);
		String filename = "" + System.currentTimeMillis() + tmpName;
		return filename;
	}

	/**
	 * 파일명 생성시, 파일명 자리수를 맞춰준다
	 *
	 * @param str
	 * @return
	 */
	private String convertFormat(String str) {
		String format = "0000";
		int strLen = str.length();
		int formatLen = format.length();
		int cnt = formatLen - strLen;
		String resultStr = str;
		String addStr = format.substring(0, 1);
		for (int inx = 0; inx < cnt; inx++) {
			resultStr = addStr + resultStr;
		}
		return resultStr;
	}
	
	/**
	 * 파일ID 생성
	 *
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public String getAtachIdSeq() throws Exception {
		return fileMapper.getAtachIdSeq();
	}
	
	/**
	 * 파일 정보 등록
	 *
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int insertFileInfo(Map<String, Object> params) throws Exception {
		return fileMapper.insertFileInfo(params);
	}
	
	/**
	 * 파일 리스트 조회
	 *
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getFileList(Map<String, Object> params) throws Exception {
		return fileMapper.getFileList(params);
	}
	
	/**
	 * 파일 리스트 조회
	 *
	 * @param atach_id
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getFileList(String atach_id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("atach_id", atach_id);
		return fileMapper.getFileList(params);
	}
	
	/**
	 * 파일 상세정보 조회
	 *
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getFileDetail(FileVO params) throws Exception {
		return fileMapper.getFileDetail(params);
	}
	
	/**
	 * 파일 다운로드
	 *
	 * @param fileVo
	 * @param response
	 * @throws Exception
	 */
	public void download(FileVO fileVo, HttpServletResponse response) throws Exception {
		//파일 상세정보 조회
		List<HashMap<String, Object>> fileMap = fileMapper.getFileDetail(fileVo);
		File file = new File(this.getPhysicalFilePath(fileMap.get(0)));
		if (!file.isFile()) {
			//사용자에게 알림을 주기 위해 Exception 사용
			throw new Exception("파일이 존재하지 않습니다.");
		}
		String fileName = StringUtil.getString(fileMap.get(0).get("FILE_NAME"));
		int fileByte = StringUtil.getInt(fileMap.get(0).get("FILE_SIZE"));
		//브라우저 종류에 따라 한글을 인코딩하여 리턴한다.
		String disposition = ServletUtil.getEncodingFilename(fileName, true, false);
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		response.setHeader("Content-Disposition", disposition);
		
		FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		response.getOutputStream().write(fileByte); 
		response.getOutputStream().close();
	}
	
	/**
	 * 물리 파일 경로 반환
	 *
	 * @param fileMap : fileMapper.getFileDetail 조회 결과 맵
	 * @return
	 * @throws Exception
	 */
	private String getPhysicalFilePath(Map<String, Object> fileMap) throws Exception {
		//사용자에게 알림을 주기 위해 Exception 사용
		//KzoneAssert.notEmpty(fileMap, "파일정보가 존재하지 않습니다.", Exception.class);
		String storedFilePath = StringUtil.getString(fileMap.get("FILE_STORE_PATH"));
		String storedFileName = StringUtil.getString(fileMap.get("FILE_STORE_NAME"));
		String etsionName = StringUtil.getString(fileMap.get("ETSION_NAME"));
		String filePath = globals.getFilePath() + storedFilePath + Const.SLASH + storedFileName;
		//html 파일이 아니면 파일경로에 확장자 추가
		if(! etsionName.toLowerCase().equals(Const.HTML)) {
			//FIXME - 서버에 기 업로드 된 파일중 확장자 없는/있는 파일이 둘다 존재하여 분기 추가함
			File file = new File(filePath);
			if (! file.exists()) {
				filePath += "." + etsionName;
			}
		}
		log.info("@@ filePath-->"+filePath);
		log.info("@@ storedFilePath-->" + storedFilePath);
		log.info("@@ storedFileName-->" + storedFileName);
		return filePath;
	}

	/**
	 * 파일 삭제
	 *
	 * @param atach_id
	 * @param atach_seq
	 * @throws Exception
	 */
	public String fileListDelete(FileVO fileVo) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("atach_id", fileVo.getAtachId());
		params.put("atach_seq", fileVo.getAtachSeq());
		List<HashMap<String, Object>> fileMap = fileMapper.getFileDetail(fileVo);
		int result = 0;
		if (fileMap.get(0) != null) {
			// DB 파일정보 삭제
			result = fileMapper.deleteFileDetail(params);
			//KzoneAssert.state(result > 0, "파일 정보 삭제를 실패하였습니다.", Exception.class);

			//파일
			File file = new File(this.getPhysicalFilePath(fileMap.get(0)));
			// 파일 삭제
			if (file.exists()) {
				file.delete();
			}
		}
		return "SUCCESS";
	}
	
	/**
	 * 파이리스트
	 * 
	 * @param StdCtgryMngVO
	 * @return List<HashMap<String, Object>>
	 */
	public List<HashMap<String, Object>> fileInfoList(FileVO fileVo) {
		return fileMapper.fileInfoList(fileVo);
	}
}
