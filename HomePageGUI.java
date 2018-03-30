/*
 * Written by: Phillip Sorrell Radke, Timothy James, Samantha Krall
 * Written for: Bug Smasher's group
 * CST-135
 * This class is for the main GUI of the application. 
 *
 */

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.shape.Line;

public class HomePageGUI extends Application {
    
    private final Global_Inventory_Management dispenser = new Global_Inventory_Management();

    //Create the dispenser. Generates 5 Products in an array within the dispenser class.
    //private final Dispenser dispenser = new Dispenser();
    private final Restock restock = new Restock();
    //Get background image and add it to the stack pane
    private final Image bgImage = new Image("file:src/DispenserDesign/Background&ButtonImages/Background.png");
    private final Button backButton = new Button("", new ImageView(
            new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/BackButton.png")));
    private final Button adminButton = new Button("", new ImageView(
            new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/Admin.png")));
    private final Button cartButton = new Button("", new ImageView(
            new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/Cart.png")));
    private final Button homeButton = new Button("", new ImageView(
            new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/HomeButton.png")));
    private final Image bgCartImage = new Image(
            "file:src/DispenserDesign/Background&ButtonImages/FinalSalePage/FinalSaleBackground.png");
    private final Image bgThankImage = new Image(
            "file:src/DispenserDesign/Background&ButtonImages/ThankYou.png");
    private final Image bgInventory = new Image(
            "file:src/DispenserDesign/Background&ButtonImages/InventoryMan.png");
    private final Image bgInvAdd = new Image(
            "file:src/DispenserDesign/Background&ButtonImages/BackgroundInv.png");
    private final Image searchButton = new Image(
            "file:src/DispenserDesign/Background&ButtonImages/Search.png");
    private StackPane backgroundPane = new StackPane();
    private TextField inventoryIn = new TextField();
    private TextField searchField;

    // Private Product[] shoppingCart = new Product[10];
    private ObservableList<Product> shoppingCart = FXCollections.observableArrayList();
    private ObservableList<Product> invManagement = FXCollections.observableArrayList();

    private TableView<Product> inventory = new TableView<>();
    private TableView<Product> table = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
//        System.out.println("Before Sort");
//        dispenser.displayProducts();
//        Global_Inventory_Management.sortName(dispenser.productsForSale);
//        System.out.println("\n\n\n\nAfter Sort");
//        dispenser.displayProducts();
//        Scene scene = new Scene(homePage());
//        primaryStage.setResizable(false);
//        primaryStage.setScene(scene);
//        primaryStage.show();
        
        Scene scene = new Scene(homePage());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Display the home page
    private StackPane homePage() {
        
        HBox searchPane = new HBox();
        searchPane.setSpacing(75);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(130);
        buttonPane.setPadding(new Insets(0, 10, 0, 10));
        buttonPane.getChildren().addAll(homeButton, adminButton, cartButton);

        cartButton.setBackground(Background.EMPTY);
        cartButton.setOnAction(e -> {
            cartButton.getScene().setRoot(shoppingCart());
        });

        homeButton.setBackground(Background.EMPTY);
        homeButton.setOnAction(e -> {
            homeButton.getScene().setRoot(homePage());
        });

        adminButton.setBackground(Background.EMPTY);
        adminButton.setOnAction(e -> {
            adminButton.getScene().setRoot(adminPage());
        });

        //IMAGE CREATION
        Image drinksCategoryImage = new Image(
                "file:src/DispenserDesign/Background&ButtonImages/StarterPage/DrinksButton.png");

        Image gumCategoryImage = new Image(
                "file:src/DispenserDesign/Background&ButtonImages/StarterPage/GumButton.png");

        Image sweetsCategoryImage = new Image(
                "file:src/DispenserDesign/Background&ButtonImages/StarterPage/SweetsButton.png");
        //END IMAGE CREATION
        
//        Button searchBtn = new Button("", new ImageView(searchButton));
//	//searchBtn.setBackground(Background.EMPTY);
//        
//        searchField = new TextField();
//        
//        //TODO: ADD THE SEARCH RESULTS STAGE TO SHOW THE FOUND ITEM
//        searchBtn.setOnAction(e -> {
//            int result = Global_Inventory_Management.nameSearch(dispenser.productsForSale, searchField.getText());
//
//            if (result >= 0) {
//                //searchResultsStage.show();
//            } else {
//                //Create an error stage to display this message
//                System.out.println("Item was not found");
//            }
//            System.out.println(result);
//       });
//
//        searchPane.getChildren().addAll(searchField, searchBtn);

        //Load drinks category
        Button drinksCategoryButton = new Button(
                "", new ImageView(drinksCategoryImage));

        drinksCategoryButton.setBackground(Background.EMPTY);

        drinksCategoryButton.setOnAction(e -> {
            drinksCategoryButton.getScene().setRoot(drinksCategory());
        });

        //Load gum category
        Button gumCategoryButton = new Button(
                "", new ImageView(gumCategoryImage));

        gumCategoryButton.setBackground(Background.EMPTY);

        gumCategoryButton.setOnAction(e -> {
            gumCategoryButton.getScene().setRoot(gumCategory());
        });

        //Load Sweets Category page 
        Button sweetsCategoryButton = new Button(
                "", new ImageView(sweetsCategoryImage));

        sweetsCategoryButton.setBackground(Background.EMPTY);

        sweetsCategoryButton.setOnAction(e -> {
            sweetsCategoryButton.getScene().setRoot(sweetsCategory());
        });

        //END BUTTON CREATION
        //This is where the buttons for the three categories will sit.
        GridPane homePane = new GridPane();
        homePane.setVgap(10);
        homePane.setHgap(10);
        //Max size is set because if not, the GridPane will not center in the StackPane
        homePane.setMaxSize(575, 293);

        //Add the buttons to the GridPane.
        homePane.addColumn(0, drinksCategoryButton, gumCategoryButton, sweetsCategoryButton);

        VBox vBox = new VBox();
        vBox.setSpacing(150);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(buttonPane, homePane, searchPane);

        backgroundPane.getChildren().addAll(new ImageView(bgImage), vBox);

        return backgroundPane;
    }

    //Displays the Drinks categories page.
    private StackPane drinksCategory() {

        //Set Grid pane settings. 
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setMaxSize(575, 293);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(460);
        buttonPane.setPadding(new Insets(0, 10, 0, 10));
        buttonPane.getChildren().addAll(homeButton, cartButton);

        cartButton.setBackground(Background.EMPTY);
        cartButton.setOnAction(e -> {
            cartButton.getScene().setRoot(shoppingCart());
        });

        homeButton.setBackground(Background.EMPTY);
        homeButton.setOnAction(e -> {
            homeButton.getScene().setRoot(homePage());
        });

        VBox vBox = new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(buttonPane, pane);

//		Button[] button = new Button[dispenser.productsForSale.length];
//		Line[] line = new Line[dispenser.productsForSale.length];
        int rowControl = 0; //used to limit how many items are in a row.
        // search the array for objects of Drink type, then create and display their buttons. 
        // add event handler here for sales page
        for (int i = 0; i < dispenser.productsForSale.length; i++) {

            if (dispenser.getProduct(i) instanceof Drink) {

                StackPane prodPics = new StackPane();

                Drink temp = (Drink) dispenser.getProduct(i);

                Button button = new Button("", new ImageView(temp.image));
                button.setBackground(Background.EMPTY);

                ImageView temp2 = new ImageView(temp.image);
                prodPics.getChildren().addAll(temp2, button);

                Line line = new Line(button.getLayoutX() + temp.image.getWidth() / 2,
                        button.getLayoutY() + temp.image.getHeight() / 2,
                        backgroundPane.getWidth(), 0);

                PathTransition pt = new PathTransition();
                pt.setDuration(Duration.millis(1000));
                pt.setPath(line);
                pt.setNode(button.getGraphic());
                button.setOnMousePressed(e -> {
                    //search the list for existing item. If it is the first of its kind, set the quantity in the shopping
                    //cart to 1, otherwise add 1 to the existing item

                    //figure out why the items added to cart add multiple instances of item in the table
                    if (!shoppingCart.contains(temp)) {
                        shoppingCart.add(new Drink(temp));
                        shoppingCart.get(shoppingCart.indexOf(temp)).setQuantity(1);
                    } else {
                        shoppingCart.get(shoppingCart.indexOf(temp)).setQuantity(
                                shoppingCart.get(shoppingCart.indexOf(temp)).quantity + 1);
                    }
                    //subtract one from the machine inventory.
                    temp.quantity--;
                    pt.play();

                });

                Text text = new Text(temp.getName() + "\n$" + temp.getPrice());
                text.setFill(Color.WHITE);
                text.setTextAlignment(TextAlignment.CENTER);
                text.setTranslateX(65);
                text.setLayoutX(i);

                if (rowControl < 3) {
                    pane.addRow(0, prodPics);
                    pane.addRow(1, text);
                } else {
                    pane.addRow(2, prodPics);
                    pane.addRow(3, text);
                }
                rowControl++;
            }
        }

        backgroundPane.getChildren().addAll(new ImageView(bgImage), vBox);

        return backgroundPane;
    }

    public StackPane gumCategory() {

        //Set Grid pane settings. 
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setMaxSize(575, 293);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(460);
        buttonPane.setPadding(new Insets(0, 10, 0, 10));
        buttonPane.getChildren().addAll(homeButton, cartButton);

        cartButton.setBackground(Background.EMPTY);
        cartButton.setOnAction(e -> {
            cartButton.getScene().setRoot(shoppingCart());
        });

        homeButton.setBackground(Background.EMPTY);
        homeButton.setOnAction(e -> {
            homeButton.getScene().setRoot(homePage());
        });

        // search the array for objects of Gum type, then create and display their buttons. 
        // add event handler here for sales page
        for (int i = 0; i < dispenser.productsForSale.length; i++) {
            if (dispenser.getProduct(i) instanceof Gum) {
                Gum temp = (Gum) dispenser.getProduct(i);

                Button button = new Button("", new ImageView(temp.image));
                button.setBackground(Background.EMPTY);

                StackPane prodPics = new StackPane();

                ImageView temp2 = new ImageView(temp.image);
                prodPics.getChildren().addAll(temp2, button);

                Line line = new Line(button.getLayoutX() + temp.image.getWidth() / 2,
                        button.getLayoutY() + temp.image.getHeight() / 2,
                        backgroundPane.getWidth(), 0);

                PathTransition pt = new PathTransition();
                pt.setDuration(Duration.millis(1000));
                pt.setPath(line);
                pt.setNode(button.getGraphic());

                button.setOnMouseReleased(e -> {
                    //search the list for existing item. If it is the first of its kind, set the quantity in the shopping
                    //cart to 1, otherwise add 1 to the existing item
                    if (!shoppingCart.contains(temp)) {
                        shoppingCart.add(new Gum(temp));
                        System.out.println("Not Found");
                        shoppingCart.get(shoppingCart.indexOf(temp)).setQuantity(1);
                    } else {
                        shoppingCart.get(shoppingCart.indexOf(temp)).setQuantity(
                                shoppingCart.get(shoppingCart.indexOf(temp)).quantity + 1);
                    }
                    //subtract one from the machine inventory.
                    temp.quantity--;
                    pt.play();
                });

                Text text = new Text(temp.getName() + "\n" + temp.getFlavor() + "\n$" + temp.getPrice());
                text.setFill(Color.WHITE);
                text.setTextAlignment(TextAlignment.CENTER);
                text.setTranslateX(65);
                text.setLayoutX(i);

                pane.addRow(0, prodPics);
                pane.addRow(1, text);
            }
        }

        VBox vBox = new VBox();
        vBox.setSpacing(200);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(buttonPane, pane);

        //  if (inventory.getItems().indexOf(vBox) <= 3) {
        //     System.out.println("Please restock");
        // }
        backgroundPane.getChildren().addAll(new ImageView(bgImage), vBox);

        return backgroundPane;
    }

    private StackPane sweetsCategory() {

        //Set Grid pane settings. 
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setMaxSize(575, 293);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(460);
        buttonPane.setPadding(new Insets(0, 10, 0, 10));
        buttonPane.getChildren().addAll(homeButton, cartButton);

        cartButton.setBackground(Background.EMPTY);
        cartButton.setOnAction(e -> {
            cartButton.getScene().setRoot(shoppingCart());
        });

        homeButton.setBackground(Background.EMPTY);
        homeButton.setOnAction(e -> {
            homeButton.getScene().setRoot(homePage());
        });

        int rowControl = 0; //used to limit how many items are in a row.

        // search the array for objects of Snack type, then create and display their buttons. 
        // add event handler here for sales page
        for (int i = 0; i < dispenser.productsForSale.length; i++) {

            if (dispenser.getProduct(i) instanceof Snack) {
                Snack temp = (Snack) dispenser.getProduct(i);

                StackPane prodPics = new StackPane();

                Button button = new Button("", new ImageView(temp.image));
                button.setBackground(Background.EMPTY);

                ImageView temp2 = new ImageView(temp.image);
                prodPics.getChildren().addAll(temp2, button);

                Line line = new Line(button.getLayoutX() + temp.image.getWidth() / 2,
                        button.getLayoutY() + temp.image.getHeight() / 2,
                        backgroundPane.getWidth(), 0);

                PathTransition pt = new PathTransition();
                pt.setDuration(Duration.millis(1000));
                pt.setPath(line);
                pt.setNode(button.getGraphic());

                button.setOnMouseReleased(e -> {
                    //search the list for existing item. If it is the first of its kind, set the quantity in the shopping
                    //cart to 1, otherwise add 1 to the existing item
                    if (!shoppingCart.contains(temp)) {
                        shoppingCart.add(temp instanceof Chips
                                ? new Chips((Chips) temp)
                                : new Candy((Candy) temp));
                        shoppingCart.get(shoppingCart.indexOf(temp)).setQuantity(1);
                    } else {
                        shoppingCart.get(shoppingCart.indexOf(temp)).setQuantity(
                                shoppingCart.get(shoppingCart.indexOf(temp)).quantity + 1);
                    }
                    //subtract one from the machine inventory.
                    temp.quantity--;
                    System.out.println(temp.quantity);
                    pt.play();
                });

                Text text = new Text(temp.getName() + "\n$" + temp.getPrice());
                text.setFill(Color.WHITE);
                text.setTextAlignment(TextAlignment.CENTER);
                text.setTranslateX(65);
                text.setLayoutX(i);

                if (rowControl < 3) {
                    pane.addRow(0, prodPics);
                    pane.addRow(1, text);
                } else if (rowControl >= 3 && rowControl < 6) {
                    pane.addRow(2, prodPics);
                    pane.addRow(3, text);
                } else if (rowControl >= 6 && rowControl < 9) {
                    pane.addRow(4, prodPics);
                    pane.addRow(5, text);
                }
                rowControl++;
            }
        }

        VBox vBox = new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(buttonPane, pane);

        backgroundPane.getChildren().addAll(new ImageView(bgImage), vBox);

        return backgroundPane;
    }

    public StackPane shoppingCart() {

        backgroundPane = new StackPane();

        // PANE CREATION
        // Home and Back Buttons Pane
        HBox buttonPane = new HBox();
        buttonPane.setSpacing(460);
        buttonPane.setPadding(new Insets(0, 10, 0, 10));
        buttonPane.getChildren().addAll(homeButton);

        homeButton.setBackground(Background.EMPTY);
        homeButton.setOnAction(e -> {
            homeButton.getScene().setRoot(homePage());
        });

        // TableView Cart Pane
        GridPane cartView = new GridPane();
        cartView.setHgap(9);
        cartView.setVgap(1);
        cartView.setOpacity(0.9);
        cartView.setPadding(new Insets(0, 10, 0, 10));
        
        // Total Price Pane
        GridPane totalPriceV = new GridPane();
        totalPriceV.setHgap(5);
        totalPriceV.setVgap(1);
        totalPriceV.setPadding(new Insets(0, 10, 0, 10));

        // Delete and Purchase Buttons Pane
        GridPane buttons2 = new GridPane();
        buttons2.setHgap(5);
        buttons2.setVgap(1);
        buttons2.setPadding(new Insets(0, 10, 0, 10));

        // Main Pane for arrangement
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(buttonPane, cartView, totalPriceV, buttons2);

        // BUTTON CREATION
        // Purchase Button
        Button purchase = new Button("", new ImageView(
                new Image("file:src/DispenserDesign/Background&ButtonImages/FinalSalePage/Purchase.png")));
        purchase.setBackground(Background.EMPTY);
        purchase.setMaxSize(5, 10);
        purchase.setMinSize(5, 10);
        buttons2.add(purchase, 87, 10);
        purchase.setBackground(Background.EMPTY);
        purchase.setOnAction(e -> {
            thankYou();
        });

        // Delete Button
        Button delete = new Button("", new ImageView(
                new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/Remove.png")));
        delete.setOnAction(e -> deleteClicked());
        delete.setBackground(Background.EMPTY);
        buttons2.add(delete, 15, 10);

        // CREATE TABLE FOR DISPLAYING CART
        // Name column
        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Price column
        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setMinWidth(20);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Weight column
        TableColumn<Product, Double> weightCol = new TableColumn<>("Weight");
        weightCol.setMinWidth(50);
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));

        // Quantity column
        TableColumn<Product, Double> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setMinWidth(20);
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        table.setItems(shoppingCart);
        table.setBackground(Background.EMPTY);
        table.setMaxSize(718, 343);
        table.setMinSize(718, 343);
        table.getColumns().addAll(nameCol, priceCol, weightCol, quantityCol);
        cartView.add(table, 11, 165);

        ObservableList<Product> productsSelected, individualProduct;

        productsSelected = table.getItems();
        Product prod = null;
        double grandTotal = 0;
        
        for (int i = 0; i < productsSelected.size(); i++) {
            prod = productsSelected.get(0);
            int quantity = prod.quantity;
            double price = prod.price;
            double total = quantity * price;
            grandTotal = grandTotal + total;
        }

        DecimalFormat currency = new DecimalFormat("0.00");

        TextField totalPrice = new TextField(currency.format(grandTotal));
        totalPrice.setEditable(false);
        totalPrice.setStyle("-fx-text-fill: white");
        totalPrice.setFont(Font.font("Times", 40));
        totalPrice.setBackground(Background.EMPTY);
        totalPrice.setAlignment(Pos.BASELINE_LEFT);
        totalPriceV.add(totalPrice, 75, 0);

        backgroundPane.getChildren().addAll(new ImageView(bgCartImage), vBox);

        return backgroundPane;
    }

    // DELETE BUTTON CLICKED
    public void deleteClicked() {
        
        ObservableList<Product> productSelected;
        productSelected = table.getSelectionModel().getSelectedItems();
        shoppingCart.get(shoppingCart.indexOf(productSelected.get(0))).setQuantity(
                shoppingCart.get(shoppingCart.indexOf(productSelected.get(0))).quantity - 1);
        table.setItems(shoppingCart);
        table.refresh();
        
        //ObservableList<Product> productSelected, allProducts;
        //allProducts = table.getItems();
        //productSelected = table.getSelectionModel().getSelectedItems();

       // productSelected.forEach(allProducts::remove);
    }

    public StackPane adminPage() {

        backgroundPane = new StackPane();

        // PANE CREATION
        // Home Button Pane
        HBox buttonPane = new HBox();
        buttonPane.setSpacing(460);
        buttonPane.setPadding(new Insets(0, 10, 0, 10));
        buttonPane.getChildren().addAll(homeButton);

        homeButton.setBackground(Background.EMPTY);
        homeButton.setOnAction(e -> {
            homeButton.getScene().setRoot(homePage());
        });

        // TableView Cart Pane
        GridPane cartView = new GridPane();
        cartView.setHgap(9);
        cartView.setVgap(9);
        cartView.setOpacity(0.9);
        cartView.setPadding(new Insets(0, 10, 0, 10));

        // Delete and Add Buttons Pane
        GridPane buttons2 = new GridPane();
        buttons2.setHgap(10);
        buttons2.setVgap(10);
        buttons2.setPadding(new Insets(0, 10, 0, 10));

        // Main Pane for arrangement
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(buttonPane, cartView, buttons2);

        // BUTTON CREATION
        // Add Inventory Button
        Button addInventory = new Button("", new ImageView(
                new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/Add.png")));
        addInventory.setBackground(Background.EMPTY);
        addInventory.setMaxSize(5, 10);
        addInventory.setMinSize(5, 10);
        buttons2.add(addInventory, 47, 3);
        addInventory.setBackground(Background.EMPTY);
        addInventory.setOnAction(e -> {
            addInventory();
        });

        // Delete Button
        Button delete = new Button("", new ImageView(
                new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/Remove.png")));
        delete.setOnAction(e -> deleteInventory());
        delete.setBackground(Background.EMPTY);
        buttons2.add(delete, 8, 3);

        // CREATE TABLE FOR DISPLAYING Inventory Management
        // Name column
        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Price column
        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setMinWidth(20);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Weight column
        TableColumn<Product, Double> weightCol = new TableColumn<>("Weight");
        weightCol.setMinWidth(50);
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));

        // Quantity column
        TableColumn<Product, Double> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setMinWidth(20);
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        inventory.setItems(invManagement);
        inventory.setBackground(Background.EMPTY);
        inventory.setMaxSize(718, 414);
        inventory.setMinSize(718, 414);
        inventory.getColumns().addAll(nameCol, priceCol, weightCol, quantityCol);
        cartView.add(inventory, 11, 18);

        invManagement.setAll(dispenser.productsForSale);

        backgroundPane.getChildren().addAll(new ImageView(bgInventory), vBox);

        return backgroundPane;
    }

    public void addInventory() {

        Pane addInv = new Pane();

        GridPane gP = new GridPane();
        gP.setHgap(9);
        gP.setVgap(9);
        gP.setOpacity(0.9);
        gP.setPadding(new Insets(0, 10, 0, 10));

        // Need to style this more and add a exit stage method upon clicking add
        inventoryIn.setEditable(true);
        inventoryIn.setStyle("-fx-text-fill: black");
        inventoryIn.setFont(Font.font("Times", 20));
        inventoryIn.setAlignment(Pos.BASELINE_CENTER);
        inventoryIn.setText("Type a quantity to add");

        gP.add(inventoryIn, 1, 10);

        Button addInventory = new Button("", new ImageView(
                new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/Add.png")));
        addInventory.setBackground(Background.EMPTY);
        addInventory.setMaxSize(5, 10);
        addInventory.setMinSize(5, 10);
        gP.add(addInventory, 2, 20);
        addInventory.setBackground(Background.EMPTY);
        addInventory.setOnAction(e -> {
            addEventHandler();
        });

        addInv.getChildren().addAll(new ImageView(bgInvAdd), gP);

        Scene scene = new Scene(addInv, 400, 250);
        Stage stage = new Stage();
        stage.setTitle("Add Inventory");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
    
    public void deleteInventory() {

        Pane addInv = new Pane();

        GridPane gP = new GridPane();
        gP.setHgap(9);
        gP.setVgap(9);
        gP.setOpacity(0.9);
        gP.setPadding(new Insets(0, 10, 0, 10));

        // Need to style this more and add a exit stage method upon clicking add
        inventoryIn.setEditable(true);
        inventoryIn.setStyle("-fx-text-fill: black");
        inventoryIn.setFont(Font.font("Times", 20));
        inventoryIn.setAlignment(Pos.BASELINE_CENTER);
        inventoryIn.setText("Type a quantity to remove");

        gP.add(inventoryIn, 1, 10);

        Button deleteInventory = new Button("", new ImageView(
                new Image("file:src/DispenserDesign/Background&ButtonImages/NavigationButtons/Remove.png")));
        deleteInventory.setBackground(Background.EMPTY);
        deleteInventory.setMaxSize(5, 10);
        deleteInventory.setMinSize(5, 10);
        gP.add(deleteInventory, 2, 20);
        deleteInventory.setBackground(Background.EMPTY);
        deleteInventory.setOnAction(e -> {
            deleteEventHandler();
        });

        addInv.getChildren().addAll(new ImageView(bgInvAdd), gP);

        Scene scene = new Scene(addInv, 400, 250);
        Stage stage = new Stage();
        stage.setTitle("Add Inventory");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
    // ADD INVENTORY
    public void addEventHandler() {
        String value = inventoryIn.getText();
        int intValue = Integer.parseInt(value);

        ObservableList<Product> productSelected;
        productSelected = inventory.getSelectionModel().getSelectedItems();
        invManagement.get(invManagement.indexOf(productSelected.get(0))).setQuantity(
                invManagement.get(invManagement.indexOf(productSelected.get(0))).quantity + intValue);
        inventory.setItems(invManagement);
        inventory.refresh();

    }
    
    //DELETE INVENTORY
    public void deleteEventHandler() {
        String value = inventoryIn.getText();
        int intValue = Integer.parseInt(value);

        ObservableList<Product> productSelected;
        productSelected = inventory.getSelectionModel().getSelectedItems();
        invManagement.get(invManagement.indexOf(productSelected.get(0))).setQuantity(
                invManagement.get(invManagement.indexOf(productSelected.get(0))).quantity - intValue);
        inventory.setItems(invManagement);
        inventory.refresh();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void thankYou() {

        Pane pane = new Pane();
        pane.getChildren().addAll(new ImageView(bgThankImage));

        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setTitle("Checkout");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
