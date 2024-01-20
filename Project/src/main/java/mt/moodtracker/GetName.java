package mt.moodtracker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class GetName implements Initializable{

	
	@FXML
	Label labelPrompt;
	@FXML
	Button buttonSubmit;
	@FXML
	TextField inputPrompt;
	@FXML
	BorderPane borderPane;
	@FXML
	StackPane stackParent;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Font customFont0BL = Font.loadFont(GetName.class.getResourceAsStream("fonts/Quicksand-Bold.ttf"), 36);
		Font customFont0 = Font.loadFont(GetName.class.getResourceAsStream("fonts/Quicksand-SemiBold.ttf"), 18);
		labelPrompt.setFont(customFont0BL);
		inputPrompt.setFont(customFont0);
	}
	
	public void start() throws IOException {
		Main.userNameString = inputPrompt.getText();
		FileHandling.replaceLine(2, Main.userNameString, "data.txt");
		
		Parent root = FXMLLoader.load(GetName.class.getResource("FXMLs/Main.fxml"));
		Scene scene = buttonSubmit.getScene();
		scene.getStylesheets().add(GetName.class.getResource("CSSs/main_style.css").toExternalForm());
		
		root.translateYProperty().set(scene.getHeight());
		stackParent.getChildren().add(root);
		
		Timeline transitionTimeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
		transitionTimeline.getKeyFrames().add(kf);
		transitionTimeline.setOnFinished(evet->{
			stackParent.getChildren().remove(borderPane);
		});
		transitionTimeline.play();	
	}

}
