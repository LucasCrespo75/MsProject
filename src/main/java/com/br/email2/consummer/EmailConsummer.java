package com.br.email2.consummer;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsummer {

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listennerQueue(@Payload String  string){
        System.out.println(string);
    }
}
