package attacks;
import ru.ifmo.se.pokemon.*;

public class Flamethrower extends SpecialMove {

	public Flamethrower(){
		super(Type.FIRE, 90, 1);
	}

	@Override
	public void applyOppEffects (Pokemon enemy){

		Effect ef = new Effect().chance(0.1);
		ef.burn(enemy);

	}

	@Override
	public String describe(){
		return "Applies Flamethrower";
	}

}
