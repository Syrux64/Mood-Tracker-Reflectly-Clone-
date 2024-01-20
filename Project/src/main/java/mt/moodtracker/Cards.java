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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Cards implements Initializable{

	@FXML
	Label labelCardsMenu, noReflections, noCheckins, reflectionEntries,
			checkinEntries;
	@FXML
	VBox entries_vbox;
	@FXML
	ScrollPane scrollPane;
	@FXML
	Button button_home;
	@FXML
	BorderPane borderPane;


	Image getCauseImage(String cause) {
		String path = "smaller_icons/" + cause.toLowerCase() + "S.png";
		Image image = new Image(Cards.class.getResourceAsStream(path));
		return image;
	}

	Image getMoodImage(String mood) {
		if(mood.equals("SUPER AWESOME")) {
			return new Image(Cards.class.getResource("smaller_icons/super_awesome.png").toExternalForm());
		}
		if(mood.equals("PRETTY GOOD")) {
			return new Image(Cards.class.getResource("smaller_icons/pretty_good.png").toExternalForm());
		}
		if(mood.equals("COMPLETELY OKAY")) {
			return new Image(Cards.class.getResource("smaller_icons/completely_okay.png").toExternalForm());
		}
		if(mood.equals("SOMEWHAT BAD")) {
			return new Image(Cards.class.getResource("smaller_icons/somewhat_bad.png").toExternalForm());
		}
		if(mood.equals("REALLY TERRIBLE")) {
			return new Image(getClass().getResource("smaller_icons/really_terrible.png").toExternalForm());
		}
		return null;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		Font customFont0 = Font.loadFont(Cards.class.getResourceAsStream("fonts/Quicksand-SemiBold.ttf"), 18);
		Font customFont0B = Font.loadFont(Cards.class.getResourceAsStream("fonts/Quicksand-Bold.ttf"), 22);
		Font customFont0M = Font.loadFont(Cards.class.getResourceAsStream("fonts/Quicksand-Bold.ttf"), 14);
		Font customFont0BXL = Font.loadFont(Cards.class.getResourceAsStream("fonts/Quicksand-Bold.ttf"), 60);
		Font customFont0BL = Font.loadFont(Cards.class.getResourceAsStream("fonts/Quicksand-Bold.ttf"), 16);
		Font customFont1 = Font.loadFont(Cards.class.getResourceAsStream("fonts/Quicksand-Regular.ttf"), 19);

		labelCardsMenu.setFont(customFont0BXL);
		noReflections.setFont(customFont0B);
		reflectionEntries.setFont(customFont0BL);
		noCheckins.setFont(customFont0B);
		checkinEntries.setFont(customFont0BL);

		noCheckins.setText(Integer.toString(Main.total_entries + 1));

		noReflections.setText(Integer.toString(Main.total_reflections));

		// debug -->
//		Main.total_entries = 1;
//		Mood_checkin.moodString = "COMPLETELY OKAY";
//		Main.moodName[Main.total_entries] = Mood_checkin.moodString;
		//


		String prev_moodEntry_date = null;
		String prev_moodEntry_month = null;
		boolean perDayReflection = false, showdateCard = true;
		for (int i = Main.total_entries, k = Main.total_reflections; i >= 0 && k >= 0; i--, k--) {

            // Entried date -->
			GridPane gridPane = new GridPane();
			ColumnConstraints column1 = new ColumnConstraints(80);
			ColumnConstraints column2 = new ColumnConstraints(450);
			gridPane.setHgap(5);

			VBox month_dateBox = new VBox();
			month_dateBox.setPrefHeight(0); // was 70

			month_dateBox.setMaxWidth(65);
			month_dateBox.setSpacing(4);

			VBox day_fromDateBox = new VBox();
			day_fromDateBox.setPrefHeight(70);
			day_fromDateBox.setSpacing(4);

			Insets vBoxMargin = new Insets(0, 20, 10, 30);
			VBox.setMargin(month_dateBox, vBoxMargin);
			VBox.setMargin(day_fromDateBox, vBoxMargin);

			gridPane.getColumnConstraints().addAll(column1, column2);

			Label date = new Label(Main.moodEntry_date.get(i));
			Label month = new Label(Main.moodEntry_month.get(i).substring(0, 3).toUpperCase());
			Label day_ = new Label(Main.moodEntry_day.get(i));
			Label day_from_now = new Label("TODAY");

			month_dateBox.getChildren().add(date);
			month_dateBox.getChildren().add(month);

			day_fromDateBox.getChildren().add(day_);
			day_fromDateBox.getChildren().add(day_from_now);

			month_dateBox.setAlignment(Pos.CENTER);

			day_fromDateBox.setAlignment(Pos.CENTER_LEFT);

			gridPane.add(month_dateBox, 0, 0);
			GridPane.setHalignment(month_dateBox, HPos.CENTER);
			GridPane.setValignment(month_dateBox, VPos.CENTER);

			gridPane.add(day_fromDateBox, 1, 0);
			GridPane.setHalignment(day_fromDateBox, HPos.CENTER);
			GridPane.setValignment(day_fromDateBox, VPos.CENTER);
			gridPane.setMaxWidth(500);

			day_.setFont(customFont0B);
			date.setFont(customFont0B);
			day_from_now.setFont(customFont0M);
			month.setFont(customFont0M);

			date.setId("date");
			day_.setId("day");
			day_from_now.setId("day_from_now");
			month.setId("month");
			month_dateBox.setId("month_dateBox");

			// Entry Detail -->
			AnchorPane detailsAnchorPane = new AnchorPane();
			detailsAnchorPane.setId("detailsAnchorPane");
			detailsAnchorPane.setMaxHeight(150);
			detailsAnchorPane.setMaxWidth(400);

			VBox detailsBox = new VBox();
			detailsBox.setMaxWidth(300);
			detailsBox.setPrefWidth(500);
			GridPane details_gridPane = new GridPane();

			details_gridPane.setHgap(0);

			Image image = getMoodImage(Main.moodName.get(i));
			ImageView imageView = new ImageView(image);

			imageView.setFitWidth(45);
			imageView.setFitHeight(45);

			HBox moodImageHBox = new HBox();
			moodImageHBox.getChildren().add(imageView);
			moodImageHBox.setAlignment(Pos.CENTER);

			details_gridPane.add(moodImageHBox, 0, 0);
			GridPane.setHalignment(moodImageHBox, HPos.CENTER);
			GridPane.setValignment(moodImageHBox, VPos.CENTER);

			details_gridPane.setMaxWidth(500);
			details_gridPane.getColumnConstraints().addAll(column1, column2);

			VBox title_timeBox = new VBox();
			title_timeBox.setPrefHeight(70);
			title_timeBox.setMaxWidth(400);
			title_timeBox.setSpacing(0);

			details_gridPane.add(title_timeBox, 1, 0);
			details_gridPane.setMaxWidth(400);

			detailsBox.getChildren().add(details_gridPane);

			Label title = new Label(Main.moodEntry_moodTitle.get(i));
			Label time = new Label(Main.moodEntry_time.get(i));
			Label descriptionLabel = new Label(Main.moodEntry_moodDescription.get(i));

			title.setWrapText(true);
			title.setMaxWidth(450);
			descriptionLabel.setWrapText(true);
			descriptionLabel.setMaxWidth(600);
			Insets descriptionInsets = new Insets(10, 0, 25, 25);
			descriptionLabel.setPadding(descriptionInsets);

			title.setId("title");
			time.setId("time");
			descriptionLabel.setId("descriptionLabel");


			title.setFont(customFont0B);
			time.setFont(customFont0);
			descriptionLabel.setFont(customFont1);

			title_timeBox.getChildren().add(title);
			title_timeBox.getChildren().add(time);
			title_timeBox.setAlignment(Pos.CENTER_LEFT);
			detailsBox.getChildren().add(descriptionLabel);

			// Causes card -->
			FlowPane causeCardFlowPane = new FlowPane();
			String[] causeArray = Main.moodEntry_moodCause_stringArrayList.get(i);

			for(int j=0;j<causeArray.length;j++) {
				if(causeArray[j] != null) {
					Label causedLabel = new Label(causeArray[j]);
					HBox causedHbox  = new HBox();

					Image causeImage = getCauseImage(causeArray[j]);
					ImageView causeImageImageView = new ImageView(causeImage);

					causeImageImageView.setFitWidth(20);
					causeImageImageView.setFitHeight(20);

					Insets causedHboxMargin = new Insets(0, 0, 0, 5);
					causedHbox.setPadding(causedHboxMargin);

					causedLabel.setStyle("-fx-text-fill: #AFB4BA; -fx-padding: 10;");
					causedLabel.setFont(customFont0M);

					causedHbox.getChildren().add(causeImageImageView);
					causedHbox.getChildren().add(causedLabel);
					causedHbox.setStyle("-fx-background-color: #F6F7F9; -fx-background-radius: 10px");
					causedHbox.setSpacing(0);
					causedHbox.setAlignment(Pos.CENTER);

					causeCardFlowPane.getChildren().add(causedHbox);
				}
			}

			Insets flowMargin = new Insets(5, 10, 20, 25);
			causeCardFlowPane.setPadding(flowMargin);
			causeCardFlowPane.setHgap(5);
			causeCardFlowPane.setVgap(5);

			detailsBox.getChildren().add(causeCardFlowPane);
			detailsAnchorPane.getChildren().add(detailsBox);

            // reflection box -->
            if (prev_moodEntry_date != null && prev_moodEntry_date.equals(Main.moodEntry_date.get(i)) &&
                    prev_moodEntry_month.equals(Main.moodEntry_month.get(i))) {
                showdateCard = false;
            } else {
                entries_vbox.getChildren().add(gridPane);
                showdateCard = false;
                perDayReflection = false; // Reset perDayReflection for the new date
            }
			AnchorPane reflectionAnchorPane = new AnchorPane();

			if (k > 0 && k <= Main.reflections_reflected.size()&&
					Main.moodEntry_date.get(i).equals(Main.reflected_date.get(k - 1)) &&
					Main.moodEntry_month.get(i).equals(Main.reflected_month.get(k - 1))) {
                String currentDate = Main.moodEntry_date.get(i);

				if (!Main.reflections_reflected.isEmpty()) {
					reflectionAnchorPane.setId("reflectionAnchorPane");
					reflectionAnchorPane.setMaxHeight(150);
					reflectionAnchorPane.setMaxWidth(400);

					VBox reflectionVbox = new VBox();
					reflectionVbox.setMaxWidth(300);
					reflectionVbox.setPrefWidth(500);

					GridPane reflection_gridPane = new GridPane();
					reflection_gridPane.setHgap(0);

					Image reflcetion_image = new Image(Cards.class.getResourceAsStream("icons/reflection.jpg"));

					ImageView reflcetion_imageView = new ImageView(reflcetion_image);
					reflcetion_imageView.setFitHeight(45);
					reflcetion_imageView.setPreserveRatio(true);

					HBox reflectionImageHBox = new HBox();
					reflectionImageHBox.getChildren().add(reflcetion_imageView);
					reflectionImageHBox.setAlignment(Pos.CENTER);

					reflection_gridPane.add(reflectionImageHBox, 0, 0);
					GridPane.setHalignment(reflectionImageHBox, HPos.CENTER);
					GridPane.setValignment(reflectionImageHBox, VPos.CENTER);

					reflection_gridPane.setMaxWidth(500);
					reflection_gridPane.getColumnConstraints().addAll(column1, column2);

					VBox reflection_title_timeBox = new VBox();
					reflection_title_timeBox.setPrefHeight(70);
					reflection_title_timeBox.setMaxWidth(400);
					reflection_title_timeBox.setSpacing(0);

					reflection_gridPane.add(reflection_title_timeBox, 1, 0);
					reflection_gridPane.setMaxWidth(400);

					reflectionVbox.getChildren().add(reflection_gridPane);


					Label reflection_title = new Label("Daily Reflection");
					Label reflection_time = new Label(Main.reflected_time.get(k - 1));


					Label reflection = new Label(Main.reflections_reflected.get(k - 1));

					reflection_title.setWrapText(true);
					reflection_title.setMaxWidth(450);
					reflection.setWrapText(true);
					reflection.setMaxWidth(600);
					Insets reflectionInsets = new Insets(10, 0, 25, 25);
					reflection.setPadding(reflectionInsets);

					reflection_title.setId("title");
					reflection_time.setId("time");
					reflection.setId("descriptionLabel");

					reflection_title.setFont(customFont0B);
					reflection_time.setFont(customFont0);
					reflection.setFont(customFont1);

					reflection_title_timeBox.getChildren().add(reflection_title);
					reflection_title_timeBox.getChildren().add(reflection_time);
					reflection_title_timeBox.setAlignment(Pos.CENTER_LEFT);
					reflectionVbox.getChildren().add(reflection);

                    if (!Main.reflections_reflected.isEmpty() && !perDayReflection) {
                        reflectionAnchorPane.getChildren().add(reflectionVbox);
                        perDayReflection = true;
                    }


				}

			}

			entries_vbox.setSpacing(20);

			if(i==Main.total_entries){	// exception
                if(!entries_vbox.getChildren().contains(gridPane)){
                    entries_vbox.getChildren().add(gridPane);
                    showdateCard = false;
                }
			}

			if (prev_moodEntry_date != null){
                if (!(prev_moodEntry_date.equals(Main.moodEntry_date.get(i)) && prev_moodEntry_month.equals(Main.moodEntry_month.get(i))))
                    showdateCard = true;
                else
                    showdateCard = false;
            }


			if (showdateCard) {
//				System.out.println("added");
                if(!entries_vbox.getChildren().contains(gridPane)){
                    entries_vbox.getChildren().add(gridPane);
                    showdateCard = false;
                    perDayReflection = false;
                }
			}

			entries_vbox.getChildren().add(detailsAnchorPane);

			try {
				if (Main.reflections.get(k) != null){
					entries_vbox.getChildren().add(reflectionAnchorPane);
					System.out.println("Reflection added");
				}

			}catch (Exception err){}


			entries_vbox.setAlignment(Pos.TOP_CENTER);

			if(k == 1) k += 1;

			prev_moodEntry_date = Main.moodEntry_date.get(i);
			prev_moodEntry_month = Main.moodEntry_month.get(i);


		}



	}







	public void switchToHome(ActionEvent e) throws IOException{
		Parent root = FXMLLoader.load(Cards.class.getResource("FXMLs/Main.fxml"));
		Scene scene_2 = button_home.getScene();
		scene_2.getStylesheets().add(Cards.class.getResource("CSSs/main_style.css").toExternalForm());

		root.translateXProperty().set(scene_2.getHeight());
		StackPane stackParent = (StackPane)scene_2.getRoot();
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


}