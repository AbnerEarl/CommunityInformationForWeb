package com.zhang.shequ.utils.oss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	private static Logger log = Logger.getLogger(FileUtil.class);
	
	public static Map<String,Object> uploadFile(MultipartFile file){
		Map<String,Object> resultMap = new HashMap<>();
		try {
			if(file != null){
				InputStream inputStream = file.getInputStream();
				String ext = getFileExt(file.getOriginalFilename());
				String url = OSSManageUtil.uploadFileByInput(inputStream, ext, null);
				resultMap.put("error", 0);
				resultMap.put("url", url);
			}
		} catch (Exception e) {
			log.info(e.getMessage(),e);
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传失败");
		} 
		return resultMap;
	}
	
	public static Map<String,Object> uploadBatchFile(MultipartFile[] multipartFiles){
		Map<String,Object> resultMap = new HashMap<>();
		try {
			if(multipartFiles != null && multipartFiles.length > 0){
				String urls = "";
				for (MultipartFile file : multipartFiles) {
					InputStream inputStream = file.getInputStream();
					String ext = getFileExt(file.getOriginalFilename());
					String url = OSSManageUtil.uploadFileByInput(inputStream, ext, null);
					System.out.println(url);
					urls = urls + url + ","; 
				}
				resultMap.put("error", 0);
				resultMap.put("url", urls.substring(0, urls.length()-1));
			}
		} catch (Exception e) {
			log.info(e.getMessage(),e);
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传失败");
		} 
		return resultMap;
	}
	
	public static void downloadFile(String fileName, String path, HttpServletResponse response){
		File file = null;
		try {
	        response.setHeader("Content-Disposition", "attachment;filename="+fileName);  
			file = new File(path + fileName);
			InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		} 
	}
	
	private static String getFileExt(String fileName) {
		return FilenameUtils.getExtension(fileName);
	}
	
}