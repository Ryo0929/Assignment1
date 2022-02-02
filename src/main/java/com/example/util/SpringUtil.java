package com.example.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Brayden
 * @create 2/1/22 4:27 PM
 * @Description
 */
@Configuration
public class SpringUtil implements BeanFactoryPostProcessor
{

	private static ConfigurableListableBeanFactory beanFactory;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
	{
		SpringUtil.beanFactory = beanFactory;
	}

	public static Object getBean(String name)
	{
		return beanFactory.getBean(name);
	}

	public static Object getBean(Class c)
	{
		return beanFactory.getBean(c);
	}
}