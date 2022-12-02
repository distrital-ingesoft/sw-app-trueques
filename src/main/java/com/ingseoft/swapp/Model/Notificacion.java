package com.ingseoft.swapp.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Notificacion {

    @Autowired
    private JavaMailSender mailSender;


    public String enviarCorreo(String to, String subject, String cuerpoMensaje){

        SimpleMailMessage email = new SimpleMailMessage();

        
        email.setFrom(to);
        email.setTo(to);
        email.setSubject(subject);
        email.setText(cuerpoMensaje);

        System.out.println("EMAILLLLLLLLLLLLLLLLLLLLLLL " + email.toString());
        this.mailSender.send(email);

        return "ok";
    };

}
