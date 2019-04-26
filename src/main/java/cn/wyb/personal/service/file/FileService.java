package cn.wyb.personal.service.file;

import cn.wyb.personal.common.result.CommResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * FileService : (请描述该类).
 *
 * @author : wangyibin
 * @date : 2019/4/26 15:03
 */
public interface FileService {


  CommResponse uploadExcel(MultipartFile file);
}
