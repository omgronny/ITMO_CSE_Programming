package attacks;
import ru.ifmo.se.pokemon.*;

public class Ember extends SpecialMove {

	public Ember() {

		super(Type.FIRE, 40, 1);

	}

	@Override
	public void applyOppEffects (Pokemon enemy){

		Effect ef = new Effect().chance(0.1);
		ef.burn(enemy);

	}



	@Override
	public String describe(){
		return("Applies Ember");
	}

}
