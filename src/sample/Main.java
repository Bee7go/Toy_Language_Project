package sample;

import Model.ADT.*;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import Repository.IRepo;
import Repository.Repo;
import View.Interpreter;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import Controller.Controller;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

//Toy Language Project
//Badaruta Bianca

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        //Initial Window
        primaryStage.setTitle("List of possible programs");

        ListView listView = new ListView();
        listView.setMaxHeight(300);
        listView.setStyle("-fx-font-size: 14px; " +
                "-fx-control-inner-background: #2BCBE5 ;\n" +
                "-fx-control-inner-background-alt: derive(-fx-control-inner-background, 40%); " +
                "-fx-font-family: 'SketchFlow Print';");

        Interpreter x = new Interpreter();


        listView.getItems().add(x.ex1().toString());
        listView.getItems().add(x.ex2().toString());
        listView.getItems().add(x.ex3().toString());
        listView.getItems().add(x.ex4().toString());
        listView.getItems().add(x.ex5().toString());
        listView.getItems().add(x.ex6().toString());
        listView.getItems().add(x.ex7().toString());
        listView.getItems().add(x.ex8().toString());
        listView.getItems().add(x.ex9().toString());
        listView.getItems().add(x.ex10().toString());


        Button button = new Button("Select Program");
        button.setStyle("-fx-color: #FF645A; -fx-margins: 15px;");
        button.setFont(Font.font ("Comic Sans MS", 17));

        List<String> fontFamilies = Font.getFamilies();
        List<String> fontNames    = Font.getFontNames();
        for(String item : fontFamilies) {
            System.out.println(item);
        }
        for(String item : fontNames) {
            System.out.println(item);
        }


        //Opening main window
        button.setOnAction(event -> {
            ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();
            IStmt selectedProgram;
            if ((int)selectedIndices.get(0) == 0) {
                selectedProgram =  x.ex1();
            }
            else if ((int)selectedIndices.get(0) == 1) {
                selectedProgram =  x.ex2();
            }
            else if ((int)selectedIndices.get(0) == 2) {
                selectedProgram =  x.ex3();
            }
            else if ((int)selectedIndices.get(0) == 3) {
                selectedProgram =  x.ex4();
            }
            else if ((int)selectedIndices.get(0) == 4) {
                selectedProgram =  x.ex5();
            }
            else if ((int)selectedIndices.get(0) == 5) {
                selectedProgram =  x.ex6();
            }
            else if ((int)selectedIndices.get(0) == 6) {
                selectedProgram =  x.ex7();
            }
            else if ((int)selectedIndices.get(0) == 7) {
                selectedProgram =  x.ex8();
            }
            else if ((int)selectedIndices.get(0) == 8) {
                selectedProgram =  x.ex9();
            }
            else {
                selectedProgram =  x.ex10();
            }


            PrgState prg1 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), selectedProgram,
                    new MyDictionary<StringValue, BufferedReader>(),new MyHeap<Integer, Value>());
            IRepo repo1 = new Repo("log1.txt");
            repo1.add(prg1);
            Controller ctr1 = new Controller(repo1);
            ExecutorService executor = Executors.newFixedThreadPool(2);
            AtomicReference<List<PrgState>> prgList= new AtomicReference<>(ctr1.removeCompletedPrg(repo1.getPrgList()));


            //Initializing text fields, tables, labels etc. and SETUP styles
            // a) nr PrgStates
            TextField nrPrgStates = new TextField();
            nrPrgStates.setStyle("-fx-font-size: 17px; "  +
                    "-fx-font-family: 'Segoe UI Black';");


            // b) Heap Table
            Label labelHeap = new Label("Heap Table");
            labelHeap.setFont(new Font("Rockwell", 18));
            labelHeap.setTextFill(Color.WHITE);
            labelHeap.setMaxWidth(Double.MAX_VALUE);
            labelHeap.setAlignment(Pos.CENTER);
            TableView<KeyValue> Heaptable = new TableView<KeyValue>();
            Heaptable.setStyle("-fx-font-size: 17px; "  +
                    "-fx-control-inner-background: #FCF3CF ;\n" +
                    "-fx-control-inner-background-alt: derive(-fx-control-inner-background, 40%); " +
                    "-fx-font-family: 'Segoe UI Black';");
            ObservableList<KeyValue> data = FXCollections.observableArrayList();
            Heaptable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            Heaptable.setMaxWidth(Double.MAX_VALUE);

            //Creating columns
            TableColumn keyCol = new TableColumn("Key");
            keyCol.setCellValueFactory(new PropertyValueFactory<>("key"));
            TableColumn valueCol = new TableColumn("Value");
            valueCol.setCellValueFactory(new PropertyValueFactory("value"));

            //Adding data to the table
            Heaptable.setItems(data);
            Heaptable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            Heaptable.getColumns().addAll(keyCol, valueCol);

            // c) Out list
            Label labelOut = new Label("Out Table");
            labelOut.setFont(new Font("Rockwell", 18));
            labelOut.setTextFill(Color.WHITE);
            labelOut.setMaxWidth(Double.MAX_VALUE);
            labelOut.setAlignment(Pos.CENTER);
            ListView<String> listOut = new ListView<String>();
            listOut.setMaxWidth(Double.MAX_VALUE);
            listOut.setStyle("-fx-font-size: 17px; "  +
                    "-fx-control-inner-background: #DCFFD7 ;\n" +
                    "-fx-control-inner-background-alt: derive(-fx-control-inner-background, 40%); " +
                    "-fx-font-family: 'Segoe UI Black';");


            ObservableList<String> itemsOut = FXCollections.observableArrayList ();

            // d) FileTable list
            Label labelFile = new Label("File Table");
            labelFile.setFont(new Font("Rockwell", 18));
            labelFile.setTextFill(Color.WHITE);
            labelFile.setMaxWidth(Double.MAX_VALUE);
            labelFile.setAlignment(Pos.CENTER);
            ListView<String> listFile = new ListView<String>();
            listFile.setStyle("-fx-font-size: 17px; "  +
                    "-fx-control-inner-background: #EBDEF0  ;\n" +
                    "-fx-control-inner-background-alt: derive(-fx-control-inner-background, 40%); " +
                    "-fx-font-family: 'Segoe UI Black';");
            listFile.setMaxWidth(Double.MAX_VALUE);
            ObservableList<String> itemsFile = FXCollections.observableArrayList ();

            // (e) the list of PrgState identifiers as a ListView
            Label labelIdPrgState = new Label("PrgState identifiers");
            labelIdPrgState.setFont(new Font("Rockwell", 18));
            labelIdPrgState.setTextFill(Color.WHITE);
            labelIdPrgState.setMaxWidth(Double.MAX_VALUE);
            labelIdPrgState.setAlignment(Pos.CENTER);

            ListView<String> listIdPrgState = new ListView<String>();
            listIdPrgState.setStyle("-fx-font-size: 17px; "  +
                    "-fx-control-inner-background: B2E0FF ;\n" +
                    "-fx-control-inner-background-alt: derive(-fx-control-inner-background, 80%); " +
                    "-fx-font-family: 'Segoe UI Black';");
            listIdPrgState.setMaxWidth(Double.MAX_VALUE);

            ObservableList<String> itemsIdPrgState = FXCollections.observableArrayList ();


            // f) SymTable

            Label labelSymTable = new Label("Sym Table");
            labelSymTable.setFont(new Font("Rockwell", 18));
            labelSymTable.setTextFill(Color.WHITE);
            labelSymTable.setMaxWidth(Double.MAX_VALUE);
            labelSymTable.setAlignment(Pos.CENTER);


            TableView<VariableValue> SymTable = new TableView<VariableValue>();
            SymTable.setStyle("-fx-font-size: 17px; "  +
                    "-fx-control-inner-background: #CFFFFA   ;\n" +
                    "-fx-control-inner-background-alt: derive(-fx-control-inner-background, 40%); " +
                    "-fx-font-family: 'Segoe UI Black';");
            SymTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            SymTable.setMaxWidth(Double.MAX_VALUE);
            ObservableList<VariableValue> data2 = FXCollections.observableArrayList();

            //Creating columns
            TableColumn variableCol = new TableColumn("Variable");
            variableCol.setCellValueFactory(new PropertyValueFactory<>("variable"));
            TableColumn valueCol2 = new TableColumn("Value");
            valueCol2.setCellValueFactory(new PropertyValueFactory("value"));

            //Adding data to the table
            SymTable.setItems(data2);
            SymTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            SymTable.getColumns().addAll(variableCol,valueCol2);

            // g) ExeStack
            Label labelExeStack = new Label("ExeStack");
            labelExeStack.setFont(new Font("Rockwell", 18));
            labelExeStack.setMaxWidth(Double.MAX_VALUE);
            labelExeStack.setAlignment(Pos.CENTER);
            labelExeStack.setTextFill(Color.WHITE);
            ListView<String> listExeStack = new ListView<String>();
            listExeStack.setStyle("-fx-font-size: 17px; "  +
                    "-fx-control-inner-background: #FFBCBC  ;\n" +
                    "-fx-control-inner-background-alt: derive(-fx-control-inner-background, 40%); " +
                    "-fx-font-family: 'Segoe UI Black';");
            ObservableList<String> itemsExeStack = FXCollections.observableArrayList ();
            listExeStack.setMaxWidth(Double.MAX_VALUE);


            // h) Run one step

            Button RunStep = new Button("Run One Step");

                RunStep.setOnAction(runStep -> {
                    if(prgList.get().size() > 0) {

                        try {
                            ctr1.oneStepForAllPrg(prgList.get());

                            // ---------> updating stuffs

                            // --> update nr PrgStates
                            nrPrgStates.setText("Number of PrgStates: " + repo1.getPrgList().size());

                            // --> update OutTable
                            itemsOut.clear();
                            for (Value i : prg1.getOut().getList())
                                itemsOut.add(String.valueOf(i));
                            listOut.setItems(itemsOut);


                            // --> update HeapTable
                            data.clear();
                            for (Map.Entry<Integer, Value> entry : prg1.getHeap().getContent().entrySet()) {
                                data.add(new KeyValue(entry.getKey(),entry.getValue()));
                            }

                            Heaptable.setItems(data);


                            // --> update FileTable
                            itemsFile.clear();
                            itemsFile.add(prg1.getFileTable().toString());
                            listFile.setItems(itemsFile);


                            // --> update PrgState identifiers

                            itemsIdPrgState.clear();
                            for (PrgState i : repo1.getPrgList())
                                itemsIdPrgState.add(String.valueOf(i.getId()));
                            listIdPrgState.setItems(itemsIdPrgState);
                           // listIdPrgState.getSelectionModel().selectFirst();

                            //---> get selected item
                            listIdPrgState.setOnMouseClicked(new EventHandler<MouseEvent>() {

                                @Override
                                public void handle(MouseEvent event) {

                                    System.out.println("clicked on " + listIdPrgState.getSelectionModel().getSelectedItem());

                                    for (PrgState i : repo1.getPrgList()) {
                                        if (String.valueOf(i.getId()).equals(listIdPrgState.getSelectionModel().getSelectedItem())) {
                                            // --> update SymTable
                                            data2.clear();
                                            for (Map.Entry<String, Value> entry : i.getSymTable().getContent().entrySet()) {
                                                data2.add(new VariableValue(entry.getKey(),entry.getValue()));
                                            }
                                            SymTable.setItems(data2);

                                            // --> update ExeStack
                                            itemsExeStack.clear();
                                            for (IStmt j: i.getStk().getContent())
                                                itemsExeStack.add(String.valueOf(j));
                                            listExeStack.setItems(itemsExeStack);

                                            break;
                                        }
                                    }

                                }
                            });

                            prgList.set(ctr1.removeCompletedPrg(repo1.getPrgList()));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        executor.shutdownNow();
                        repo1.setPrgList(prgList.get());

                        RunStep.setDisable(true);
                    }
                });

            // Setup all
            VBox HeapBox = new VBox(labelHeap,Heaptable);
            VBox SymBox = new VBox(labelSymTable,SymTable);
            VBox FileBox = new VBox(labelFile,listFile);
            VBox ExeStackBox = new VBox(labelExeStack,listExeStack);

            HBox aux1 = new HBox(15);
            HBox.setHgrow(HeapBox, Priority.ALWAYS);
            HBox.setHgrow(SymBox, Priority.ALWAYS);
            HBox.setHgrow(FileBox, Priority.ALWAYS);
            HBox.setHgrow(ExeStackBox, Priority.ALWAYS);
            HeapBox.setMaxWidth(Double.MAX_VALUE);
            SymBox.setMaxWidth(Double.MAX_VALUE);
            ExeStackBox.setMaxWidth(Double.MAX_VALUE);
            FileBox.setMaxWidth(Double.MAX_VALUE);


            VBox OutBox = new VBox(labelOut,listOut);
            VBox PrgStateBox = new VBox(labelIdPrgState,listIdPrgState);
            HBox aux2 = new HBox();
            HBox.setHgrow(OutBox, Priority.ALWAYS);
            HBox.setHgrow(PrgStateBox, Priority.ALWAYS);
            OutBox.setMaxWidth(Double.MAX_VALUE);
            PrgStateBox.setMaxWidth(Double.MAX_VALUE);

            aux1.getChildren().addAll(HeapBox,FileBox,PrgStateBox);
            aux2.getChildren().addAll(OutBox, SymBox);

            VBox mainVBox = new VBox(nrPrgStates, aux1, aux2, ExeStackBox, RunStep);
            mainVBox.setSpacing(15);
            mainVBox.setStyle("-fx-background-color: #006494 ;");
            Stage anotherstage = new Stage();
            anotherstage.setTitle("Main Window");
            anotherstage.setScene(new Scene(mainVBox, 1200, 500));
            anotherstage.show();
        });


        VBox vBox = new VBox(listView, button);
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #21618C ;");
        Scene scene = new Scene(vBox, 1300, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
