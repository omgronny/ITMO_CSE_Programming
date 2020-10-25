package mypokemons;
import ru.ifmo.se.pokemon.*;
import attacks.*;

public class Floette extends Pokemon {

	PetalBlizzard pb = new PetalBlizzard();
	DoubleTeam dt = new DoubleTeam();
	FairyWind fw = new FairyWind();

	public Floette(String name, int level) {
		
		super(name, level);
		super.setStats(54, 45, 47, 75, 98, 52);
		super.setType(Type.FAIRY);
		super.setMove(pb, dt, fw);


	}

}