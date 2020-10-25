package mypokemons;
import ru.ifmo.se.pokemon.*;
import attacks.*;

public class Togedemaru extends Pokemon {

	FlashCannon fs = new FlashCannon();
	RockTomb rt = new RockTomb();
	Ember em = new Ember();
	ShellSmash ss = new ShellSmash();

	public Togedemaru(String name, int level) {
		
		super(name, level);
		super.setStats(65, 98, 63, 40, 73, 96);
		super.setType(Type.STEEL, Type.ELECTRIC);
		super.setMove(fs, rt, em, ss);


	}

}