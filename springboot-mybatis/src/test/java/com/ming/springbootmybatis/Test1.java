package com.ming.springbootmybatis;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import com.ming.springbootmybatis.entity.SysUser;
import com.ming.springbootmybatis.service.SysUserService;
import com.ming.springbootmybatis.vo.PageVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/15 8:55 AM
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1  extends AbstractTestNGSpringContextTests {
    @Autowired
    private SysUserService sysUserService;

    @org.testng.annotations.Test(invocationCount = 1, threadPoolSize = 1)
    public void save() throws Exception {
        for (int i=41;i<=50;i++){
            String id = IdUtil.simpleUUID();
            SysUser user = new SysUser();
            user.setId(id);
            user.setName(i+"");
            user.setSex(i);
            user.setBirthDate(new Date());
            sysUserService.save(user);
            Thread.sleep(200);
        }
    }

    @org.testng.annotations.Test(invocationCount = 1, threadPoolSize = 1)
    public void saveBath() {
        List<SysUser> users = new ArrayList<>();
        for (int i=41;i<=50;i++){
            String id = IdUtil.simpleUUID();
            SysUser user = new SysUser();
            user.setId(id);
            user.setName("tom_"+i);
            user.setSex(i);
            user.setBirthDate(new Date());
            users.add(user);
        }

        sysUserService.saveBath(users);


    }

    @org.testng.annotations.Test(invocationCount = 1, threadPoolSize = 1)
    public void findByUserId(){
        SysUser user = sysUserService.findByUserId("345353533");
        Console.log(user);
    }

    @org.testng.annotations.Test(invocationCount = 100, threadPoolSize = 10)
    public void findPage(){
        PageVo pageVo = new PageVo();
        pageVo.setPageNum(1);
        pageVo.setPageSize(10);

       // PageInfo<SysUser> pageInfo = sysUserService.findPage(pageVo);

        //Console.log("[分页信息] - [{}]", pageInfo.toString());
    }

    @org.testng.annotations.Test(invocationCount = 100, threadPoolSize = 10)
    public void findAll(){
        List<SysUser> list =sysUserService.findAll();

        Console.log("sysUser all size :"+list.size());
    }



    @Test
    public void del(){
        int count = sysUserService.deleteByUserId("16190c02e1584d859d278d9b791ef46f");
        Console.log("==========="+count);
    }
}
