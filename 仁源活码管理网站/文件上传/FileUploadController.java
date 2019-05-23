package com.qhit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by 墨涵 on 2019/5/14.
 */
@RestController
@RequestMapping("/multifileUpload")
public class FileUploadController {

    @RequestMapping("/test")
    @ResponseBody
    public  String multifileUpload(HttpServletRequest request){
//request.getFiles()获取多个类型格式的文件
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
        //判断是否上传文件
        if(files.isEmpty()){
            return "上传失败";
        }
//创建文件保存路径
        String path = "F:/test" ;
//通过for循环遍历文件名
        for(MultipartFile file:files){
           // 得到上传时的文件名
            String fileName = file.getOriginalFilename();
            //获取文件大小
            int size = (int) file.getSize();
                System.out.println(fileName + "-->" + size);
                if (file.isEmpty()) {
                    return "上传失败";
                } else {
                    //new一个file结合存放路径构建新的文件名称
                    File dest = new File(path + "/" + fileName);
                    //判断文件父目录是否存在
                    if (!dest.getParentFile().exists()) {
                        //如果不存在创建一个路径
                        dest.getParentFile().mkdir();
                    }
                    try {
                        //最后通过File接口下的transferTo（方法）将文件保存到创建的目标路径下
                        file.transferTo(dest);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return "上传失败";
                    }
                }

        }
        return "上传成功";
    }
}
