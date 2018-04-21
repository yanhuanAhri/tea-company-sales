package com.yh.sales.comment.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yh.entity.CommentVo;
import com.yh.sales.comment.mapper.CommentMapper;

@Service
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Value("${img.url}")
	private String imgUrl;
	
	/**
	 * 根据商品编号查找商品评论
	 * @param commodityNum
	 * @return
	 */
	public Map<String,Object> findByCommodityNum(String commodityNum){
		Map<String,Object> map=new HashMap<>();
		List<CommentVo> list=commentMapper.findByCommodityNum(commodityNum);
		Integer count=commentMapper.getCount(commodityNum,null);
		Integer good=commentMapper.getCount(commodityNum, 3);// 1-差评，2-中评，3-好评
		Integer medium=commentMapper.getCount(commodityNum, 2);
		Integer bad=commentMapper.getCount(commodityNum, 1);
		map.put("CommentVoList", list);
		map.put("count", count);
		map.put("good", good);
		map.put("medium", medium);
		map.put("bad", bad);
		return map;
	}
	
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
