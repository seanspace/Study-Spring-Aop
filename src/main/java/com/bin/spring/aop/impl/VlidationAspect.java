package com.bin.spring.aop.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 可以使用@order注解指定切面的优先级,值越小优先级越高.
 */
@Component
@Aspect
@Order(2)
public class VlidationAspect {
	
	//@Before("com.bin.spring.aop.impl.LoggingAspect.declareJoinPointExpression()")// 引用切入点表达式.
	@Before("execution(public * com.bin.spring.aop.impl.*.*(..))")
	public void validateArgs(JoinPoint joinPoint){
		System.out.println("-->vlidation ");
		
	}
}
