package com.bin.spring.aop.xml;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {
	
	
	public void beforeMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName() ;
		List<Object> args = Arrays.asList(joinPoint.getArgs()) ;
		System.out.println("The Method begins " + methodName + args);
	}
	
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName() ;
		List<Object> args = Arrays.asList(joinPoint.getArgs()) ;
		System.out.println(" Ends of The Method " + methodName + args);
	}
	
	public void afterReturning(JoinPoint joinPoint,Object result){
		String methodName = joinPoint.getSignature().getName() ;
		List<Object> args = Arrays.asList(joinPoint.getArgs()) ;
		System.out.println(" Ends of The Method " + methodName + args + ",return " + result);
	}
	
	public void afterThrowing(JoinPoint joinPoint,Exception ex){
		String methodName = joinPoint.getSignature().getName() ;
		List<Object> args = Arrays.asList(joinPoint.getArgs()) ;
		System.out.println(" Ends of The Method " + methodName + args + ",occurs exception " + ex);
		
	}
	
	public Object aroundMethod(ProceedingJoinPoint pjd) {
		System.out.println("aroundMethod");
		Object result = null ;
		String methodName = pjd.getSignature().getName() ;
		// 执行目标方法
		try {
			// 前置通知
			System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
			result = pjd.proceed() ;
			// 后置通知
			System.out.println("ends");
		} catch (Throwable e) {
			// 异常通知
			System.out.println("The method occurs exception: " + e);
			throw new RuntimeException(e) ;
		}
		// 后置通知
		System.out.println("ends");
		return result ;
	}
	
	

}
