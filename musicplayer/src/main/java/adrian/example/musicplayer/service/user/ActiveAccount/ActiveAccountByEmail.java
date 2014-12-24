package adrian.example.musicplayer.service.user.ActiveAccount;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class ActiveAccountByEmail implements ActiveAccount {

	private MailSender mailSender;
	
	private SimpleMailMessage simpleMailMessage;
	
	@Override
	public void activeAccount(int user_id,int active_code, String email) {
		
		SimpleMailMessage msg = new SimpleMailMessage(this.simpleMailMessage);
		msg.setTo(email);
		msg.setText("http://localhost:8080/musicplayer/verify?user_id=" + user_id + 
				    "&active_code=" + active_code);
		
		try{
			this.mailSender.send(msg);
		}catch(MailException e){
			e.printStackTrace();
		}
	}
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

}
