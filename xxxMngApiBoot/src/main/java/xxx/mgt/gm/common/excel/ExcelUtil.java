package xxx.mgt.gm.common.excel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import xxx.mgt.gm.common.com.Const;
import xxx.mgt.gm.common.message.ErrorMessage;
import xxx.mgt.gm.common.utils.CoreUtil;
import xxx.mgt.gm.common.utils.StringUtil;
/***************************************************
 * <ul>
 * <li>업무 그룹명 : 엑셀 Sheet 추출 Util</li>
 * <li>서브 업무명 : 엑셀 Sheet 추출 Util</li>
 * <li>설       명 : 엑셀 Sheet 추출 Util</li>
 * <li>작   성  자 : Lee Yun Je</li>
 * <li>작   성  일 : 2021. 08. 26.</li>
 * </ul>
 * <pre>
 * ======================================
 * 변경자/변경일 :
 * 변경사유/내역 :
 * ======================================
 * </pre>
 **************************************************/
@Component
@Slf4j
public class ExcelUtil {
	/** 엑셀 목록 key*/
	private static final String LIST_EXCE_LNM = "listExcel";

	/**
	 * <b>엑셀 파일 정보 추출</b><br />
	 * @param file						MultipartFile 엑셀 파일
	 * @param excelHeaderList	 		항목별 매핑할 KEY명
	 * @param failureMessages			실패 메세지 List
	 * @return List
	 */
	public static List<Map<String, Object>> excelFileToList(MultipartFile file, String[] excelHeaderList, List<String> failureMessages, ErrorMessage errorMessage) {
		return excelFileToList(file, excelHeaderList, failureMessages, errorMessage, 1);
	}

	/**
	 * <b>엑셀 파일 정보 추출</b><br />
	 * @param file						MultipartFile 엑셀 파일
	 * @param excelHeaderList	 		항목별 매핑할 KEY명
	 * @param failureMessages			실패 메세지 List
	 * @param effectiveFirstRowIndex	시작 Row Index(디폴트 1부터 시작)
	 * @return List
	 */
	public static List<Map<String, Object>> excelFileToList(MultipartFile file, String[] excelHeaderList, List<String> failureMessages, ErrorMessage errorMessage, int effectiveFirstRowIndex) {
		return excelFileToList(file, excelHeaderList, failureMessages, errorMessage, effectiveFirstRowIndex, 0);
	}

	/**
	 * <b>엑셀 파일 정보 추출</b><br />
	 * @param file						MultipartFile 엑셀 파일
	 * @param excelHeaderList	 		항목별 매핑할 KEY명
	 * @param failureMessages			실패 메세지 List
	 * @param effectiveFirstRowIndex	시작 Row Index(디폴트 1부터 시작)
	 * @param sheetIdx					시트 인덱스(0부터 시작)
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> excelFileToList(MultipartFile file, String[] excelHeaderList, List<String> failureMessages, ErrorMessage errorMessage, int effectiveFirstRowIndex, int sheetIdx) {
		List<Map<String, Object>> listReturn = null;
		Map<String, Object> mapExcelInfo = excelFileToListAndSaveFile(file, excelHeaderList, failureMessages, errorMessage, effectiveFirstRowIndex, sheetIdx);
		if(CoreUtil.isNotEmpty(mapExcelInfo) && CoreUtil.isNotEmpty(mapExcelInfo.get(LIST_EXCE_LNM))){
			listReturn = (List<Map<String, Object>>) mapExcelInfo.get(LIST_EXCE_LNM);
		}
		return listReturn;
	}

	/**
	 * <b>엑셀 파일 정보 추출</b><br />
	 * @param file						MultipartFile 엑셀 파일
	 * @param excelHeaderList	 		항목별 매핑할 KEY명
	 * @param failureMessages			실패 메세지 List
	 * @param effectiveFirstRowIndex	시작 Row Index(디폴트 1부터 시작)
	 * @param sheetIdx					시트 인덱스(0부터 시작)
	 * @return Map listExcel
	 */
	private static Map<String, Object> excelFileToListAndSaveFile(MultipartFile file, String[] excelHeaderList, List<String> failureMessages, ErrorMessage errorMessage, int effectiveFirstRowIndex, int sheetIdx) {
		if(file == null){
			failureMessages.add(errorMessage.getArgumentsMessage(Const.Message.EXCEL_MESSAGE_NOT_DOC_ERROR, null));
			return null;
		}

		if(excelHeaderList == null || excelHeaderList.length < 1){
			failureMessages.add(errorMessage.getArgumentsMessage(Const.Message.EXCEL_MESSAGE_NOT_COLUMN_ERROR, null));
			return null;
		}

		Row row;
		Cell cell;

		Map<String, Object> mapExcelInfo = new HashMap<>();
		Map<String, Object> excelInfoData = null;

		List<Map<String, Object>> excelInfoDetail = new ArrayList<>();

		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(file.getInputStream());
		} catch (EncryptedDocumentException e) {
			log.error("EncryptedDocumentException =====> ", e);
		} catch (IOException e) {
			log.error("IOException =====> ", e);
		}

		if(wb == null || wb.getNumberOfSheets() < sheetIdx){
			failureMessages.add(errorMessage.getArgumentsMessage(Const.Message.EXCEL_MESSAGE_NOT_SHEET_ERROR, new String[] {String.valueOf(sheetIdx+1)}));
			return null;
		}

		// set effective position
		int effectiveFirstSheetIndex = sheetIdx;
		// 엑셀 양식에는 강제로 시트 1개만 넣도록 한다 따라서 시작인덱스는 0, 마지막 인덱스도 0
		int effectiveLastSheetIndex = sheetIdx;

		// traverse sheet
		for (int i = effectiveFirstSheetIndex; i <= effectiveLastSheetIndex; i++) {
			Sheet sheet = wb.getSheetAt(i);
			int effectiveLastRowIndex = sheet.getLastRowNum();


			// 한줄씩 읽기
			for (int j = effectiveFirstRowIndex; j <= effectiveLastRowIndex; j++) {

				excelInfoData = new HashMap<>();
				row = sheet.getRow(j);

				if(row != null){

					for (int k = row.getFirstCellNum(); k <= excelHeaderList.length - 1; k++) {
						cell = row.getCell(k);
						if (cell != null) {
							if (cell.getCellType() == CellType.NUMERIC) {

								if (DateUtil.isCellDateFormatted(cell)) {
									SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
									excelInfoData.put(excelHeaderList[k], StringUtil.nvl(formatter.format(cell.getDateCellValue())));
								} else {
									excelInfoData.put(excelHeaderList[k], StringUtil.nvl(cell.getNumericCellValue()));
								}

							} else {
								excelInfoData.put(excelHeaderList[k], StringUtil.nvl(cell.getRichStringCellValue()));
							}
						} else {
							excelInfoData.put(excelHeaderList[k], "");
						}
					}// k

					excelInfoDetail.add(excelInfoData);
				}
			}
		}
		System.err.println("!!!!!!!!!!!!!!!!!");
		System.err.println(excelInfoDetail);
		System.err.println("!!!!!!!!!!!!!!!!!");
		mapExcelInfo.put(LIST_EXCE_LNM, excelInfoDetail);
		return mapExcelInfo;
	}

}