package com.bin.spring.aop.xml;

import org.aspectj.lang.JoinPoint;

public class VlidationAspect {
	
	public void validateArgs(JoinPoint joinPoint){
		System.out.println("-->vlidation ");
		
	}
}
