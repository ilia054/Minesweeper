package simpleFx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MyController {
	private int i=0;

	@FXML
	private Button button1;

	@FXML
	private Button button2;

	@FXML
	private Label vote;

	@FXML
	void press1(ActionEvent event) {//increase by 1
		i++;
		vote.setText(""+i);

	}

	@FXML
	void press2(ActionEvent event) {//decrease by one
		i--;
		vote.setText(""+i);
	}

}
