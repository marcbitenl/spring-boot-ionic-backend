package com.marcos.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.marcos.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	

}
