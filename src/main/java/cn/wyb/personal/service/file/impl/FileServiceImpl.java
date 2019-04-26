package cn.wyb.personal.service.file.impl;

import cn.wyb.personal.common.result.CommResponse;
import cn.wyb.personal.common.utils.FileUtils;
import cn.wyb.personal.service.file.FileService;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * FileServiceImpl : (请描述该类).
 *
 * @author : wangyibin
 * @date : 2019/4/26 15:04
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

  @Override
  public CommResponse uploadExcel(MultipartFile file) {

    if(null == file){
      return CommResponse.success();
    }
    Workbook workbook = FileUtils.getWorkbookByUploadFile(file);
    if(null == workbook){
      return CommResponse.success();
    }
    int numberOfSheets = workbook.getNumberOfSheets();
    for (int i =0; i < numberOfSheets; i++){
      Sheet sheet = workbook.getSheetAt(i);
      if(null == sheet){
        continue;
      }
      int rowNum = sheet.getLastRowNum();
      for (int y =0; y < rowNum; y++){
        StringBuffer cellStr = new StringBuffer("行数：");
        ArrayList<Object> cellList = Lists.newArrayList();
        cellStr.append(y);
        cellStr.append("-");
        Row row = sheet.getRow(y);
        if(null == row){
          continue;
        }
        int cellNum = row.getLastCellNum();
        for (int z =0; z < rowNum; z++){
          Cell cell = row.getCell(z);
          if(null == cell){
            cellList.add("");
            continue;
          }
          Object cellValue = FileUtils.getCellValue(cell);
          cellList.add(cellValue);
        }
        cellStr.append(JSON.toJSONString(cellList));
        System.out.println(cellStr);
      }
    }

    return CommResponse.success();
  }
}
