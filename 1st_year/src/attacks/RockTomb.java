package attacks;
import ru.ifmo.se.pokemon.*;

public class RockTomb extends PhysicalMove {

	public RockTomb(){
		super(Type.ROCK, 60, 0.95);
	}

	@Override
	public void applyOppEffects(Pokemon enemy){ enemy.setMod(Stat.SPEED, -1); }


	@Override
	public String describe(){
		return "Applies Rock Tomb";
	}

}