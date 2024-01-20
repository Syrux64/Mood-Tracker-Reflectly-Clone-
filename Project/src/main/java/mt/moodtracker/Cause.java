package mt.moodtracker;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


public class Cause extends Mood_checkin implements Initializable {
	
	@FXML
	Button button_continue2, option1_color, option2_color, 
	option3_color, option4_color, option5_color, option6_color,
	option7_color, option8_color, option9_color, option10_color,
	option11_color, option12_color, option13_color, option14_color,
	option15_color, option16_color, option17_color, option18_color;
	
	@FXML 
	Label prompt, option1_label, option2_label, option3_label, option4_label,
	option5_label, option6_label, option7_label, option8_label, option9_label,
	option10_label, option11_label, option12_label, option13_label, option14_label,
	option15_label, option16_label, option17_label, option18_label;
	
	@FXML
	ImageView options1, options2, options3, options4, options5, options6,
	options7, options8, options9, options10, options11, options12, options13,
	options14, options15, options16, options17, options18;
	
	@FXML
	ScrollPane option_pane;
	@FXML
	GridPane option_grid;
	@FXML
	BorderPane borderPane;
	
	
	private Parent root_2;
	
	
	Image[][] images = new Image[18][2];
	boolean[] mooEntry_moodsBool = new boolean[18];// 0: 1:... 17: 18:
	
