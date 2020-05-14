package fr.uvsq.pglp_9_9.Command;

import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.Forme;
import fr.uvsq.pglp_9_9.Dessin.Rectangle;
import fr.uvsq.pglp_9_9.Dessin.Triangle;

public class CreationCommand implements Command{
	private Forme forme;

	
	public CreationCommand(Forme forme) {
		super();
		this.forme = forme;
	}


	@Override
	public void execute() {
	
		
	}

}
