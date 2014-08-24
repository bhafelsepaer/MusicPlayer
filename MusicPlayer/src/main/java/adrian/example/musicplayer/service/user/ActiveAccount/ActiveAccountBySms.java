package adrian.example.musicplayer.service.user.ActiveAccount;

import org.springframework.stereotype.Component;

@Component("smsActivation")
public class ActiveAccountBySms implements ActiveAccount {

	@Override
	public void activeAccount(int user_id, int active_code, String email) {
		System.out.println("SMS CONCEPT COMING SOON");
	}
}
