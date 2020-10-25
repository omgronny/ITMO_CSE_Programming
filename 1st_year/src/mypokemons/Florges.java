package mypokemons;
import ru.ifmo.se.pokemon.*;
import attacks.*;

public class Florges extends Pokemon {

	PetalBlizzard pb = new PetalBlizzard();
	DoubleTeam dt = new DoubleTeam();
	FairyWind fw = new FairyWind();
	PetalBlizzard pb1 = new PetalBlizzard();

	public Florges(String name, int level) {
		
		super(name, level);
		super.setStats(78, 65, 68, 112, 154, 75);
		super.setType(Type.FAIRY);
		super.setMove(pb, dt, fw, pb1);


	}

}