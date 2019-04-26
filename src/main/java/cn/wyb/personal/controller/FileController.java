package cn.wyb.personal.controller;

import cn.wyb.personal.common.result.CommResponse;
import cn.wyb.personal.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * FileController : (请描述该类).
 *
 * @author : wangyibin
 * @date : 2019/4/26 14:58
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

  @Autowired
  private FileService fileService;

  @RequestMapping(value = "/uploadExcel")
  @ResponseBody
  public CommResponse uploadExcel(MultipartFile file){
    fileService.uploadExcel(file);
    return CommResponse.success();
  }



}
