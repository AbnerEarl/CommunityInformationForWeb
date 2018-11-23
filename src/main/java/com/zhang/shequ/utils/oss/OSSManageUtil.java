package com.zhang.shequ.utils.oss;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.zhang.shequ.utils.util.ResourceUtil;

/**
 * 对OSS服务器进行上传删除等的处理
 */
public class OSSManageUtil {
	
    private static Logger logger = LoggerFactory.getLogger(OSSManageUtil.class);
    
    private static Map<String, String> PUBLC_COMMON = ResourceUtil.getResource("properties/common").getMap();
	
    private static String ENDPOINT;// 阿里云API的内或外网域名
    
    private static String ACCESS_KEY_ID;// 阿里云API的密钥Access Key ID
    
    private static String ACCESS_KEY_SECRET;// 阿里云API的密钥Access Key Secret
    
    private static String BACKET_NAME;// 阿里云API的bucket名称
    
    private static String ACCESS_URL;//阿里云文件访问域名
    
    private static final String FOLDER_USER = "user/"; //阿里云API的用户上传的文件夹名称
    
    private static final String FOLDER_FILE = "file/";//阿里云API的文件资源文件夹名称
    
    private static final String FORMATDATE = new SimpleDateFormat("yyyyMMdd").format(new Date());
    
    // 初始化属性
    static {
    	ENDPOINT = PUBLC_COMMON.get("oss.endpoint").trim();    
    	ACCESS_KEY_ID = PUBLC_COMMON.get("oss.accessKeyId").trim();    
    	ACCESS_KEY_SECRET = PUBLC_COMMON.get("oss.accessKeySecret").trim();    
    	BACKET_NAME = PUBLC_COMMON.get("oss.bucketName").trim();    
    	ACCESS_URL = PUBLC_COMMON.get("oss.accessUrl").trim(); 
    }
	
    /**
     * 获取阿里云OSS客户端对象
     */
	public static OSSClient getOSSClient() {
        return new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }
	
    /**
     * 创建存储空间
     */
    public static String createBucketName(String bucketName) {
    	OSSClient ossClient = getOSSClient();
        final String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            // 创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
            logger.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }
    
    /**
     * 删除存储空间buckName
     */
    public static void deleteBucket(String bucketName) {
    	OSSClient ossClient = getOSSClient();
        ossClient.deleteBucket(bucketName);
        logger.info("删除" + bucketName + "存储空间成功");
    }
    
    /**
     * 创建模拟文件夹
     */
    public static String createFolder(String folder) {
    	OSSClient ossClient = getOSSClient();
    	final String bucketName = BACKET_NAME;
        final String keySuffixWithSlash = folder;
        // 判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            // 创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            logger.info("创建文件夹成功");
            // 得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }
    
    /**
     * 根据key删除OSS服务器上的文件
     */
    public static void deleteFile(String folder, String key) {
    	OSSClient ossClient = getOSSClient();
    	final String bucketName = BACKET_NAME;
        ossClient.deleteObject(bucketName, folder + key);
        logger.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }
    
    /**
     * 获得url链接
     */
    public static String getUrl(String key) {
    	if(key != null && key.length() > 0){
    		// 设置URL过期时间为10年 3600l* 1000*24*365*10
    		Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24 * 365 * 10);
    		// 生成URL
    		OSSClient ossClient = getOSSClient();
    		String bucketName = BACKET_NAME;
    		URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
    		if (url != null) {
    			return url.toString();
    		}
    	}
    	return "";
    }
    
    //上传文件至OSS的file文件中(1.byte数组 2.字符串 3.网络流 4.文件流 5.本地文件)
    public static String uploadFile(Integer type, String content, String ext){
    	return uploadFile(type, content, ext, null);
    }
    
    public static String uploadFileByInput(InputStream inputStream, String ext, Integer userId) {
    	try {
			String folder = "";
			if(null != userId){
				folder = FOLDER_USER + userId + "/" + FORMATDATE + "/";
			}else {
				folder = FOLDER_FILE + FORMATDATE + "/";
			}
		    String FORMATDATETIME = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + getVerificationCode(6);
			String fileName = FORMATDATETIME + "." +  ext;//文件名
			logger.info("上传到路径" + folder + fileName);
			// 创建上传Object的Metadata
			ObjectMetadata metadata = new ObjectMetadata();
			//指定该Object的文件大小
			Long fileSize = (long) inputStream.available();
			fileSize = (long) inputStream.available();
			metadata.setContentLength(fileSize);
			// 指定该Object被下载时的网页的缓存行为
			metadata.setCacheControl("no-cache");
			// 指定该Object下设置Header
			metadata.setHeader("Pragma", "no-cache");
			// 指定该Object被下载时的内容编码格式
			metadata.setContentEncoding("utf-8");
			// 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
			// 如果没有扩展名则填默认值application/octet-stream
			metadata.setContentType(getContentType(fileName));
			// 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
			metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
			OSSClient ossClient = getOSSClient();
	    	String bucketName = BACKET_NAME;
			String key = folder + fileName;
			ossClient.putObject(bucketName, key, inputStream, metadata);
			ossClient.shutdown();
			return ACCESS_URL + key;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		} 
    }
	
