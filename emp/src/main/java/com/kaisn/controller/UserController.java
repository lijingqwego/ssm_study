package com.kaisn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaisn.http.JavaWebToken;
import com.kaisn.pojo.Msg;
import com.kaisn.pojo.User;
import com.kaisn.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/login" ,method=RequestMethod.POST)
	public Msg verify(String userName,String password,HttpServletResponse response){
		try {
			response.setHeader("Access-Control-Allow-Origin","*");
			User userInfo=userService.getUserInfo(userName,password);
			if(userInfo!=null){
		        String token = JavaWebToken.createJavaWebToken(String.valueOf(userInfo.getUserId()));// 根据存在用户的id生成token字符串
				return Msg.success().add("token", token);
			}else{
				return Msg.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return Msg.fail();
		}
	}
	
	/**
     *  token验证方法
     * @param rc
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/tokenConfirm" ,method=RequestMethod.POST)
    public Msg tokenConfirm(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        String token = request.getHeader("token");
        System.out.println(token);
        if (JavaWebToken.parserJavaWebToken(token) != null){//根据前端带回来的token验证
            return Msg.success();//验证通过
        }else{
        	return Msg.fail();//验证失败
        }
    }
}
