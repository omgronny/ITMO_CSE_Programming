package attacks;
import ru.ifmo.se.pokemon.*;

public class Confide extends StatusMove {

	@Override
	public boolean checkAccuracy(Pokemon att, Pokemon def){ return true;}

	public Confide(){
		super(Type.NORMAL, 0, 1);
	}

	@Override
	public void applyOppEffects(Pokemon enemy){
		Effect ef = new Effect().chance(0.1).stat(Stat.SPECIAL_ATTACK, -1);
		enemy.addEffect(ef);

	}

	@Override
	public String describe(){
		return "Applies Confide";
	}

}
