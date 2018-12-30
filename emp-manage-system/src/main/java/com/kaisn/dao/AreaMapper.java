package com.kaisn.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.kaisn.pojo.Area;

public interface AreaMapper {

	List<Area> getAreaInfo(Area area);
	
	List<String> getProvinceList();
	
	List<String> getCityList(String province,RowBounds rowBounds);
	
	List<String> getAreaList(String city);
}
