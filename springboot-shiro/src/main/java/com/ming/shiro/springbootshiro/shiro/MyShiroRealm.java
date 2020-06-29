package com.ming.shiro.springbootshiro.shiro;

import com.ming.shiro.springbootshiro.service.ShiroService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 登录认证
     * username：认证的实体信息。object,一般存放用户信息对象。可以通过SecurityUtils.getSubject().getPrincipal()获取。
     * password：数据库中保存的密码
     * realmName：当前realm对象的name，调用父类的getName()方法即可
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        //获取用户账号
        String username = token.getPrincipal().toString();

        String password = shiroService.getPasswordByUsername(username);
        if (password == null){
            throw new UnknownAccountException(); // 账号不存在
            //throw new LockedAccountException();  // 账号被锁定
        }
        if (password != null) {
            String realmName = getName();
            return new SimpleAuthenticationInfo(username, password, realmName);
        }
        return null;
    }
}
