package com.kaisn.test.javabase;

public class ReflectServiceImpl implements ReflectService{
	
	private String name;
	
	public ReflectServiceImpl(){
		
	}
	
	public void test(){
		System.out.println("Test");
	}
	
	public ReflectServiceImpl(String name){
		this.name=name;
	}
	public void sayHello(String name) {
		System.out.println("Hello "+name);
	}

}
