package org.cnbi.web.system.pojo.base;
import java.util.Map;
import org.cnbi.entity.Menu;
import org.cnbi.entity.User;
/**
 * 在线用户对象
 * 
 * @author 龚佳新
 * @date 2014-04-10
 * @version 1.0
 */
public class Client implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private User user;

	private Map<String, Menu> menus;
	/**
	 * 用户IP
	 */
	private java.lang.String ip;
	/**
	 *登录时间
	 */
	private java.util.Date logindatetime;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Map<String, Menu> getMenus() {
		return menus;
	}

	public void setMenus(Map<String, Menu> menus) {
		this.menus = menus;
	}

	public java.lang.String getIp() {
		return ip;
	}

	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}

	public java.util.Date getLogindatetime() {
		return logindatetime;
	}

	public void setLogindatetime(java.util.Date logindatetime) {
		this.logindatetime = logindatetime;
	}


}
