package org.cnbi.utils.expression;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class ExpressionParserUtil {
	
	final static Log logger = LogFactory.getLog(ExpressionParserUtil.class);
	
	public static final String ZEROVALUE = "0.00";
	
	public static final String BY_ZERO = "/0";
	
	/**
	 * 千分位显示且四舍五入
	 */
	public static final DecimalFormat format = new DecimalFormat("###,##0.00");
	/**
	 * 相当于el表达式
	 */
	public static final ExpressionParser parser = new SpelExpressionParser();
	
	public static String calculateNumber(String exp) throws Exception {
		String value = ZEROVALUE;
		if (exp.contains(BY_ZERO)) {
			logger.info("有关【" + exp + "】的表达式非法了！除数为0了！");
		} else {
			value = parser.parseExpression(exp).getValue(String.class);
		}
		return value;
	}
	
	/**
	 * 单位切换
	 * @param unit
	 *            1=元 2=千元 3=万元 4=亿元
	 * @param value
	 *            值
	 * @return String
	 * 
	 * */
	public static String convertUnit(Integer unit, String value) {
		if (unit != 1) {// 如果为元的话，没必要再去单位处理了，因为本身从数据库查出来的变是元。免得浪费资源
			BigDecimal convertAfterBigdecimalValue = new BigDecimal(value);
			convertAfterBigdecimalValue = convertAfterBigdecimalValue.divide(BigDecimal.valueOf(unit));
			value = String.valueOf(convertAfterBigdecimalValue.toString());
		}
		return value;
	}
	
	/**
	 * 提供精确的加法运算。
	 * @param v1   被加数
	 * @param v2  加数
	 * 
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).doubleValue();
	}
	/**
	 * 
	 * @param   dividend 被除数
	 * @param   divisor  除数
	 * @Author 龚佳新
	 * @Time 2014年3月24日上午11:17:34
	 * @return  净利润额/销售收入
	 * 就是给divide设置精确的小数点divide(xxxxx,2, BigDecimal.ROUND_HALF_EVEN) 
	 */
	public static double divide(double dividend, double divisor){
		if(dividend ==0){
			return 0.0d;
		}
		BigDecimal b1 = new BigDecimal(dividend);
		BigDecimal b2 = new BigDecimal(divisor);
		BigDecimal	value = b1.divide(b2,4,BigDecimal.ROUND_HALF_EVEN);
		value = value.multiply(BigDecimal.valueOf(100));
		return value.doubleValue();
	}
	
	public static double getDoubleRandom(int max,int min){
	      return new Random().nextDouble()*max*(max-min+1) + min;
	}
	public static int getIntRandom(int max,int min){
	        return new Random().nextInt(max)%(max-min+1) + min;
	}

}
