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
		//������е�������
		QueueingConsumer consumer=new QueueingConsumer(channel);
		//������Ϣ����
		channel.basicConsume(QUEUE_NAME, true, consumer);
		while(true) 
		{
			Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(message);
		}
	}

}
