package com.ming.springbootmail;

import com.ming.springbootmail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMailApplicationTests {

	@Autowired
	private MailService mailService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test1(){
		mailService.sendSimpleMail("zhangm@ahtsoft.com","章明邮件"," 这是一份测试邮件");
	}

	@Test
	public void test2(){
		String content="<html>\n" +
				"<body>\n" +
				"    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
				"</body>\n" +
				"</html>";
		mailService.sendHtmlMail("zhangm@ahtsoft.com","html邮件",content);
	}

	@Test
	public void test3(){
		String filePath = "/tmp/logs/netty/info.log";
		mailService.sendAttachmentsMail("zhangm@ahtsoft.com","主题：带附件的邮件","有附件，请查收",filePath);
	}

}
