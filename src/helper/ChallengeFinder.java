package helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import com.appspot.patota_hackathon.api.model.Challenge;
import com.appspot.patota_hackathon.api.model.Group;
import com.fglp.futeba.ChallengeGroup;
import com.fglp.futeba.PatotaAPIFactory;

public class ChallengeFinder {

	public static LinkedList<ChallengeGroup> find(String groupId, double lat,
			double lon, int km) {
		Point thisP = new Point(lat, lon);
		LinkedList<ChallengeGroup> ret = new LinkedList<>();

		Collection<Challenge> all;
		try {
			all = (Collection<Challenge>) PatotaAPIFactory.getService()
					.allChallenges();
		} catch (IOException e) {
			throw new IllegalStateException(
					"Erro executando serviço pra carregar os challenges", e);
		}

		for (Challenge challenge : all) {
			if (challenge.getGroupId() != groupId) {
				double d = getDistante(thisP, new Point(challenge.getLat(),
						challenge.getLng()));
				double dKm = d / 1000;// transforma de metros pra km
				if (dKm < km) {
					try {
						long lgroupid = Long.parseLong(challenge.getGroupId());
						Group group = PatotaAPIFactory.getService()
								.getGroup(lgroupid).execute();
						ret.add(new ChallengeGroup(challenge.getId()
								.longValue(), group.getName(), lgroupid, dKm));
					} catch (IOException e) {
						throw new IllegalStateException(
								"Erro executando serviço pra carregar o grupo: "
										+ challenge.getGroupId(), e);
					}
				}
			}
		}

		return ret;
	}

	private static double getDistante(Point p1, Point p2) {
		long R = 6378137; // Earth’s mean radius in meter
		double dLat = rad(p2.latitude - p1.latitude);
		double dLong = rad(p2.longitute - p1.longitute);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(rad(p1.latitude)) * Math.cos(rad(p2.latitude))
				* Math.sin(dLong / 2) * Math.sin(dLong / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;
		return d;
	}

	private static double rad(double x) {
		return x * Math.PI / 180;
	}

	private static Collection<Challenge> getAll() {
		ArrayList<Challenge> ret = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Point gen = generateLatLon(new Point(-27.432682, -48.3998844), 10);
			Challenge c = new Challenge();
			c.setLat((float) gen.latitude);
			c.setLng((float) gen.longitute);

			ret.add(c);
		}

		for (int i = 0; i < 5; i++) {
			Point gen = generateLatLon(new Point(-27.432682, -48.3998844), 15);
			Challenge c = new Challenge();
			c.setLat((float) gen.latitude);
			c.setLng((float) gen.longitute);

			ret.add(c);
		}
		return ret;
	}

	private static Point generateLatLon(Point p, int maxMeters) {
		double l = p.longitute;// -48.392460;
		System.out.println(l);
		System.out.println(l * 100);

		int difKm = new Random().nextInt(maxMeters);
		double nd = l * 100 - difKm;
		System.out.println(nd);
		l = nd / 100;
		return new Point(p.latitude, l);
	}
}
