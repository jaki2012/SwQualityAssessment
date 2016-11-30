package com.tongji409.util.token;

import com.tongji409.domain.Token;

/**
 * 对token进行操作的接口
 * Created by lijiechu on 16/11/30.
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     * @param userId 指定用户的id
     * @return 生成的token
     */
    public Token createToken(long userId);

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    public boolean checkToken(Token model);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    public Token getToken(String authentication);

    /**
     * 清除token
     * @param userId 登录用户的id
     */
    public void deleteToken(long userId);

}
