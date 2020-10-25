package mypokemons;
import ru.ifmo.se.pokemon.*;
import attacks.*;

public class Flabebe extends Pokemon {

	PetalBlizzard pb = new PetalBlizzard();
	DoubleTeam dt = new DoubleTeam();

	public Flabebe(String name, int level) {
		
		super(name, level);
		super.setStats(44, 38, 39, 61, 79, 42);
		super.setType(Type.FAIRY);
		super.setMove(pb, dt);


	}

}