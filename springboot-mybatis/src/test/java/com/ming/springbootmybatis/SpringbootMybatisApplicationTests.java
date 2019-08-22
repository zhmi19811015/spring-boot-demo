package com.ming.springbootmybatis;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ming.springbootmybatis.entity.SysUser;
import com.ming.springbootmybatis.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootMybatisApplicationTests {
	@Autowired
	private SysUserMapper sysUserMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void save(){
		for (int i = 0;i<13;i++){
			String id = IdUtil.simpleUUID();
			SysUser user = new SysUser();
			user.setId(id);
			user.setName("tom");
			user.setSex(1);
			user.setBirthDate(new Date());
			int c = sysUserMapper.insertSelective(user);
			log.info("========="+c);
		}

	}

	@Test
	public void findPage(){
		int count = sysUserMapper.countByUsername("tom");
		log.info("[调用自己写的SQL] - [{}]", count);

		PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> this.sysUserMapper.selectAll());
		log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());

		PageHelper.startPage(1, 10).setOrderBy("id desc");
		final PageInfo<SysUser> userPageInfo = new PageInfo<>(this.sysUserMapper.selectAll());
		log.info("[普通写法] - [{}]", userPageInfo);
	}



}
