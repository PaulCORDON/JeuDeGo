package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ensim.jeuGo.Plateau;

public class PlateauTest {

 public void testPlateau() {
		 
		 Plateau p = new Plateau();
		 assertEquals("test intersection 1", "vide", p.contenuPlateau[0][0]);
		 
		
	 }

}
