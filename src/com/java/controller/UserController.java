package com.java.controller;


import com.java.entity.User;
import com.java.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	/**
	 * 我们在Controller的目标方法上直接设置方法的参数。就可以直接传入请求参数的值。
	 * 如http://localhost:8080/ssmTestMySQL/user/getUser.do?id=1
	 * 要求：参数名必须和方法的参数名相匹配。
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getUser" )
	public String getUser(Map map,int id) {

		User resultUser=userService.getUserFromId(id);
		System.out.println(resultUser.getUserName());

		map.put("userOne", resultUser);

		return "welcome";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response){

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String username=request.getParameter("userName");
		String password=request.getParameter("passWord");

		User users=new User(username, password);
		userService.add(users);
		return "reback";
	}


	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request,HttpServletResponse response) throws IOException {

		User resultUser=userService.login(user);
		System.out.println(resultUser);

		if(resultUser==null) {
			request.setAttribute("user", user);
			request.setAttribute("errorMsg", "用户名或密码错误！");

			response.sendRedirect("../index.jsp");//(为了冲破前缀后缀的束缚，用重定向)  重定向到index.jsp

			return null;
		}else {
			HttpSession session=request.getSession();
			session.setAttribute("userOne", resultUser);
			return "welcome";
		}

	}
}
