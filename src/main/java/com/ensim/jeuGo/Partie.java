package com.ensim.jeuGo;

public class Partie {
	
	Joueur j1;
	Joueur j2;
	Joueur jCourant;
	Plateau plateau;
	Boolean isFinish;
	int tour;
	
	
	Partie(){	//initialisation de la partie
		j1 = new Joueur("blanc");
		j2 = new Joueur("noir");
		jCourant= j2;
		plateau = new Plateau();
		isFinish=false;
		tour=0;
	}
	
	public void JouerPartie() {
		while(!isFinish) {
			/*JouerTour(jCourant, ligne, colonne );*/
			tour++;
			
			if(tour%2==0) {
				jCourant=j2;
			}
			else jCourant=j1;
			
			
			
			if(!PasserTour(jCourant)) { //on verifie si le joueur courant passe son tour. Si non, on joue
				
				
				
				
			}
			else { //si le joueur courant passe son tour on verifie que les deux joueurs n'ont pas passé leur tour consécutivement. Si cest le cas la partie se termine
				if(j1.passe==true && j2.passe==true) isFinish=true;
			}
		}
		
		
		
		
		
		j1.score=CompterPoint(j1);
		j2.score=CompterPoint(j2);
	}
	
	
	
	
	/*public void JouerTour(Joueur jc, int ligne, int colonne){
		
		jc=jCourant;
		jCourant.passe=false;
		
		if(plateau.VerifCoupValide(ligne, colonne)) {
			
			plateau.contenuPlateau[ligne][colonne].contenu=jc.couleur;
		}
		
	}*/
	
	
	
	public Boolean PasserTour(Joueur j) {
		Boolean passer= false;
		
		//il y aura un bouton "passer" dans l'interface et si on clique dessus passer = true
		j.passe=true;
		return passer;
	}
	
	
	
	public int CompterPoint(Joueur j) {
		int score=0;
		
		/*regle trouvee sur internet:
		 * 
		 * La partie s'arrête lorsque les deux joueurs passent consécutivement. On compte alors les points. 
		 * Chaque intersection du territoire d'un joueur lui rapporte un point, ainsi que chacune de ses pierres encore présentes sur le goban.
		 * 
		 */
		
		
		return score;
	}
}