	public void causes(ActionEvent event) throws IOException {
		LinkedList<String> temp_causes = new LinkedList<>();

		for(int i=0;i<18;i++) {
			if(mooEntry_moodsBool[i]) {
				switch(i) {
					case 0:
						temp_causes.add("Work");
						break;
					case 1:
						temp_causes.add("Family");
						break;
					case 2:
						temp_causes.add("Friends");
						break;
					case 3:
						temp_causes.add("School");
						break;
					case 4:
						temp_causes.add("Relationship");
						break;
					case 5:
						temp_causes.add("Travelling");
						break;
					case 6:
						temp_causes.add("Food");
						break;
					case 7:
						temp_causes.add("Exercise");
						break;
					case 8:
						temp_causes.add("Health");
						break;
					case 9:
						temp_causes.add("Hobbies");
						break;
					case 10:
						temp_causes.add("Gaming");
						break;
					case 11:
						temp_causes.add("Weather");
						break;
					case 12:
						temp_causes.add("Sleep");
						break;
					case 13:
						temp_causes.add("Shopping");
						break;
					case 14:
						temp_causes.add("Workout");
						break;
					case 15:
						temp_causes.add("Music");
						break;
					case 16:
						temp_causes.add("Relaxing");
						break;
					case 17:
						temp_causes.add("Sports");
						break;

				}

			}
		}


		String[] temp_causesArray = temp_causes.toArray(new String[0]);
		Main.moodEntry_moodCause_stringArrayList.add(temp_causesArray);
		FileHandling.saveStringArrayData(Main.moodEntry_moodCause_stringArrayList, "causes.txt");



		root_2 = FXMLLoader.load(Cause.class.getResource("FXMLs/reason.fxml"));
		Scene scene_2 = button_continue2.getScene();
		scene_2.getStylesheets().add(Cause.class.getResource("CSSs/reason_style.css").toExternalForm());
		
		root_2.translateXProperty().set(scene_2.getHeight());
		
		StackPane stackParent = (StackPane)scene_2.getRoot();
		stackParent.getChildren().add(root_2);
		
		Timeline transitionTimeline = new Timeline();
		KeyValue kv = new KeyValue(root_2.translateXProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
		transitionTimeline.getKeyFrames().add(kf);
		transitionTimeline.setOnFinished(evet->{
			stackParent.getChildren().remove(borderPane);
		});
		transitionTimeline.play();		
		
//		stage_2.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		
		if (0 <= current_mood_value && current_mood_value < 20) {
			prompt.setText("Alright. What's making your " + time_period + " really terrible?");
		}
		
		if (20 <= current_mood_value && current_mood_value < 40) {
			prompt.setText("Alright. What's making your " + time_period + " somewhat bad?");
		}
		
		if (40 <= current_mood_value && current_mood_value < 60) {
			prompt.setText("Great. What's making your " + time_period + " completely okay?");
		}
		
		if (60 <= current_mood_value && current_mood_value < 80) {
			prompt.setText("Great. What's making your " + time_period + " pretty good?");
		}
		
		if (80 <= current_mood_value && current_mood_value <= 100) {
			prompt.setText("Cool! What's making your " + time_period + " super awesome?");
		}
		

		option_pane.setContent(option_grid);
		option_pane.getStyleClass().add("scroll-pane");
		
		images[0][0] = new Image(Cause.class.getResource("icons/work.png").toExternalForm());
		images[0][1] = new Image(Cause.class.getResource("icons/workS.png").toExternalForm());
		images[1][0] = new Image(Cause.class.getResource("icons/family.png").toExternalForm());
		images[1][1] = new Image(Cause.class.getResource("icons/familyS.png").toExternalForm());
		images[2][0] = new Image(Cause.class.getResource("icons/friends.png").toExternalForm());
		images[2][1] = new Image(Cause.class.getResource("icons/friendsS.png").toExternalForm());
		images[3][0] = new Image(Cause.class.getResource("icons/school.png").toExternalForm());
		images[3][1] = new Image(Cause.class.getResource("icons/schoolS.png").toExternalForm());
		images[4][0] = new Image(Cause.class.getResource("icons/relationship.png").toExternalForm());
		images[4][1] = new Image(Cause.class.getResource("icons/relationshipS.png").toExternalForm());
		images[5][0] = new Image(Cause.class.getResource("icons/travelling.png").toExternalForm());
		images[5][1] = new Image(Cause.class.getResource("icons/travellingS.png").toExternalForm());
		images[6][0] = new Image(Cause.class.getResource("icons/food.png").toExternalForm());
		images[6][1] = new Image(Cause.class.getResource("icons/foodS.png").toExternalForm());
		images[7][0] = new Image(Cause.class.getResource("icons/exercise.png").toExternalForm());
		images[7][1] = new Image(Cause.class.getResource("icons/exerciseS.png").toExternalForm());
		images[8][0] = new Image(Cause.class.getResource("icons/health.png").toExternalForm());
		images[8][1] = new Image(Cause.class.getResource("icons/healthS.png").toExternalForm());
		images[9][0] = new Image(Cause.class.getResource("icons/hobbies.png").toExternalForm());
		images[9][1] = new Image(Cause.class.getResource("icons/hobbiesS.png").toExternalForm());
		images[10][0] = new Image(Cause.class.getResource("icons/gaming.png").toExternalForm());
		images[10][1] = new Image(Cause.class.getResource("icons/gamingS.png").toExternalForm());
		images[11][0] = new Image(Cause.class.getResource("icons/weather.png").toExternalForm());
		images[11][1] = new Image(Cause.class.getResource("icons/weatherS.png").toExternalForm());
		images[12][0] = new Image(Cause.class.getResource("icons/sleep.png").toExternalForm());
		images[12][1] = new Image(Cause.class.getResource("icons/sleepS.png").toExternalForm());
		images[13][0] = new Image(Cause.class.getResource("icons/shopping.png").toExternalForm());
		images[13][1] = new Image(Cause.class.getResource("icons/shoppingS.png").toExternalForm());
		images[14][0] = new Image(Cause.class.getResource("icons/workout.png").toExternalForm());
		images[14][1] = new Image(Cause.class.getResource("icons/workoutS.png").toExternalForm());
		images[15][0] = new Image(Cause.class.getResource("icons/music.png").toExternalForm());
		images[15][1] = new Image(Cause.class.getResource("icons/musicS.png").toExternalForm());
		images[16][0] = new Image(Cause.class.getResource("icons/relaxing.png").toExternalForm());
		images[16][1] = new Image(Cause.class.getResource("icons/relaxingS.png").toExternalForm());
		images[17][0] = new Image(Cause.class.getResource("icons/sports.png").toExternalForm());
		images[17][1] = new Image(Cause.class.getResource("icons/sportsS.png").toExternalForm());

        for (int i = 0; i < option_grid.getChildren().size(); i++) {
            if (option_grid.getChildren().get(i) instanceof Label) {
                Label label = (Label) option_grid.getChildren().get(i);
                label.setFont(customFont0);
            }
        }
	}
	

	boolean selectedFlag1 = true;
	public void select_option1(ActionEvent e) {
		if(selectedFlag1) {
			options1.setImage(images[0][1]);
			option1_label.setStyle("-fx-text-fill:#7F7FD9");
			option1_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[0] = true;
		}
		else {
			options1.setImage(images[0][0]);
			option1_label.setStyle("-fx-text-fill:#D0CFF0");
			option1_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[0] = false;
		}
		selectedFlag1 = !selectedFlag1;	
	}
	
	boolean selectedFlag2 = true;
	public void select_option2(ActionEvent e) {
		if(selectedFlag2) {
			options2.setImage(images[1][1]);
			option2_label.setStyle("-fx-text-fill:#7F7FD9");
			option2_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[1] = true;
		}
		else {
			options2.setImage(images[1][0]);
			option2_label.setStyle("-fx-text-fill:#D0CFF0");
			option2_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[1] = false;
		}
		selectedFlag2 = !selectedFlag2;
	}
	
	boolean selectedFlag3 = true;
	public void select_option3(ActionEvent e) {
		if(selectedFlag3) {
			options3.setImage(images[2][1]);
			option3_label.setStyle("-fx-text-fill:#7F7FD9");
			option3_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[2] = true;
		}
		else {
			options3.setImage(images[2][0]);
			option3_label.setStyle("-fx-text-fill:#D0CFF0");
			option3_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[2] = false;
		}
		selectedFlag3 = !selectedFlag3;
		
	}
	
	
	
	boolean selectedFlag4 = true;
	public void select_option4(ActionEvent e) {
		if(selectedFlag4) {
			options4.setImage(images[3][1]);
			option4_label.setStyle("-fx-text-fill:#7F7FD9");
			option4_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[3] = true;
		}
		else {
			options4.setImage(images[3][0]);
			option4_label.setStyle("-fx-text-fill:#D0CFF0");
			option4_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[3] = false;
		}
		selectedFlag4 = !selectedFlag4;
	}
	
	boolean selectedFlag5 = true;
	public void select_option5(ActionEvent e) {
		if(selectedFlag5) {
			options5.setImage(images[4][1]);
			option5_label.setStyle("-fx-text-fill:#7F7FD9");
			option5_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[4] = true;
		}
		else {
			options5.setImage(images[4][0]);
			option5_label.setStyle("-fx-text-fill:#D0CFF0");
			option5_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[4] = false;
		}
		selectedFlag5 = !selectedFlag5;
	}
	
	boolean selectedFlag6 = true;
	public void select_option6(ActionEvent e) {
		if(selectedFlag6) {
			options6.setImage(images[5][1]);
			option6_label.setStyle("-fx-text-fill:#7F7FD9");
			option6_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[5] = true;
		}
		else {
			options6.setImage(images[5][0]);
			option6_label.setStyle("-fx-text-fill:#D0CFF0");
			option6_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[5] = false;
		}
		selectedFlag6 = !selectedFlag6;
	}
	
	boolean selectedFlag7 = true;
	public void select_option7(ActionEvent e) {
		if(selectedFlag7) {
			options7.setImage(images[6][1]);
			option7_label.setStyle("-fx-text-fill:#7F7FD9");
			option7_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[6] = true;
		}
		else {
			options7.setImage(images[6][0]);
			option7_label.setStyle("-fx-text-fill:#D0CFF0");
			option7_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[6] = false;
		}
		selectedFlag7 = !selectedFlag7;
	}
	
	boolean selectedFlag8 = true;
	public void select_option8(ActionEvent e) {
		if(selectedFlag8) {
			options8.setImage(images[7][1]);
			option8_label.setStyle("-fx-text-fill:#7F7FD9");
			option8_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[7] = true;
		}
		else {
			options8.setImage(images[7][0]);
			option8_label.setStyle("-fx-text-fill:#D0CFF0");
			option8_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[7] = false;
		}
		selectedFlag8 = !selectedFlag8;
	}
	
	boolean selectedFlag9 = true;
	public void select_option9(ActionEvent e) {
		if(selectedFlag9) {
			options9.setImage(images[8][1]);
			option9_label.setStyle("-fx-text-fill:#7F7FD9");
			option9_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[8] = true;
		}
		else {
			options9.setImage(images[8][0]);
			option9_label.setStyle("-fx-text-fill:#D0CFF0");
			option9_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[8] = false;
		}
		selectedFlag9 = !selectedFlag9;
	}
	
	boolean selectedFlag10 = true;
	public void select_option10(ActionEvent e) {
		if(selectedFlag10) {
			options10.setImage(images[9][1]);
			option10_label.setStyle("-fx-text-fill:#7F7FD9");
			option10_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[9] = true;
		}
		else {
			options10.setImage(images[9][0]);
			option10_label.setStyle("-fx-text-fill:#D0CFF0");
			option10_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[9] = false;
		}
		selectedFlag10 = !selectedFlag10;
	}
	
	boolean selectedFlag11 = true;
	public void select_option11(ActionEvent e) {
		if(selectedFlag11) {
			options11.setImage(images[10][1]);
			option11_label.setStyle("-fx-text-fill:#7F7FD9");
			option11_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[10] = true;
		}
		else {
			options11.setImage(images[10][0]);
			option11_label.setStyle("-fx-text-fill:#D0CFF0");
			option11_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[10] = false;
		}
		selectedFlag11 = !selectedFlag11;
	}
	
	boolean selectedFlag12 = true;
	public void select_option12(ActionEvent e) {
		if(selectedFlag12) {
			options12.setImage(images[11][1]);
			option12_label.setStyle("-fx-text-fill:#7F7FD9");
			option12_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[11] = true;
		}
		else {
			options12.setImage(images[11][0]);
			option12_label.setStyle("-fx-text-fill:#D0CFF0");
			option12_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[11] = false;
		}
		selectedFlag12 = !selectedFlag12;
	}
	
	boolean selectedFlag13 = true;
	public void select_option13(ActionEvent e) {
		if(selectedFlag13) {
			options13.setImage(images[12][1]);
			option13_label.setStyle("-fx-text-fill:#7F7FD9");
			option13_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[12] = true;
		}
		else {
			options13.setImage(images[12][0]);
			option13_label.setStyle("-fx-text-fill:#D0CFF0");
			option13_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[12] = false;
		}
		selectedFlag13 = !selectedFlag13;
	}
	
	boolean selectedFlag14 = true;
	public void select_option14(ActionEvent e) {
		if(selectedFlag14) {
			options14.setImage(images[13][1]);
			option14_label.setStyle("-fx-text-fill:#7F7FD9");
			option14_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[13] = true;
		}
		else {
			options14.setImage(images[13][0]);
			option14_label.setStyle("-fx-text-fill:#D0CFF0");
			option14_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[13] = false;
		}
		selectedFlag14 = !selectedFlag14;
	}
	
	boolean selectedFlag15 = true;
	public void select_option15(ActionEvent e) {
		if(selectedFlag15) {
			options15.setImage(images[14][1]);
			option15_label.setStyle("-fx-text-fill:#7F7FD9");
			option15_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[14] = true;
		}
		else {
			options15.setImage(images[14][0]);
			option15_label.setStyle("-fx-text-fill:#D0CFF0");
			option15_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[14] = false;
		}
		selectedFlag15 = !selectedFlag15;
	}
	
	boolean selectedFlag16 = true;
	public void select_option16(ActionEvent e) {
		if(selectedFlag16) {
			options16.setImage(images[15][1]);
			option16_label.setStyle("-fx-text-fill:#7F7FD9");
			option16_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[15] = true;
		}
		else {
			options16.setImage(images[15][0]);
			option16_label.setStyle("-fx-text-fill:#D0CFF0");
			option16_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[15] = false;
		}
		selectedFlag16 = !selectedFlag16;
	}
	
	boolean selectedFlag17 = true;
	public void select_option17(ActionEvent e) {
		if(selectedFlag17) {
			options17.setImage(images[16][1]);
			option17_label.setStyle("-fx-text-fill:#7F7FD9");
			option17_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[16] = true;
		}
		else {
			options17.setImage(images[16][0]);
			option17_label.setStyle("-fx-text-fill:#D0CFF0");
			option17_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[16] = false;
		}
		selectedFlag17 = !selectedFlag17;
	}
	
	boolean selectedFlag18 = true;
	public void select_option18(ActionEvent e) {
		if(selectedFlag18) {
			options18.setImage(images[17][1]);
			option18_label.setStyle("-fx-text-fill:#7F7FD9");
			option18_color.setStyle("-fx-background-color: #FDFEFC");
			mooEntry_moodsBool[17] = true;
		}
		else {
			options18.setImage(images[17][0]);
			option18_label.setStyle("-fx-text-fill:#D0CFF0");
			option18_color.setStyle("-fx-background-color: #7F7FD9");
			mooEntry_moodsBool[17] = false;
		}
		selectedFlag18 = !selectedFlag18;
	}
	
	
}
