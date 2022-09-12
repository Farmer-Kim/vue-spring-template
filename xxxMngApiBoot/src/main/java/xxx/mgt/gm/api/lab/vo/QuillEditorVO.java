package xxx.mgt.gm.api.lab.vo;

import lombok.Data;
import xxx.mgt.gm.common.com.CommonVO;

@Data
public class QuillEditorVO extends CommonVO{
	
	private String id;
	
	private String srchWord;
	
	private String useYn;
	
	private String content;
}
