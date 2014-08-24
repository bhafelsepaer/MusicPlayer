package adrian.example.musicplayer.service;

import java.util.ArrayList;
import java.util.List;

public class UserInformationServiceImpl implements UserInformationService{

	@Override
	public List<String> getInterest() {
		List<String> interest = new ArrayList<String>();
		interest.add("Football");
		interest.add("Video Game");
		interest.add("Travel");
		interest.add("Tennis");
		interest.add("Fight");
		return interest;
	}

	@Override
	public List<String> getProgrammingStyle() {
		List<String> programmingStyle = new ArrayList<String>();
		programmingStyle.add("JAVA");
		programmingStyle.add("JAVAEE");
		programmingStyle.add("Spring");
		programmingStyle.add("Struts");
		programmingStyle.add("Hibernate");
		programmingStyle.add("JSP");
		
		return programmingStyle;
	}

}
