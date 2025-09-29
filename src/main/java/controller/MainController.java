package controller;

import dao.CurrencyDao;
import entity.Currency;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private TableView<Currency> currencyTable;

    @FXML
    private TableColumn<Currency, String> codeColumn;

    @FXML
    private TableColumn<Currency, String> nameColumn;

    @FXML
    private TableColumn<Currency, Double> rateColumn;

    private final CurrencyDao currencyDao = new CurrencyDao();
    private final ObservableList<Currency> currencyList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Yhdistetään taulukon sarakkeet entiteetin kenttiin
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));

        // Lataa tietokannan valuutat taulukkoon
        refreshTable();
    }

    /**
     * Päivittää taulukon sisällön tietokannasta
     */
    public void refreshTable() {
        currencyList.setAll(currencyDao.findAll());
        currencyTable.setItems(currencyList);
    }

    /**
     * Avataan uusi ikkuna valuutan lisäämiselle
     */
    @FXML
    private void onAddCurrency() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/add-currency-view.fxml"));
            Parent root = loader.load();

            // Haetaan AddCurrencyController ja annetaan sille viittaus tähän kontrolleriin
            AddCurrencyController controller = loader.getController();
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Currency");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
