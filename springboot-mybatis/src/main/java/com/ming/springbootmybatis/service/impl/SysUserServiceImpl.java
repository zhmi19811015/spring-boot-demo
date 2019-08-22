package com.ming.springbootmybatis.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaolyuh.annotation.CacheEvict;
import com.github.xiaolyuh.annotation.CachePut;
import com.github.xiaolyuh.annotation.Cacheable;
import com.github.xiaolyuh.annotation.SecondaryCache;
import com.ming.springbootmybatis.entity.SysUser;
import com.ming.springbootmybatis.mapper.SysUserMapper;
import com.ming.springbootmybatis.service.SysUserService;
import com.ming.springbootmybatis.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/15 8:38 AM
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @CachePut(value = "user", key = "#user.id")
    @Override
    public void save(SysUser user) {
        sysUserMapper.insertSelective(user);
    }

    @Override
    public void saveBath(List<SysUser> list) {
        int i= 1;
        for (SysUser user: list){
            save(user);
            if (i % 2 == 0){
                throw  new RuntimeException("故意抛出异常，测试回滚");
            }
            i++;
        }
    }

    @CachePut(value = "user", key = "#user.id")
    @Override
    public void update(SysUser sysUser) {
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * ,unless = "#result == null"接口返回值不为空的时候才缓存 spring cache无法处理缓存穿透等问题
     *
     * @param userId
     * @return
     */
    @Cacheable(value = "user", key = "#userId",
            secondaryCache=@SecondaryCache(timeUnit = TimeUnit.SECONDS,expireTime = 200,preloadTime = 20,isAllowNullValue = true,magnification = 5))
    @Override
    public SysUser findByUserId(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @CacheEvict(value = "user", key = "#userID")
    @Override
    public int deleteByUserId(String userID) {
       return sysUserMapper.deleteByPrimaryKey(userID);
    }

    @Cacheable(value = "user_page", key = "#userVo.pageNum",
            secondaryCache=@SecondaryCache(timeUnit = TimeUnit.SECONDS,expireTime = 200,preloadTime = 20,isAllowNullValue = true,magnification = 5))
    @Override
    public PageInfo<SysUser> findPage(UserVo userVo) {
        Page page = PageHelper.startPage(userVo.getPageNum(), userVo.getPageSize());


        if (null != userVo.getSortField() && null != userVo.getSortType()){
            page = page.setOrderBy(userVo.getSortField()+" "+userVo.getSortType());
        }


        SysUser user = new SysUser();

        user.setName(userVo.getName());
        user.setSex(userVo.getSex());


        PageInfo<SysUser> pageInfo = page.doSelectPageInfo(() -> this.sysUserMapper.select(user));

        return pageInfo;
    }

    @Cacheable(value = "user_all",
            secondaryCache=@SecondaryCache(timeUnit = TimeUnit.SECONDS,expireTime = 200,preloadTime = 20,isAllowNullValue = true,magnification = 5))
    @Override
    public List<SysUser> findAll() {
       return  sysUserMapper.selectAll();
    }
}
