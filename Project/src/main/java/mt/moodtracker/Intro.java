package mt.moodtracker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Intro implements Initializable{
	
	@FXML
	Label labelMessage, labelWelcome;
	@FXML
	Button buttonStart;
	@FXML
	BorderPane borderPane;
	@FXML
	StackPane stackParent;
	
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {	
		Font customFont0 = Font.loadFont(getClass().getResourceAsStream("fonts/Quicksand-SemiBold.ttf"), 18);
		Font customFont0BL = Font.loadFont(getClass().getResourceAsStream("fonts/Quicksand-Bold.ttf"), 56);
		
		labelWelcome.setFont(customFont0BL);
		labelMessage.setFont(customFont0);
	}
	
	public void start(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(Intro.class.getResource("FXMLs/getName.fxml"));
		Scene scene = buttonStart.getScene();
		scene.getStylesheets().add(Intro.class.getResource("CSSs/intro.css").toExternalForm());
		
		root.translateYProperty().set(scene.getHeight());
		stackParent.getChildren().add(root);
		
		Timeline transitionTimeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
		transitionTimeline.getKeyFrames().add(kf);
		transitionTimeline.setOnFinished(event->{
			stackParent.getChildren().remove(borderPane);
		});
		transitionTimeline.play();	
	}
	
}
