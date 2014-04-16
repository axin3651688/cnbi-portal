package org.cnbi.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.cnbi.entity.User;
import org.cnbi.web.system.manager.ClientManager;
import org.cnbi.web.system.pojo.base.Client;
import org.cnbi.web.utils.ContextHolderUtils;
import org.cnbi.web.utils.IpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class LoginController{
	/**
	 * 用户登录
	 */
	@RequestMapping("/login")
	public String  login(HttpServletRequest req,User user){
		HttpSession session = org.cnbi.web.utils.ContextHolderUtils.getSession();
		Client client = new Client();
		client.setIp(IpUtil.getIpAddr(req));
		client.setLogindatetime(new Date());
		client.setUser(user);
		ClientManager.getInstance().addClinet(session.getId(),client);
		//添加登陆日志
		//systemService.addLog(message, Globals.Log_Type_LOGIN,Globals.Log_Leavel_INFO);
		return "redirect:/main/index"; 
	}
	/**
	 * 用户安全退出
	 */
	@RequestMapping("/loginOut")
	public ModelAndView loginOut(){
		HttpSession session = ContextHolderUtils.getSession();
		//TSUser user = ResourceUtil.getSessionUserName();
	//	systemService.addLog("用户" + user.getUserName() + "已退出",Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
		ClientManager.getInstance().removeClinet(session.getId());
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/"));
		return modelAndView;
	}
	/**
	 * 登录用户权限加载
	 */
	private void setRights(){
		
	}

}
