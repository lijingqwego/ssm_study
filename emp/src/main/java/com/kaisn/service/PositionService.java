package com.kaisn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaisn.dao.PositionMapper;
import com.kaisn.pojo.Position;

@Service
public class PositionService {
	
	@Autowired
	private PositionMapper positionMapper;
	
	public List<Position> getPositionList(){
		return positionMapper.getPositionList();
	}

}
