import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.util.Scanner;
import java.io.*;

public class HW4 extends Application {
	private Stage program = new Stage();
	
    public static void main(String[] args) {
    	launch(args);
    } 

	@Override
	public void start(Stage arg0) throws Exception {
		program.setTitle("Heart Health Imaging and Recording System");
		mainUI(program);
	}
	
	public void mainUI(Stage program) {
		Button pIntake = new Button("Patient Intake");
		Button CTView = new Button("CT Scan Tech View");
		Button PView = new Button("Patient View");

		pIntake.setStyle("-fx-font-size: 16px; -fx-min-width: 200px; -fx-min-height: 50px; -fx-background-color: lightblue");
		CTView.setStyle("-fx-font-size: 16px; -fx-min-width: 200px; -fx-min-height: 50px; -fx-background-color: lightblue");
		PView.setStyle("-fx-font-size: 16px; -fx-min-width: 200px; -fx-min-height: 50px; -fx-background-color: lightblue");

        pIntake.setOnAction(e -> pIntake(program));
        CTView.setOnAction(e -> CTView(program));
        PView.setOnAction(e -> PLogin(program));
        
        Label title = new Label("Welcome to Heart Health Imaging and Recording System");
        title.setAlignment(Pos.CENTER);
       	title.setStyle("-fx-font-size: 16px; -fx-min-width: 200px; -fx-min-height: 50px;");

        VBox UI = new VBox(40);
        VBox.setVgrow(UI, Priority.ALWAYS);
        
        UI.getChildren().addAll(title, pIntake, CTView, PView);
        UI.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(UI, 800, 500);
        program.setScene(scene);
        program.show();
	}
	
