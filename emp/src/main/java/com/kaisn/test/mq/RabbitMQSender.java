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
		//����һ�����ӹ���
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setUsername("lijing");
		factory.setPassword("asd3135");
		factory.setVirtualHost("/emp");
		//��������
		Connection connection = factory.newConnection();
		//����ͨ��
		Channel channel = connection.createChannel();
		//��Ϣ�����������
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//������Ϣ
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		//�ر���Դ
		channel.close();
		connection.close();
	}

}
