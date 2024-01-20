package mt.moodtracker;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main_window {
	@FXML
	Button button_checkin, button_view_cards;
	@FXML
	Label labelMood, labelChallenge, labelChallengeTimer, labelChallengeBold,
	labelFeelingB, labelCheckin, labelWelcome, labelWelcomeBack;
	@FXML
	StackPane stackParent;
	@FXML
	BorderPane borderPane;


	Font customFont0 = Font.loadFont(getClass().getResourceAsStream("fonts/Quicksand-SemiBold.ttf"), 20);
	Font customFont0B = Font.loadFont(getClass().getResourceAsStream("fonts/Quicksand-Bold.ttf"), 20);
	Font customFont1 = Font.loadFont(getClass().getResourceAsStream("fonts/Quicksand-Regular.ttf"), 20);
	Font customFont0BL = Font.loadFont(GetName.class.getResourceAsStream("fonts/Quicksand-Bold.ttf"), 36);
	// initialize global variable -->
    public static LocalTime endOfDay = LocalTime.of(23, 59, 59);
    public static LocalTime currentTime = LocalTime.now();
    public static long secondsUntilEndOfDay = currentTime.until(endOfDay, ChronoUnit.SECONDS);
    static LocalDate today = LocalDate.now();
    static DayOfWeek dayOfWeek = today.getDayOfWeek();
    public static String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
    static LocalDate currentDate = LocalDate.now();
	static DateTimeFormatter formatter_readable = DateTimeFormatter.ofPattern("h:mm a");

	// Format the current time using the formatter
	public static String formattedTime = currentTime.format(formatter_readable);
    static Month month = currentDate.getMonth();
    public static String monthName = month.name();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
    public static String current_mood_date = today.format(formatter);


	public void initialize() {

		// Load -->
		Main.moodName = FileHandling.loadStringData("\\moodName.txt");
		Main.moodEntry_moodValue = FileHandling.loadIntData("\\moodEntry_moodValue.txt");
		Main.noMoodEntriesArray = FileHandling.loadIntData("\\noMoodEntriesArray.txt");
		Main.moodEntry_day = FileHandling.loadStringData("\\moodEntry_day.txt");
		Main.moodEntry_date = FileHandling.loadStringData("\\moodEntry_date.txt");
		Main.moodEntry_month = FileHandling.loadStringData("\\moodEntry_month.txt");
		Main.moodEntry_time = FileHandling.loadStringData("\\moodEntry_time.txt");
		Main.moodEntry_moodTitle = FileHandling.loadStringData("\\moodEntry_moodTitle.txt");
		Main.moodEntry_moodDescription = FileHandling.loadStringData("\\moodEntry_moodDescription.txt");
		//moodEntry_moodCause = FileHandling.loadStringData("\\causes.txt");
		//moodEntry_moodCause = FileHandling.loadEntries("\\causes.txt", "-");
		Main.moodEntry_moodCause_stringArrayList = FileHandling.readStringArrayData("\\causes.txt");

		Main.reflections_reflected = FileHandling.loadStringData("\\reflected.txt");
		Main.reflections = FileHandling.loadStringData("\\reflections.txt");
		Main.reflected_day = FileHandling.loadStringData("\\reflected_day.txt");
		Main.reflected_date = FileHandling.loadStringData("\\reflected_date.txt");
		Main.reflected_month = FileHandling.loadStringData("\\reflected_month.txt");
		Main.reflected_time = FileHandling.loadStringData("\\reflected_time.txt");
		Main.reflected = Boolean.parseBoolean(FileHandling.readLine(1, "\\reflected_check.txt"));


		//System.out.println(FileHandling.readLine(4, "\\reflected_check.txt") + current_mood_date);
		if(Main.reflected) {
			if (!FileHandling.readLine(4, "\\reflected_check.txt").equals(current_mood_date)) {
				Main.reflected = false;

				FileHandling.replaceLine(1, Boolean.toString(Main.reflected), "reflected_check.txt");
			}

		}


		labelMood.setFont(customFont1);
		labelChallenge.setFont(customFont0);
		labelChallengeBold.setFont(customFont0B);
		labelFeelingB.setFont(customFont0B);
		labelCheckin.setFont(customFont0B);
		labelWelcome.setFont(customFont0BL);

		labelWelcome.setText("Welcome, " + FileHandling.readLine(2, "\\data.txt"));
		Timeline timeline = new Timeline();
		if(!Main.reflected){            // Set up a Timeline to update the countdown timer
			timeline = new Timeline(
					new KeyFrame(Duration.seconds(1), event -> {
						secondsUntilEndOfDay--;
						long hours = secondsUntilEndOfDay / 3600;
						long minutes = (secondsUntilEndOfDay % 3600) / 60;
						long seconds = secondsUntilEndOfDay % 60;
						String countdownText = String.format("%02d:%02d:%02d", hours, minutes, seconds);
						labelChallengeTimer.setText("ENDS IN " + countdownText);
						labelChallengeTimer.setFont(customFont0B);

					})
			);
		}else {
			labelChallengeTimer.setText("Completed!");
			labelChallengeTimer.setFont(customFont0B);
		}

        // Set the timeline to repeat indefinitely
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Start the timeline
        timeline.play();
	}

	private Parent root_2;
	private Stage stage_2;
	private Scene scene_2;



	public void moodCheckin(ActionEvent event) throws IOException {
		Main.total_entries += 1;
		root_2 = FXMLLoader.load(Main_window.class.getResource("FXMLs/mood_checkin.fxml"));
		stage_2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene_2 = button_checkin.getScene();
		scene_2.getStylesheets().add(Main_window.class.getResource("CSSs/application.css").toExternalForm());

		root_2.translateYProperty().set(scene_2.getHeight());
		stackParent.getChildren().add(root_2);

		Timeline transitionTimeline = new Timeline();
		KeyValue kv = new KeyValue(root_2.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
		transitionTimeline.getKeyFrames().add(kf);
		transitionTimeline.setOnFinished(evet -> {
			// Only remove the BorderPane if it is present in the stackParent
			if (stackParent.getChildren().contains(borderPane)) {
				stackParent.getChildren().remove(borderPane);
			}
		});
		transitionTimeline.play();
	}

	public void cardsView(ActionEvent event) throws IOException {
		root_2 = FXMLLoader.load(Main_window.class.getResource("FXMLs/cards.fxml"));
		stage_2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene_2 = button_checkin.getScene();
		scene_2.getStylesheets().add(Main_window.class.getResource("CSSs/cards.css").toExternalForm());

		root_2.translateYProperty().set(scene_2.getHeight());
		stackParent.getChildren().add(root_2);

		Timeline transitionTimeline = new Timeline();
		KeyValue kv = new KeyValue(root_2.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
		transitionTimeline.getKeyFrames().add(kf);
		transitionTimeline.setOnFinished(evet -> {
			// Only remove the BorderPane if it is present in the stackParent
			if (stackParent.getChildren().contains(borderPane)) {
				stackParent.getChildren().remove(borderPane);
			}
		});
		transitionTimeline.play();
	}

	public void reflection(ActionEvent event) throws IOException{

		root_2 = FXMLLoader.load(Main_window.class.getResource("FXMLs/reflection.fxml"));
		stage_2 = (Stage)((Node)event.getSource()).getScene().getWindow();
//		scene_2 = new Scene(root_2);
		Scene scene_2 = button_checkin.getScene();
//		stage_2.setScene(scene_2);
		scene_2.getStylesheets().add(Main_window.class.getResource("CSSs/reflection.css").toExternalForm());

		root_2.translateYProperty().set(scene_2.getHeight());
		stackParent.getChildren().add(root_2);

		Timeline transitionTimeline = new Timeline();
		KeyValue kv = new KeyValue(root_2.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
		transitionTimeline.getKeyFrames().add(kf);
		transitionTimeline.setOnFinished(evet->{
			stackParent.getChildren().remove(borderPane);
		});
		transitionTimeline.play();


	}



}
