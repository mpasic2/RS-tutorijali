package ba.unsa.etf.rs.tutorijal8;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;


public class Kontroler {
    private TransportDAO dao = TransportDAO.getInstance();

    public ListView<String> nameList;
    public ListView<String> surnameList;
    public ListView<String> jmbList;
    public ListView<LocalDate> birthdayList;
    public ListView<LocalDate> employedDateList;

    public ListView<String> makerList;
    public ListView<String> seriesList;
    public ListView<Integer> seatNumberList;
    public ListView<Driver> driverOneList;
    public ListView<Driver> driverTwoList;

    public TextField nameFld;
    public TextField surnameFld;
    public TextField jmbFld;
    public DatePicker birthdayDP;
    public DatePicker employedDateDP;

    public TextField makerFld;
    public TextField seriesFld;
    public Spinner seatNumberSpinn;
    public ChoiceBox<Driver> driverCB;
    public ChoiceBox<String> positionCB;
    public ChoiceBox<String> busCB;

    public Kontroler () {
    }

    private ArrayList<Driver> Drivers = new ArrayList<>();
    private ArrayList<Bus> Busses = new ArrayList<>();

    @FXML
    public void initialize() {
        dao.resetDatabase();
        updateDriverTab();
        updateBusTab();
        updateChoiceBox();
        positionCB.getItems().add("Driver one");
        positionCB.getItems().add("Driver two");
        updateChoiceBoxBus();
    }

    public void updateDriverTab(){
        nameList.setItems(FXCollections.observableArrayList());
        surnameList.setItems(FXCollections.observableArrayList());
        jmbList.setItems(FXCollections.observableArrayList());
        birthdayList.setItems(FXCollections.observableArrayList());
        employedDateList.setItems(FXCollections.observableArrayList());

        Drivers = new ArrayList<>();
        Drivers = dao.getDrivers();
        for (Driver d: Drivers){
            nameList.getItems().add(d.getName());
            surnameList.getItems().add(d.getSurname());
            jmbList.getItems().add(d.getJmb());
            birthdayList.getItems().add(d.getBirthday());
            employedDateList.getItems().add(d.getEmployedDate());
        }
    }

    public void updateBusTab (){
        makerList.setItems(FXCollections.observableArrayList());
        seriesList.setItems(FXCollections.observableArrayList());
        seatNumberList.setItems(FXCollections.observableArrayList());
        driverOneList.setItems(FXCollections.observableArrayList());
        driverTwoList.setItems(FXCollections.observableArrayList());

        Busses = new ArrayList<>();
        Busses = dao.getBusses();
        for (Bus b: Busses){
            makerList.getItems().add(b.getMaker());
            seriesList.getItems().add(b.getSerija());
            seatNumberList.getItems().add(b.getSeatNumber());
            if (b.getDriverOne()!=null) driverOneList.getItems().add(b.getDriverOne());
            if (b.getDriverTwo()!=null) driverTwoList.getItems().add(b.getDriverTwo());
        }
    }

    public void addDriver(){
        String name = nameFld.getText().trim();
        String surname= surnameFld.getText().trim();
        String jmb = jmbFld.getText().trim();
        LocalDate birthday = birthdayDP.getValue();
        LocalDate employedDate = employedDateDP.getValue();
        if (name.equals("") || surname.equals("") || jmb.equals("") ||
                birthday==null || employedDate.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Polja ne smiju biti prazna !!!");
            alert.showAndWait();
            nameFld.setText("");
            surnameFld.setText("");
            jmbFld.setText("");
            birthdayDP.setValue(null);
            employedDateDP.setValue(null);
        } else
            try {
                Driver d = new Driver(name, surname, jmb, birthday,employedDate);
                dao.addDriver(d);
                updateDriverTab();
                updateChoiceBox();
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
                jmbFld.setText("");
            }
    }

    public void deleteDriver(){
        String jmb = jmbFld.getText().trim();
        if (jmb.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("JMB ne smije biti prazan !!!");
            alert.showAndWait();
            jmbFld.setText("");
        }
        else{
            boolean ima= false;
            Driver vozac = new Driver();
            for (Driver d: Drivers){
                if (jmb.equals(d.getJmb())){
                    ima=true;
                    vozac = d;
                }
            }
            if (ima==false){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Nema vozaca sa tim jmb !");
                alert.showAndWait();
            }
            else {
                dao.deleteDriver(vozac);
                updateDriverTab();
                updateChoiceBox();
            }
        }
    }

    public void addBus(){
        String maker = makerFld.getText().trim();
        String series = seriesFld.getText().trim();
        int seatNumber = (int) seatNumberSpinn.getValue();
        if (maker.equals("") || series.equals("") || seatNumber==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Polja ne smiju biti prazna !!!");
            alert.showAndWait();
            makerFld.setText("");
            seriesFld.setText("");
        } else {
            Bus b = new Bus (maker, series, seatNumber);
            dao.addBus(b);
            updateBusTab();
            updateChoiceBoxBus();
        }
    }

    public void deleteBus(){
        String maker = makerFld.getText().trim();
        String series = seriesFld.getText().trim();
        int seatNumber = (int) seatNumberSpinn.getValue();
        if (maker.equals("") || series.equals("") || seatNumber==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Polja ne smiju biti prazna !!!");
            alert.showAndWait();
            makerFld.setText("");
            seriesFld.setText("");
        } else {
            Bus b = new Bus (maker, series, seatNumber);
            dao.deleteBus(b);
            updateBusTab();
            updateChoiceBoxBus();
        }
    }

    public void updateChoiceBox(){
        driverCB.setItems(FXCollections.observableArrayList());
        for (Driver d : Drivers)
            driverCB.getItems().add(d);
    }

    public void updateChoiceBoxBus(){
        busCB.setItems(FXCollections.observableArrayList());
        for (Bus b : Busses)
            busCB.getItems().add(b.getMaker() + " " + b.getSerija() + " " + b.getSeatNumber());
    }

    public void dodaj(){
        if (busCB.getSelectionModel().getSelectedIndex()<0 || driverCB.getSelectionModel().getSelectedIndex()<0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Morate izabrati vozaca i autobus !!!");
            alert.showAndWait();
        }
        else{
            boolean nije = false;
            Bus bus = Busses.get(busCB.getSelectionModel().getSelectedIndex());
            Driver driver = Drivers.get(driverCB.getSelectionModel().getSelectedIndex());
            int pozicija = 1;
            if (bus.getDriverOne() != null && bus.getDriverTwo() != null) {
                if (positionCB.getSelectionModel().getSelectedIndex()<0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Morate izabrati poziciju !!!");
                    alert.showAndWait();
                    nije=true;
                }
                else pozicija = positionCB.getSelectionModel().getSelectedIndex()+1;
            } else if(bus.getDriverOne()!=null){
                pozicija = 2;
            }
            if (nije == false){
                dao.dodijeliVozacuAutobus(driver,bus,pozicija);
                updateBusTab();
            }
        }
    }
}
