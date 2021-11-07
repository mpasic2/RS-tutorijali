package ba.unsa.etf.rs.tut5;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableArray;

public class Korisnik{
    SimpleStringProperty ime = new SimpleStringProperty("");
    SimpleStringProperty prezime = new SimpleStringProperty("");
    SimpleStringProperty email = new SimpleStringProperty("");
    SimpleStringProperty username = new SimpleStringProperty("");
    SimpleStringProperty password = new SimpleStringProperty("");

    public Korisnik() {
    }

    public Korisnik(SimpleStringProperty ime, SimpleStringProperty prezime, SimpleStringProperty email,
                    SimpleStringProperty username, SimpleStringProperty password) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Korisnik(String ime, String prezime, String email, String username, String password) {
        this.ime.set(ime);
        this.prezime.set(prezime);
        this.email.set(email);
        this.username.set(username);
        this.password.set(password);
    }

    @Override
    public String toString() {
        return prezime.get() + " " + ime.get();
    }

    public String getIme() {
        return ime.get();
    }

    public SimpleStringProperty imeProperty() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime.set(ime);
    }

    public String getPrezime() {
        return prezime.get();
    }

    public SimpleStringProperty prezimeProperty() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime.set(prezime);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

}
