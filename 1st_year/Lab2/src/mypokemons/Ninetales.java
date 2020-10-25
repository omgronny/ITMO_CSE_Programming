package mypokemons;
import ru.ifmo.se.pokemon.*;
import attacks.*;

public class Ninetales extends Pokemon {

	FlashCannon fs = new FlashCannon();
	RockTomb rt = new RockTomb();
	Ember em = new Ember();

	public Ninetales(String name, int level) {
		
		super(name, level);
		super.setStats(73, 76, 75, 81, 100, 100);
		super.setType(Type.FIRE);
		super.setMove(fs, rt, em);


	}

}