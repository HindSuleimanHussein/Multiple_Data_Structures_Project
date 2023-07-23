package com.example.thirddatastructureproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    Scene scene, main, insert, update, delete, find, AVLTraverse, getAll;
    String strFileChoosing = "", branch = "";
    File selectedFile;

    @Override
    public void start(Stage stage) throws IOException {
        TawjihiDS<Tawjihi> tawjihiDS = new TawjihiDS<>();

        //background image
        Image image = new Image("https://static.vecteezy.com/system/resources/previews/008/425/653/original/one-line-drawing-of-stack-of-books-decoration-free-vector.jpg");
        BackgroundImage bgImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true, true, true, true));
        Background bg = new Background(bgImage);

        //Main Scene
        BorderPane mainPane = new BorderPane();
        mainPane.setBackground(bg);

        Button insertMain = new Button("Insert A New Student");
        insertMain.setOnAction(e -> stage.setScene(insert));
        insertMain.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        insertMain.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        insertMain.setTextFill(Color.DARKBLUE);

        Button deleteMain = new Button("Delete A Student");
        deleteMain.setOnAction(e -> stage.setScene(delete));
        deleteMain.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        deleteMain.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        deleteMain.setTextFill(Color.DARKBLUE);

        Button updateMain = new Button("Update A Student");
        updateMain.setOnAction(e -> stage.setScene(update));
        updateMain.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        updateMain.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        updateMain.setTextFill(Color.DARKBLUE);

        Button findMain = new Button("Find the Student's Previous and Next");
        findMain.setOnAction(e -> stage.setScene(find));
        findMain.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        findMain.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        findMain.setTextFill(Color.DARKBLUE);

        Button traverseMain = new Button("Print out the List of Students");
        traverseMain.setOnAction(e -> stage.setScene(AVLTraverse));
        traverseMain.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        traverseMain.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        traverseMain.setTextFill(Color.DARKBLUE);

        Button getAllMain = new Button("Get All Students With Certain Average");
        getAllMain.setOnAction(e -> stage.setScene(getAll));
        getAllMain.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        getAllMain.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        getAllMain.setTextFill(Color.DARKBLUE);

        Button backMain = new Button("Back");
        backMain.setOnAction(e -> stage.setScene(scene));
        backMain.setFont(Font.font("Times New Roman", FontWeight.BOLD, 17));
        backMain.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        backMain.setTextFill(Color.DARKBLUE);


        VBox mainButtons = new VBox(10);
        mainButtons.getChildren().addAll(insertMain, deleteMain, updateMain, findMain, traverseMain, getAllMain, backMain);
        mainButtons.setAlignment(Pos.CENTER);
        mainPane.setCenter(mainButtons);

        main = new Scene(mainPane, 350, 400);
        // end main


        // First Scene
        // choose Branch
        RadioButton scienceRadio = new RadioButton("Scientific");
        scienceRadio.setDisable(true);
        RadioButton literaryRadio = new RadioButton("Literary");
        literaryRadio.setDisable(true);
        ToggleGroup toggleBranch = new ToggleGroup();
        scienceRadio.setToggleGroup(toggleBranch);
        literaryRadio.setToggleGroup(toggleBranch);

        //files
        Button load = new Button("load");
        load.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        load.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        load.setTextFill(Color.DARKBLUE);
        load.setDisable(true);

        Button fileChooserButton = new Button("Choose Your File!");
        fileChooserButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        fileChooserButton.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        fileChooserButton.setTextFill(Color.DARKBLUE);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open My Files");

        fileChooserButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(stage);
            strFileChoosing="";
            Scanner sc = null;
            try {
                sc = new Scanner(selectedFile);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            while (sc.hasNext()) {
                String s = sc.nextLine();
                strFileChoosing += s.trim() + "\n";

            }
            scienceRadio.setDisable(false);
            literaryRadio.setDisable(false);
            load.setDisable(false);
        });
        load.setOnAction(e -> {
            tawjihiDS.doublyLinkedList.deleteAllNodes();
            tawjihiDS.avlTreeAverage.emptyOut();
            tawjihiDS.avlTreeSeat.emptyOut();

            if (scienceRadio.isSelected()) {
                strFileChoosing = strFileChoosing.replace(" ", "");
                Scanner scan = new Scanner(strFileChoosing);
                while (scan.hasNext()) {
                    String s = scan.nextLine();
                    String[] str = s.trim().split(",");
                    if (str[1].equalsIgnoreCase("Scientific")) {
                        tawjihiDS.insert(new Tawjihi(Integer.parseInt(str[0]), str[1], Double.parseDouble(str[2])));
                    }
                }
            } else if (literaryRadio.isSelected()) {
                strFileChoosing = strFileChoosing.replace(" ", "");
                Scanner scan = new Scanner(strFileChoosing);
                while (scan.hasNext()) {
                    String s = scan.nextLine();
                    String[] str = s.trim().split(",");
                    if (str[1].equalsIgnoreCase("Literary")) {
                        tawjihiDS.insert(new Tawjihi(Integer.parseInt(str[0]), str[1], Double.parseDouble(str[2])));
                    }
                }
            }
            stage.setScene(main);
        });

        HBox branches = new HBox(10);
        branches.getChildren().addAll(scienceRadio, literaryRadio);
        branches.setAlignment(Pos.CENTER);
        scienceRadio.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        scienceRadio.setTextFill(Color.DARKBLUE);
        literaryRadio.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        literaryRadio.setTextFill(Color.DARKBLUE);

        VBox mainBox = new VBox(20);
        mainBox.getChildren().addAll(fileChooserButton, branches, load);
        mainBox.setAlignment(Pos.CENTER);
        // end First Scene

        //Insert Scene
        BorderPane insertPane = new BorderPane();
        TextField insertID = new TextField();
        Label labelInsertID = new Label("The Seat Number: ");
        labelInsertID.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        labelInsertID.setTextFill(Color.DARKBLUE);

        TextField insertAverage = new TextField();
        Label labelInsertAverage = new Label("The Average: ");
        labelInsertAverage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        labelInsertAverage.setTextFill(Color.DARKBLUE);

        Label labelForInsertUser = new Label();
        labelForInsertUser.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        labelForInsertUser.setTextFill(Color.DARKBLUE);

        Button insertButton = new Button("Insert");
        insertButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        insertButton.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        insertButton.setTextFill(Color.DARKBLUE);

        insertButton.setOnAction(e -> {
            try {
                DoublyNode curr = tawjihiDS.doublyLinkedList.getHead();
                if (curr.getData().getBranch().equalsIgnoreCase("Scientific"))
                    branch = "Scientific";
                else
                    branch = "Literary";

                while (curr.getNext() != tawjihiDS.doublyLinkedList.getHead()) {
                        if (curr.getData().getSeatNumber() == Integer.parseInt(insertID.getText())) {
                            labelForInsertUser.setText("The Seat Number Is Already Registered");
                            break;
                        } else
                            curr = curr.getNext();
                }
                if (curr.getNext() == tawjihiDS.doublyLinkedList.getHead()) {
                    tawjihiDS.insert(new Tawjihi(Integer.parseInt(insertID.getText()), branch, Double.parseDouble(insertAverage.getText())));
                    labelForInsertUser.setText("Successfully Added");
                }
                insertID.setText(null);
                insertAverage.setText(null);
            }catch(NumberFormatException ex){
                ex.printStackTrace();
                labelForInsertUser.setText("Hey watch out");
            }
        });


        Button backInsert = new Button("Back");
        backInsert.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        backInsert.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        backInsert.setTextFill(Color.DARKBLUE);

        backInsert.setOnAction(e -> {
            stage.setScene(main);
            labelForInsertUser.setText(null);
        });

        HBox insertAndBack = new HBox(20);
        insertAndBack.getChildren().addAll(insertButton, backInsert);
        insertAndBack.setAlignment(Pos.CENTER);
        insertAndBack.setTranslateX(70);
        insertAndBack.setTranslateY(10);

        GridPane insertGrid = new GridPane();
        insertGrid.addRow(1, labelInsertID, insertID);
        insertGrid.addRow(2, labelInsertAverage, insertAverage);
        insertGrid.addRow(3, labelForInsertUser);
        insertGrid.addRow(4, insertAndBack);
        insertGrid.setVgap(20);
        insertGrid.setAlignment(Pos.CENTER);

        insertPane.setCenter(insertGrid);
        insert = new Scene(insertPane, 500, 320);
        // end Insert Scene

        //Delete Scene
        BorderPane deletePane = new BorderPane();
        TextField deleteID = new TextField();
        Label labelDeleteID = new Label("The Seat Number: ");
        labelDeleteID.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        labelDeleteID.setTextFill(Color.DARKBLUE);

        TextField deleteAverage = new TextField();
        Label labelDeleteAverage = new Label("The Average: ");
        labelDeleteAverage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        labelDeleteAverage.setTextFill(Color.DARKBLUE);

        Label labelForUserDelete = new Label();
        labelForUserDelete.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        labelForUserDelete.setTextFill(Color.DARKBLUE);

        Button deleteButton = new Button("Delete");
        deleteButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        deleteButton.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        deleteButton.setTextFill(Color.DARKBLUE);

        deleteButton.setOnAction(e -> {
            try{
            tawjihiDS.delete(Integer.parseInt(deleteID.getText()));
            deleteID.setText("");
            deleteAverage.setText("");
            }catch(NullPointerException ex){
                ex.printStackTrace();

            }
        });

        Button findDeleteButton = new Button("Find");
        findDeleteButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        findDeleteButton.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        findDeleteButton.setTextFill(Color.DARKBLUE);

        findDeleteButton.setOnAction(e -> {
            AVLNodeSeat node= tawjihiDS.find(Integer.parseInt(deleteID.getText()));
            if(node!=null) {
                node.setPoint(tawjihiDS.findNode(Integer.parseInt(deleteID.getText())));
            }
            try {
                if (node == null) {
                    labelForUserDelete.setText("Hmm...The Student Is Not On the System");
                    deleteAverage.setText("");
                }
                else if (node.getPoint() != null && node != null) {
                    deleteAverage.setText(String.valueOf(node.getPoint().getData().getAverage()));
                    labelForUserDelete.setText("Successful Operation!!!!");
                }
            }catch(NullPointerException ex){
                ex.printStackTrace();

            }

        });

        Button backDelete = new Button("Back");
        backDelete.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        backDelete.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        backDelete.setTextFill(Color.DARKBLUE);

        backDelete.setOnAction(e -> {
            stage.setScene(main);
            labelForUserDelete.setText("");
            deleteID.setText("");
            deleteAverage.setText("");
        });


        HBox deleteAndBack = new HBox(20);
        deleteAndBack.getChildren().addAll(findDeleteButton, deleteButton, backDelete);
        deleteAndBack.setAlignment(Pos.CENTER);
        deleteAndBack.setTranslateX(70);
        deleteAndBack.setTranslateY(10);

        GridPane deleteGrid = new GridPane();
        deleteGrid.addRow(1, labelDeleteID, deleteID);
        deleteGrid.addRow(2, labelDeleteAverage, deleteAverage);
        deleteGrid.addRow(3, labelForUserDelete);
        deleteGrid.addRow(4, deleteAndBack);
        deleteGrid.setAlignment(Pos.CENTER);
        deleteGrid.setVgap(20);

        deletePane.setCenter(deleteGrid);
        delete = new Scene(deletePane, 500, 320);
        //end delete scene

        //update Scene
        BorderPane updatePane = new BorderPane();
        TextField updateID = new TextField();
        Label labelUpdateID = new Label("The Seat Number: ");
        labelUpdateID.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        labelUpdateID.setTextFill(Color.DARKBLUE);

        TextField updateAverage = new TextField();
        Label labelUpdateAverage = new Label("The Average: ");
        labelUpdateAverage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        labelUpdateAverage.setTextFill(Color.DARKBLUE);

        Label labelForUserUpdate = new Label();
        labelForUserUpdate.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        labelForUserUpdate.setTextFill(Color.DARKBLUE);

        Button updateButton = new Button("Update");
        updateButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        updateButton.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        updateButton.setTextFill(Color.DARKBLUE);

        Button updateClear = new Button("Clear");
        updateClear.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        updateClear.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        updateClear.setTextFill(Color.DARKBLUE);

        updateButton.setOnAction(e -> {
            tawjihiDS.update(Integer.parseInt(updateID.getText()), Double.parseDouble(updateAverage.getText()));
            updateID.setText("");
            updateAverage.setText("");
        });

        Button findUpdateButton = new Button("Find");
        findUpdateButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        findUpdateButton.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        findUpdateButton.setTextFill(Color.DARKBLUE);

        findUpdateButton.setOnAction(e -> {
            AVLNodeSeat node= tawjihiDS.find(Integer.parseInt(updateID.getText()));
            if(node!=null) {
                node.setPoint(tawjihiDS.findNode(Integer.parseInt(updateID.getText())));
            }
            try {
                if (node == null) {
                    labelForUserUpdate.setText("Hmm...The Student Is Not On the System");
                    updateAverage.setText("");
                }
                else if (node.getPoint() != null && node != null) {
                    updateAverage.setText(String.valueOf(node.getPoint().getData().getAverage()));
                    labelForUserUpdate.setText("Successful Operation!!!!");
                }
            }catch(NullPointerException ex){
                ex.printStackTrace();

            }

        });

        updateClear.setOnAction(e -> {
            labelForUserUpdate.setText("Cleared!");
            updateID.setText("");
            updateAverage.setText("");
        });

        Button backUpdate = new Button("Back");
        backUpdate.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        backUpdate.setStyle("-fx-border-color: #00008B; " + "-fx-border-width: 2px");
        backUpdate.setTextFill(Color.DARKBLUE);
        backUpdate.setTranslateX(160);
        backUpdate.setTranslateY(10);

        backUpdate.setOnAction(e -> {
            stage.setScene(main);
            labelForUserUpdate.setText("");
            updateID.setText("");
            updateAverage.setText("");
        });


        HBox updateAndBack = new HBox(20);
        updateAndBack.getChildren().addAll(findUpdateButton, updateButton, updateClear);
        updateAndBack.setAlignment(Pos.CENTER);
        updateAndBack.setTranslateX(70);
        updateAndBack.setTranslateY(10);

        GridPane updateGrid = new GridPane();
        updateGrid.addRow(1, labelUpdateID, updateID);
        updateGrid.addRow(2, labelUpdateAverage, updateAverage);
        updateGrid.addRow(3, labelForUserUpdate);
        updateGrid.addRow(4, updateAndBack);
        updateGrid.addRow(5, backUpdate);
        updateGrid.setAlignment(Pos.CENTER);
        updateGrid.setVgap(20);

        updatePane.setCenter(updateGrid);
        update = new Scene(updatePane, 500, 320);
        //end update scene

        // find previous and next
        BorderPane findPrevAndNextPane = new BorderPane();
        TextField findPrevAndNextID = new TextField();
        Label labelFindPrevAndNextID = new Label("The Seat Number: ");
        labelFindPrevAndNextID.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        labelFindPrevAndNextID.setTextFill(Color.DARKBLUE);

        Label labelInformUserPrevAndNext = new Label();
        labelInformUserPrevAndNext.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        labelInformUserPrevAndNext.setTextFill(Color.DARKBLUE);
        labelInformUserPrevAndNext.setTranslateX(70);

        Button findPrevNextButton = new Button("Find");
        findPrevNextButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        findPrevNextButton.setTextFill(Color.DARKBLUE);
        findPrevNextButton.setTranslateX(70);

        Button findPrevButton = new Button("Previous");
        findPrevButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        findPrevButton.setTextFill(Color.DARKBLUE);
        findPrevButton.setTranslateX(70);
        findPrevButton.setDisable(true);

        Button findNextButton = new Button("Next");
        findNextButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        findNextButton.setTextFill(Color.DARKBLUE);
        findNextButton.setTranslateX(70);
        findNextButton.setDisable(true);

        Label findNode = new Label();
        findNode.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        findNode.setTextFill(Color.DARKBLUE);
        findNode.setTranslateX(70);

        Label findPrevLabel = new Label();
        findPrevLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        findPrevLabel.setTextFill(Color.DARKBLUE);
        findPrevLabel.setTranslateX(70);

        Label findNextLabel = new Label();
        findNextLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        findNextLabel.setTextFill(Color.DARKBLUE);
        findNextLabel.setTranslateX(70);

        findPrevButton.setOnAction(e->{
            AVLNodeSeat node= tawjihiDS.find(Integer.parseInt(findPrevAndNextID.getText()));
            if(node!=null) {
                node.setPoint(tawjihiDS.findNode(Integer.parseInt(findPrevAndNextID.getText())));
            }
            try {
                if (node == null) {
                    labelInformUserPrevAndNext.setText("Hmm...The Student Is Not On the System");
                    findPrevLabel.setText("");
                    findNextLabel.setText("");
                    findNode.setText("");
                }

                else if (findPrevAndNextID.getText().equals("")) {
                    labelInformUserPrevAndNext.setText("Make Sure To Fill In Seat Number");
                    findPrevLabel.setText("");
                    findNextLabel.setText("");
                    findNode.setText("");
                }
                else if (node.getPoint().getPrev() != null && node != null) {
                    labelInformUserPrevAndNext.setText("");
                    findPrevLabel.setText("The Previous: " + node.getPoint().getPrev().getPrev());
                    findNextLabel.setText("The Next Record: " + node.getPoint().getNext().getPrev());
                    findNode.setText("The Record: " + node.getPoint().getPrev());
                    findPrevAndNextID.setText(String.valueOf(node.getPoint().getPrev().getData().getSeatNumber()));

                }
            }catch(NullPointerException ex){
                ex.printStackTrace();

            }

        });

        findNextButton.setOnAction(e->{
            AVLNodeSeat node= tawjihiDS.find(Integer.parseInt(findPrevAndNextID.getText()));
            if(node!=null) {
                node.setPoint(tawjihiDS.findNode(Integer.parseInt(findPrevAndNextID.getText())));
            }
            try {
                if (node == null) {
                    labelInformUserPrevAndNext.setText("Hmm...The Student Is Not On the System");
                    findPrevLabel.setText("");
                    findNextLabel.setText("");
                    findNode.setText("");
                }

                else if (findPrevAndNextID.getText().equals("")) {
                    labelInformUserPrevAndNext.setText("Make Sure To Fill In Seat Number");
                    findPrevLabel.setText("");
                    findNextLabel.setText("");
                    findNode.setText("");
                }
                else if (node.getPoint().getNext() != null && node != null) {
                    labelInformUserPrevAndNext.setText("");
                    findPrevLabel.setText("The Previous: " + node.getPoint().getPrev().getNext());
                    findNextLabel.setText("The Next Record: " + node.getPoint().getNext().getNext());
                    findNode.setText("The Record: " + node.getPoint().getNext());
                    findPrevAndNextID.setText(String.valueOf(node.getPoint().getNext().getData().getSeatNumber()));

                }
            }catch(NullPointerException ex){
                ex.printStackTrace();

            }
        });

        findPrevNextButton.setOnAction(e -> {
            AVLNodeSeat node= tawjihiDS.find(Integer.parseInt(findPrevAndNextID.getText()));
            if(node!=null) {
                node.setPoint(tawjihiDS.findNode(Integer.parseInt(findPrevAndNextID.getText())));
            }
            try {
                if (node == null) {
                    labelInformUserPrevAndNext.setText("Hmm...The Student Is Not On the System");
                    findNextButton.setDisable(true);
                    findPrevButton.setDisable(true);
                    findPrevLabel.setText("");
                    findNextLabel.setText("");
                    findNode.setText("");
                }

            else if (findPrevAndNextID.getText().equals("")) {
                labelInformUserPrevAndNext.setText("Make Sure To Fill In Seat Number");
                findNextButton.setDisable(true);
                findPrevButton.setDisable(true);
                findPrevLabel.setText("");
                findNextLabel.setText("");
                findNode.setText("");
            }
            else if (node.getPoint() != null && node != null) {
                labelInformUserPrevAndNext.setText("");
                findPrevLabel.setText("The Previous: " + node.getPoint().getPrev());
                findNextLabel.setText("The Next Record: " + node.getPoint().getNext());
                findNode.setText("The Record: " + node.getPoint());
                findNextButton.setDisable(false);
                findPrevButton.setDisable(false);

            }
            }catch(NullPointerException ex){
                ex.printStackTrace();

            }

        });

        Button prevNextBack = new Button("Back");
        prevNextBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        prevNextBack.setTextFill(Color.DARKBLUE);
        prevNextBack.setTranslateX(70);
        prevNextBack.setOnAction(e -> {
            stage.setScene(main);
            findPrevLabel.setText("");
            labelInformUserPrevAndNext.setText("");
            findPrevAndNextID.setText("");
            findNextLabel.setText("");
            findNode.setText("");
        });

        GridPane findPrevNextPane = new GridPane();
        findPrevNextPane.addRow(1, labelFindPrevAndNextID, findPrevAndNextID);
        findPrevNextPane.addRow(2, findPrevButton, findNextButton);
        findPrevNextPane.addRow(3, findPrevNextButton);
        findPrevNextPane.addRow(4, labelInformUserPrevAndNext);
        findPrevNextPane.addRow(5, findNode);
        findPrevNextPane.addRow(6, findPrevLabel);
        findPrevNextPane.addRow(7, findNextLabel);
        findPrevNextPane.addRow(8, prevNextBack);
        findPrevNextPane.setAlignment(Pos.CENTER);
        findPrevNextPane.setVgap(15);

        findPrevAndNextPane.setCenter(findPrevNextPane);

        find = new Scene(findPrevAndNextPane, 750, 500);
        // end the previous and next scene

        // traversal scene
        BorderPane traversalPane = new BorderPane();
        Label askUserTraversalPane = new Label("Would You Like To See The Seat Number, Averages, Or Records?");
        askUserTraversalPane.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        askUserTraversalPane.setTextFill(Color.DARKBLUE);

        Label avlHeight = new Label();
        avlHeight.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        avlHeight.setTextFill(Color.DARKBLUE);
        avlHeight.setTranslateX(20);

        ToggleGroup AVLToggle = new ToggleGroup();
        RadioButton seatNumberTraversalRadio = new RadioButton("Seat Numbers");
        seatNumberTraversalRadio.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        seatNumberTraversalRadio.setTextFill(Color.DARKBLUE);

        RadioButton averagesTraversalRadio = new RadioButton("Averages");
        averagesTraversalRadio.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        averagesTraversalRadio.setTextFill(Color.DARKBLUE);
        //averagesTraversalRadio.setTranslateX(10);

        RadioButton doublyTraversalRadio = new RadioButton("The Records");
        doublyTraversalRadio.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        doublyTraversalRadio.setTextFill(Color.DARKBLUE);

        seatNumberTraversalRadio.setToggleGroup(AVLToggle);
        averagesTraversalRadio.setToggleGroup(AVLToggle);
        doublyTraversalRadio.setToggleGroup(AVLToggle);

        TextArea textArea = new TextArea();
        textArea.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        textArea.setStyle("-fx-text-fill: navy;");
        textArea.setMaxSize(500, 200);
        textArea.setTranslateX(20);

        seatNumberTraversalRadio.setOnMouseClicked(e -> {
            try{
            if(String.valueOf(tawjihiDS.forAvlSeatString())!=null) {
                textArea.setText(String.valueOf(tawjihiDS.forAvlSeatString()));
                avlHeight.setText("Height: " + tawjihiDS.avlTreeSeat.printHeight());
            }
            else{
                textArea.setText("");
                avlHeight.setText("");
            }
            }catch(NullPointerException ex){
                ex.printStackTrace();
                textArea.setText("");
                avlHeight.setText("");

            }
            //textArea.setText(tawjihiDS.getAll(55));

        });

        doublyTraversalRadio.setOnMouseClicked(e -> {
            textArea.setText(String.valueOf(tawjihiDS.toString()));
            avlHeight.setText("");

        });


        averagesTraversalRadio.setOnMouseClicked(e -> {
            textArea.setText(String.valueOf(tawjihiDS.forAvlAverageString()));
            avlHeight.setText("Height: " + tawjihiDS.avlTreeAverage.printHeight());
        });


        Button AVLBack = new Button("Back");
        AVLBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        AVLBack.setTextFill(Color.DARKBLUE);
        AVLBack.setTranslateX(50);
        AVLBack.setOnAction(e -> {
            stage.setScene(main);
            textArea.setText("");
            avlHeight.setText("");

        });

        GridPane AVLPane = new GridPane();
        AVLPane.addRow(1, askUserTraversalPane);
        AVLPane.addRow(3, doublyTraversalRadio);
        AVLPane.addRow(4, seatNumberTraversalRadio);
        AVLPane.addRow(5, averagesTraversalRadio);
        AVLPane.addRow(6, textArea);
        AVLPane.addRow(7, avlHeight);
        AVLPane.addRow(8, AVLBack);
        AVLPane.setAlignment(Pos.CENTER);
        AVLPane.setVgap(10);

        traversalPane.setCenter(AVLPane);

        AVLTraverse = new Scene(traversalPane, 560, 600);
        // end traverse scene

        // start getAll scene
        BorderPane getAllPane = new BorderPane();
        Label askUserAverage = new Label("Insert An Average");
        askUserAverage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        askUserAverage.setTextFill(Color.DARKBLUE);

        Button averageButton = new Button("Get All Students With That Average");
        averageButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        averageButton.setTextFill(Color.DARKBLUE);

        Label informUserAverage = new Label();
        informUserAverage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        informUserAverage.setTextFill(Color.DARKBLUE);
        informUserAverage.setTranslateX(20);

        TextField averageText = new TextField();
        averageText.setTranslateX(-370);

        TextArea textAreaAverage = new TextArea();
        textAreaAverage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        textAreaAverage.setStyle("-fx-text-fill: navy;");
        textAreaAverage.setMaxSize(500, 200);
        textAreaAverage.setTranslateX(20);

        averageButton.setOnAction(e -> {
            textAreaAverage.setText(String.valueOf(tawjihiDS.getAll(Double.parseDouble(averageText.getText()))));
            if(tawjihiDS.getAll(Double.parseDouble(averageText.getText())).equals("")){
                informUserAverage.setText("There Are No Students With That Average!!");
            }
            else{
                informUserAverage.setText("");
            }
        });
        averageButton.setTranslateX(20);

        Button getAllBack = new Button("Back");
        getAllBack.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        getAllBack.setTextFill(Color.DARKBLUE);
        getAllBack.setTranslateX(20);
        getAllBack.setOnAction(e -> {
            stage.setScene(main);
            textAreaAverage.setText("");
            averageText.setText("");
            informUserAverage.setText("");

        });

        GridPane getAllGridPane = new GridPane();
        getAllGridPane.addRow(1, askUserAverage, averageText);
        getAllGridPane.addRow(3, textAreaAverage);
        getAllGridPane.addRow(4, informUserAverage);
        getAllGridPane.addRow(5, averageButton);
        getAllGridPane.addRow(6, getAllBack);
        getAllGridPane.setAlignment(Pos.CENTER);
        getAllGridPane.setVgap(10);

        getAllPane.setCenter(getAllGridPane);
        getAll = new Scene(getAllPane, 680, 600);
        //end getAll scene


        scene = new Scene(mainBox, 320, 240);
        stage.setTitle("Third Data Structure Project!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}