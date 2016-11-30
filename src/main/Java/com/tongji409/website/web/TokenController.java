package com.tongji409.website.web;

import com.tongji409.domain.ResultModel;
import com.tongji409.domain.Token;
import com.tongji409.domain.User;
import com.tongji409.util.config.ResultStatus;
import com.tongji409.util.token.Authorization;
import com.tongji409.util.token.CurrentUser;
import com.tongji409.util.token.TokenManager;
import com.tongji409.website.services.UserService;
import com.tongji409.website.web.Support.BaseDispatcher;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lijiechu on 16/11/30.
 */
@RestController
@Scope("prototype")
@RequestMapping("/tokens")
public class TokenController extends BaseDispatcher{

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "tokenManager")
    private TokenManager tokenManager;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) {
        Assert.notNull(username, "username can not be empty");
        Assert.notNull(password, "password can not be empty");

        User user = userService.getUserByName(username);
        if (user == null ||  //未注册
                !user.getPassword().equals(password)) {  //密码错误
            //提示用户名或密码错误
            return new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }
        //生成一个token，保存用户登录状态
        Token model = tokenManager.createToken(user.getId());
        return new ResponseEntity<>(ResultModel.ok(model), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    public ResponseEntity logout(@CurrentUser User user) {
        tokenManager.deleteToken(user.getId());
        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }

}
