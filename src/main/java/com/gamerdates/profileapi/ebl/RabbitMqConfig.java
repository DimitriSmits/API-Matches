package com.gamerdates.profileapi.ebl;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMqConfig {
    private String LISTENER_METHOD_B = "receiveMessageB";
    private String LISTENER_METHOD_C = "receiveMessageC";

    @Value("${direct.exchange}")
    private String directExchange;

    @Value("${queue.B}")
    private String queueNameB;

    @Value("${queue.C}")
    private String queueNameC;

    @Value("${routing.direct.1}")
    private String direct1RoutingKey;

    @Value("${routing.direct.2}")
    private String direct2RoutingKey;

    @Bean
    Queue queueB() {
        return new Queue(queueNameB, true);
    }

    @Bean
    Queue queueC() {
        return new Queue(queueNameC, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(directExchange);
    }

    @Bean
    public Binding bindingDirectExchangeQueueADirect1(DirectExchange directExchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(directExchange).with(direct1RoutingKey);
    }

    @Bean
    public Binding bindingDirectExchangeQueueCDirect2(DirectExchange directExchange, Queue queueC) {
        return BindingBuilder.bind(queueC).to(directExchange).with(direct2RoutingKey);
    }

    @Bean
    SimpleMessageListenerContainer container1(ConnectionFactory connectionFactory,
                                              @Qualifier("listenerB") MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueNameB);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    SimpleMessageListenerContainer container2(ConnectionFactory connectionFactory,
                                              @Qualifier("listenerC") MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueNameC);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean("listenerB")
    MessageListenerAdapter listenerAdapterB(QueueConsumer receiver) {
        return new MessageListenerAdapter(receiver, LISTENER_METHOD_B);
    }

    @Bean("listenerC")
    MessageListenerAdapter listenerAdapterC(QueueConsumer receiver) {
        return new MessageListenerAdapter(receiver, LISTENER_METHOD_C);
    }
}

