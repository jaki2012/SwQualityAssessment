package com.tongji409.website.services;

import com.tongji409.domain.User;
import com.tongji409.util.config.StaticConstant;
import com.tongji409.util.token.CurrentUser;
import com.tongji409.website.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.annotation.Resource;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * Created by lijiechu on 16/11/30.
 */

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是User并且有CurrentUser注解则支持
        if (parameter.getParameterType().isAssignableFrom(User.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取出鉴权时存入的登录用户Id
        Long currentUserId = (Long) webRequest.getAttribute(StaticConstant.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        if (currentUserId != null) {
            //从数据库中查询并返回
            return userDao.get(currentUserId);
        }
         throw new MissingServletRequestPartException(StaticConstant.CURRENT_USER_ID);
    }
}
