package br.com.otimo.reviv.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.caelum.vraptor.Resource;

@Resource
public class EmailController {

	String email = "clinicareviv@outlook.com.br";
	String senha = "@R&v!V10";
	String remetente = "contato@sistemareviv.com";

	public void enviarEmail(String destinatario, String assunto, String mensagem) {

		Properties props = dadosConfiguracao();
		Session session = dadosSessao(props);

		try {
			Message message = dadosMensagem(destinatario, assunto, mensagem, session);
			Transport.send(message);
			System.out.println("Feito!!!");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	private Message dadosMensagem(String destinatario, String assunto, String mensagem, Session session)
			throws MessagingException, AddressException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(remetente));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
		message.setSubject(assunto);
		message.setContent(mensagem, "text/html; charset=utf-8");
		return message;
	}

	private Session dadosSessao(Properties props) {
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, senha);
			}
		});
		return session;
	}

	private Properties dadosConfiguracao() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.live.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		return props;
	}

	public static void main(String[] args) {
		String destinatario = "wmacibnc@gmail.com";
		String assunto = "teste de assunto";
		String mensagem = "texto sem formatação <b>Bold</b> <p>Paragrafo</p>";
		new EmailController().enviarEmail(destinatario, assunto, mensagem);
	}

}
