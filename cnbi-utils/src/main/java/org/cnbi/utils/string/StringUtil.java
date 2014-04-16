package org.cnbi.utils.string;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
public class StringUtil extends StringUtils {
	/**
	 * 日志类
	 */
	final static Log logger = LogFactory.getLog(StringUtil.class);
	
	
	private static final Pattern chineseCharacter = Pattern.compile("[\u4E00-\u9FA0]", Pattern.CANON_EQ);

	private static final Pattern letterAndChineseCharacter = Pattern.compile("[[\u4E00-\u9FA0]a-zA-Z]", Pattern.CANON_EQ);

	private static final Pattern pattern = Pattern.compile("(-|\\+|)[0-9]+(\\.|)[0-9]*");

	private static final Pattern isFullShape = Pattern.compile("[\\uFF00-\\uFFFF]");

//	private static final Pattern isCellPattern = Pattern.compile("([a-zA-Z]+)(\\d+)");
	public static boolean isBlank(String string){
		return isEmpty(string);
	}
	
	/**
	 * 获取字符串中的所有数字
	 * @param string
	 * @return
	 * @Author 龚佳新
	 * @Time 2014年3月23日上午11:18:29
	 */
	public static String getAllNumber(String string){
		Pattern p = Pattern.compile("[^0-9]");   
		Matcher m = p.matcher(string);   
		return m.replaceAll("").trim();
	}
	/**
	 * 获取字符串中的所有字符
	 * @param string
	 * @return
	 * @Author 龚佳新
	 * @Time 2014年3月23日上午11:18:29
	 */
	public static String getAllLetter(String string){
		Pattern p = Pattern.compile("[^a-zA-Z]"); //a-zA-Z  
		Matcher m = p.matcher(string);   
		return m.replaceAll("").trim();
	}
	
	public static String  pathRemoveBlank(String path){
		path = path.replaceAll("%20", " ");
		return path;
	}
	/**
	 * 判断是否为数字
	 * 
	 * @param str
	 * @return
	 * @Author 龚佳新
	 * @Time 2014年3月14日上午8:43:29
	 */
	public static boolean isNumeric(String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		if ("0E-31".equals(str)) {
			return true;
		}
		if ("0E-15".equals(str)) {
			return true;
		}
		if ("0E-16".equals(str)) {
			return true;
		}
		return pattern.matcher(str.replaceAll(",", "")).matches();
	}
	public static boolean hasFullShape(String s) {
		Matcher m = isFullShape.matcher(s == null ? "" : s);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean hasChineseCharacter(String s) {
		Matcher m = chineseCharacter.matcher(s == null ? "" : s);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isLetterAndChineseCharacter(String s) {
		Matcher m = letterAndChineseCharacter.matcher(s == null ? "" : s);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isFormula(String value) {
		return value.startsWith("=<") && value.indexOf(">") != -1;
	}
	public static int convertLetterToNum(String column) {
		for (int i = 0; i < 256; i++) {
			String columnName = convertNumToColumnName(i);
			if (columnName.equalsIgnoreCase(column)) {
				return i+1;
			}
		}
		return -1;
	}
	
	public static String convertNumToLetter(int column) {
		column = column-1;
		String result = "";
		for (; column >= 0; column = column / 26 - 1) {
			result = (char) ((char) (column % 26) + 'A') + result;
		}

		return result;
	}
	public static String convertNumToColumnName(int column) {
		String result = "";
		for (; column >= 0; column = column / 26 - 1) {
			result = (char) ((char) (column % 26) + 'A') + result;
		}

		return result;
	}

	public static String parseNumber(String str) {
		if (str == null || str.equals("") || str.equals("0E-31") || str.equals("0E-15") || str.equals("0E-16") || str.equals("N/A")) {
			return "0";
		}
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			char x = str.charAt(i);
			if (Character.isDigit(x) == true || x == '.' || x == '+' || x == '-') {
				result += x;
			}
		}
		return result;
	}

	/**
	 * 判断是否有百分符号
	 * 
	 * @param str
	 * @return
	 */
	public static boolean PercentageTest(String str) {
		return str.indexOf("%") != -1;
	}

	/**
	 * 用来统计某个单词出现的个数
	 * 
	 * @param source
	 * @param word
	 * @return
	 */
	public static int countWord(String source, String word) {
		char[] word_arr = source.toCharArray();
		int word_num = 0; // 用来统计单词出现的次数
		char[] char_arr = word.toCharArray();

		for (int i = 0; i < word_arr.length; i++) {
			if (char_arr[0] == word_arr[i] && i + char_arr.length <= word_arr.length) {
				int m = i + 1;
				boolean flag = true;
				for (int j = 1; j < char_arr.length;) {
					if (word_arr[j++] != char_arr[m++]) {
						flag = false;
						break;
					}
				}
				if (flag) {
					word_num++;
				}
			}
		}
		return word_num;
	}

	/**
	 * 首字母转小写
	 * 
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * 首字母转大写
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * 去除所有空格 回车 换行等…………
	 * @param str
	 * @return
	 * @Author 龚佳新
	 * @Time 2014年3月14日上午8:49:42
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		dest = dest.replace("\\n", "").replace("\t", "");
		return dest;
	}
	/**
	 * 去除左边与右边的空格
	 * @param str
	 * @return
	 */
	public static String replaceLeftAndRightBlank(String str) {
		str = str.trim();
		str = str.substring(str.lastIndexOf(str.trim()));
		Pattern p = Pattern.compile("\\t|\r|\n");
		Matcher m = p.matcher(str);
		str = m.replaceAll("");
		return str;
	}
}
