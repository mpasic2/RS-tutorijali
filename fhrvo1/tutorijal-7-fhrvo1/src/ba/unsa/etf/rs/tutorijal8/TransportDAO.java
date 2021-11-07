package ba.unsa.etf.rs.tutorijal8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TransportDAO {
        private static TransportDAO instance;
        private Connection conn;
        private ArrayList<Driver> Drivers = new ArrayList<>();
        private ArrayList<Bus> Busses = new ArrayList<>();

        public static TransportDAO getInstance(){
            if (instance==null) instance = new TransportDAO();
            return instance;
        }

        private TransportDAO() {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void resetDatabase() {
            try {
                Scanner ulaz = new Scanner(new FileInputStream("reset.sql"));
                String sqlupit = "";
                while (ulaz.hasNext()){
                    sqlupit+=ulaz.nextLine();
                    if (sqlupit.charAt(sqlupit.length()-1)==';') {
                        try {
                            Statement s = conn.createStatement();
                            s.execute(sqlupit);
                            sqlupit = "";
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
                ulaz.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public ArrayList<Driver> getDrivers() {
            Drivers = new ArrayList<>();
            try {
                Statement statement = conn.createStatement();
                ResultSet rezultat = statement.executeQuery("SELECT * FROM Driver");
                while(rezultat.next()) {
                    Drivers.add(new Driver(rezultat.getString(2),rezultat.getString(3),
                            rezultat.getString(4),
                            LocalDate.parse(rezultat.getDate(5).toString()),
                            LocalDate.parse(rezultat.getDate(6).toString())));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Drivers;
        }

        public void setDrivers(ArrayList<Driver> drivers) {
            Drivers = drivers;
        }

        public void addDriver(Driver driver) {
            getDrivers();
            boolean isti = false;
            for (Driver d:Drivers)
                if (d.getJmb().equals(driver.getJmb())) isti=true;
            if (isti==false) {
                Drivers.add(driver);
                try {
                    PreparedStatement addDriver  = conn.prepareStatement("insert into Driver values(?,?,?,?,?,?)");
                    int id = 0;
                    try {
                        Statement statement = conn.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT max(DriverID)+1 from Driver");
                        if(resultSet.next()){
                            id = resultSet.getInt(1);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    addDriver.setInt(1,id);
                    addDriver.setString(2, driver.getName());
                    addDriver.setString(3, driver.getSurname());
                    addDriver.setString(4, driver.getJmb());
                    addDriver.setDate(5, Date.valueOf(driver.getBirthday()));
                    addDriver.setDate(6, Date.valueOf(driver.getEmployedDate()));
                    addDriver.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else throw new IllegalArgumentException("Taj vozač već postoji!");
        }

        public void deleteDriver(Driver driver) {
            Drivers.remove(driver);
            try {
                PreparedStatement addDriver  = conn.prepareStatement("DELETE FROM Driver where jmb=?");
                addDriver.setString(1, driver.getJmb());
                addDriver.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public ArrayList<Bus> getBusses() {
            Busses = new ArrayList<>();
            try {
                Statement statement = conn.createStatement();
                ResultSet rezultat = statement.executeQuery("SELECT * FROM bus");
                while(rezultat.next()) {
                    Driver prvi = null;
                    Driver drugi = null;
                    if (rezultat.getString(5)!=null) {
                        for (Driver d : Drivers) {
                            if(d.getJmb().equals(rezultat.getString(5))) prvi=d;
                        }
                    }
                    if (rezultat.getString(6)!=null) {
                        for (Driver d : Drivers) {
                            if(d.getJmb().equals(rezultat.getString(5))) drugi=d;
                        }
                    }
                    Busses.add(new Bus(rezultat.getString(2), rezultat.getString(3),
                            rezultat.getInt(4), prvi, drugi));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Busses;
        }

        public void setBusses(ArrayList<Bus> busses) {
            Busses = busses;
        }

        public void addBus(Bus bus) {
            Busses.add(bus);
            try {
                PreparedStatement addDriver  = conn.prepareStatement("insert into bus values(?,?,?,?,?,?)");
                int id = 0;
                try {
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT max(BusID)+1 from bus");
                    if(resultSet.next()){
                        id = resultSet.getInt(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                addDriver.setInt(1,id);
                addDriver.setString(2, bus.getMaker());
                addDriver.setString(3, bus.getSerija());
                addDriver.setInt(4, bus.getSeatNumber());
                addDriver.setString(5, null);
                addDriver.setString(6, null);
                addDriver.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void deleteBus(Bus bus) {
            Busses.remove(bus);
            try {
                PreparedStatement addDriver  = conn.prepareStatement("DELETE FROM bus where Maker=? and Series=? and SeatNumber=?");
                addDriver.setString(1, bus.getMaker());
                addDriver.setString(2, bus.getSerija());
                addDriver.setInt(3, bus.getSeatNumber());
                addDriver.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void dodijeliVozacuAutobus(Driver driver, Bus bus, int which) {
            try {
                PreparedStatement updateBus;
                if (which==1){
                    updateBus  = conn.prepareStatement(
                            "UPDATE bus SET DriverOne=? WHERE Maker=? and Series=? and SeatNumber=?");
                    bus.setDriverOne(driver);
                }
                else {
                    updateBus = conn.prepareStatement(
                            "UPDATE bus SET DriverTwo=? WHERE Maker=? and Series=? and SeatNumber=?");
                    bus.setDriverTwo(driver);
                }
                updateBus.setString(1, driver.getJmb());
                updateBus.setString(2, bus.getMaker());
                updateBus.setString(3, bus.getSerija());
                updateBus.setInt(4, bus.getSeatNumber());
                updateBus.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
}
