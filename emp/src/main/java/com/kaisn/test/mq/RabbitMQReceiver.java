package com.kaisn.test.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

public class RabbitMQReceiver {

	private final static String QUEUE_NAME = "com.kaisn.queue1";

	public static void main(String[] args) throws TimeoutException, IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		receive();
	}

	public static void receive() throws TimeoutException, IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
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
		//定义队列的消费者
		QueueingConsumer consumer=new QueueingConsumer(channel);
		//监听消息队列
		channel.basicConsume(QUEUE_NAME, true, consumer);
		while(true) 
		{
			Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(message);
		}
	}

}
