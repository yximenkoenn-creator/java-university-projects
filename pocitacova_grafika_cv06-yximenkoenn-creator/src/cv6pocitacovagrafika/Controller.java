package cv6pocitacovagrafika;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class Controller implements Initializable {

    @FXML
    private TableView<Student> tableViewStudenti;

    @FXML
    private TableColumn<Student, Integer> tableColumnId;

    @FXML
    private TableColumn<Student, String> tableColumnJmeno;

    @FXML
    private TableColumn<Student, String> tableColumnPrijmeni;

    @FXML
    private TableColumn<Student, Integer> tableColumnRocnik;

    @FXML
    private PieChart pieChart;

    private ObservableList<Student> seznamStudentu = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        seznamStudentu.add(new Student(123456, "Anna", "Yukhymenko", 1));
        seznamStudentu.add(new Student(333333, "Vojtech", "Mak", 2));
        seznamStudentu.add(new Student(666666, "David", "Maly", 2));
        seznamStudentu.add(new Student(787998, "Karel", "Capek", 3));
        seznamStudentu.add(new Student(798989, "Eliska", "Novak", 4));

        tableViewStudenti.setItems(seznamStudentu);

        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnJmeno.setCellValueFactory(new PropertyValueFactory<>("jmeno"));
        tableColumnPrijmeni.setCellValueFactory(new PropertyValueFactory<>("prijmeni"));
        tableColumnRocnik.setCellValueFactory(new PropertyValueFactory<>("rocnik"));

        tableViewStudenti.setEditable(true);

        tableColumnId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tableColumnJmeno.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnPrijmeni.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnRocnik.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        tableColumnId.setOnEditCommit(e -> {
            Student s = e.getRowValue();
            s.setId(e.getNewValue());
            tableViewStudenti.refresh();
        });

        tableColumnJmeno.setOnEditCommit(e -> {
            Student s = e.getRowValue();
            s.setJmeno(e.getNewValue());
            tableViewStudenti.refresh();
        });

        tableColumnPrijmeni.setOnEditCommit(e -> {
            Student s = e.getRowValue();
            s.setPrijmeni(e.getNewValue());
            tableViewStudenti.refresh();
        });

        tableColumnRocnik.setOnEditCommit(e -> {
            Student s = e.getRowValue();
            s.setRocnik(e.getNewValue());
            tableViewStudenti.refresh();
            obnovGraf();
        });

        obnovGraf();
    }

    @FXML
    private void handlePridejStudenta(ActionEvent event) {
        try {
            TextInputDialog d1 = new TextInputDialog();
            d1.setTitle("Novy student");
            d1.setHeaderText("Zadej ID");
            Optional<String> r1 = d1.showAndWait();
            if (!r1.isPresent()) {
                return;
            }
            int id = Integer.parseInt(r1.get().trim());

            TextInputDialog d2 = new TextInputDialog();
            d2.setTitle("Novy student");
            d2.setHeaderText("Zadej jmeno");
            Optional<String> r2 = d2.showAndWait();
            if (!r2.isPresent()) {
                return;
            }
            String jmeno = r2.get().trim();

            TextInputDialog d3 = new TextInputDialog();
            d3.setTitle("Novy student");
            d3.setHeaderText("Zadej prijmeni");
            Optional<String> r3 = d3.showAndWait();
            if (!r3.isPresent()) {
                return;
            }
            String prijmeni = r3.get().trim();

            TextInputDialog d4 = new TextInputDialog();
            d4.setTitle("Novy student");
            d4.setHeaderText("Zadej rocnik");
            Optional<String> r4 = d4.showAndWait();
            if (!r4.isPresent()) {
                return;
            }
            int rocnik = Integer.parseInt(r4.get().trim());

            if (jmeno.isEmpty() || prijmeni.isEmpty()) {
                upozorneni("Jmeno a prijmeni nesmi byt prazdne.");
                return;
            }

            if (rocnik < 1 || rocnik > 5) {
                upozorneni("Rocnik musi byt od 1 do 5.");
                return;
            }

            seznamStudentu.add(new Student(id, jmeno, prijmeni, rocnik));
            obnovGraf();

        } catch (NumberFormatException e) {
            upozorneni("ID a rocnik musi byt cisla.");
        }
    }

    @FXML
    private void handleSmazStudenta(ActionEvent event) {
        Student vybranyStudent = tableViewStudenti.getSelectionModel().getSelectedItem();

        if (vybranyStudent == null) {
            upozorneni("Nejdriv vyber studenta v tabulce.");
            return;
        }

        seznamStudentu.remove(vybranyStudent);
        obnovGraf();
    }

    private void obnovGraf() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        int prvni = 0;
        int druhy = 0;
        int treti = 0;
        int ctvrty = 0;
        int paty = 0;

        for (Student s : seznamStudentu) {
            if (s.getRocnik() == 1) {
                prvni++;
            }
            if (s.getRocnik() == 2) {
                druhy++;
            }
            if (s.getRocnik() == 3) {
                treti++;
            }
            if (s.getRocnik() == 4) {
                ctvrty++;
            }
            if (s.getRocnik() == 5) {
                paty++;
            }
        }

        data.add(new PieChart.Data("1. rocnik [" + prvni + "]", prvni));
        data.add(new PieChart.Data("2. rocnik [" + druhy + "]", druhy));
        data.add(new PieChart.Data("3. rocnik [" + treti + "]", treti));
        data.add(new PieChart.Data("4. rocnik [" + ctvrty + "]", ctvrty));
        data.add(new PieChart.Data("5. rocnik [" + paty + "]", paty));

        pieChart.setData(data);
    }

    private void upozorneni(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}