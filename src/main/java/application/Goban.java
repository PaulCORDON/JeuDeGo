package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.ensim.jeuGo.Partie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Goban implements Initializable{

	Partie p=new Partie();
	@FXML
	private Button BtnPasser;
	
	@FXML
	private Button BtnJouer;
	  
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
	
	public void passerTour(ActionEvent event) {
		p.passerTour();
		if(CercleJoueur.getFill()==Color.BLACK) {
			CercleJoueur.setFill(Color.WHITE);
		}
		else {
			CercleJoueur.setFill(Color.BLACK);
		}
		
	}
	public void JouerTour (ActionEvent event) {
		if((!TxtAbs.getPromptText().equals(""))&&(!TxtOrd.getPromptText().equals(""))) {
			p.JouerTour(TxtAbs.getPromptText(),TxtOrd.getPromptText());
		}
		
	}
}
