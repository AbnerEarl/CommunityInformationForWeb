package com.zhang.shequ.modular.home;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.code.kaptcha.Producer;
import com.zhang.shequ.base.common.constant.Constants;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.utils.json.GsonUtil;
import com.zhang.shequ.utils.oss.FileUtil;

@RestController
@RequestMapping("/file")
public class FileController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Resource
	private Producer producer; 
	
    @RequestMapping(value = "/upload")
    public String uploadFile(MultipartFile uploadFile) {
    	Map<String, Object> map = FileUtil.uploadFile(uploadFile);
		return GsonUtil.objectToJson(map);
    }
    
    @RequestMapping(value = "/protal/upload")
    public String protalUploadFile(@RequestPart(value = "uploadFile", required = false) MultipartFile[] multipartFiles) {
    	Map<String, Object> map = FileUtil.uploadBatchFile(multipartFiles);
		return GsonUtil.objectToJson(map);
    }
	
    /**
     * 验证码
     */
    @RequestMapping("/kaptcha")
    public void kaptcha(HttpServletRequest request, HttpServletResponse response){
        response.setDateHeader("Expires",0);  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
        String capText = producer.createText().toLowerCase();  
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);  
        BufferedImage bi = producer.createImage(capText);  
        ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
    }
    
}