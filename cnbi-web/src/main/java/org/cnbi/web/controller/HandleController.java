package org.cnbi.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnbi.entity.User;
import org.cnbi.service.IHandleService;
import org.cnbi.utils.exception.BusinessException;
import org.cnbi.utils.pagination.PagingUtil;
import org.cnbi.utils.string.StringUtil;
import org.cnbi.web.controller.base.BaseController;
import org.cnbi.web.utils.ContextHolderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/main")
public class HandleController /*extends BaseController*/{
	@Autowired
	private  IHandleService handleService;
	
	/**
	 * 课程列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView course(HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("welcome");
		view.addObject("msg","hello 别人的话你也信啊！");
		ContextHolderUtils.setContext(view);
		ContextHolderUtils.setContentType(response, null);
		return view;
	}

	/**
	 * 用户请求页面
	 */
	/*@RequestMapping("/index")
	public String index() {
		return "redirect:/welcome";
	}
*/	
	@RequestMapping("/save")
    public String save(@ModelAttribute("form") Bean form,RedirectAttributes attr){
       // String code =  service.save(form);
        /*if(code.equals("000")){
            attr.addFlashAttribute("name", form.getName());  
            attr.addFlashAttribute("success", "添加成功!");
            return "redirect:/index";
        }else{
            attr.addAttribute("projectName", form.getProjectName());  
            attr.addAttribute("enviroment", form.getEnviroment());  
            attr.addFlashAttribute("msg", "添加出错!错误码为："+rsp.getCode().getCode()+",错误为："+rsp.getCode().getName());
            return "redirect:/maintenance/toAddConfigCenter";
        }*/
		return "redirect:/index";
    }
	@RequestMapping("/query")
	@ResponseBody
	public Object query(PagingUtil page){
		//HttpServletRequest request = ContextHolderUtils.getRequest();
		String sql = "select * from sys_user";
		if(!StringUtil.isBlank(page.getSql())){
			sql = page.getSql();
		}
		  return handleService.query(sql,new User());
	}

}
/*
 * 方式一：使用ModelAndView return new ModelAndView("redirect:/toList");
 * 这样可以重定向到toList这个方法 方式二：返回String return "redirect:/ toList "; 方式一：自己手动拼接url
 * 
 * new ModelAndView("redirect:/toList？param1="+value1+"&param2="+value2);
 * 这样有个弊端，就是传中文可能会有乱码问题。
 * 
 * 方式二：用RedirectAttributes，这个是发现的一个比较好用的一个类
 * 这里用它的addAttribute方法，这个实际上重定向过去以后你看url，是它自动给你拼了你的url。 使用方法：
 * 
 * attr.addAttribute("param", value); return "redirect:/namespace/toController";
 * 这样在toController这个方法中就可以通过获得参数的方式获得这个参数，再传递到页面。过去的url还是和方式一一样的。
 */
