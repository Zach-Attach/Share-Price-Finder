import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Zachary Laborde on 11/2/16.
 */
public class mainClass extends Application {

	public static void main(String[] args) throws IOException {
		launch(args);
		System.exit(0);
	}

	@Override
	public void start(Stage primaryStage) {

		StockMap stockMap = new StockMap();

		Label lbl0 = new Label("Input Stock Symbol: ");

		Label lbl1 = new Label("Symbol");
		lbl1.setMaxWidth(Double.MAX_VALUE);
		lbl1.setAlignment(Pos.CENTER);

		Label lbl2 = new Label("Price");
		lbl2.setMaxWidth(Double.MAX_VALUE);
		lbl2.setAlignment(Pos.CENTER);

		TextField txt = new TextField();


		ObservableList<String> listKeys = FXCollections.observableArrayList(stockMap.toMap().keySet());
		ListView<String> listViewKeys = new ListView<>(listKeys);

		ObservableList<Double> listVals = FXCollections.observableArrayList(stockMap.toMap().values());
		ListView<Double> listViewVals = new ListView<>(listVals);

		Button btn = new Button("Enter");
		btn.setMaxWidth(Double.MAX_VALUE);
		btn.setAlignment(Pos.CENTER);


		GridPane pane = new GridPane();
		pane.add(lbl0, 0, 0, 1, 1);
		pane.add(lbl1, 0, 1, 1, 1);
		pane.add(lbl2, 1, 1, 1, 1);
		pane.add(txt, 1, 0, 1, 1);
		pane.add(listViewKeys, 0, 2, 1, 2);
		pane.add(listViewVals, 1, 2, 1, 2);
		pane.add(btn, 0, 4, 2, 1);

		pane.getChildren().forEach(x -> {
			pane.setHgrow(x, Priority.ALWAYS);
			pane.setVgrow(x, Priority.ALWAYS);
		});

		btn.setOnAction(x -> {
			stockMap.put(new Stock(txt.getText().toUpperCase()));
			txt.clear();
			ObservableList<String> a = FXCollections.observableArrayList(stockMap.toMap().keySet());
			FXCollections.reverse(a);
			listViewKeys.setItems(a);

			ObservableList<Double> y = FXCollections.observableArrayList(stockMap.toMap().values());
			FXCollections.reverse(y);
			listViewVals.setItems(y);

			primaryStage.show();
		});

		primaryStage.setMaxHeight(Double.MAX_VALUE);
		primaryStage.setMaxWidth(Double.MAX_VALUE);

		primaryStage.setScene(new Scene(pane));

		primaryStage.show();
	}
}
