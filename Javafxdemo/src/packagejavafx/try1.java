/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagejavafx;

/**
 *
 * @author perus
 */
import java.io.File;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.*;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class try1 extends Application{
    private Text actionStatus; 
    public static void main(String[] args)
     {
         launch(args);
     }
     public void start(Stage s)
     {
         s.setTitle("Upload");
         Label l=new Label("Upload a text document");
         VBox layout=new VBox(5);
         layout.setAlignment(Pos.CENTER);
         
         Button upload=new Button("UPLOAD");
         actionStatus = new Text();
	 actionStatus.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
	 actionStatus.setFill(Color.FIREBRICK);
         
         s.setScene(new Scene(layout,300,300));
         layout.getChildren().addAll(l,upload,actionStatus);
         
         upload.setOnAction(new SingleFcButtonListener());
         
         s.show();
         
     }
     private class SingleFcButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {

			showSingleFileChooser();
		}
	}

	private void showSingleFileChooser() {
	
		FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
		File selectedFile = fileChooser.showOpenDialog(null);
                
		if (selectedFile != null) {

			actionStatus.setText("File selected: " + selectedFile.getName());
                        String filestr=selectedFile.toString();
                        actionStatus.setText(filestr);
		}
		else {
			actionStatus.setText("File selection cancelled.");
		}
	}
}
