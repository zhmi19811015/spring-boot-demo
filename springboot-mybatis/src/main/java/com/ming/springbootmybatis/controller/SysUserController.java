package com.ming.springbootmybatis.controller;

import com.github.pagehelper.PageInfo;
import com.ming.springbootmybatis.entity.SysUser;
import com.ming.springbootmybatis.service.SysUserService;
import com.ming.springbootmybatis.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/23 8:42 AM
 */
@RestController
@RequestMapping("/users")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/findAll")
    public List<SysUser> findAll(){
        return sysUserService.findAll();
    }

    @PostMapping("/findPage")
    public PageInfo findPage(UserVo userVo){
        return sysUserService.findPage(userVo);
    }

    @PostMapping("/save")
    public void save(SysUser user){
        sysUserService.save(user);
    }

    @DeleteMapping("/del")
    public void del(String userId){
        sysUserService.deleteByUserId(userId);
    }

}
