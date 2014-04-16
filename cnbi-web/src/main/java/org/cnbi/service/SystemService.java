package org.cnbi.service;


import org.cnbi.entity.SysLog;



public interface SystemService {
	
	/**
	 * 添加日志
	 */
	
	public void addLog(SysLog log)throws Exception;
	/*public void addLog(SysLog log) {
		HttpServletRequest request = ContextHolderUtils.getRequest();
		String broswer = BrowserUtils.checkBrowse(request);
		TSLog log = new TSLog();
		log.setLogcontent(logcontent);
		log.setLoglevel(loglevel);
		log.setOperatetype(operatetype);
		log.setNote(oConvertUtils.getIp());
		log.setBroswer(broswer);
		log.setOperatetime(DataUtils.gettimestamp());
		log.setTSUser(ResourceUtil.getSessionUserName());
		commonDao.save(log);
	}*/

}
