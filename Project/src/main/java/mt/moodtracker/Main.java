package mt.moodtracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.LinkedList;

public class Main extends Application {
	public static boolean intro_completed = Boolean.parseBoolean(FileHandling.readLine(1, "\\data.txt"));

	// global variables -->
	public static String userNameString;

	public static LinkedList<Integer> noMoodEntriesArray = new LinkedList<>();
	public static LinkedList<Integer> moodEntry_moodValue = new LinkedList<>();
	public static LinkedList<String> moodEntry_time = new LinkedList<>();
	public static LinkedList<String> moodName = new LinkedList<>();
	public static LinkedList<String> moodEntry_day = new LinkedList<>();
	public static LinkedList<String> moodEntry_date = new LinkedList<>();
	public static LinkedList<String> moodEntry_month = new LinkedList<>();
	public static LinkedList<String> moodEntry_moodTitle = new LinkedList<>();
	public static LinkedList<String> moodEntry_moodDescription = new LinkedList<>();

	// public static String[][] moodEntry_moodCause = new String[20][18]; //[currentMoodIndex][total_causes]
	public static LinkedList<String> moodEntry_moodCause = new LinkedList<>();

	public static LinkedList<String[]> moodEntry_moodCause_stringArrayList = new LinkedList<>();

	public static boolean[] moodEntry_entered_today = new boolean[20];

	// reflections -->
	public static LinkedList<String> reflections_reflected = new LinkedList<>();
	public static int total_reflections = 0;
	//String sentence = FileHandling.readLine(Main.total_reflections + 1, "\\reflections.txt");
	// public static LinkedList<String> reflections_reflected = new LinkedList<>();
	public static LinkedList<String> reflections = new LinkedList<>();

	public static boolean reflected = false;
	public static String reflected_check_date = Main_window.current_mood_date;
	public static LinkedList<String> reflected_day = new LinkedList<>();
	public static LinkedList<String> reflected_date = new LinkedList<>();
	public static LinkedList<String> reflected_month = new LinkedList<>();
	public static LinkedList<String> reflected_time = new LinkedList<>();

	static protected int total_entries = -1;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		if (!intro_completed) {
			FileHandling.replaceLine(1, "true", "data.txt");

			Stage introStage = new Stage();
			Parent root = FXMLLoader.load(Main.class.getResource("FXMLs/intro.fxml"));
			Scene scene = new Scene(root);
			introStage.setTitle("Mood-Tracker");
			scene.getStylesheets().add(Main.class.getResource("CSSs/intro.css").toExternalForm());
			introStage.setScene(scene);
			introStage.show();


		} else {
			// load saved data
			moodName = FileHandling.loadStringData("\\moodName.txt");
			moodEntry_moodValue = FileHandling.loadIntData("\\moodEntry_moodValue.txt");
			noMoodEntriesArray = FileHandling.loadIntData("\\noMoodEntriesArray.txt");
			moodEntry_day = FileHandling.loadStringData("\\moodEntry_day.txt");
			moodEntry_date = FileHandling.loadStringData("\\moodEntry_date.txt");
			moodEntry_month = FileHandling.loadStringData("\\moodEntry_month.txt");
			moodEntry_time = FileHandling.loadStringData("\\moodEntry_time.txt");
			moodEntry_moodTitle = FileHandling.loadStringData("\\moodEntry_moodTitle.txt");
			moodEntry_moodDescription = FileHandling.loadStringData("\\moodEntry_moodDescription.txt");
			//moodEntry_moodCause = FileHandling.loadStringData("\\causes.txt");
			//moodEntry_moodCause = FileHandling.loadEntries("\\causes.txt", "-");
			moodEntry_moodCause_stringArrayList = FileHandling.readStringArrayData("\\causes.txt");

			reflections_reflected = FileHandling.loadStringData("\\reflected.txt");
			reflections = FileHandling.loadStringData("\\reflections.txt");
			reflected_day = FileHandling.loadStringData("\\reflected_day.txt");
			reflected_date = FileHandling.loadStringData("\\reflected_date.txt");
			reflected_month = FileHandling.loadStringData("\\reflected_month.txt");
			reflected_time = FileHandling.loadStringData("\\reflected_time.txt");
			reflected = Boolean.parseBoolean(FileHandling.readLine(1, "\\reflected_check.txt"));


			total_entries = Integer.parseInt(FileHandling.readLine(3, "\\data.txt"));
			total_reflections = Integer.parseInt(FileHandling.readLine(4, "\\data.txt"));


			Stage mainStage = new Stage();
			Parent root = FXMLLoader.load(Main.class.getResource("FXMLs/Main.fxml"));
			Scene scene = new Scene(root);
			mainStage.setTitle("Mood Tracker");
			scene.getStylesheets().add(Main.class.getResource("CSSs/main_style.css").toExternalForm());
			mainStage.setScene(scene);
			mainStage.show();

		}
	}


}
