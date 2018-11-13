package com.topjet;

import com.topjet.manage.controller.SysUserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationsystemApplicationTests {

	private MockMvc mvc;

	public void setUp() throws Exception{
		mvc = MockMvcBuilders.standaloneSetup(new SysUserController()).build();
	}

	@Test
	public void contextLoads() throws Exception{

	}

}
