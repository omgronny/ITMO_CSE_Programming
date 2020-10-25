import ru.ifmo.se.pokemon.*;
import attacks.*;
import mypokemons.*;

public class Main {
	public static void main(String[] args) {

			Battle b = new Battle();

			Vulpix p1 = new Vulpix("Vulpix", 1);
			Togedemaru t = new Togedemaru("Togedemaru", 2);
			Ninetales nt = new Ninetales("Ninetales", 2);

			b.addAlly(p1);
			b.addAlly(t);
			b.addAlly(nt);


			Flabebe fb = new Flabebe("Flabebe", 1);
			Floette ft = new Floette("Floette", 2);
			Florges fg = new Florges("Florges", 2);


			b.addFoe(fb);
			b.addFoe(ft);
			b.addFoe(fg);

			b.go();

			//Test b = new Test();
			//b.abc();

			

	}
}




