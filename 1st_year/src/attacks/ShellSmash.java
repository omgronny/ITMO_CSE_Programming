package attacks;
import ru.ifmo.se.pokemon.*;

public class ShellSmash extends StatusMove {

	public ShellSmash(){
		super(Type.NORMAL, 0, 1);
	}

	@Override
	public boolean checkAccuracy(Pokemon att, Pokemon def){ return true;}

	@Override
	public void applySelfEffects(Pokemon self){
		self.setMod(Stat.DEFENSE, -1);
		self.setMod(Stat.SPECIAL_DEFENSE, -1);
		self.setMod(Stat.ATTACK, +2);//aaa
		self.setMod(Stat.SPECIAL_ATTACK , +2);

	}


	@Override
	public String describe(){
		return "Applies Shell Smash";
	}

}
