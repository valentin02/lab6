package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;

public class Controller {

    @FXML
    RadioButton USD = new RadioButton();
    @FXML
    RadioButton RUB = new RadioButton();
    @FXML
    RadioButton EUR = new RadioButton();
    @FXML
    RadioButton AtoZ = new RadioButton();
    @FXML
    RadioButton ZtoA = new RadioButton();
    @FXML
    RadioButton IDl = new RadioButton();
    @FXML
    RadioButton IDh = new RadioButton();
    @FXML
    Label data = new Label();
    @FXML
    Button selectSort = new Button();
    @FXML
    TextArea textout = new TextArea();
    @FXML
    RadioButton today = new RadioButton();
    @FXML
    RadioButton yesterday = new RadioButton();
    @FXML
    RadioButton weekAgo = new RadioButton();
    @FXML
    RadioButton yearAgo = new RadioButton();
    @FXML
    ToggleGroup tglgroupLoad = new ToggleGroup();
    @FXML
    ToggleGroup tglgroupSort = new ToggleGroup();

    Minfin minfin = new Minfin();
    LocalDate day = LocalDate.now();


    public void selectSort() {

        USD.setToggleGroup(tglgroupSort);
        RUB.setToggleGroup(tglgroupSort);
        EUR.setToggleGroup(tglgroupSort);
        AtoZ.setToggleGroup(tglgroupSort);
        ZtoA.setToggleGroup(tglgroupSort);
        IDl.setToggleGroup(tglgroupSort);
        IDh.setToggleGroup(tglgroupSort);
        RadioButton selectedRadioButton = (RadioButton) tglgroupSort.getSelectedToggle();

        switch (selectedRadioButton.getText()) {
            case "USD":
                textout.setText(minfin.filterByCurrency("usd").toString());
                break;
            case "RUB":
                textout.setText(minfin.filterByCurrency("rub").toString());
                break;
            case "EUR":
                textout.setText(minfin.filterByCurrency("eur").toString());
                break;
            case "name of currency from A to Z":
                minfin.getMinfin().sort(Currency.byNameAsc);
                textout.setText(minfin.toString());
                break;
            case "name of currency from Z to A":
                minfin.getMinfin().sort(Currency.byNameDesc);
                textout.setText(minfin.toString());
                break;
            case "ID from low to high":
                minfin.getMinfin().sort(Currency.byIdAsc);
                textout.setText(minfin.toString());
                break;
            case "ID from high to low":
                minfin.getMinfin().sort(Currency.byIdDesc);
                textout.setText(minfin.toString());
                break;
        }
    }

    public void selectLoad() throws IOException {
        selectSort.setVisible(true);
        today.setToggleGroup(tglgroupLoad);
        yesterday.setToggleGroup(tglgroupLoad);
        weekAgo.setToggleGroup(tglgroupLoad);
        yearAgo.setToggleGroup(tglgroupLoad);

        RadioButton selectedRadioButton = (RadioButton) tglgroupLoad.getSelectedToggle();

        String API;
        // https://developers.minfin.com.ua/registration/
        // only 10 requests for one API key
        API = "af8702dc253dc71216f3144576db533409398758";

        String url = "https://api.minfin.com.ua/mb/" + API + "/";
        switch (selectedRadioButton.getText()) {
            case "today":
                data.setText(day.toString());
                minfin.run(url + day.toString());
                textout.setText(minfin.toString());
                break;
            case "yesterday":
                data.setText(day.minusDays(1).toString());
                minfin.run(url + day.minusDays(1).toString());
                textout.setText(minfin.toString());
                break;
            case "week ago":
                data.setText(day.minusWeeks(1).toString());
                minfin.run(url + day.minusWeeks(1).toString());
                textout.setText(minfin.toString());
                break;
            case "year ago":
                data.setText(day.minusYears(1).toString());
                minfin.run(url + day.minusYears(1).toString());
                textout.setText(minfin.toString());
                break;
        }
    }
}
