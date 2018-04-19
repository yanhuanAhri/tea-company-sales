package com.yh.sales.comment.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommentService {
	
	@Value("${img.url}")
	private String imgUrl;
	
	
	
	/**
     * 上传
     * @param mfile 文件
     * @param type 类型
     */
	public Map<String,Object> upload(MultipartFile mfile,String type){
		try {
			//获取图片路径
			Map<String,Object> map  = new HashMap<>();
			
			File path = new File(imgUrl);
			String url= "";
			/*if(type.indexOf(COVER) > -1){
				url = COVER_PATH+COVER;
			}else if(type.indexOf(DETAIL) >-1){
				url = DETAIL_PATH+DETAIL;
			}else if(type.indexOf(PARTICULAR) >-1){
				url = PARTICULAR_PATH+PARTICULAR;
			}*/
			 String OriginalFilename[] = mfile.getOriginalFilename().split("\\.");
			 String fileName = url  + "-" +UUID.randomUUID().toString().replace("-", "")+"."+OriginalFilename[1];
			 String tempPath = path.getAbsolutePath() + fileName ;
	         File tempFile = null;
	      
	         tempFile =  new File(tempPath);
	         FileUtils.copyInputStreamToFile(mfile.getInputStream(), tempFile);
	         map.put("path", fileName);
	         map.put("res",1);
	         return map;
         }catch(Exception e){
        	// LOG.error(e.getMessage(),e);
     		 try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
     		 
     		 return null;
         }

	}
}
