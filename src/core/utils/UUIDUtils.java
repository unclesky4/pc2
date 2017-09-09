package core.utils;

import java.util.UUID;

/**
 * 获取uuid工具类
 * @author unclesky4  09/09/2017
 *
 */
public class UUIDUtils {
	
	public String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
