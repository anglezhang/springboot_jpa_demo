package cn.com.doone.tx.cloud.service.config.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class VirtualAccountUtil {
	/**
	 * 生成N位随机数
	 *
	 * @return
	 */
	public static String getCharAndNumr(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

			if ("char".equalsIgnoreCase(charOrNum)) { // 字符串
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 65; // 取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字

				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
	//虚拟账号REGISTyyyyMMdd+6位随机数
	public static String getVirAccountId() {
		String accountId = "REGIST";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String now = sdf.format(date);
		String val = getCharAndNumr(6);
		accountId = accountId+now+val;
		return accountId;
	}

}
