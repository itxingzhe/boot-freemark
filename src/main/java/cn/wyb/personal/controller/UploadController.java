package cn.wyb.personal.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.wyb.personal.common.enums.BaseExceptionEnum;
import cn.wyb.personal.common.exception.BaseException;
import cn.wyb.personal.common.result.CommResponse;
import cn.wyb.personal.common.utils.FileUtils;

/**
 * UploadController: 文件上传下载控制层
 *
 * @author wangyibin
 * @date 2018/9/19 17:46
 * @see
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping("/init")
    public ModelAndView uploadManager() {
        return new ModelAndView("/upload/upload_manager");
    }

    @RequestMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request) {
        // 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = request.getServletContext().getRealPath("/static/upload");
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {
            // 创建保存目录
            saveFile.mkdir();
        }
        InputStream in = null;
        FileOutputStream out = null;
        try {
            if (request instanceof MultipartHttpServletRequest) {
                MultipartHttpServletRequest fileUploadRequest = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> fileMap = fileUploadRequest.getFileMap();
                for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                    MultipartFile multipartFile = entry.getValue();
                    // 如果fileitem中封装的是上传文件
                    if (!multipartFile.isEmpty()) {
                        // 得到上传的文件名称，
                        String filename = multipartFile.getOriginalFilename();
                        logger.debug("得到上传的文件名称:" + filename);
                        if (filename == null || filename.trim().equals("")) {
                            continue;
                        }
                        // 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                        // 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                        filename = filename.substring(filename.lastIndexOf("\\") + 1);
                        // 得到上传文件的扩展名
                        String fileExtName = filename.substring(filename.lastIndexOf("."));
                        // 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                        logger.debug("上传的文件的扩展名是：" + fileExtName);
                        // 获取item中的上传文件的输入流
                        in = multipartFile.getInputStream();
                        // 得到文件保存的名称
                        String saveFilename = FileUtils.makeFileName(filename);
                        // 得到文件的保存目录
                        String realSavePath = FileUtils.makePath(saveFilename, savePath);
                        // 创建一个文件输出流
                        out = new FileOutputStream(realSavePath + "\\" + saveFilename);
                        // 创建一个缓冲区
                        byte buffer[] = new byte[1024];
                        // 判断输入流中的数据是否已经读完的标识
                        int len = 0;
                        // 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                        while ((len = in.read(buffer)) > 0) {
                            // 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                            out.write(buffer, 0, len);
                        }
                        // 关闭输入流
                        in.close();
                        // 关闭输出流
                        out.close();
                    }
                }
            }
            return "redirect:/upload/init";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        throw new BaseException(BaseExceptionEnum.COMMON_500, "文件上传失败！！！");
    }

    @RequestMapping("/showUploadFiles")
    @ResponseBody
    public CommResponse showFiles(HttpServletRequest request) {
        // 获取上传文件的目录
        String uploadFilePath = request.getServletContext().getRealPath("/static/upload");
        // 存储要下载的文件名
        Map<String, String> fileNameMap = new HashMap<String, String>();
        // 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
        listfile(new File(uploadFilePath), fileNameMap);// File既可以代表一个文件也可以代表一个目录
        // 将Map集合发送到listfile.jsp页面进行显示
        // request.setAttribute("fileNameMap", fileNameMap);
        // request.getRequestDispatcher("/listfile.jsp").forward(request, response);
        return CommResponse.success(fileNameMap);
    }

    /**
     * @Method: listfile
     * @Description: 递归遍历指定目录下的所有文件
     * @Anthor:孤傲苍狼
     * @param file 即代表一个文件，也代表一个文件目录
     * @param map 存储文件名的Map集合
     */
    public void listfile(File file, Map<String, String> map) {

        // 如果file代表的不是一个文件，而是一个目录
        if (!file.isFile()) {
            // 列出该目录下的所有文件和目录
            File files[] = file.listFiles();
            if (files != null && files.length > 0) {
                // 遍历files[]数组
                for (File f : files) {
                    // 递归
                    listfile(f, map);
                }
            }
        } else {

            /**
             * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
             * file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置
             */
            String realName = file.getName().substring(file.getName().indexOf("_") + 1);
            map.put(file.getName(), realName);
        }
    }

    @RequestMapping("/downloadFile")
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response, String fileName)
            throws ServletException, IOException {
        if (fileName != null) {
            // 上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
            String fileSaveRootPath = request.getServletContext().getRealPath("/static/upload");
            // 通过文件名找出文件的所在目录
            String path = FileUtils.makePath(fileName, fileSaveRootPath);
            // 得到要下载的文件
            File file = new File(path + "\\" + fileName);
            // 如果文件不存在
            if (!file.exists()) {
                throw new BaseException(BaseExceptionEnum.COMMON_500, "您要下载的资源已被删除！！");
            }
            // 处理文件名
            String realname = fileName.substring(fileName.indexOf("_") + 1);

            // 读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(file);

            // 设置响应头，控制浏览器下载该文件
			response.reset();
			response.setContentType("application/octet-stream;charset=UTF-8");
            request.setCharacterEncoding("utf-8");
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(realname,"UTF-8"));
            //response.setContentType("application/x-msdownload");
            response.setHeader("Content-Length", String.valueOf(file.length()));
            // 创建输出流
            OutputStream out = response.getOutputStream();
            // 创建缓冲区
            byte buffer[] = new byte[10240];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                // 输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            // 关闭文件输入流
            in.close();
            // 关闭输出流
            out.close();
        }
    }

}
