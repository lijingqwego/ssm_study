package com.kaisn.test.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQSender {

	private final static String QUEUE_NAME = "com.kaisn.queue1";

	public static void main(String[] args) throws TimeoutException,IOException {
		send("hello world");
	}

	public static void send(String message) throws TimeoutException, IOException {
		//创建一个连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setUsername("lijing");
		factory.setPassword("asd3135");
		factory.setVirtualHost("/emp");
		//创建连接
		Connection connection = factory.newConnection();
		//创建通道
		Channel channel = connection.createChannel();
		//消息队列声明或绑定
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//发送消息
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		//关闭资源
		channel.close();
		connection.close();
	}

}
