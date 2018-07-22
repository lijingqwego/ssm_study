package com.kaisn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaisn.pojo.Msg;
import com.kaisn.pojo.Position;
import com.kaisn.service.PositionService;

@Controller
@RequestMapping(value="/pos")
public class PositionController {
	
	@Autowired
	PositionService positionService;
	
	@ResponseBody
	@RequestMapping(value="/list" ,method=RequestMethod.GET)
	public Msg getPositionList(HttpServletRequest request,HttpServletResponse response){
		List<Position> posList=null;
		try {
			posList = positionService.getPositionList();
		} catch (Exception e) {
			return Msg.fail();
		}
		return Msg.success().add("list", posList);
	}

}
