package application;




import java.net.URL;

import java.util.ResourceBundle;


import com.ensim.jeuGo.Partie;
import com.ensim.jeuGo.Plateau;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Menu;


public class Goban implements Initializable{

	Partie p=new Partie();
	
	
	
	@FXML
	public Menu MenuFile;
	
	@FXML
	public MenuItem MenuNew;
	
	@FXML
	public MenuItem MenuOpen;
	
	@FXML
	public  MenuItem MenuSave;
	
	@FXML
	public  MenuItem MenuQuit;
	
	@FXML
	public MenuItem MenuInformation;
	
	@FXML
	public  MenuItem MenuInfo;
	
	@FXML
	public  Label Info;
	
	@FXML
	private Button BtnPasser;
	
	@FXML
	private Button BtnJouer;
	
	@FXML
	private Button BtnSup;
	
	@FXML
	private Button BtnPlacer;
	
	@FXML
	private Button BtnCalcScore;
	  
	@FXML
	private TextField TxtAbs;
	
	@FXML
	private TextField TxtOrd;
	
	@FXML
	private ImageView ImgGoban;
	
	
	
	@FXML
	private Circle CercleJoueur;
	
	
	@FXML
	private AnchorPane AnchorPaneImg;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	
	public void NewGame (ActionEvent event) {
		//TODO lancer nouvelle partie
	}
	
	public void OpenGame (ActionEvent event) {
		p.ChargerPartie();
		Info.setText("INFO : Partie Chargée.");
		afficherPlateau(p.plateau);
		
	}
	public void SaveGame (ActionEvent event) {
		p.SauvgarderPartie();
		Info.setText("INFO : Partie Sauvgardée.");
		
		
	}
	public void QuitGame (ActionEvent event) {

	}
	public void AfficheInfo (ActionEvent event) {

	}
	
	public void passerTour(ActionEvent event) {
		p.passerTour();
		if(CercleJoueur.getFill()==Color.BLACK) {
			CercleJoueur.setFill(Color.WHITE);
		}
		else {
			CercleJoueur.setFill(Color.BLACK);
		}
		if(p.getjCourant().isaPasse()&&p.getjAttendant().isaPasse()) {

			BtnPasser.setDisable(true);
			BtnJouer.setDisable(true);
			BtnSup.setVisible(true);
			BtnSup.setDisable(false);
			BtnCalcScore.setVisible(true);
			BtnCalcScore.setDisable(false);
				
			Info.setText("INFO : Choisissez les pions morts\nà enlever.");
		}
		afficherPlateau(p.plateau);
	}
	
	public void JouerTour (ActionEvent event) {
		
		System.out.println("ord "+TxtAbs.getText()+" abs "+TxtOrd.getText());
		int rep=p.JouerTour(TxtOrd.getText(),TxtAbs.getText());
		switch (rep) {
		case 1:
			Info.setText("INFO : Vous avez jouer en \ndehors du plateau");			
			break;		
		case 2:
			Info.setText("INFO : Vous avez jouer \ndans une case déjà occupée.");		
			break;
		case 3:
			Info.setText("INFO : Vous ne pouvez pas jouer\nici c'est un coup suicidaire.");	
			break;
			
		case 4:
			afficherPlateau(p.plateau);
			if(CercleJoueur.getFill()==Color.BLACK) {
				CercleJoueur.setFill(Color.WHITE);
			}
			else {
				CercleJoueur.setFill(Color.BLACK);
			}
			break;
		default:
			break;
		}
		
	}
	
	public void SupprPion (ActionEvent event) {
		int rep=p.plateau.SupprimerPion(TxtOrd.getText(),TxtAbs.getText());
		switch (rep) {
		case 1:
			Info.setText("INFO : Vous avez entré des coordonée\nen dehors du plateau");			
			break;		
		case 2:
			Info.setText("INFO : Vous avez bien supprimé le pion\nen ("+TxtAbs.getText()+", "+TxtOrd.getText()+")");	
			afficherPlateau(p.plateau);
			break;
		default:
			break;
		}
	}
		
	
	public void afficherPlateau(Plateau pl) {
		AnchorPaneImg.getChildren().clear();
		AnchorPaneImg.getChildren().add(ImgGoban);				
		for(int i=1;i<=19;i++) {
			for(int j=1;j<=19;j++) {
				if(pl.contenuPlateau.get(i).get(j).getContenu().equals("blanc")){
					Circle cercle=new Circle(21*i-10,21*j-10,8,Color.WHITE);
					AnchorPaneImg.getChildren().add(cercle);
				}
				else if(pl.contenuPlateau.get(i).get(j).getContenu().equals("noir")){
					Circle cercle=new Circle(21*i-10,21*j-10,8,Color.BLACK);
					AnchorPaneImg.getChildren().add(cercle);
				}
			}
		}
	}
	
	public void CalculerScore() {
		p.getJ1().setScore(p.CompterPoint(p.getJ1()));
		p.getJ2().setScore(p.CompterPoint(p.getJ2()));
			
		if(p.getJ1().getScore()<p.getJ2().getScore()) {
			Info.setText("SCORE : \tBlanc : "+p.getJ1().getScore()+"\n\t\tNoir : "+p.getJ2().getScore()+"\nLe vainqueur est noir");
			CercleJoueur.setFill(Color.BLACK);
		}
		else {
			Info.setText("SCORE : \tBlanc : "+p.getJ1().getScore()+"\n\t\tNoir : "+p.getJ2().getScore()+"\nLe vainqueur est blanc");
			CercleJoueur.setFill(Color.WHITE);
		}
	}
	public void PlacerPion() {
		//TODO placer pion
	}
	
	
	
}