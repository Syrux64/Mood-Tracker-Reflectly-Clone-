package mt.moodtracker;


import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Mood_checkin implements Initializable{
	
	@FXML
	Slider mySlider;
	@FXML
	ImageView myImageView;	
	@FXML
	Label myLabel ;
	@FXML
	Button button_continue1;
	@FXML
	Label prompt_mood;
	@FXML
	BorderPane borderPane;
	
	private Stage stage;
	private Parent root;
	

	static protected int current_mood_value = 50;
	
	// SHARING COMPANONENTS B/W CLASSES!! -->
	public static String time_period;
	LocalTime current_time = LocalTime.now();
	int time_period_int = current_time.getHour();
	public static String moodString;
	Font customFont0 = Font.loadFont(getClass().getResourceAsStream("fonts/Quicksand-SemiBold.ttf"), 19);
	Font customFont1 = Font.loadFont(getClass().getResourceAsStream("fonts/Quicksand-SemiBold.ttf"), 27);
	Font customFont2 = Font.loadFont(getClass().getResourceAsStream("fonts/Quicksand-SemiBold.ttf"), 22);
	

	public void switchToReason(ActionEvent event) throws IOException {

		root = FXMLLoader.load(Mood_checkin.class.getResource("FXMLs/cause.fxml"));
		Scene scene = button_continue1.getScene();
		scene.getStylesheets().add(Mood_checkin.class.getResource("CSSs/cause_style.css").toExternalForm());

		root.translateXProperty().set(scene.getHeight());
		
		StackPane stackParent = (StackPane)scene.getRoot();
		stackParent.getChildren().add(root);
		
		Timeline transitionTimeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
		transitionTimeline.getKeyFrames().add(kf);
		transitionTimeline.setOnFinished(evet->{
			stackParent.getChildren().remove(borderPane);
		});
		transitionTimeline.play();
		
	}
			
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Mood_checkin.moodString = "COMPLETELY OKAY";

		myLabel.setFont(customFont2);
		mySlider.valueProperty().addListener(new ChangeListener<Number>() {
		Image myImage_5 = new Image(Mood_checkin.class.getResourceAsStream("Faces/super_awesome.png"));
		Image myImage_4 = new Image(Mood_checkin.class.getResourceAsStream("Faces/pretty_good.png"));
		Image myImage_3 = new Image(Mood_checkin.class.getResourceAsStream("Faces/completely_okay.png"));
		Image myImage_2 = new Image(Mood_checkin.class.getResourceAsStream("Faces/somewhat_bad.png"));
		Image myImage_1 = new Image(Mood_checkin.class.getResourceAsStream("Faces/really_terrible.png"));

		
		
		@Override
		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
			current_mood_value = (int) mySlider.getValue();
			mySlider.setFocusTraversable(true);
			
			if (0 < current_mood_value && current_mood_value < 20) {
				moodString = "REALLY TERRIBLE";
				myLabel.setText(moodString);
				myImageView.setImage(myImage_1);
				myLabel.setFont(customFont2);

			}
			
			if (20 < current_mood_value && current_mood_value < 40) {
				myImageView.setImage(myImage_2);
				moodString = "SOMEWHAT BAD";
				myLabel.setText(moodString);
				myLabel.setFont(customFont2);

			}
			
			if (40 < current_mood_value && current_mood_value < 60) {
				myImageView.setImage(myImage_3);
				moodString = "COMPLETELY OKAY";
				myLabel.setText(moodString);
				myLabel.setFont(customFont2);

			}
			
			if (60 < current_mood_value && current_mood_value < 80) {
				myImageView.setImage(myImage_4);
				moodString = "PRETTY GOOD";
				myLabel.setText(moodString);
				myLabel.setFont(customFont2);

			}
			if (80 < current_mood_value && current_mood_value <= 100) {
				myImageView.setImage(myImage_5);
				moodString = "SUPER AWESOME";
				myLabel.setText(moodString);
				myLabel.setFont(customFont2);
			}
			
			}
		});
		
		// getting the current time and prompt 
		if(time_period_int > 21 || time_period_int <= 4) {
			time_period = "night";
		}
		
		if(time_period_int > 4 && time_period_int <= 12) {
			time_period = "morning";
		}
		
		if(time_period_int > 12 && time_period_int <= 17) {
			time_period = "afternoon";
		}
		
		if(time_period_int > 17 && time_period_int <= 21) {
			time_period = "evening";
		}
		
		prompt_mood.setText("Hey, "+ FileHandling.readLine(2, "\\data.txt") +". How are you this fine " + time_period + "?");
		prompt_mood.setFont(customFont1);
	}
	

	
}
