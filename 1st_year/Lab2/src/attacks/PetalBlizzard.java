package attacks;
import ru.ifmo.se.pokemon.*;

public class PetalBlizzard extends PhysicalMove {

	public PetalBlizzard(){
		super(Type.GRASS, 90, 1);
	}


	@Override
	public String describe(){
		return "Applies Petal Blizzard";
	}

}
