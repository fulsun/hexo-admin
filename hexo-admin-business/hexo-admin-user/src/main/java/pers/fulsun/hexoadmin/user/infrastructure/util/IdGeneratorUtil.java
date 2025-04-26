package pers.fulsun.hexoadmin.user.infrastructure.util;

import cn.hutool.core.util.IdUtil;

public class IdGeneratorUtil {
    
    /**
     * 利用雪花算法生成一个唯一ID
     */
    public static long nexId() {
        return IdUtil.getSnowflakeNextId();
    }
}
