package mt.moodtracker;


import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Reason implements Initializable{
	
	@FXML
	TextField mood_title;
	@FXML
	TextField mood_notes;
	@FXML
	Button submit_button;
	@FXML
	Label notes_date;
	@FXML
	AnchorPane anchorPane;
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	
	static protected String msg;
	
	
	public void submit(ActionEvent event) throws IOException {
		//System.out.println(msg);
		
		root = FXMLLoader.load(Reason.class.getResource("FXMLs/Main.fxml"));
//		stage_2 = (Stage)((Node)event.getSource()).getScene().getWindow();
//		scene_2 = new Scene(root_2);
//		stage_2.setScene(scene_2);
		Scene scene_2 = submit_button.getScene();
		scene_2.getStylesheets().add((Reason.class.getResource("CSSs/main_style.css").toExternalForm()));
		
		root.translateXProperty().set(scene_2.getHeight());
		StackPane stackParent = (StackPane)scene_2.getRoot();
		stackParent.getChildren().add(root);
		
		Timeline transitionTimeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
		transitionTimeline.getKeyFrames().add(kf);
		transitionTimeline.setOnFinished(evet->{
			stackParent.getChildren().remove(anchorPane);
		});
		transitionTimeline.play();

		Main.moodEntry_moodValue.add(Mood_checkin.current_mood_value);
		Main.moodName.add(Mood_checkin.moodString);
		Main.noMoodEntriesArray.add(Mood_checkin.current_mood_value);
		Main.moodEntry_day.add(Main_window.dayName);
		Main.moodEntry_date.add(Main_window.current_mood_date);
		Main.moodEntry_month.add(Main_window.monthName);
		Main.moodEntry_moodTitle.add(mood_title.getText());
		Main.moodEntry_moodDescription.add(mood_notes.getText());
		Main.moodEntry_time.add(Main_window.formattedTime);

		// save data -->
		FileHandling.saveIntData(Main.moodEntry_moodValue,"moodEntry_moodValue.txt");
		FileHandling.saveStringData(Main.moodName, "moodName.txt");
		FileHandling.saveIntData(Main.noMoodEntriesArray, "noMoodEntriesArray.txt");
		FileHandling.saveStringData(Main.moodEntry_day, "moodEntry_day.txt");
		FileHandling.saveStringData(Main.moodEntry_date, "moodEntry_date.txt");
		FileHandling.saveStringData(Main.moodEntry_month, "moodEntry_month.txt");
		FileHandling.saveStringData(Main.moodEntry_moodTitle, "moodEntry_moodTitle.txt");
		FileHandling.saveStringData(Main.moodEntry_moodDescription, "moodEntry_moodDescription.txt");
		FileHandling.saveStringData(Main.moodEntry_time, "moodEntry_time.txt");
		FileHandling.replaceLine(3, Integer.toString(Main.total_entries), "data.txt");
	}

	public void reasoning(ActionEvent event) throws IOException {
		root = FXMLLoader.load(Reason.class.getResource("FXMLs/reason.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		scene.getStylesheets().add(Reason.class.getResource("CSSs/application.css").toExternalForm());
		stage.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM dd", Locale.ENGLISH);
		DateTimeFormatter timeNow = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
		String formatted = ldt.format(format);

		notes_date.setText(Main_window.formattedTime + ", " + formatted);
		mood_title.setFocusTraversable(false);
		mood_notes.setFocusTraversable(false);
	}
	
}
