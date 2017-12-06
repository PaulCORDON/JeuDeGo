package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.ensim.jeuGo.Partie;
import com.ensim.jeuGo.Plateau;

public class PlateauTest {

 public void testPlateau(){
		 
	 	Partie partie = new Partie();
		 Plateau p = new Plateau(partie);
		 
		 
			
		 assertEquals("test intersection 1", "vide", p.contenuPlateau.get(1).get(1));
		 
		
	 }

}
