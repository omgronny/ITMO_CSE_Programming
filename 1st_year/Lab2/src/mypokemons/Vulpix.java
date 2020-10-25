package mypokemons;
import ru.ifmo.se.pokemon.*;
import attacks.*;

public class Vulpix extends Pokemon {

	Flamethrower ff = new Flamethrower();
	Confide cf = new Confide();
	FlameCharge fc = new FlameCharge();

	public Vulpix(String name, int level){

		super(name, level);
		super.setStats(38, 41, 40, 50, 65, 65);
		super.setType(Type.FIRE, Type.ICE);
		super.setMove(ff, cf, fc);
		
	}

	
}