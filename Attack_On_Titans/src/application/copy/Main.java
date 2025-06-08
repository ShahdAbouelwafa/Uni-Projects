package application.copy;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;

public class Main extends Application implements EventHandler<ActionEvent> {
	private Stage startStage;
	private Battle battle;
	private AnchorPane ap;
	private Label errorMsg;
    private Button Return;
	private Scene Startscene;
	private Button start;
	private Button Instructions;
	private Stage errorStage;
	private Button backE;
	private Button backY;
	
	private Scene levelscene;
	private Button easy;
	private Button hard;
	private Button backH;
	private Button backS;
	
	private Scene easyscene;
	private Label Score;
	private Label Turn;
	private Label Phase;
	private Label Resources;
	private Button weaponshop;
	private Button passturn;
	private VBox vbox1;
	private VBox vbox2;
	private VBox vbox3;
	private Pane [] lanes;
	private Label [] labels;
	private Button back;

   
	private Scene wsscene;
	private RadioButton pc;
	private RadioButton sc;
	private RadioButton vc;
	private RadioButton wt;
	
	private Scene contscene;
	private Button cont;
	
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b1h;
	private Button b2h;
	private Button b3h;
	private Button b4;
	private Button b5;
	
	private Button pcw;
	private Button scw;
	private Button  vcw;
	private Button wtw;
	
	private Button abt;
	private Button art;
	private Button pt;
	private Button ct;
	
	private Scene hardscene;
	private Button weaponshopH;
	private Scene wssceneH;
	private Button contH;
	private Scene contsceneH;
	private VBox vbox4;
	private VBox vbox5;
	private Scene instructionScene;

	@Override
public void start(Stage startStage) {
		this.startStage =startStage;
		ap = new AnchorPane();
		Startscene = new Scene (ap,500,400);
		startStage.setScene(Startscene);
		startStage.show();
		start = new Button ("Start");
		ap.setTopAnchor(start, 50.0);
		ap.setRightAnchor(start, 250.0);
		Instructions= new Button ("Instructions");
		ap.setTopAnchor(Instructions, 100.0);
		ap.setRightAnchor(Instructions, 250.0);
		start.setOnAction(this);
		Instructions.setOnAction(this);
		ap.getChildren().addAll(start,Instructions);	  
}
	

