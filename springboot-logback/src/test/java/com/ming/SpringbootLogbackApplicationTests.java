package com.ming;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringbootLogbackApplicationTests extends AbstractTestNGSpringContextTests {

//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@Test
//	public void testHome() {
//		ResponseEntity<String> entity = this.restTemplate.getForEntity("/", String.class);
//		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(entity.getBody()).isEqualTo("Hello World");
//	}
//
//
//	@Test(invocationCount = 100, threadPoolSize = 50)
//	public void contextLoads() {
//		System.out.println(Thread.currentThread().getId()+"===="+System.currentTimeMillis());
//	}

}
