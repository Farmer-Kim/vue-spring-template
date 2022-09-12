package xxx.mgt.gm.common.file;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xxx.mgt.gm.common.com.CommonVO;

@Setter
@Getter
@ToString
public class FileVO extends CommonVO{
	private String storedFilePath;
	private String etsionName;
	private String atachSeq;
	private String fileName;	
	private String fileSize;
	private String fileEtsionExptName;
	private String storedFileName;
	private String fileStoreName;
	private String fileStorePath;
	private String atachId;
	private String url;
	private String apctEmpId;
}
