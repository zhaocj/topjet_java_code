package com.topjet;

import com.topjet.manage.controller.TaskJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationsystemApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(OperationsystemApplicationTests.class);


	@Autowired
	private TaskJob taskJob;

	@Test
	public void contextLoads() {
	}






}
