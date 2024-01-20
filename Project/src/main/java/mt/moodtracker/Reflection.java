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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Reflection implements Initializable{

	@FXML
	Button button_submit, button_back;
	@FXML
	BorderPane borderPane;
	@FXML
	Label label_title;
	FlowPane entryFlowPane = new FlowPane();
	String sentence = FileHandling.readLine(Main.total_reflections + 1, "\\reflections.txt");
	String[] words = sentence.split("\\s+");
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Font customFont0B = Font.loadFont(Reflection.class.getResourceAsStream("fonts/Quicksand-Bold.ttf"), 25);
		Font customFontCur = Font.loadFont(Reflection.class.getResourceAsStream("fonts/GreatVibes-Regular.ttf"), 60);
		Font customFontCurS = Font.loadFont(Reflection.class.getResourceAsStream("fonts/GreatVibes-Regular.ttf"), 50);

	    entryFlowPane.setMaxHeight(250);
	    entryFlowPane.setMaxWidth(650);

	    if(!Main.reflected) {
			label_title.setVisible(true);
			label_title.setFont(customFontCurS);
			button_back.setVisible(false);
			button_submit.setVisible(true);

			TextField textField = new TextField();
			textField.setPrefWidth(200);
			textField.setPromptText("Type here");
			textField.setFont(customFont0B);
			textField.setId("textField");

	    	for (String word : words) {
		    	if (word.equals("___")) {
		    		entryFlowPane.getChildren().add(textField);
					entryFlowPane.setVgap(15);
		        } 
		    	else{
		    		Label label = new Label(word);
		            entryFlowPane.getChildren().add(label);
		            label.setFont(customFont0B);
		            label.setStyle("-fx-text-fill: #465385;");
		            }
		        }
	    	Main.reflected = true;
	    	entryFlowPane.setAlignment(Pos.CENTER_LEFT);

	    }
	    else {
	    	button_submit.setVisible(false);
			button_back.setVisible(true);
			label_title.setVisible(false);
	    	Label label = new Label("Reflection Completed");
	    	entryFlowPane.getChildren().add(label);
            label.setFont(customFontCur);
            label.setStyle("-fx-text-fill: #465385;");
            entryFlowPane.setAlignment(Pos.CENTER);
            
//            if(!Main.reflected_check_date.equals(Main_window.current_mood_date)){
//            	Main.reflected = false;
//				FileHandling.replaceLine(1, Boolean.toString(Main.reflected), "reflected_check.txt");
//
//            }
		}
	        
	        entryFlowPane.setHgap(8);
	        
	        entryFlowPane.setStyle("");
	        entryFlowPane.setPadding(new javafx.geometry.Insets(0, 0, 0, 20));
	        entryFlowPane.setId("entryFlowPane");
	        borderPane.setCenter(entryFlowPane);
	     
	}

	public void submit(ActionEvent event) throws IOException {

		Main.total_reflections += 1;
		FileHandling.replaceLine(4, Integer.toString(Main.total_reflections), "data.txt");

		Main.reflected_day.add(Main_window.dayName);
		Main.reflected_month.add(Main_window.monthName);
		Main.reflected_date.add(Main_window.current_mood_date);
		Main.reflected_time.add(Main_window.formattedTime);

		FileHandling.saveStringData(Main.reflected_day, "reflected_day.txt");
		FileHandling.saveStringData(Main.reflected_month, "reflected_month.txt");
		FileHandling.saveStringData(Main.reflected_date, "reflected_date.txt");
		FileHandling.saveStringData(Main.reflected_time, "reflected_time.txt");

		FileHandling.replaceLine(3, Main_window.monthName, "reflected_check.txt");
		FileHandling.replaceLine(4, Main_window.current_mood_date, "reflected_check.txt");
		FileHandling.replaceLine(1, Boolean.toString(Main.reflected), "reflected_check.txt");
		// Get the content from the FlowPane
		StringBuilder userAnswer = new StringBuilder();
		for (javafx.scene.Node node : entryFlowPane.getChildren()) {
			if (node instanceof TextField) {
				userAnswer.append(((TextField) node).getText());
			} else if (node instanceof Label) {
				userAnswer.append(((Label) node).getText());
			}

			// Add space between words
			userAnswer.append(" ");
		}

		// Store the user's answer in Main.reflections
		Main.reflections.add(userAnswer.toString().trim());
		Main.reflections_reflected.add(userAnswer.toString().trim());
		FileHandling.appendLine(userAnswer.toString().trim(), "reflected.txt");

		Parent root;
		root = FXMLLoader.load(Reflection.class.getResource("FXMLs/Main.fxml"));
		Scene scene_2 = button_submit.getScene();
		scene_2.getStylesheets().add(Reflection.class.getResource("CSSs/main_style.css").toExternalForm());

		root.translateXProperty().set(scene_2.getHeight());
		StackPane stackParent = (StackPane) scene_2.getRoot();
		stackParent.getChildren().add(root);

		Timeline transitionTimeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
		transitionTimeline.getKeyFrames().add(kf);
		transitionTimeline.setOnFinished(evet -> {
			stackParent.getChildren().remove(borderPane);
		});
		transitionTimeline.play();
	}


	public void back(ActionEvent event) throws IOException {

		Parent root;
		root = FXMLLoader.load(Reflection.class.getResource("FXMLs/Main.fxml"));
		Scene scene_2 = button_back.getScene();
		scene_2.getStylesheets().add(Reflection.class.getResource("CSSs/main_style.css").toExternalForm());

		root.translateXProperty().set(scene_2.getHeight());
		StackPane stackParent = (StackPane) scene_2.getRoot();
		stackParent.getChildren().add(root);

		Timeline transitionTimeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
		transitionTimeline.getKeyFrames().add(kf);
		transitionTimeline.setOnFinished(evet -> {
			stackParent.getChildren().remove(borderPane);
		});
		transitionTimeline.play();
	}



}