    //上传文件至OSS中(1.byte数组 2.字符串 3.网络流 4.文件流 5.本地文件)
    public static String uploadFile(Integer type, String content, String ext, Integer userId) {
    	try {
			String folder = "";
			if(null != userId){
				folder = FOLDER_USER + userId + "/" + FORMATDATE + "/";
			}else {
				folder = FOLDER_FILE + FORMATDATE + "/";
			}
		    String FORMATDATETIME = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + getVerificationCode(6);
			String fileName = FORMATDATETIME + "." +  ext;//文件名
			logger.info("上传到路径" + folder + fileName);
			byte[] bytes = null;
			InputStream inputStream = null;
			switch (type) {
			case 1:
				bytes = formatToByte(content);//图片转byte数组
			    inputStream = new ByteArrayInputStream(bytes);
				break;
			case 2:
				bytes = content.getBytes("UTF-8");//字符串转byte数组
				inputStream = new ByteArrayInputStream(bytes);
				break;
			case 3:
				inputStream = new URL(content).openStream();//上传网络流
				break;
			case 4:
				inputStream = new FileInputStream(content);//上传文件流
				break;
			case 5:
				inputStream = new FileInputStream(new File(content));//上传本地文件
				break;
			default:
				break;
			}
			// 创建上传Object的Metadata
			ObjectMetadata metadata = new ObjectMetadata();
			//指定该Object的文件大小
			Long fileSize = null;
			if(null != bytes){
				fileSize = (long) bytes.length;
			}else {
				fileSize = (long) inputStream.available();
			}
			metadata.setContentLength(fileSize);
			// 指定该Object被下载时的网页的缓存行为
			metadata.setCacheControl("no-cache");
			// 指定该Object下设置Header
			metadata.setHeader("Pragma", "no-cache");
			// 指定该Object被下载时的内容编码格式
			metadata.setContentEncoding("utf-8");
			// 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
			// 如果没有扩展名则填默认值application/octet-stream
			metadata.setContentType(getContentType(fileName));
			// 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
			metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
			OSSClient ossClient = getOSSClient();
	    	String bucketName = BACKET_NAME;
			String key = folder + fileName;
			ossClient.putObject(bucketName, key, inputStream, metadata);
			ossClient.shutdown();
			return ACCESS_URL + key;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		} 
    }
    
    public String batchUploadFile(Integer type, List<String[]> files) {
    	return batchUploadFile(type, files, null);
    }
    
    public String batchUploadFile(Integer type, List<String[]> files, Integer userId) {
	    String urls = "";
	    for (String[] file : files) {
	        String url = uploadFile(type, file[0], file[1], userId);
	        logger.info("访问网址路径:" + url);
	        urls = url + ",";
	    }
	    return urls.substring(0, urls.length()-1);
    }
    
    //图片转化为byte数组
    private static byte[] formatToByte(String imgSrc) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(imgSrc));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
        return data;
    }
    
    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     */
    private static String getContentType(String fileName) {
        // 文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)
                || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        if (".mp4".equalsIgnoreCase(fileExtension)) {
            return "video/mp4";
        }
        if (".apk".equalsIgnoreCase(fileExtension)) {
        	return "application/octet-stream";
        }
        // 默认返回类型
        return "image/jpeg";
    }
    
    /**
     * 随机生成验证码
     */
    private static String getVerificationCode(int length) {
        StringBuffer vcode = new StringBuffer();
        for (int i = 0; i < length; i++) {
            vcode.append(RandomUtils.nextInt(0, 9));
        }
        return vcode.toString();
    }
    
 // 测试
    public static void main(String[] args) throws Exception {
    	String key = OSSManageUtil.uploadFile(3,"F:/EntertainmentCenter/图片/Pictures/金莎1/8.jpg", "jpg");
    	System.out.println(key);
    	String url = OSSManageUtil.getUrl(key);
    	System.out.println(url);
/*        String user_id = "localism";
        String[] urllist = OSSManageUtil.uploadObject2OSS(new File("g:\\余杰的avi小视频.mp4"), BACKET_NAME, user_id);
        String url = urllist[1];
        System.out.println(url);*/
        /*// 初始化OSSClient
        OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
        // 上传文件
        String files = "D:\\1.jpg";
        String[] file = files.split(",");
        String user_id = "3";
        
         * for (String filename : file) { //
         * System.out.println("filename:"+filename); File filess = new
         * File(filename); String[] s =
         * AliyunOSSClientUtil.uploadObject2OSS(ossClient, filess, BACKET_NAME,
         * user_id); logger.info("上传后的文件MD5数字唯一签名:" + s[0]); logger.info("文件路径:"
         * + s[1]); String url = AliyunOSSClientUtil.getUrl(ossClient,
         * BACKET_NAME, s[1]); logger.info("访问网址路径:" + url); //
         * 上传后的文件MD5数字唯一签名:40F4131427068E08451D37F02021473A }
         

        byte[] b1 = AliyunOSSClientUtil.image2byte("g:\\余杰AVI.png");
        
         * String str =
         * "";
         * BASE64Decoder decoder = new BASE64Decoder(); byte[] b =
         * decoder.decodeBuffer(str);
         * 
         * System.out.println(b.length);
         
        
//        String path = AliyunOSSClientUtil.uploadByteVideoOSS(ossClient, b1, BACKET_NAME, user_id);
        String path = AliyunOSSClientUtil.uploadByteOSS(ossClient, b1, BACKET_NAME, user_id);
//        String path = AliyunOSSClientUtil.uploadObjectOSS(ossClient, b1, BACKET_NAME, user_id);
        System.out.println(b1.length + "," + b1.toString());
        logger.info("文件路径:" + path);
        String url = AliyunOSSClientUtil.getUrl(ossClient, BACKET_NAME, path);
        logger.info("访问网址路径:" + url);*/
    }
	
}