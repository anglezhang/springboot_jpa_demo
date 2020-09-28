package cn.com.doone.tx.cloud.service.config.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 * Created by Zhenghb on 2018/5/3
 *
 */
public class RegexUtil {
	
	public static boolean checkAgent(String pwd) {
		Pattern p = Pattern.compile(
				"^(?![0-9a-z]+$)(?![0-9A-Z]+$)(?![0-9\\W]+$)(?![a-z\\W]+$)(?![a-zA-Z]+$)(?![A-Z\\W]+$)[a-zA-Z0-9\\W_]+$");
		Matcher m = p.matcher(pwd);
		return m.find();
	}
	
}
