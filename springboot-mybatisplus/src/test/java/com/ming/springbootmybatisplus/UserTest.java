package com.ming.springbootmybatisplus;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import com.ming.springbootmybatisplus.mapper.SysUserMapper;
import com.ming.springbootmybatisplus.model.SysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-11-23 21:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Resource
    private SysUserMapper sysUserMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<SysUser> userList = sysUserMapper.selectList(null);
        Assert.assertNotNull(userList);//.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testSave(){
        SysUser sysUser = new SysUser();
        sysUser.setId(IdUtil.simpleUUID());
        sysUser.setName("章明");
        sysUser.setSex(1);
        sysUser.setBirthDate(new Date());
        int count = sysUserMapper.insert(sysUser);

        Console.log(count);
    }

    @Test
    public void testUpdate(){
        SysUser sysUser = new SysUser();
        sysUser.setId("88acf2cedbe94ed691904a955af91c94");
        sysUser.setBirthDate(new Date());
        sysUser.setSex(2);
        sysUser.setName("王操");
        int count = sysUserMapper.updateById(sysUser);
        Console.log(count);
    }

}
