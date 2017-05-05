package com.wzf.mvpdemo.utils;/** * 一些正则表达式 */import android.text.InputFilter;import android.text.Spanned;import android.text.TextUtils;import java.util.ArrayList;import java.util.List;import java.util.regex.Matcher;import java.util.regex.Pattern;/** * 常用正则验证 *  * @author wangzhenfei * @version 1.0 * @date 2015-04-14 */public class REGX {	/**	 * 密码的正则	 */	public static final String PASSWORD_REGEX = "[A-Z0-9a-z]+";	/**	 * 手机正则	 */	public static final String REGX_MOBILE = "^((1[3-9][0-9]))\\d{8}$";	/**	 * 邮箱正则	 */	public static final String REGX_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";//	public static final String REGX_MOBILE_INPUT = "^1$|^1[34578]$|^1[3578]\\d{0,9}$";	public static final String REGX_MOBILE_INPUT = "^1$|^1[34578]$|^1[3-8]\\d{0,9}$";	public static final String REGX_NOT_START_WITH_ZERO = "^[123456789]\\d{0,9}$";	/**中文 5个字 */	public static String  THEMESTR_FORMAT = "^[\u4e00-\u9fa5]{0,5}$";	public static List<String> ILLEGAL = new ArrayList<>();	static {		ILLEGAL.add("weixin");		ILLEGAL.add("wx");		ILLEGAL.add("微信");		ILLEGAL.add("扣扣");		ILLEGAL.add("qq");		ILLEGAL.add("QQ");		ILLEGAL.add("Qq");		ILLEGAL.add("qQ");	}		/**	 * 获得手机号码的正则过滤器	 * @return	 */	public static InputFilter[] getFilters(final String str){		return new InputFilter[] { new InputFilter() {			@Override			public CharSequence filter(CharSequence source, int start, int end,					Spanned dest, int dstart, int dend) {				String content = dest.toString().substring(0, dstart)						+ source						+ dest.toString().substring(dend,								dest.toString().length());				if (TextUtils.isEmpty(content)) {					return source;				} else if (content.matches(str)) {					return source;				} else {					return "";				}			}		}};    	    }	/**	 * 不合法的输入	 * @param string	 * @return	 */	public static String illegalText(String string) {		Pattern pattern = Pattern.compile("\\d{5}");		Matcher matcher = pattern.matcher(string);		StringBuffer bf = new StringBuffer(64);		while (matcher.find()) {			DebugLog.i("正则结果" + string.substring(matcher.start(), matcher.end()));			return "连续数字长度超长，请重新输入";		}		for(String ill : ILLEGAL){			if(string.contains(ill)){				return "输入不能包含\"" + ill +"\"";			}		}		return null;	}	public static boolean isEmail(String input) {		Pattern p = Pattern.compile(REGX_EMAIL);		Matcher m = p.matcher(input);		return m.matches();	}	public static boolean isMobile(String input) {		Pattern p = Pattern.compile(REGX_MOBILE);		Matcher m = p.matcher(input);		return m.matches();	}}