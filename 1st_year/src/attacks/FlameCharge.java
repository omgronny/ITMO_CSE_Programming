package attacks;
import ru.ifmo.se.pokemon.*;

public class FlameCharge extends PhysicalMove {

	public FlameCharge(){
		super(Type.FIRE, 50, 1);
	}

	@Override
	public void applySelfEffects(Pokemon self){
		self.setMod(Stat.SPEED, +1);

	}

	@Override
	public String describe(){
		return "Applies Flame Charge";
	}

}