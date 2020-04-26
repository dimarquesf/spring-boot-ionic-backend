package br.com.diego.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.diego.cursomc.domain.Pedido;

public interface  EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
}
