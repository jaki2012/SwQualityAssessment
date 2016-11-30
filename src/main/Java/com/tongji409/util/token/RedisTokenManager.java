package com.tongji409.util.token;

import com.tongji409.domain.Token;
import com.tongji409.util.config.StaticConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by lijiechu on 16/11/30.
 */

public class RedisTokenManager implements TokenManager{
    public RedisTemplate getRedis() {
        return redis;
    }

    private RedisTemplate redis;

    @Autowired
    public void setRedis(RedisTemplate redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    public Token createToken(long userId) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        Token model = new Token(userId, token);
        //存储到redis并设置过期时间
        redis.boundValueOps(userId).set(token, StaticConstant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    public Token getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        long userId = Long.parseLong(param[0]);
        String token = param[1];
        return new Token(userId, token);
    }

    public boolean checkToken(Token model) {
        if (model == null) {
            return false;
        }
        String token = String.valueOf(redis.boundValueOps(model.getUserId()).get());
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(model.getUserId()).expire(StaticConstant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    public void deleteToken(long userId) {
        redis.delete(userId);
    }
}
