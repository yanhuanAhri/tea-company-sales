package com.yh.sales.commodity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.Commodity;
import com.yh.entity.CommodityVo;

@Repository
@Mapper
public interface CommodityMapper {
	  /* @Insert("INSERT INTO tb_user(login_name, password) VALUES(#{loginName},#{password})")  
    public int insertUser(@Param("loginName") String loginName, @Param("password")  String password); */ 
	
	public Long saveCommodity(@Param("commodity") Commodity commodity);
	
	public void modifyCommodity(@Param("commodity") Commodity commodity);
	
	public Commodity findOneById(@Param("id")Long id,@Param("commodityNum")String commodityNum);
	
	//public Integer
	
	/**
	 * 商品搜索
	 * @param search  输入框搜索条件
	 * @param commodityVo 选择搜索条件
	 * @param pageStart 页面开始数量
	 * @param pageSize 页面需要多少条数据
	 * @param sort 排序条件 可多个
	 * @return
	 */
	public List<CommodityVo> findCommodityVoBySearch(
			@Param("search")String search,@Param("commodityVo") CommodityVo commodityVo,
			@Param("pageStart")Integer pageStart,@Param("pageSize")Integer pageSize,
			@Param("sort")String sort);
	public Integer getCountBySearch(@Param("search")String search,@Param("commodityVo") CommodityVo commodityVo);
	
	//@Param("commodityNumList")List<String> commodityNumList
	public void updateCommodityProductNum(@Param("commodityList")List<Commodity> commodityList);
	public List<Commodity> findByCommodityNum(@Param("commodityNumList")List<String> commodityNumList);
}
