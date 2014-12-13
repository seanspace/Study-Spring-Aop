package com.bin.spring.aop.impl;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 把这个类声明为一个切面:
 * 1. 需要把该类放到IOC容器中,再声明为一个切面.
 * 2. 
 *
 */
@Aspect
@Component
public class LoggingAspect {
	
	/**
	 * 定义一个方法,用于声明切入点表达式.一般此方法中不需要填入其他的代码.
	 */
	@Pointcut("execution(public * com.bin.spring.aop.impl.*.*(..))")
	public void declareJoinPointExpression(){
		
	}
	
	
	/**
	 * 声明该方法是一个前置通知:在目标方法开始之前执行.
	 * 参数括号中两个点,代表所有参数,包括个数和类型都不限制.
	 */
	//@Before("execution(int com.bin.spring.aop.impl.ArithmeticCalculator.*(int,int))")
	@Before("declareJoinPointExpression()")// 这样就引用了上面的切入点,
	public void beforeMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName() ;
		List<Object> args = Arrays.asList(joinPoint.getArgs()) ;
		System.out.println("The Method begins " + methodName + args);
	}
	
	/**
	 * 后置通知: 在目标方法执行后(无论是否发生异常),执行的通知.
	 * 在后置通知中还不能访问目标方法的执行结果.
	 */
	@After("execution(int com.bin.spring.aop.impl.*.*(int,int))")
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName() ;
		List<Object> args = Arrays.asList(joinPoint.getArgs()) ;
		System.out.println(" Ends of The Method " + methodName + args);
	}
	
	/**
	 * 在方法正常结束受影响的代码
	 * 返回通知是可以访问到方法的返回值的.
	 */
	@AfterReturning(value="execution(int com.bin.spring.aop.impl.*.*(int,int))",returning="result")
	public void afterReturning(JoinPoint joinPoint,Object result){
		String methodName = joinPoint.getSignature().getName() ;
		List<Object> args = Arrays.asList(joinPoint.getArgs()) ;
		System.out.println(" Ends of The Method " + methodName + args + ",return " + result);
	}
	
	/**
	 * 在目标方法出现异常时,会执行此方法.
	 * 可以访问到异常对象 ; 且可以指定出现特定的异常.
	 */
	@AfterThrowing(value = "execution(int com.bin.spring.aop.impl.*.*(..))",throwing="ex")
	public void afterThrowing(JoinPoint joinPoint,Exception ex){
		String methodName = joinPoint.getSignature().getName() ;
		List<Object> args = Arrays.asList(joinPoint.getArgs()) ;
		System.out.println(" Ends of The Method " + methodName + args + ",occurs exception " + ex);
		
	}
	
	/**
	 * 环绕通知需要携带ProceedingJoinPoint类型的参数.
	 * 环绕通知类似于动态代理的全过程:ProceedingJoinPoint类型的参数可以决定是否执行目标方法.
	 * 且环绕通知必须要有返回值,返回值即为目标方法的返回值.
	 */
	@Around(value = "execution(int com.bin.spring.aop.impl.*.*(..))")
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
