package attacks;
import ru.ifmo.se.pokemon.*;

public class FlashCannon extends SpecialMove {

	public FlashCannon(){
		super(Type.STEEL, 80, 1);
	}

	@Override
	public void applyOppEffects(Pokemon enemy){
		Effect ef = new Effect().chance(0.1).stat(Stat.SPECIAL_DEFENSE, -1);
		enemy.addEffect(ef);

	}

	@Override
	public String describe(){
		return "Applies Flash Cannon";
	}

}
