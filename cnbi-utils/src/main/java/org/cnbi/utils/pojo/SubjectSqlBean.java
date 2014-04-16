package org.cnbi.utils.pojo;
import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.cnbi.entity.Compose;
import org.cnbi.entity.Subject;
import org.cnbi.utils.tools.Constants;
/**
 * Copyright © 2014中国经邦. All rights reserved.
 * 
 * @Author 龚佳新
 * @Time 2014年3月12日下午7:14:11 维度数据查询类
 */
public class SubjectSqlBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主题对象
	 */
	private Subject subject;
	/**
	 * 所有主题维度集合-----当传入相关维度时，就在这个集合里遍历所引
	 */
	private Map<String,Compose> composeMap;
	/**
	 * 事实列的map
	 */
	private Map<String, String> measureMap;

	/**
	 * 传入相关维度的集合
	 */
	private Map<String, String> conditionMap;
	/**
	 * 传入的查询事实列集合
	 */
	private String[] queryFact;
	/**
	 * 显示相关维度的列 当showDim的长度为0时，显示的所有维度+事实列
	 */
	private String[] showDim;

	/**
	 * 行列转置的维度名称
	 */
	private String povitDim;
	/**
	 * 
	 */
	private String orderByDim [];
	
	
	public SubjectSqlBean() {

	}

	/**
	 * @param subject
	 * @param compseList
	 * @param measureMap
	 * @Author 龚佳新
	 * @Time 2014年3月12日下午7:31:40
	 */

	public SubjectSqlBean(Subject subject,Map<String,Compose> compseMap,Map<String, String> measureMap) {
		super();
		this.subject = subject;
		this.composeMap = compseMap;
		this.measureMap = measureMap;
	}

	/**
	 * 行列转置时需要的辅助列数组
	 */
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	

	

	public Map<String, Compose> getComposeMap() {
		return composeMap;
	}

	public void setComposeMap(Map<String, Compose> composeMap) {
		this.composeMap = composeMap;
	}

	

	public Map<String, String> getConditionMap() {
		return conditionMap;
	}

	public void setConditionMap(Map<String, String> conditionMap) {
		this.conditionMap = conditionMap;
	}

	public String[] getQueryFact() {
		return queryFact;
	}

	public void setQueryFact(String[] queryFact) {
		this.queryFact = queryFact;
	}

	public String[] getShowDim() {
		return showDim;
	}

	public void setShowDim(String[] showDim) {
		this.showDim = showDim;
	}

	public String getPovitDim() {
		return povitDim;
	}

	public void setPovitDim(String povitDim) {
		this.povitDim = povitDim;
	}

	public String[] getOrderByDim() {
		return orderByDim;
	}

	public void setOrderByDim(String[] orderByDim) {
		this.orderByDim = orderByDim;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<String, String> getMeasureMap() {
		return measureMap;
	}

	public void setMeasureMap(Map<String, String> measureMap) {
		this.measureMap = measureMap;
	}
	private void set_fact(String ds,StringBuffer sql,Compose compose,DisplayBean displayBean){
		if(!displayBean.isShowScode() ||!StringUtils.isBlank(povitDim)){//如果不行列转置
			sql.append(ds+compose.getField());
		}else{
			if(displayBean.isShowScode()){
				sql.append(ds+compose.getField()+" as "+ds+compose.getField());
			}else{
				
			}
			if(displayBean.isShowSname()){
				if(displayBean.isShowScode()){
					 sql.append(",");
				}
				 sql.append("(select sname from ").append(compose.getFactTable()).append(" where scode =").append(ds+compose.getField()).append(") as \""+compose.getSname()+"\"");
			}
           
            // .append(") as "+ds+compose.getField()+"");////gjx
		}
    	sql.append(",");
	}
	
	
	
	/**
	 *  select dim_Item as "编码" , (select sname from DW_DimItem where scode =a.dim_Item ) as "项目",
	 *   fact_A/10000 as "本期数", fact_E/10000 as "上年同期累计"
            from DW_FactFinance
             as a where dim_Period in ('201303')
              and dim_Company in ('100201') 
              and dim_Item in ('1150','1114','1113','112910','11291012','1126') 
              and subject = '0001'
	 */
	public String getSql(DisplayBean displayBean){
		String  split = subject.getSplit(),//_
			    dim= subject.getDim(),//dim
			    ds=dim+split,
			    fact= subject.getFact(),//fact
			    fs=fact+split;
		StringBuffer sql  = new StringBuffer("select ");
		if(null==showDim||showDim.length==0){
			for (Map.Entry<String, Compose> entry : composeMap.entrySet()){//String key = entry.getKey();//field
				    Compose compose = entry.getValue();
				    set_fact(ds, sql, compose,displayBean);
			}
		}else{
			for(int x=0;x<showDim.length;x++){//String dimS = showDim[x].substring(0,1).toUpperCase()+showDim[x].substring(1);
				Compose compose =composeMap.get(showDim[x]);
				set_fact(ds, sql, compose,displayBean);
			}
		}
		if(null==queryFact||queryFact.length ==0){
			for (Map.Entry<String, String> entry : measureMap.entrySet()){
				 String key = entry.getKey();//value = entry.getValue();
				 sql.append(fs+key);
				 if(StringUtils.isBlank(povitDim)){
					 if(!displayBean.isShowSname()){
						 sql.append(" as "+fs+key+""); 
					 }else{
						 sql.append(" as \""+entry.getValue()+"\""); 
					 }
				 }
				 sql.append(",");
			}
			sql.delete(sql.length()-1, sql.length());
		}else{
			for (int l = 0; l < queryFact.length; l++) {
				sql.append(fs+queryFact[l]);
				 if(StringUtils.isBlank(povitDim)){
					 if(!displayBean.isShowSname()){
						 sql.append(" as "+fs+queryFact[l]);
					 }else{
						 sql.append(" as \""+measureMap.get(queryFact[l])+"\"");
					 }
					
				 }
				if(l!=queryFact.length-1){
					sql.append(",");
				}
			}
			
		}
		sql.append("  from ").append(subject.getFactTable());
		
		/**
		 * 条件开始  一定要有条件维度map
	    */
		if(null !=conditionMap && conditionMap.size() > 0){
			sql.append(" where subject = '"+subject.getScode()+"'");
			for (Map.Entry<String, String> entry : conditionMap.entrySet()){
				String dimKey = entry.getKey(),dimValue = entry.getValue();
				 if(dimValue.contains(Constants.SEMICOLON)){//有分号  条件数组
					 dimValue =  dimValue.split("\\"+Constants.SEMICOLON)[0];
				 }
				 StringBuffer value=new StringBuffer(dimValue);//201301-1月
				 String []temp  = dimValue.split("\\,");//说明传的有多个值
				 for (int l = 0,ll = temp.length; l <ll ; l++) {
						String vv = temp[l];
						if(vv.contains(Constants.UNDERLINE)){}//有别名
						value.append("'").append(vv.split("\\"+Constants.UNDERLINE)[0]).append("'");
						if(l !=ll-1){
							value.append(",");
						}
				 }
				 sql.append(" and "+ds+dimKey+" in ("+value.toString()+")").append(",");
			}
			sql.delete(sql.length()-1, sql.length());
		}
		if( null != orderByDim){
			if(StringUtils.isBlank(povitDim)){
				sql.append(" order by ");
				for(int x=0,len=orderByDim.length;x<len;x++){
					String tF  = orderByDim[x];
					if(tF.contains("_")){
						sql.append(tF);
					}else{
						if(tF.length()>1){
							StringBuffer  tf  = new StringBuffer(orderByDim[x]);
							tf.insert(1, " ");
							tF = tf.toString();
						}
						sql.append(fs+tF);
						if(x!=orderByDim.length-1){
							sql.append(",");
						}
					}
				}
			}
		}
		return sql.toString();
	}
	
	public void sumSql(){
		
	}

}