	@Override
public void handle(ActionEvent event) {
		if (event.getSource()==start){
			ap = new AnchorPane();
			levelscene = new Scene (ap,500,400);
			levelscene();
			startStage.setScene(levelscene);
			startStage.show();
			
		}
		else if (event.getSource()==Instructions){
			ap = new AnchorPane();
			instructionScene = new Scene (ap,500,400);
			instructionScene();
			startStage.setScene(instructionScene);
			startStage.show();
		}
		else if (event.getSource()==easy){
			ap = new AnchorPane();
			easyscene = new Scene (ap,600,500);
			easyscene();
			startStage.setScene(easyscene);
			startStage.show();
		
		}
		else if (event.getSource()==hard){
			ap = new AnchorPane();
			hardscene = new Scene (ap,600,650);
			hardscene();
			startStage.setScene(hardscene);
			startStage.show();
		}
		else if (event.getSource()==weaponshop){
			ap = new AnchorPane();
			wsscene = new Scene (ap,700,400);
			wsscene();
			startStage.setScene(wsscene);
			startStage.show();
		}
		else if (event.getSource()==cont){
			ap = new AnchorPane();
			contscene = new Scene (ap,500,400);
			contscene();
			startStage.setScene(contscene);
			startStage.show();
		}
		else if (event.getSource()==b1){
			if (pc.isSelected()){
				pcw = new Button ("pc");
				vbox1.getChildren().add(pcw);
				try {
					battle.purchaseWeapon(1, battle.getOriginalLanes().get(0));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage()); 
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
		
			 }
			
			else if (sc.isSelected()){
				scw = new Button ("cw");
				vbox1.getChildren().add(scw);
				try {
					battle.purchaseWeapon(2, battle.getOriginalLanes().get(0));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());				}
			}
			else if (vc.isSelected()){
				vcw = new Button ("vw");
				vbox1.getChildren().add(vcw);
				try {
					battle.purchaseWeapon(3, battle.getOriginalLanes().get(0));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
					} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
						showErrorStage ( e.getMessage());
				}
				
			}
			else if (wt.isSelected()){
				wtw = new Button ("wt");
				vbox1.getChildren().add(wtw);
				try {
					battle.purchaseWeapon(4, battle.getOriginalLanes().get(0));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			addTitan ();
			updatelanevalues();
			updatebattlevalues();
			startStage.setScene(easyscene);
			startStage.show();
			
		}
		else if (event.getSource()==b2){
			if (pc.isSelected()){
				pcw = new Button ("pc");
				vbox2.getChildren().add(pcw);
				try {
					battle.purchaseWeapon(1, battle.getOriginalLanes().get(1));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
		
			 }
			
			else if (sc.isSelected()){
				scw = new Button ("cw");
				vbox2.getChildren().add(scw);
				try {
					battle.purchaseWeapon(2, battle.getOriginalLanes().get(1));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			else if (vc.isSelected()){
				vcw = new Button ("vw");
				vbox2.getChildren().add(vcw);
				try {
					battle.purchaseWeapon(3, battle.getOriginalLanes().get(1));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
				
			}
			else if (wt.isSelected()){
				wtw = new Button ("wt");
				vbox2.getChildren().add(wtw);
				try {
					battle.purchaseWeapon(4, battle.getOriginalLanes().get(1));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			addTitan ();
			updatelanevalues();
			updatebattlevalues();
			
			startStage.setScene(easyscene);
			startStage.show();
			
		}
		else if (event.getSource()==b3){
			if (pc.isSelected()){
				pcw = new Button ("pc");
				vbox3.getChildren().add(pcw);
				try {
					battle.purchaseWeapon(1, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
		
			 }
			
			else if (sc.isSelected()){
				scw = new Button ("cw");
				vbox3.getChildren().add(scw);
				try {
					battle.purchaseWeapon(2, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			else if (vc.isSelected()){
				vcw = new Button ("vw");
				vbox3.getChildren().add(vcw);
				try {
					battle.purchaseWeapon(3, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
				
			}
			else if (wt.isSelected()){
				wtw = new Button ("wt");
				vbox3.getChildren().add(wtw);
				try {
					battle.purchaseWeapon(4, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			addTitan ();
			updatelanevalues();
			updatebattlevalues();
			startStage.setScene(easyscene);
			startStage.show();
			
		}
		else if (event.getSource()==b4){
			if (pc.isSelected()){
				pcw = new Button ("pc");
				vbox4.getChildren().add(pcw);
				try {
					battle.purchaseWeapon(1, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
		
			 }
			
			else if (sc.isSelected()){
				scw = new Button ("cw");
				vbox4.getChildren().add(scw);
				try {
					battle.purchaseWeapon(2, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			else if (vc.isSelected()){
				vcw = new Button ("vw");
				vbox4.getChildren().add(vcw);
				try {
					battle.purchaseWeapon(3, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
				
			}
			else if (wt.isSelected()){
				wtw = new Button ("wt");
				vbox4.getChildren().add(wtw);
				try {
					battle.purchaseWeapon(4, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			addTitan ();
			updatelanevalues();
			updatebattlevalues();
			startStage.setScene(hardscene);
			startStage.show();
			
		}
		else if (event.getSource()==b5){
			if (pc.isSelected()){
				pcw = new Button ("pc");
				vbox5.getChildren().add(pcw);
				try {
					battle.purchaseWeapon(1, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
		
			 }
			
			else if (sc.isSelected()){
				scw = new Button ("cw");
				vbox5.getChildren().add(scw);
				try {
					battle.purchaseWeapon(2, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			else if (vc.isSelected()){
				vcw = new Button ("vw");
				vbox5.getChildren().add(vcw);
				try {
					battle.purchaseWeapon(3, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
				
			}
			else if (wt.isSelected()){
				wtw = new Button ("wt");
				vbox5.getChildren().add(wtw);
				try {
					battle.purchaseWeapon(4, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			addTitan ();
			updatelanevalues();
			updatebattlevalues();
			startStage.setScene(hardscene);
			startStage.show();
			
		}
		else if (event.getSource()==b1h){
			if (pc.isSelected()){
				pcw = new Button ("pc");
				vbox1.getChildren().add(pcw);
				try {
					battle.purchaseWeapon(1, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
		
			 }
			
			else if (sc.isSelected()){
				scw = new Button ("cw");
				vbox1.getChildren().add(scw);
				try {
					battle.purchaseWeapon(2, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			else if (vc.isSelected()){
				vcw = new Button ("vw");
				vbox1.getChildren().add(vcw);
				try {
					battle.purchaseWeapon(3, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
				
			}
			else if (wt.isSelected()){
				wtw = new Button ("wt");
				vbox5.getChildren().add(wtw);
				try {
					battle.purchaseWeapon(4, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			addTitan ();
			updatelanevalues();
			updatebattlevalues();
			startStage.setScene(hardscene);
			startStage.show();
			
		}
		else if (event.getSource()==b2h){
			if (pc.isSelected()){
				pcw = new Button ("pc");
				vbox2.getChildren().add(pcw);
				try {
					battle.purchaseWeapon(1, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
		
			 }
			
			else if (sc.isSelected()){
				scw = new Button ("cw");
				vbox2.getChildren().add(scw);
				try {
					battle.purchaseWeapon(2, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			else if (vc.isSelected()){
				vcw = new Button ("vw");
				vbox2.getChildren().add(vcw);
				try {
					battle.purchaseWeapon(3, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
				
			}
			else if (wt.isSelected()){
				wtw = new Button ("wt");
				vbox2.getChildren().add(wtw);
				try {
					battle.purchaseWeapon(4, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			addTitan ();
			updatelanevalues();
			updatebattlevalues();
			startStage.setScene(hardscene);
			startStage.show();
			
		}
		else if (event.getSource()==b3h){
			if (pc.isSelected()){
				pcw = new Button ("pc");
				vbox3.getChildren().add(pcw);
				try {
					battle.purchaseWeapon(1, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
		
			 }
			
			else if (sc.isSelected()){
				scw = new Button ("cw");
				vbox3.getChildren().add(scw);
				try {
					battle.purchaseWeapon(2, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			else if (vc.isSelected()){
				vcw = new Button ("vw");
				vbox3.getChildren().add(vcw);
				try {
					battle.purchaseWeapon(3, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
				
			}
			else if (wt.isSelected()){
				wtw = new Button ("wt");
				vbox3.getChildren().add(wtw);
				try {
					battle.purchaseWeapon(4, battle.getOriginalLanes().get(2));
				} catch (InsufficientResourcesException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				} catch (InvalidLaneException e) {
					// TODO Auto-generated catch block
					showErrorStage ( e.getMessage());
				}
			}
			addTitan ();
			updatelanevalues();
			updatebattlevalues();
			startStage.setScene(hardscene);
			startStage.show();
			
		}
		else if (event.getSource()==passturn){
			if (battle.isGameOver()){
				showErrorStage("Game Over");
				
				}
			else {
			battle.passTurn();
			addTitan ();
			updatelanevalues();
			updatebattlevalues();
//			startStage.setScene(easyscene);
//			startStage.show();
			}
		}
		else if (event.getSource()==weaponshopH){
			ap = new AnchorPane();
			wssceneH = new Scene (ap,700,400);
			wssceneH();
			startStage.setScene(wssceneH);
			startStage.show();
		}
		else if (event.getSource()==contH){
			ap = new AnchorPane();
			contsceneH = new Scene (ap,500,400);
			contsceneH();
			startStage.setScene(contsceneH);
			startStage.show();
			}
		else if (event.getSource()==Return){
			startStage.setScene(Startscene);
			startStage.show();
		
			
		}
		else if (event.getSource()== back){
			startStage.setScene(easyscene);
			startStage.show();
		}
		else if (event.getSource()== backH){
			startStage.setScene(hardscene);
			startStage.show();
		}
		else if (event.getSource()==backS){
			startStage.setScene(Startscene);
			startStage.show();
		}
		else if (event.getSource()==backE){
			startStage.setScene(wsscene);
			startStage.show();
		}
		else if (event.getSource()==backY){
			startStage.setScene(wssceneH);
			startStage.show();
		}
		
	}
	
public void levelscene(){
		 easy = new Button ("Easy");
		 hard = new Button ("Hard");
		 ap.setTopAnchor(easy, 50.0);
		 ap.setRightAnchor(easy, 250.0);
		 ap.setTopAnchor(hard, 90.0);
		 ap.setRightAnchor( hard, 250.0);
		 ap.getChildren().addAll(easy, hard);
		 easy.setOnAction(this);
		 hard.setOnAction(this);
	}
	
public void wsscene(){
		ToggleGroup tg = new ToggleGroup();
		 pc = new RadioButton ("Piercing Canon");
		 pc.setLayoutX(40);
		 pc.setLayoutY(107);
		 pc.setPrefHeight(42);
		 pc.setPrefWidth(151);
		sc = new RadioButton ("Snipper Canon");
		sc.setLayoutX(40);
		 sc.setLayoutY(159);
		 sc.setPrefHeight(42);
		 sc.setPrefWidth(151);
		 vc = new RadioButton ("VolleySpread Canon");
		 vc.setLayoutX(38);
		 vc.setLayoutY(214);
		 vc.setPrefHeight(42);
		 vc.setPrefWidth(180);
		wt = new RadioButton ("Wall Trap");
		wt.setLayoutX(41);
		 wt.setLayoutY(269);
		 wt.setPrefHeight(42);
		 wt.setPrefWidth(151);
		cont= new Button ("continue");
		cont.setLayoutX(175);
		 cont.setLayoutY(327);
		 cont.setPrefHeight(42);
		 cont.setPrefWidth(151);
		pc.setToggleGroup(tg);
		sc.setToggleGroup(tg);
		vc.setToggleGroup(tg);
		wt.setToggleGroup(tg);
		Label Price = new Label ("Price");
		Price.setLayoutX(232);
		Price.setLayoutY(54);
		Price.setPrefHeight(42);
		Price.setPrefWidth(55);	
		Label P1 = new Label ("25");
		P1.setLayoutX(232);
		P1.setLayoutY(109);
		P1.setPrefHeight(42);
		P1.setPrefWidth(55);
		Label P2 = new Label ("25");
		P2.setLayoutX(232);
		P2.setLayoutY(159);
		P2.setPrefHeight(42);
		P2.setPrefWidth(55);
		Label P3 = new Label ("100");
		P3.setLayoutX(232);
		P3.setLayoutY(214);
		P3.setPrefHeight(42);
		P3.setPrefWidth(55);
		Label P4 = new Label ("75");
		P4.setLayoutX(232);
		P4.setLayoutY(269);
		P4.setPrefHeight(42);
		P4.setPrefWidth(55);
		Label Damage = new Label ("Damage");
		Damage.setLayoutX(318);
		Damage.setLayoutY(54);
		Damage.setPrefHeight(42);
		Damage.setPrefWidth(82);
		Label D1 = new Label ("10");
		D1.setLayoutX(314);
		D1.setLayoutY(114);
		D1.setPrefHeight(42);
		D1.setPrefWidth(55);
		Label D2 = new Label ("35");
		D2.setLayoutX(314);
		D2.setLayoutY(166);
		D2.setPrefHeight(42);
		D2.setPrefWidth(55);
		Label D3 = new Label ("5");
		D3.setLayoutX(314);
		D3.setLayoutY(221);
		D3.setPrefHeight(42);
		D3.setPrefWidth(55);
		Label D4 = new Label ("100");
		D4.setLayoutX(314);
		D4.setLayoutY(276);
		D4.setPrefHeight(42);
		D4.setPrefWidth(55);
		Label Name = new Label ("Name");
		Name.setLayoutX(478);
		Name.setLayoutY(54);
		Name.setPrefHeight(42);
		Name.setPrefWidth(82);
		Label N1 = new Label ("Anti-Titan Shell");
		N1.setLayoutX(429);
		N1.setLayoutY(107);
		N1.setPrefHeight(42);
		N1.setPrefWidth(211);
		Label N2 = new Label ("Long Range Spear");
		N2.setLayoutX(429);
		N2.setLayoutY(159);
		N2.setPrefHeight(42);
		N2.setPrefWidth(211);
		Label N3 = new Label ("WallSpread Cannon");
		N3.setLayoutX(429);
		N3.setLayoutY(214);
		N3.setPrefHeight(42);
		N3.setPrefWidth(211);
		Label N4 = new Label ("Proximity Trap");
		N4.setLayoutX(429);
		N4.setLayoutY(269);
		N4.setPrefHeight(42);
		N4.setPrefWidth(211);
		back = new Button ("Back");
		back.setLayoutX(587);
		back.setLayoutY(14);
		back.setPrefHeight(42);
		back.setPrefWidth(99);
		
		ap.getChildren().addAll(pc,sc,vc,wt,cont,Price,P1,P2,P3,P4,Damage, D1, D2, D3, D4, Name, N1, N2, N3, N4,back );
		cont.setOnAction(this);
		back.setOnAction(this);
	}

public void contscene(){
	    backE = new Button ("Back");
	    backE.setLayoutX(206);
	    backE.setLayoutY(326);
	    backE.setPrefHeight(38);
	    backE.setPrefWidth(89);
		b1 = new Button ("lane 1");
		AnchorPane.setTopAnchor(b1, 70.0);
	    AnchorPane.setLeftAnchor(b1, 20.0);
		b2 = new Button ("lane 2");
		AnchorPane.setTopAnchor(b2, 100.0);
	    AnchorPane.setLeftAnchor(b2, 20.0);
		b3 = new Button ("lane 3");
		AnchorPane.setTopAnchor(b3, 130.0);
	    AnchorPane.setLeftAnchor(b3, 20.0);
	    if (!battle.getOriginalLanes().get(0).isLaneLost())
	    	ap.getChildren().add(b1);
	    if (!battle.getOriginalLanes().get(1).isLaneLost())	
	    	ap.getChildren().add(b2);
	    if (!battle.getOriginalLanes().get(2).isLaneLost())	
	    	ap.getChildren().add(b3);
	    ap.getChildren().add(backE);
	    backE.setOnAction(this);
	    b1.setOnAction(this);
	    b2.setOnAction(this);
	    b3.setOnAction(this);	
	}

public void hardscene(){
	try {
		battle = new Battle (1,0,250,5,125);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 weaponshopH = new Button ("Weapon shop");
	 weaponshopH.setLayoutX(24);
	 weaponshopH.setLayoutY(29);
	 weaponshopH.setPrefHeight(36);
	 weaponshopH.setPrefWidth(166);
     passturn = new Button ("Pass Turn");
     passturn.setLayoutX(361);
     passturn.setLayoutY(29);
     passturn.setPrefHeight(36);
	 passturn.setPrefWidth(122);
	 Score = new Label("Current score:"+battle.getScore());
	  Score.setLayoutX(24);
	  Score.setLayoutY(65);
	  Score.setPrefHeight(26);
	  Score.setPrefWidth(166);
	 Turn = new Label("Current turn:"+battle.getNumberOfTurns());
	  Turn.setLayoutX(24);
	  Turn.setLayoutY(91);
	  Turn.setPrefHeight(26);
	  Turn.setPrefWidth(166);
	 Phase = new Label("Current phase:"+battle.getBattlePhase());
	  Phase.setLayoutX(24);
	  Phase.setLayoutY(117);
	  Phase.setPrefHeight(26);
	  Phase.setPrefWidth(200);
	 Resources = new Label("Current resources:"+battle.getResourcesGathered());
	 Resources.setLayoutX(24);
	 Resources.setLayoutY(143);
	 Resources.setPrefHeight(26);
	 Resources.setPrefWidth(166);
	   vbox1 = new VBox();
		 vbox1.setLayoutX(151);
		 vbox1.setLayoutY(175);
		 vbox1.setPrefHeight(62);
		 vbox1.setPrefWidth(79);
		 vbox2 = new VBox();
		 vbox2.setLayoutX(151);
		 vbox2.setLayoutY(248);
		 vbox2.setPrefHeight(62);
		 vbox2.setPrefWidth(79);
		 vbox3 = new VBox();
		 vbox3.setLayoutX(151);
		 vbox3.setLayoutY(324);
		 vbox3.setPrefHeight(62);
		 vbox3.setPrefWidth(79);
		 vbox4= new VBox();
		 vbox4.setLayoutX(151);
		 vbox4.setLayoutY(400);
		 vbox4.setPrefHeight(62);
		 vbox4.setPrefWidth(79);
		 vbox5 = new VBox();
		 vbox5.setLayoutX(151);
		 vbox5.setLayoutY(476);
		 vbox5.setPrefHeight(62);
		 vbox5.setPrefWidth(79);
		 lanes = new Pane[5];
		    for (int i = 0; i < lanes.length; i++) 
		        lanes[i] = new Pane();
		 lanes[0].setLayoutX(236);
		 lanes[0].setLayoutY(176);
		 lanes[0].setPrefHeight(62);
		 lanes[0].setPrefWidth(250);
		 lanes[1]= new Pane ();
		 lanes[1].setLayoutX(236);
		 lanes[1].setLayoutY(248);
		 lanes[1].setPrefHeight(62);
		 lanes[1].setPrefWidth(250);
		 lanes[2] = new Pane();
		 lanes[2].setLayoutX(236);
		 lanes[2].setLayoutY(324);
		 lanes[2].setPrefHeight(62);
		 lanes[2].setPrefWidth(250);
		 lanes[3] = new Pane();
		 lanes[3].setLayoutX(236);
		 lanes[3].setLayoutY(400);
		 lanes[3].setPrefHeight(62);
		 lanes[3].setPrefWidth(250);
		 lanes[4] = new Pane();
		 lanes[4].setLayoutX(236);
		 lanes[4].setLayoutY(476);
		 lanes[4].setPrefHeight(62);
		 lanes[4].setPrefWidth(250);
		 labels  = new Label [35];
	
		 labels[0] = new Label ("Danger:"+battle.getOriginalLanes().get(0).getDangerLevel()+ "\n"+ "Health:"+ battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth());
		   labels[0].setLayoutX(13);
		   labels[0].setLayoutY(176);
		   labels[0].setPrefHeight(62);
		   labels[0].setPrefWidth(131);
		   labels[1] = new Label ("Danger:"+battle.getOriginalLanes().get(1).getDangerLevel()+ "\n"+ "Health:"+ battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth());
		   labels[1].setLayoutX(13);
		   labels[1].setLayoutY(248);
		   labels[1].setPrefHeight(62);
		   labels[1].setPrefWidth(131);
		   labels[2] = new Label ("Danger:"+battle.getOriginalLanes().get(2).getDangerLevel()+ "\n"+ "Health:"+ battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth());
		   labels[2].setLayoutX(13);
		   labels[2].setLayoutY(324);
		   labels[2].setPrefHeight(62);
		   labels[2].setPrefWidth(131);
		   labels[3] = new Label ("Danger:"+battle.getOriginalLanes().get(3).getDangerLevel()+ "\n"+ "Health:"+ battle.getOriginalLanes().get(3).getLaneWall().getCurrentHealth());
		   labels[3].setLayoutX(13);
		   labels[3].setLayoutY(400);
		   labels[3].setPrefHeight(62);
		   labels[3].setPrefWidth(131);
		   labels[4] = new Label ("Danger:"+battle.getOriginalLanes().get(4).getDangerLevel()+ "\n"+ "Health:"+ battle.getOriginalLanes().get(4).getLaneWall().getCurrentHealth());
		   labels[4].setLayoutX(13);
		   labels[4].setLayoutY(476);
		   labels[4].setPrefHeight(62);
		   labels[4].setPrefWidth(131);
		   ap.getChildren().addAll(Score,Turn,Phase,Resources,weaponshopH,passturn,vbox1,vbox2,vbox3,vbox4,vbox5,lanes[0],lanes[1],lanes[2],lanes[3],lanes[4],labels[0],labels[1],labels[2],labels[3],labels[4]);
		   weaponshopH.setOnAction(this);
		   passturn.setOnAction(this);
}

public void easyscene(){
	try {
		battle = new Battle (1,0,250,3,250);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Score = new Label("Current score:"+battle.getScore());
	  Score.setLayoutX(24);
	  Score.setLayoutY(65);
	  Score.setPrefHeight(26);
	  Score.setPrefWidth(166);
	 Turn = new Label("Current turn:"+battle.getNumberOfTurns());
	  Turn.setLayoutX(24);
	  Turn.setLayoutY(91);
	  Turn.setPrefHeight(26);
	  Turn.setPrefWidth(166);
	 Phase = new Label("Current phase:"+battle.getBattlePhase());
	  Phase.setLayoutX(24);
	  Phase.setLayoutY(117);
	  Phase.setPrefHeight(26);
	  Phase.setPrefWidth(200);
	 Resources = new Label("Current resources:"+battle.getResourcesGathered());
	 Resources.setLayoutX(24);
	 Resources.setLayoutY(143);
	 Resources.setPrefHeight(26);
	 Resources.setPrefWidth(166);
	 weaponshop = new Button ("Weapon shop");
	 weaponshop.setLayoutX(24);
	 weaponshop.setLayoutY(29);
	 weaponshop.setPrefHeight(36);
	 weaponshop.setPrefWidth(166);
     passturn = new Button ("Pass Turn");
     passturn.setLayoutX(361);
     passturn.setLayoutY(29);
     passturn.setPrefHeight(36);
	 passturn.setPrefWidth(122);
     vbox1 = new VBox();
	 vbox1.setLayoutX(151);
	 vbox1.setLayoutY(175);
	 vbox1.setPrefHeight(62);
	 vbox1.setPrefWidth(79);
	 vbox2 = new VBox();
	 vbox2.setLayoutX(151);
	 vbox2.setLayoutY(248);
	 vbox2.setPrefHeight(62);
	 vbox2.setPrefWidth(79);
	 vbox3 = new VBox();
	 vbox3.setLayoutX(151);
	 vbox3.setLayoutY(324);
	 vbox3.setPrefHeight(62);
	 vbox3.setPrefWidth(79); 
	 
	 lanes = new Pane[3];
	    for (int i = 0; i < lanes.length; i++) 
	        lanes[i] = new Pane();
	    
	 lanes[0].setLayoutX(236);
	 lanes[0].setLayoutY(176);
	 lanes[0].setPrefHeight(62);
	 lanes[0].setPrefWidth(250);
	 lanes[1]= new Pane ();
	 lanes[1].setLayoutX(236);
	 lanes[1].setLayoutY(248);
	 lanes[1].setPrefHeight(62);
	 lanes[1].setPrefWidth(250);
	 lanes[2] = new Pane();
	 lanes[2].setLayoutX(236);
	 lanes[2].setLayoutY(324);
	 lanes[2].setPrefHeight(62);
	 lanes[2].setPrefWidth(250);
	 labels  = new Label [3];
	 labels[0] = new Label ("Danger:"+battle.getOriginalLanes().get(0).getDangerLevel()+ "\n"+ "Health:"+ battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth());
	   labels[0].setLayoutX(13);
	   labels[0].setLayoutY(176);
	   labels[0].setPrefHeight(62);
	   labels[0].setPrefWidth(131);
	   labels[1] = new Label ("Danger:"+battle.getOriginalLanes().get(1).getDangerLevel()+ "\n"+ "Health:"+ battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth());
	   labels[1].setLayoutX(13);
	   labels[1].setLayoutY(248);
	   labels[1].setPrefHeight(62);
	   labels[1].setPrefWidth(131);
	   labels[2] = new Label ("Danger:"+battle.getOriginalLanes().get(2).getDangerLevel()+ "\n"+ "Health:"+ battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth());
	   labels[2].setLayoutX(13);
	   labels[2].setLayoutY(324);
	   labels[2].setPrefHeight(62);
	   labels[2].setPrefWidth(131);
	 ap.getChildren().addAll(Score,Turn,Phase,Resources,weaponshop,passturn,vbox1,vbox2,vbox3,lanes[0],lanes[1],lanes[2],labels[0],labels[1],labels[2]);
	 weaponshop.setOnAction(this);
	 passturn.setOnAction(this);
}
public void wssceneH(){
	ToggleGroup tg = new ToggleGroup();
	 pc = new RadioButton ("Piercing Canon");
	 pc.setLayoutX(40);
	 pc.setLayoutY(107);
	 pc.setPrefHeight(42);
	 pc.setPrefWidth(151);
	sc = new RadioButton ("Snipper Canon");
	sc.setLayoutX(40);
	 sc.setLayoutY(159);
	 sc.setPrefHeight(42);
	 sc.setPrefWidth(151);
	 vc = new RadioButton ("VolleySpread Canon");
	 vc.setLayoutX(38);
	 vc.setLayoutY(214);
	 vc.setPrefHeight(42);
	 vc.setPrefWidth(180);
	wt = new RadioButton ("Wall Trap");
	wt.setLayoutX(41);
	 wt.setLayoutY(269);
	 wt.setPrefHeight(42);
	 wt.setPrefWidth(151);
	contH= new Button ("continue");
	contH.setLayoutX(175);
	 contH.setLayoutY(327);
	 contH.setPrefHeight(42);
	 contH.setPrefWidth(151);
	pc.setToggleGroup(tg);
	sc.setToggleGroup(tg);
	vc.setToggleGroup(tg);
	wt.setToggleGroup(tg);
	Label Price = new Label ("Price");
	Price.setLayoutX(232);
	Price.setLayoutY(54);
	Price.setPrefHeight(42);
	Price.setPrefWidth(55);	
	Label P1 = new Label ("25");
	P1.setLayoutX(232);
	P1.setLayoutY(109);
	P1.setPrefHeight(42);
	P1.setPrefWidth(55);
	Label P2 = new Label ("25");
	P2.setLayoutX(232);
	P2.setLayoutY(159);
	P2.setPrefHeight(42);
	P2.setPrefWidth(55);
	Label P3 = new Label ("100");
	P3.setLayoutX(232);
	P3.setLayoutY(214);
	P3.setPrefHeight(42);
	P3.setPrefWidth(55);
	Label P4 = new Label ("75");
	P4.setLayoutX(232);
	P4.setLayoutY(269);
	P4.setPrefHeight(42);
	P4.setPrefWidth(55);
	Label Damage = new Label ("Damage");
	Damage.setLayoutX(318);
	Damage.setLayoutY(54);
	Damage.setPrefHeight(42);
	Damage.setPrefWidth(82);
	Label D1 = new Label ("10");
	D1.setLayoutX(314);
	D1.setLayoutY(114);
	D1.setPrefHeight(42);
	D1.setPrefWidth(55);
	Label D2 = new Label ("35");
	D2.setLayoutX(314);
	D2.setLayoutY(166);
	D2.setPrefHeight(42);
	D2.setPrefWidth(55);
	Label D3 = new Label ("5");
	D3.setLayoutX(314);
	D3.setLayoutY(221);
	D3.setPrefHeight(42);
	D3.setPrefWidth(55);
	Label D4 = new Label ("100");
	D4.setLayoutX(314);
	D4.setLayoutY(276);
	D4.setPrefHeight(42);
	D4.setPrefWidth(55);
	Label Name = new Label ("Name");
	Name.setLayoutX(478);
	Name.setLayoutY(54);
	Name.setPrefHeight(42);
	Name.setPrefWidth(82);
	Label N1 = new Label ("Anti-Titan Shell");
	N1.setLayoutX(429);
	N1.setLayoutY(107);
	N1.setPrefHeight(42);
	N1.setPrefWidth(211);
	Label N2 = new Label ("Long Range Spear");
	N2.setLayoutX(429);
	N2.setLayoutY(159);
	N2.setPrefHeight(42);
	N2.setPrefWidth(211);
	Label N3 = new Label ("WallSpread Cannon");
	N3.setLayoutX(429);
	N3.setLayoutY(214);
	N3.setPrefHeight(42);
	N3.setPrefWidth(211);
	Label N4 = new Label ("Proximity Trap");
	N4.setLayoutX(429);
	N4.setLayoutY(269);
	N4.setPrefHeight(42);
	N4.setPrefWidth(211);
	backH = new Button ("Back");
	backH.setLayoutX(587);
	backH.setLayoutY(14);
	backH.setPrefHeight(42);
	backH.setPrefWidth(99);
	
	ap.getChildren().addAll(pc,sc,vc,wt,contH,Price,P1,P2,P3,P4,Damage, D1, D2, D3, D4, Name, N1, N2, N3, N4, backH );	
	contH.setOnAction(this);
	backH.setOnAction(this);
}
public void contsceneH(){
	 backY = new Button ("Back");
	 backY.setLayoutX(206);
	 backY.setLayoutY(326);
	 backY.setPrefHeight(38);
	 backY.setPrefWidth(89);
	b1h = new Button ("lane 1");
	AnchorPane.setTopAnchor(b1h, 70.0);
    AnchorPane.setLeftAnchor(b1h, 20.0);
	b2h = new Button ("lane 2");
	AnchorPane.setTopAnchor(b2h, 100.0);
    AnchorPane.setLeftAnchor(b2h, 20.0);
	b3h = new Button ("lane 3");
	AnchorPane.setTopAnchor(b3h, 130.0);
    AnchorPane.setLeftAnchor(b3h, 20.0);
    b4 = new Button ("lane 4");
	AnchorPane.setTopAnchor(b4, 170.0);
    AnchorPane.setLeftAnchor(b4, 20.0);
    b5 = new Button ("lane 5");
	AnchorPane.setTopAnchor(b5, 210.0);
    AnchorPane.setLeftAnchor(b5, 20.0);
    
    if (!battle.getOriginalLanes().get(0).isLaneLost())
    	ap.getChildren().add(b1h);
    if (!battle.getOriginalLanes().get(1).isLaneLost())	
    	ap.getChildren().add(b2h);
    if (!battle.getOriginalLanes().get(2).isLaneLost())	
    	ap.getChildren().add(b3h);
    if (!battle.getOriginalLanes().get(3).isLaneLost())
    	ap.getChildren().add(b4);
    if (!battle.getOriginalLanes().get(4).isLaneLost())	
    	ap.getChildren().add(b5);
    ap.getChildren().add(backY);
    backY.setOnAction(this);
    b1h.setOnAction(this);
    b2h.setOnAction(this);
    b3h.setOnAction(this);
    b4.setOnAction(this);
    b5.setOnAction(this);
    
}

public void addTitan (){
	//System.out.println(battle.getOriginalLanes().get(0).getTitans().size());
	//System.out.println(battle.getOriginalLanes().get(1).getTitans().size());
	//System.out.println(battle.getOriginalLanes().get(2).getTitans().size());
	for (int i =0; i<battle.getOriginalLanes().size();i++){
		Lane lane= battle.getOriginalLanes().get(i);
		//System.out.println(lane.getTitans().size());
		
		lanes[i].getChildren().clear();
		if (!lane.isLaneLost()){	
		for (Titan t : lane.getTitans()){
			if (t instanceof AbnormalTitan){	
			int y =lane.getTitans().peek().getCurrentHealth();
			String s = Integer.toString(y);
			abt= new Button ( "abt"+"\n"+s);
			abt.setLayoutX(lane.getTitans().peek().getDistance());
			abt.setLayoutY(Math.random()*100);
			abt.setPrefHeight(50);
			abt.setPrefWidth(70);
			lanes[i].getChildren().add(abt);
				
			}
			else if (t instanceof ArmoredTitan){
				int y =lane.getTitans().peek().getCurrentHealth();
				String s = Integer.toString(y);
				art= new Button ( "art"+"\n"+s);
				art.setLayoutX(lane.getTitans().peek().getDistance());
				art.setLayoutY(Math.random()*100);
				art.setPrefHeight(60);
				art.setPrefWidth(60);
				lanes[i].getChildren().add(art);
			}
			else if (t instanceof ColossalTitan){
				int y =lane.getTitans().peek().getCurrentHealth();
				String s = Integer.toString(y);
				ct= new Button ( "ct"+"\n"+s);
				ct.setLayoutX(lane.getTitans().peek().getDistance());
				ct.setLayoutY(Math.random()*100);
				ct.setPrefHeight(70);
				ct.setPrefWidth(60);
				lanes[i].getChildren().add(ct);
			}
			else if (t instanceof PureTitan){
			   //	System.out.print("p");
				int y =lane.getTitans().peek().getCurrentHealth();
				String s = Integer.toString(y);
				pt= new Button ( "pt"+"\n"+s);
				pt.setLayoutX(lane.getTitans().peek().getDistance());
				pt.setLayoutY(Math.random()*100);
				pt.setPrefHeight(80);
				pt.setPrefWidth(60);
				lanes[i].getChildren().add(pt);
			}
			//lane.getTitans().remove();
		}
		}	
	}
		
}

public void updatelanevalues(){
	for (int i = 0; i<battle.getOriginalLanes().size();i++){
	 Lane lane = battle.getOriginalLanes().get(i);
	 String S= "Danger:"+lane.getDangerLevel()+ "\n"+ "Health:"+ lane.getLaneWall().getCurrentHealth();
     labels[i].setText(S);
	}
}

public void updatebattlevalues(){
	Score.setText("Current Score:"+battle.getScore());
	Turn.setText("Current turn:"+battle.getNumberOfTurns());
	Phase.setText("Current phase:"+battle.getBattlePhase());
	Resources.setText("Current resources:"+battle.getResourcesGathered());
}


public static void main(String[] args) {
	launch(args);
}

private void showErrorStage (String message) {
    Stage errorStage = new Stage();
    if (message == "Game Over"){
    	 errorMsg = new Label(message+"\n"+ "Score: "+"\n"+ battle.getScore());
    	Return = new Button ("Play Again"); 
    	 Return.setLayoutX(200);
    	 Return.setLayoutY(100);
    	 Return.setPrefHeight(50);
    	 Return.setPrefWidth(100);
    	 ap = new AnchorPane();
    	 ap.getChildren().addAll(errorMsg,Return);
    	 Scene scene = new Scene(ap, 400, 150);
    	 Return.setOnAction(this);
    	 errorStage.setScene(scene);
    	 errorStage.showAndWait();
    }
   else {
    errorMsg = new Label(message);
    VBox vbox = new VBox(10, errorMsg);
    Scene scene = new Scene(vbox, 400, 150);
    errorStage.setScene(scene);
    errorStage.showAndWait();
    }
}
public void instructionScene(){
	Label instructions= new Label ("Defeat as many Titans as possible"+"\n"+"The game continues indefinitely"+"\n"+"Lose when all starting lanes become lost "+"\n"+"Buy and deploy weapons using resources.");
	instructions.setLayoutX(0);
	instructions.setLayoutY(0);
	instructions.setPrefHeight(400);
	instructions.setPrefWidth(400);
	backS = new Button ("Back");
	backS.setLayoutX(300);
	backS.setLayoutY(14);
	backS.setPrefHeight(42);
	backS.setPrefWidth(100);
	ap.getChildren().addAll(instructions,backS);
	backS.setOnAction(this);
	
}

}


	
	
	
	

