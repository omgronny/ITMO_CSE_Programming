package attacks;
import ru.ifmo.se.pokemon.*;

public class CalmMind extends StatusMove {

	@Override
	public boolean checkAccuracy(Pokemon att, Pokemon def){ return true;}

	public CalmMind(){
		super(Type.PSYCHIC, 0, 1);
	}

	@Override
	public void applySelfEffects(Pokemon self){

		self.setMod(Stat.SPECIAL_DEFENSE, +1);

		self.setMod(Stat.SPECIAL_ATTACK, +1);

	}


	@Override
	public String describe(){
		return "Applies Calm Mind";
	}

}
