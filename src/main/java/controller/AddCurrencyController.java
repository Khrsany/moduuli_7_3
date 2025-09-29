package controller;

import entity.Currency;
import dao.CurrencyDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddCurrencyController {

    @FXML
    private TextField codeField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField rateField;

    private CurrencyDao currencyDao = new CurrencyDao();
    private MainController mainController;  // viittaus pääkontrolleriin

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void onSaveCurrency() {
        try {
            String code = codeField.getText();
            String name = nameField.getText();
            double rate = Double.parseDouble(rateField.getText());

            Currency currency = new Currency();
            currency.setCode(code);
            currency.setName(name);
            currency.setRate(rate);

            currencyDao.save(currency);

            System.out.println("Currency saved: " + code + " " + name + " " + rate);

            // Päivitä pääikkunan taulukko heti tallennuksen jälkeen
            if (mainController != null) {
                mainController.refreshTable();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
