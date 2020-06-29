package com.ming.springbootmybatis.service;

import com.github.pagehelper.PageInfo;
import com.ming.springbootmybatis.entity.SysUser;
import com.ming.springbootmybatis.vo.UserVo;

import java.util.List;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/15 8:35 AM
 */
public interface SysUserService {
    void save(SysUser user);

    void saveBath(List<SysUser> list);

    void update(SysUser sysUser);

    SysUser findByUserId(String userId);

    int deleteByUserId(String userID);

    PageInfo<SysUser> findPage(UserVo userVo);

    List<SysUser> findAll();
}
