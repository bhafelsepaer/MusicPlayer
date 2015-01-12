package adrian.example.musicplayer.dao.music;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import adrian.example.musicplayer.model.Music.RateSong;
import adrian.example.musicplayer.model.Music.Song;
import adrian.example.musicplayer.model.User.User;

@Repository
public class RateSongDaoImpl implements RateSongDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveRatingSong(int user_id, int song_id, int ratingSong) {
		Session session = 
				this.sessionFactory.getCurrentSession();
		
		User user = (User) session.load(User.class, user_id);
		Song song = (Song) session.load(Song.class, song_id);
		
		RateSong rateSong =  (RateSong) session.createQuery(
				"FROM RateSong rateSong WHERE rateSong.user.user_id = :user_id"
				+ " AND rateSong.song.song_id = :song_id")
				.setParameter("user_id", user_id)
				.setParameter("song_id", song_id).uniqueResult();
		
		if(rateSong == null){
			rateSong = new RateSong();
		}
		
		rateSong.setRating(ratingSong);
		rateSong.setSong(song);
		rateSong.setUser(user);
		
		session.merge(rateSong);
	}

	@Override
	public RateSong getRateSongById(int rating_id) {
		Session session = 
				this.sessionFactory.getCurrentSession();
		
		RateSong rateSong = (RateSong) session.get(RateSong.class, rating_id);
		return rateSong;
	}

	@Override
	public double getRateSongByUser(int user_id, int song_id) {
		Session session =
		        this.sessionFactory.getCurrentSession();
		
		double ratingSong = (Double) session.createQuery("SELECT rating FROM "
				+ "RateSong rateSong WHERE rateSong.song.song_id = :song_id AND "
				+ "rateSong.user.user_id = :user_id")
				.setParameter("song_id", song_id)
				.setParameter("user_id", user_id)
				.uniqueResult();
		
		return ratingSong;
	}

	@Override
	public List<Double> getRateingSongBelongToSong(int song_id) {
		Session session = 
				this.sessionFactory.getCurrentSession();
		
		List<Double> allRatingSong = (List<Double>)
				session.createQuery("SELECT rating FROM RateSong ratingSong "
						+ "WHERE ratingSong.song.song_id = :song_id")
						.setParameter("song_id", song_id).list();
	     
		System.out.println(allRatingSong.toString());
		return  allRatingSong;
	}

}
