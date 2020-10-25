package attacks;
import ru.ifmo.se.pokemon.*;

public class FairyWind extends SpecialMove {

	public FairyWind(){
		super(Type.FAIRY, 40, 1);
	}

	@Override
	public String describe(){
		return "Applies Fairy Wind";
	}

}
