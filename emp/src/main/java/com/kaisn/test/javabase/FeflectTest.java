package com.kaisn.test.javabase;

public class FeflectTest {

	public static void main(String[] args) throws Exception{
		
//		Object target = Class.forName("com.kaisn.test.javabase.ReflectServiceImpl").newInstance();
//		
//		Method method = ReflectServiceImpl.class.getMethod("sayHello", String.class);
//		
//		method.invoke(target, "Àî¾§");
		
		
		JdkProxyExample proxy = new JdkProxyExample();
		
		ReflectService bind = (ReflectService)proxy.bind(new ReflectServiceImpl());
		
		bind.sayHello("ÕÅÈý");
		
		
	}

}
