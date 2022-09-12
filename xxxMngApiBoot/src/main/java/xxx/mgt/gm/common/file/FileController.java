package xxx.mgt.gm.common.file;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import xxx.mgt.gm.common.utils.ResponseWriteUtil;

@Controller
@RequestMapping(value = "/api/file")
public class FileController {
	

	private FileService fileService;
	
	@Autowired
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
		
	/**
	 * 파일 다운로드
	 *
	 * @param atach_id
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = { "/download" }, method = RequestMethod.POST)
	public void download( @RequestBody FileVO fileVo,HttpServletResponse response) throws Exception {
		try {
			Field[] fields = fileVo.getClass().getDeclaredFields();
			for(int i=0; i <fields.length; i++){
				fields[i].setAccessible(true);
				System.err.println(fields[i].getName() + " : " +fields[i].get(fileVo));
			}  
			
			fileService.download(fileVo, response);
		} catch(Exception e) {
			response.reset();
			ResponseWriteUtil.alert(response, e.getMessage());
		} 
	}

	/**
	 * 파일 삭제
	 *
	 * @param atach_id
	 * @param atach_seq
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = { "/fileListDelete" }, method = RequestMethod.POST)
	public void fileListDelete( @RequestBody FileVO fileVo,HttpServletResponse response) throws Exception {
		try {
			Field[] fields = fileVo.getClass().getDeclaredFields();
			for(int i=0; i <fields.length; i++){
				fields[i].setAccessible(true);
				System.err.println(fields[i].getName() + " : " +fields[i].get(fileVo));
			}  
			fileService.fileListDelete(fileVo);
		} catch(Exception e) {
			response.reset();
			ResponseWriteUtil.alert(response, e.getMessage());
		} 
	}


	/**
	 * 파일 리스트
	 * 
	 * @param stdCtgryMngVO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/fileInfoList", method = { RequestMethod.POST })
	public Object fileInfoList(@RequestBody FileVO params) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		/*파라미터 값 찾기(VO버전) */
		Field[] fields = params.getClass().getDeclaredFields();
		for(int i=0; i <fields.length; i++){
			fields[i].setAccessible(true);
			System.err.println(fields[i].getName() + " : " +fields[i].get(params));
		} 
		//레벨별 표준 카테고리 목록
		//params.setAtachId("A001");
		List<HashMap<String, Object>> fileInfoList = fileService.fileInfoList(params);
		result.put("fileInfoList", fileInfoList);
		result.put("code", 200);
		return result; 
	}
	
	/**
	 * 파일 테스트
	 * 
	 * @param stdCtgryMngVO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/fileTest", method = { RequestMethod.POST })
	public String fileTest(
            MultipartHttpServletRequest uploadFile,
            @RequestParam Map<String, Object> params
		) throws Exception {
		// 넘어온 값 확인
		System.err.println("uploadFile"+uploadFile);
		
       fileService.upload(uploadFile);

		return "success";
	}
}
