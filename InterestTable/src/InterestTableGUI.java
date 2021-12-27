
import javafx.scene.control.TextArea;
import javafx.application.*;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InterestTableGUI extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		int sceneWidth = 700;
		int sceneHeight = 600;

		// Principal and Rate(Percentage) Fields
		FlowPane inputWindows = new FlowPane();
		inputWindows.setHgap(10);

		Label principalLabel = new Label("Principal: ");
		Label ratePercentageLabel = new Label("Rate: ");
		TextField principalField = new TextField();
		TextField ratePercentage = new TextField();
		
		// Input Windows labels
		inputWindows.getChildren().add(principalLabel);
		inputWindows.getChildren().add(principalField);
		inputWindows.getChildren().add(ratePercentageLabel);
		inputWindows.getChildren().add(ratePercentage);

		inputWindows.setAlignment(Pos.CENTER);
		
		// Text Area
		TextArea displayArea = new TextArea();
		displayArea.setWrapText(true);

		
		// Number of years horizontal slider
		FlowPane slider = new FlowPane();
		
		Slider horizontalSlider = new Slider();	
		horizontalSlider.setMin(0);
		horizontalSlider.setMax(25);
		horizontalSlider.setValue(25);
		horizontalSlider.setMajorTickUnit(1);
		horizontalSlider.setShowTickMarks(true);
		horizontalSlider.setShowTickLabels(true);
		Label sliderLabel = new Label("Number of Years: ");
		horizontalSlider.setPrefWidth(450.0);

		slider.getChildren().add(sliderLabel);
		slider.getChildren().add(horizontalSlider);
		slider.setAlignment(Pos.CENTER);
				
		// Bottom Buttons
		FlowPane buttonFormat = new FlowPane();
		buttonFormat.setHgap(6);
		buttonFormat.setVgap(6);
		buttonFormat.setAlignment(Pos.BOTTOM_CENTER);
		Button simpleInterest = new Button("Simple Interest");
		Button compoundInterest = new Button("Compound Interest");
		Button bothInterests = new Button("Both Interests");
		buttonFormat.getChildren().addAll(simpleInterest, compoundInterest, bothInterests);

		
		// Overall Structure
		VBox ovrStructure = new VBox(8);
		ovrStructure.getChildren().addAll(displayArea, inputWindows, slider, buttonFormat);

		// Controller

		// inner class
		class SimpleInterestEventHandler implements EventHandler<ActionEvent> {

			@Override
			public void handle(ActionEvent event) {
				int sliderValue = (int) horizontalSlider.getValue();
				displayArea.setText(Interest.format(Double.parseDouble(principalField.getText()),
						Double.parseDouble(ratePercentage.getText()), sliderValue, 1));
			}

		}

		simpleInterest.setOnAction(new SimpleInterestEventHandler());

		// anonymous class

		compoundInterest.setOnAction(new EventHandler<ActionEvent>() {
			@Override

			public void handle(ActionEvent event) {

				int sliderValue = (int) horizontalSlider.getValue();

				displayArea.setText(Interest.format(Double.parseDouble(principalField.getText()),
						Double.parseDouble(ratePercentage.getText()), sliderValue, 2));

			}
		});

		// Lambda functions

		bothInterests.setOnAction(

				e -> {
					displayArea.setText(Interest.format(Double.parseDouble(principalField.getText()),
							Double.parseDouble(ratePercentage.getText()), (int) horizontalSlider.getValue(), 3));
				}

		);

		Scene scene = new Scene(ovrStructure, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