	public void pIntake(Stage program) {
		Label title = new Label("Patient Intake Form");
		
		Label lfName = new Label("First Name:        ");
		TextArea fName = new TextArea();
		fName.setEditable(true);
		fName.setPrefSize(300, 20);
		HBox bfName = new HBox(20);
		bfName.getChildren().addAll(lfName, fName);
        
		Label llName = new Label("Last Name:        ");
		TextArea lName = new TextArea();
		lName.setEditable(true);
		lName.setPrefSize(300, 20);
		HBox blName = new HBox(20);
		blName.getChildren().addAll(llName, lName);
        
		Label lemail = new Label("Email:                ");
		TextArea email = new TextArea();
		email.setEditable(true);
		email.setPrefSize(300, 20);
		HBox bemail = new HBox(20);
		bemail.getChildren().addAll(lemail, email);
        
		Label lpNumber = new Label("Phone Number:");
		TextArea pNumber = new TextArea();
		pNumber.setEditable(true);
		pNumber.setPrefSize(300, 20);
		HBox bpNumber= new HBox(20);
		bpNumber.getChildren().addAll(lpNumber, pNumber);
        
		Label lhHistory = new Label("Health History: ");
		TextArea hHistory = new TextArea();
		hHistory.setEditable(true);
		hHistory.setPrefSize(300, 20);
		HBox bhHistory = new HBox(20);
		bhHistory.getChildren().addAll(lhHistory, hHistory);
        
		Label linsuranceID = new Label("Insurance ID:    ");
		TextArea insuranceID = new TextArea();
		insuranceID.setEditable(true);
		insuranceID.setPrefSize(300, 20);
		HBox binsuranceID = new HBox(20);
		binsuranceID.getChildren().addAll(linsuranceID, insuranceID);

		Button save = new Button("Save");
		HBox bsave = new HBox(save);
		save.setStyle("-fx-background-color: lightblue; -fx-font-size: 16px; -fx-min-width: 100px; -fx-min-height: 50px;");
		bsave.setAlignment(Pos.CENTER_RIGHT);
		
		VBox form = new VBox(20);
		form.setPadding(new Insets(30));
		form.getChildren().addAll(title, bfName, blName, bemail, bpNumber, bhHistory, binsuranceID, bsave);
        VBox.setVgrow(form, Priority.ALWAYS);
        form.setAlignment(Pos.CENTER);

		Scene scene = new Scene(form, 800, 500);
		program.setScene(scene);
		program.show();
		
        fName.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {lName.requestFocus();}});
        lName.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {email.requestFocus();}});
        email.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {pNumber.requestFocus();}});
        pNumber.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {hHistory.requestFocus();}});
        hHistory.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {insuranceID.requestFocus();}});
        insuranceID.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {save.requestFocus();}});   
		
		save.setOnAction(e -> {
			try {
				File f = GenID();
				FileWriter w = new FileWriter(f);
				
				w.write(fName.getText().trim()+" "+lName.getText().trim()+"\n");
				w.write(email.getText().trim()+"\n");
				w.write(pNumber.getText().trim()+"\n");
				w.write(hHistory.getText().trim()+"\n");
				w.write(insuranceID.getText().trim());
				w.close();
			} catch (IOException e1) {e1.printStackTrace();}
			
			mainUI(program);
		});
	}
	
	public void CTView(Stage program) {
		Label lpID = new Label("Patient ID:                                ");
		TextArea pID = new TextArea();
		pID.setEditable(true);
		pID.setPrefSize(300, 20);
		HBox bpID = new HBox(20);
		bpID.getChildren().addAll(lpID, pID);
		
        Label failed = new Label("Missing Required Fields");
        failed.setStyle("-fx-font-size: 12px; -fx-text-fill: red");
        failed.setVisible(false);
        
		Label ltCAC = new Label("The total Agatston CAC score:");
		TextArea tCAC = new TextArea();
		tCAC.setEditable(true);
		tCAC.setPrefSize(300, 20);
		HBox btCAC = new HBox(20);
		btCAC.getChildren().addAll(ltCAC, tCAC);
        
		Label VCAC = new Label("Vessel level Agatston CAC score");
		
		Label lLM = new Label("LM:  ");
		TextArea LM = new TextArea();
		LM.setEditable(true);
		LM.setPrefSize(300, 20);
		HBox bLM = new HBox(20);
		bLM.getChildren().addAll(lLM, LM);
        
		Label lLAD = new Label("LAD: ");
		TextArea LAD = new TextArea();
		LAD.setEditable(true);
		LAD.setPrefSize(300, 20);
		HBox bLAD= new HBox(20);
		bLAD.getChildren().addAll(lLAD, LAD);
        
		Label lLCX = new Label("LCX: ");
		TextArea LCX = new TextArea();
		LCX.setEditable(true);
		LCX.setPrefSize(300, 20);
		HBox bLCX = new HBox(20);
		bLCX.getChildren().addAll(lLCX, LCX);
        
		Label lRCA = new Label("RCA:");
		TextArea RCA = new TextArea();
		RCA.setEditable(true);
		RCA.setPrefSize(300, 20);
		HBox bRCA = new HBox(20);
		bRCA.getChildren().addAll(lRCA, RCA);
		
		Label lPDA = new Label("PDA:");
		TextArea PDA = new TextArea();
		PDA.setEditable(true);
		PDA.setPrefSize(300, 20);
		HBox bPDA = new HBox(20);
		bPDA.getChildren().addAll(lPDA, PDA);

		Button save = new Button("Save");
		HBox bsave = new HBox(save);
		save.setStyle("-fx-background-color: lightblue; -fx-font-size: 16px; -fx-min-width: 100px; -fx-min-height: 50px;");
		bsave.setAlignment(Pos.CENTER_RIGHT);

		VBox form = new VBox(10);
		form.setPadding(new Insets(30));
		form.getChildren().addAll(failed, bpID, btCAC, VCAC, bLM, bLAD, bLCX, bRCA, bPDA, bsave);
        VBox.setVgrow(form, Priority.ALWAYS);        

		Scene scene = new Scene(form, 800, 500);
		program.setScene(scene);
		program.show();
		
        pID.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {tCAC.requestFocus();}});
        tCAC.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {LM.requestFocus();}});
        LM.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {LAD.requestFocus();}});
        LAD.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {LCX.requestFocus();}});
        LCX.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {RCA.requestFocus();}});
        RCA.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {PDA.requestFocus();}});  
        PDA.setOnKeyPressed(event -> {if (event.getCode() == KeyCode.TAB) {save.requestFocus();}});   
		
		save.setOnAction(e -> {
			try {
				if (pID.getText().trim().equals("")||tCAC.getText().trim().equals("")||LM.getText().trim().equals("")||LAD.getText().trim().equals("")||LCX.getText().trim().equals("")||RCA.getText().trim().equals("")||PDA.getText().trim().equals("")) {
					failed.setVisible(true);
				}
				else {
					String fileName = pID.getText().trim()+"CTResults.txt";
					File f = new File("C:\\Users\\taods\\eclipse-workspace\\HW4\\CTResults", fileName);
					f.createNewFile();
					
					FileWriter w = new FileWriter(f);
					
					w.write(pID.getText().trim()+"\n");
					w.write(tCAC.getText().trim()+"\n");
					w.write(LM.getText().trim()+"\n");
					w.write(LAD.getText().trim()+"\n");
					w.write(LCX.getText().trim()+"\n");
					w.write(RCA.getText().trim()+"\n");
					w.write(PDA.getText().trim());
					w.close();
					
					mainUI(program);
				}
			} catch (IOException e1) {e1.printStackTrace();}
		});
	}
	
	public void PLogin(Stage program) {
		Label lpID = new Label("Please Enter Your Patient ID:");
		lpID.setStyle("-fx-font-size: 16px;");
		
		TextArea pID = new TextArea();
		pID.setPromptText("Patient ID");
		pID.setEditable(true);
		pID.setPrefSize(100, 20);
		
		Button login = new Button("Sign In");
		login.setStyle("-fx-background-color: lightblue; -fx-font-size: 16px; -fx-min-width: 100px; -fx-min-height: 50px;");		
		
        Label failed = new Label("Invalid Patient ID");
        failed.setStyle("-fx-font-size: 12px; -fx-text-fill: red");
        failed.setVisible(false);
        
		VBox form = new VBox(20);
		form.setPadding(new Insets(250));
		form.getChildren().addAll(lpID, pID, failed, login);
        VBox.setVgrow(form, Priority.ALWAYS);        
        form.setAlignment(Pos.CENTER);

		Scene scene = new Scene(form, 800, 500);
		program.setScene(scene);
		program.show();
		
        login.setOnAction(e -> {
        	File f = null;
        	try {f = validate(pID.getText().trim());} 
        	catch (Exception e1) {e1.printStackTrace();}

			if (f != null) {
				try {PView(program, f);} 
				catch (Exception e1) {e1.printStackTrace();}
			}
			else failed.setVisible(true);
        });
	}
	
	public void PView(Stage program, File pInfo) throws Exception {
		FileReader r = new FileReader(pInfo);
		BufferedReader br = new BufferedReader(r);		
		String name = br.readLine();
		br.close();
		
		Label Hello = new Label("Hello "+name);
		HBox hHello = new HBox(Hello);
		hHello.setAlignment(Pos.CENTER);
		
		Label unavailable = new Label("CT Results not available");
		unavailable.setStyle("-fx-font-size: 12px; -fx-text-fill: red");
		unavailable.setVisible(false);

		Label ltCAC = new Label("The total Agatston CAC score:");
		TextArea tCAC = new TextArea();
		tCAC.setEditable(false);
		tCAC.setPrefSize(300, 20);
		HBox btCAC = new HBox(20);
		btCAC.getChildren().addAll(ltCAC, tCAC);
		
		Label lLM = new Label("LM:  ");
		TextArea LM = new TextArea();
		LM.setEditable(false);
		LM.setPrefSize(300, 20);
		HBox bLM = new HBox(20);
		bLM.getChildren().addAll(lLM, LM);
        
		Label lLAD = new Label("LAD: ");
		TextArea LAD = new TextArea();
		LAD.setEditable(false);
		LAD.setPrefSize(300, 20);
		HBox bLAD= new HBox(20);
		bLAD.getChildren().addAll(lLAD, LAD);
        
		Label lLCX = new Label("LCX: ");
		TextArea LCX = new TextArea();
		LCX.setEditable(false);
		LCX.setPrefSize(300, 20);
		HBox bLCX = new HBox(20);
		bLCX.getChildren().addAll(lLCX, LCX);
        
		Label lRCA = new Label("RCA:");
		TextArea RCA = new TextArea();
		RCA.setEditable(false);
		RCA.setPrefSize(300, 20);
		HBox bRCA = new HBox(20);
		bRCA.getChildren().addAll(lRCA, RCA);
		
		Label lPDA = new Label("PDA:");
		TextArea PDA = new TextArea();
		PDA.setEditable(false);
		PDA.setPrefSize(300, 20);
		HBox bPDA = new HBox(20);
		bPDA.getChildren().addAll(lPDA, PDA);

		VBox form = new VBox(15);
		form.setPadding(new Insets(30));
		form.getChildren().addAll(hHello, unavailable, btCAC, bLM, bLAD, bLCX, bRCA, bPDA);
        VBox.setVgrow(form, Priority.ALWAYS);        

		String fileName = pInfo.getName().substring(0,5)+"CTResults.txt";
		File f = new File("C:\\Users\\taods\\eclipse-workspace\\HW4\\CTResults", fileName);
		System.out.print(f.getName());
		if (f.exists()) {
			r = new FileReader(f);
			br = new BufferedReader(r);		
			br.readLine();
			
			tCAC.setText(br.readLine());
			LM.setText(br.readLine());
			LAD.setText(br.readLine());
			LCX.setText(br.readLine());
			RCA.setText(br.readLine());
			PDA.setText(br.readLine());			
		}
		else {
			unavailable.setVisible(true);}        
        
		Scene scene = new Scene(form, 800, 500);
		program.setScene(scene);
		program.show();

		
		
		
	}//unfinished
	
	public File GenID() throws IOException {
		int ID = (int) (Math.random()*90000)+10000;
		String fileName = ID+"_PatientInfo.txt";
		File f = new File("C:\\Users\\taods\\eclipse-workspace\\HW4\\PatientInfo", fileName);
		
		while (f.exists()) {
			ID = (int) (Math.random()*90000)+10000;
			fileName = ID+"_PatientInfo.txt";
			f = new File("C:\\Users\\taods\\eclipse-workspace\\HW4\\PatientInfo", fileName);
		}
	
		f.createNewFile();
		System.out.print(ID);
		return f;
	}
	
	public File validate(String ID) throws Exception {
		String fileName = ID+"_PatientInfo.txt";
		File f = new File("C:\\Users\\taods\\eclipse-workspace\\HW4\\PatientInfo", fileName);
		
		if (f.exists()) return f;
		return null;
	}
}