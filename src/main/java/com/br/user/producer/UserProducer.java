package com.br.user.producer;

import com.br.user.dtos.EmailDto;
import com.br.user.model.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;


    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel) {


        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Meus parabens voce foi cadastrado com sucesso!!");
        emailDto.setText(userModel.getName() + ", seja bem vindo a nossa plataforma!!\nAproveite a estadia aproveite o que podemos proporcionar além de mensagens automaticas com Microsserviço!!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);//tipos de exchange, a key da fila, e o corpo do que vai mostrar
    }

}
