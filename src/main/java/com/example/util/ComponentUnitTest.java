package com.example.util;

//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Brayden
 * @create 2/1/22 4:59 PM
 * @Description
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ComponentUnitTest {

	@Autowired
	private ApplicationContext applicationContext;
/*
	@Test
	public void givenInScopeComponents_whenSearchingInApplicationContext_thenFindThem() {
		Object a = (applicationContext.getBean(SpringUtil.class));
//		Object b = (applicationContext.getBean(.class));
//		Object c = (applicationContext.getBean(.class));
		System.out.println(1);
	}
*/
}