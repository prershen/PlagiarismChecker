package packagejavafx;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author perus
 */
import java.io.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.*;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.paint.Color;

public class Javafxdemo extends Application{
 
    Scene scene1,scene2,scene3;
    private Text actionStatus;
    File selectedFile;
    TextArea text1,text2;
    public static void main(String[] args){
        System.out.println("LAunching the application: ");
        launch(args);
    }
    
    public void start(Stage s) throws Exception{
        
        s.setTitle("FIrst demo");
        //first scene
        FlowPane layout1=new FlowPane(10,10);
        layout1.setAlignment(Pos.CENTER);
        
        Label mylabel=new Label("Welcome to Plagiarism checker!\n");
        Label mylabel1=new Label("Choose one\n");
        Button button1=new Button("Text");
        Button button2=new Button("Document(txt)");
        
        button1.setOnAction(e ->s.setScene(scene2) );
        button2.setOnAction(e-> s.setScene(scene3));
        
        layout1.getChildren().addAll(mylabel,mylabel1,button1,button2);
        scene1=new Scene(layout1,300,200);
        
        //second scene
        VBox layout2=new VBox(10);
        layout2.setAlignment(Pos.CENTER);
        
        Label mylabel2=new Label("Enter text to be compared: ");
        text1=new TextArea("Enter...");
        ScrollPane srl1=new ScrollPane(text1);
        srl1.setPannable(true);
        text2=new TextArea("Enter...");
        ScrollPane srl2=new ScrollPane(text2);
        srl2.setPannable(true);
        
        Button submit1=new Button("SUBMIT");
        Button back1=new Button("BACK");
        
        back1.setOnAction(e->s.setScene(scene1));
        EventHandler<ActionEvent> buttonHandler1 = (ActionEvent event) -> {
            TextButtonListener t = new TextButtonListener(1);
            TextButtonListener t1 = new TextButtonListener(2);
            t.handle(event);
            t1.handle(event);
            
            stopwords_removal.stopwords(1);
            ngramnew.ngram(1);
            stopwords_removal.stopwords(2);
            ngramnew.ngram(2);
        };
        submit1.setOnAction(buttonHandler1);
        layout2.getChildren().addAll(mylabel,mylabel2,text1,text2,submit1,back1);
        scene2=new Scene(layout2,400,350);
        
        //third scene
        VBox layout3=new VBox(10);
        layout3.setAlignment(Pos.CENTER);
        
        Label mylabel3=new Label("Upload text documents\n");
        Button button4=new Button("Upload Doc1");
        Button button5=new Button("Upload Doc2");
        
         actionStatus = new Text();
	 actionStatus.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
	 actionStatus.setFill(Color.FIREBRICK);
         
         button4.setOnAction(new Javafxdemo.SingleFcButtonListener(1));
        button5.setOnAction(new Javafxdemo.SingleFcButtonListener(2));
        
        Button submit2=new Button("SUBMIT");
        Button back2=new Button("BACK");
        
        back2.setOnAction(e->s.setScene(scene1));
        
        EventHandler<ActionEvent> buttonHandler2 = new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        stopwords_removal.stopwords(1);
        ngramnew.ngram(1);
        stopwords_removal.stopwords(2);
        ngramnew.ngram(2);
    }
};
        submit2.setOnAction(buttonHandler2);
        layout3.getChildren().addAll(mylabel,mylabel3,button4,button5,actionStatus,submit2,back2);
        scene3=new Scene(layout3,500,500);
        
        s.setScene(scene1);
        s.show();
    }
    private class TextButtonListener implements EventHandler<ActionEvent>{
        int n;
        TextButtonListener(int n){
            this.n=n;
            //handle(new ActionEvent());
        }
        @Override public void handle(ActionEvent e){
               String filestr=" ";
               try{
               PrintWriter writer=new PrintWriter("F:\\RVCE\\DAA\\Javafxdemo\\src\\data\\textdoc"+n+".txt");
               writer.print("");
               writer.close();
               FileWriter fw=new FileWriter("F:\\RVCE\\DAA\\Javafxdemo\\src\\data\\textdoc"+n+".txt",false);
               System.out.println("IN HANDLE");
               if(n==1)
                   filestr=text1.getText();
               else
                   filestr=text2.getText();
               System.out.println(text1.getText()+text2.getText());
               fw.write(filestr);
               fw.close();
               }
               catch(Exception ex)
            {
                actionStatus.setText("Something went wrong");
            }
        }
    }
    private class SingleFcButtonListener implements EventHandler<ActionEvent> {
        int n;        
        SingleFcButtonListener(int n)
                {
                  this.n=n;
                }
		@Override
		public void handle(ActionEvent e) {

			showSingleFileChooser(n);
		}
    }

    private void showSingleFileChooser(int n) {
	String filestr=" ";
	FileChooser fileChooser = new FileChooser();
        
        //set Extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
	
        selectedFile = fileChooser.showOpenDialog(null);
        
	if (selectedFile != null) {
            int i;
            actionStatus.setText(actionStatus.getText()+"\nFile selected for doc"+n+": " + selectedFile.getName());
            try{
            
               filestr=new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
            
            /*ScrollPane srl=new ScrollPane(actionStatus);
         srl.setPrefViewportHeight(100);
         srl.setPrefViewportWidth(100);
         srl.setPannable(true);
         actionStatus.setText(actionStatus.getText() + "\nFile"+n+": "+filestr);
	*/
               PrintWriter writer=new PrintWriter("F:\\RVCE\\DAA\\Javafxdemo\\src\\data\\textdoc"+n+".txt");
               writer.print("");
               writer.close();
               FileWriter fw=new FileWriter("F:\\RVCE\\DAA\\Javafxdemo\\src\\data\\textdoc"+n+".txt",false);
               
               fw.write(filestr);
               fw.close();
            }catch(Exception e)
            {
                actionStatus.setText("Something went wrong");
            }
        }
	else {
            actionStatus.setText("File selection cancelled.");
	}
    }
}
