package application;




import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.ensim.jeuGo.Intersection;
import com.ensim.jeuGo.Partie;
import com.ensim.jeuGo.Plateau;
import com.sun.media.jfxmedia.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.PopupWindow.AnchorLocation;
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
	public Menu MenuInformation;
	
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
		Partie p=new Partie();
	}
	
	public void OpenGame (ActionEvent event) {

	}
	public void SaveGame (ActionEvent event) {
		p.SauvgarderPartie();
		Info.setText("INFO : Partie Sauvgardée.");
		
	}
	public void QuitGame (ActionEvent event) {
		p.ChargerPartie();
		Info.setText("INFO : Partie Chargée.");
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
			BtnSup.setDisable(false);
			BtnSup.setVisible(true);	
			Info.setText("INFO : Choisissez les pions morts\nà enlever.");
		}
		afficherPlateau(p.plateau);
	}
	
	public void JouerTour (ActionEvent event) {
		
		System.out.println("ord "+TxtAbs.getText()+" abs "+TxtOrd.getText());
		int rep=p.JouerTour(TxtAbs.getText(),TxtOrd.getText());
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
}