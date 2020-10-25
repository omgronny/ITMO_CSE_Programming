package attacks;
import ru.ifmo.se.pokemon.*;

public class DoubleTeam extends StatusMove {

	@Override
	public boolean checkAccuracy(Pokemon att, Pokemon def){ return true;}

	public DoubleTeam(){
		super(Type.NORMAL, 0, 1);
	}

	@Override
	public void applySelfEffects(Pokemon self){
		self.setMod(Stat.EVASION, +1);

	}


	@Override
	public String describe(){
		return "Applies Double Team";
	}

}
