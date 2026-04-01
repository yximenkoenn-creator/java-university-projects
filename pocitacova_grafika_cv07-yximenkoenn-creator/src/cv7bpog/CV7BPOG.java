package cv7bpog;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CV7BPOG extends Application {

    private TreeView<String> treeView;

    private Image rootImage;
    private Image europeImage;
    private Image asiaImage;
    private Image americaImage;
    private Image africaImage;
    private Image australiaImage;
    private Image countryImage;

    @Override
    public void start(Stage stage) {
        loadImages();

        TreeItem<String> rootItem = createTree();

        treeView = new TreeView<>(rootItem);
        treeView.setEditable(true);
        treeView.setCellFactory(TextFieldTreeCell.forTreeView());
        treeView.setContextMenu(createContextMenu());

        StackPane root = new StackPane(treeView);
        Scene scene = new Scene(root, 520, 420);

        stage.setTitle("Seznam států");
        stage.setScene(scene);
        stage.show();
    }

    private void loadImages() {
        rootImage = loadImage("World_16.png");
        europeImage = loadImage("Europe_16.png");
        asiaImage = loadImage("Asia_16.png");
        americaImage = loadImage("America_16.png");
        africaImage = loadImage("Africa_16.png");
        australiaImage = loadImage("Australia_16.png");
        countryImage = loadImage("Unknown_16.png");
    }

    private List<Country> createCountryList() {
        return Arrays.asList(
                new Country("Česko", "Evropa"),
                new Country("Polsko", "Evropa"),
                new Country("Německo", "Evropa"),
                new Country("Čína", "Asie"),
                new Country("Japonsko", "Asie"),
                new Country("Kanada", "Amerika"),
                new Country("Mexiko", "Amerika"),
                new Country("Egypt", "Afrika"),
                new Country("Madagaskar", "Afrika")
        );
    }

    private TreeItem<String> createTree() {
        TreeItem<String> rootItem = new TreeItem<>("Seznam států", createIcon(rootImage));
        rootItem.setExpanded(true);

        Map<String, TreeItem<String>> continentMap = new LinkedHashMap<>();

        for (Country country : createCountryList()) {
            String continentName = country.getContinent();

            TreeItem<String> continentItem = continentMap.get(continentName);

            if (continentItem == null) {
                continentItem = new TreeItem<>(
                        continentName,
                        createIcon(getContinentImage(continentName))
                );

                continentMap.put(continentName, continentItem);
                rootItem.getChildren().add(continentItem);
            }

            TreeItem<String> countryItem = new TreeItem<>(country.getName());
            continentItem.getChildren().add(countryItem);
        }

        return rootItem;
    }

    private ContextMenu createContextMenu() {
        MenuItem addItem = new MenuItem("Add new ...");
        MenuItem removeItem = new MenuItem("Remove");

        addItem.setOnAction(e -> addItem());
        removeItem.setOnAction(e -> removeItem());

        return new ContextMenu(addItem, removeItem);
    }

    private void addItem() {
        TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            return;
        }

        if (selectedItem == treeView.getRoot()) {
            selectedItem.getChildren().add(
                    new TreeItem<>("New continent", createIcon(countryImage))
            );
        } else {
            selectedItem.getChildren().add(new TreeItem<>("New country"));
        }

        selectedItem.setExpanded(true);
    }

    private void removeItem() {
        TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            return;
        }

        TreeItem<String> parent = selectedItem.getParent();

        if (parent != null) {
            parent.getChildren().remove(selectedItem);
        }
    }

    private Image getContinentImage(String continentName) {
        switch (continentName) {
            case "Evropa":
                return europeImage;
            case "Asie":
                return asiaImage;
            case "Amerika":
                return americaImage;
            case "Afrika":
                return africaImage;
            case "Austrálie a Oceánie":
                return australiaImage;
            default:
                return countryImage;
        }
    }

    private Image loadImage(String fileName) {
        try {
            return new Image(getClass().getResourceAsStream(fileName));
        } catch (Exception e) {
            System.out.println("Ikonu nenalezeno: " + fileName);
            return null;
        }
    }

    private ImageView createIcon(Image image) {
        if (image == null) {
            return null;
        }
        return new ImageView(image);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
