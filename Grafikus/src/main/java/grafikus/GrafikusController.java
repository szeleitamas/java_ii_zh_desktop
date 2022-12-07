package grafikus;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

public class GrafikusController {

    @FXML private TextField tfnev, tfigazgato, tfkinevezes;
    @FXML private TableColumn<Np, String> IDCol;
    @FXML private TableColumn<Np, String> nevCol;
    @FXML private TableColumn<Np, String> igazgatoCol;
    @FXML private TableColumn<Np, String> kinevezesCol;
    @FXML public Label döntésiFaMsg;
    @FXML
    public ComboBox cb1;
    @FXML
    private ComboBox cb2;
    @FXML private TableView tv1;
    @FXML private Label lb1, lb2, lb3;
    @FXML private GridPane gp1, gp2, gp5, gp4, gp6, gp7, gp8, gp9, gp10, gp11, gp12, gp13, gp14, gp15, gp16, gp17;
    @FXML public TextArea ta1, ta2, ta3, ta4;
    @FXML private TextField tf6, tf7, tf8, tf9, tf10, tf11, tf12, tf13, tf14, tf15, tf16;


    @FXML private TextField unev, uigazgato, ukinevezes;
    SessionFactory factory;
    final String token = "83edfa60bcf34796df2da5e4eed53785a49f2539408e95ca23e241bfdef64dec";
    HttpsURLConnection httpsURLConnection;
    @FXML void initialize(){
        ElemekTörlése();
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
        cb1.getItems().addAll("1", "2", "3","4", "5", "6");
        cb1.getSelectionModel().select("1");
        cb2.getItems().addAll("1", "2", "3","4", "5", "6");
        cb2.getSelectionModel().select("1");
    }
    void ElemekTörlése(){
        lb1.setVisible(false);
        lb1.setManaged(false);
        lb3.setVisible(false);
        lb3.setManaged(false);
        lb2.setVisible(false);
        lb2.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);
        gp1.setVisible(false);
        gp1.setManaged(false);
        gp2.setVisible(false);
        gp2.setManaged(false);
        gp4.setVisible(false);
        gp4.setManaged(false);
        gp5.setVisible(false);
        gp5.setManaged(false);
        gp6.setVisible(false);
        gp6.setManaged(false);
        gp7.setVisible(false);
        gp7.setManaged(false);
        gp8.setVisible(false);
        gp8.setManaged(false);
        gp9.setVisible(false);
        gp9.setManaged(false);
        gp10.setVisible(false);
        gp10.setManaged(false);
        gp11.setVisible(false);
        gp11.setManaged(false);
        gp12.setVisible(false);
        gp12.setManaged(false);
        gp13.setVisible(false);
        gp13.setManaged(false);
        gp14.setVisible(false);
        gp14.setManaged(false);
        gp15.setVisible(false);
        gp15.setManaged(false);
        gp16.setVisible(false);
        gp16.setManaged(false);
        gp17.setVisible(false);
        gp17.setManaged(false);

    }
    @FXML protected void menuCreateClick() {
        ElemekTörlése();
        gp1.setVisible(true);
        gp1.setManaged(true);
    }

    @FXML protected void menuReadClick() {
        ElemekTörlése();
        tv1.setVisible(true);
        tv1.setManaged(true);
        tv1.getColumns().removeAll(tv1.getColumns());
        IDCol = new TableColumn("id");
        nevCol = new TableColumn("nev");
        igazgatoCol = new TableColumn("igazgato");
        kinevezesCol = new TableColumn("kinevezes");
        tv1.getColumns().addAll(IDCol, nevCol, igazgatoCol, kinevezesCol);
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nevCol.setCellValueFactory(new PropertyValueFactory<>("nev"));
        igazgatoCol.setCellValueFactory(new PropertyValueFactory<>("igazgato"));
        kinevezesCol.setCellValueFactory(new PropertyValueFactory<>("kinevezes"));
        tv1.getItems().clear();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<Np> lista = session.createQuery("FROM Np ").list();
        for(Np np:lista)
            tv1.getItems().add(np);
        System.out.println();
        t.commit();
    }

    @FXML protected void menuRead2Click() {
    }

    void Create(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Np np=new Np(tfnev.getText(), (tfigazgato.getText()), Integer.parseInt(tfkinevezes.getText()));
        session.save(np);
        t.commit();
    }
    @FXML void bt1Click(){
        Create();
        ElemekTörlése();
        lb1.setVisible(true);
        lb1.setManaged(true);
        lb1.setText("Adatok beírva az adatbázisba");
    }
    @FXML
    protected void menuUpdateClick() {
        ElemekTörlése();
        gp2.setVisible(true);
        gp2.setManaged(true);
    }

    @FXML protected void menuDeleteClick() {
        ElemekTörlése();
        gp4.setVisible(true);
        gp4.setManaged(true);
    }
    void Delete(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Np np = session.load(Np.class, 3);
        session.delete(np);
        t.commit();
    }

    @FXML void bt4Click(){
        Delete();
        ElemekTörlése();
        lb3.setVisible(true);
        lb3.setManaged(true);
        cb2.getItems().removeAll(cb2.getItems());
        cb2.getItems().addAll("1", "2", "3","4", "5", "6");
        cb2.getSelectionModel().select("1");
        lb3.setText("Adatok törölve az adatbázisban");
    }





    protected void segéd1(){
        httpsURLConnection.setRequestProperty("Content-Type", "application/json");
        httpsURLConnection.setRequestProperty("Authorization", "Bearer " + token);
        httpsURLConnection.setUseCaches(false);
        httpsURLConnection.setDoOutput(true);
    }
    protected void segéd2(String params) throws IOException {
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(httpsURLConnection.getOutputStream(), "UTF-8"));
        wr.write(params);
        wr.close();
        httpsURLConnection.connect();
    }
    protected String segéd3(int code) throws IOException {
        int statusCode = httpsURLConnection.getResponseCode();
        System.out.println("statusCode: "+statusCode);
        if (statusCode == code) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = null;
            while ((readLine = in.readLine()) != null)
                jsonResponseData.append(readLine);
            in.close();
            httpsURLConnection.disconnect();
            return jsonResponseData.toString();
        } else {
            httpsURLConnection.disconnect();
            return "Hiba!!!";
        }
    }
    protected void clearControlUIData(TextField... tfList) {
        for(TextField tf : tfList) tf.setText("");
    }
    @FXML
    protected void Rest1ReadClick() {
        ElemekTörlése();
        clearControlUIData(tf6);
        ta1.setText("");
        gp6.setVisible(true);
        gp6.setManaged(true);
    }
    @FXML
    protected void btnRest1ReadClick() throws IOException {
        ta1.setText("");
        String url = "https://gorest.co.in/public/v2/users";
        String ID = tf6.getText();
        if(ID != null)
            url = url + "/" + ID;
        URL usersUrl = new URL(url);
        httpsURLConnection = (HttpsURLConnection) usersUrl.openConnection();
        httpsURLConnection.setRequestMethod("GET");
        if(ID != null)
            httpsURLConnection.setRequestProperty("Authorization", "Bearer " + token);
        String response = segéd3(HttpsURLConnection.HTTP_OK);
        if(!response.equals("Hiba!!!")) {
            ta1.setText(response);
        } else {
            ta1.setText("Nincs user ilyen ID-val az adatbázisban.");
        }
    }
    @FXML
    protected void Rest1CreateClick() {
        ElemekTörlése();
        clearControlUIData(tf7, tf8, tf9, tf10);
        ta2.setText("");
        gp7.setVisible(true);
        gp7.setManaged(true);
    }
    @FXML
    protected void btnRest1CreateClick() throws IOException {
        ta2.setText("");
        URL postUrl = new URL("https://gorest.co.in/public/v2/users");
        httpsURLConnection = (HttpsURLConnection) postUrl.openConnection();
        httpsURLConnection.setRequestMethod("POST");
        segéd1();
        String name = tf7.getText();
        String gender = tf8.getText();
        String email = tf9.getText();
        String status = tf10.getText();
        String params = "{\"name\":\""+name+"\", \"gender\":\""+gender+"\", \"email\":\""+email+"\", \"status\":\""+status+"\"}";
        segéd2(params);
        String response = segéd3(HttpsURLConnection.HTTP_CREATED);
        if(!response.equals("Hiba!!!")) {
            ta2.setText(response);
        } else {
            ta2.setText("A létrehozás sikeres.");
        }
    }
    @FXML
    protected void Rest1UpdateClick() {
        ElemekTörlése();
        clearControlUIData(tf11, tf12, tf13, tf14, tf15);
        ta3.setText("");
        gp8.setVisible(true);
        gp8.setManaged(true);
    }
    @FXML
    protected void btnRest1UpdateClick() throws IOException {
        ta3.setText("");
        String ID = tf11.getText();
        String name = tf12.getText();
        String gender = tf13.getText();
        String email = tf14.getText();
        String status = tf15.getText();
        String url = "https://gorest.co.in/public/v2/users"+"/"+ID;
        URL postUrl = new URL(url);
        httpsURLConnection = (HttpsURLConnection) postUrl.openConnection();
        httpsURLConnection.setRequestMethod("PUT");
        segéd1();
        String params = "{\"name\":\""+name+"\", \"gender\":\""+gender+"\", \"email\":\""+email+"\", \"status\":\""+status+"\"}";
        segéd2(params);
        String response = segéd3(HttpsURLConnection.HTTP_OK);
        if(!response.equals("Hiba!!!")) {
            ta3.setText(response);
        } else {
            ta3.setText("A módosítás nem sikerült.");
        }
    }
    @FXML
    protected void Rest1DeleteClick() {
        ElemekTörlése();
        clearControlUIData(tf16);
        ta4.setText("");
        gp9.setVisible(true);
        gp9.setManaged(true);
    }
    @FXML
    protected void btnRest1DeleteClick() throws IOException {
        ta4.setText("");
        String ID = tf16.getText();
        String url = "https://gorest.co.in/public/v2/users"+"/"+ID;
        URL postUrl = new URL(url);
        httpsURLConnection = (HttpsURLConnection) postUrl.openConnection();
        httpsURLConnection.setRequestMethod("DELETE");
        segéd1();
        String response = segéd3(HttpsURLConnection.HTTP_NO_CONTENT);
        if(!response.equals("Hiba!!!")) {
            ta4.setText("Sikeresen törölte a user-t!");
        } else {
            ta4.setText("A törlés nem sikerült.");
        }
    }






    @FXML protected void Rest2DeleteClick() {
        ElemekTörlése();
        clearControlUIData(tf16);
        ta4.setText("");
        gp13.setVisible(true);
        gp13.setManaged(true);
    }
    @FXML protected void Rest2CreateClick() {
        ElemekTörlése();
        clearControlUIData(tf16);
        ta4.setText("");
        gp10.setVisible(true);
        gp10.setManaged(true);
    }
    @FXML protected void Rest2ReadClick() {
        ElemekTörlése();
        clearControlUIData(tf16);
        ta4.setText("");
        gp11.setVisible(true);
        gp11.setManaged(true);
    }
    @FXML protected void Rest2UpdateClick() {
        ElemekTörlése();
        clearControlUIData(tf16);
        ta4.setText("");
        gp12.setVisible(true);
        gp12.setManaged(true);
    }

    @FXML protected void SoapLetoltesClick() {
        ElemekTörlése();
        clearControlUIData(tf16);
        ta4.setText("");
        gp14.setVisible(true);
        gp14.setManaged(true);
    }
    @FXML protected void SoapLetoltes2Click() {
        ElemekTörlése();
        clearControlUIData(tf16);
        ta4.setText("");
        gp15.setVisible(true);
        gp15.setManaged(true);
    }
    @FXML protected void SoapGrafikonClick() {
        ElemekTörlése();
        clearControlUIData(tf16);
        ta4.setText("");
        gp16.setVisible(true);
        gp16.setManaged(true);
    }
    @FXML protected void AdatbanyaszatDontesifaClick() {
        ElemekTörlése();
        döntésiFaMsg.setText("");
        gp17.setVisible(true);
        gp17.setManaged(true);
    }

    protected void setTimerForLabel(Label label) {
        Timer tm = new Timer();
        tm.schedule(new TimerTask(){
            @Override
            public void run() {
                Platform.runLater(() -> {
                    label.setText("");
                });
            }
        },3600);
    }

    @FXML
    protected void btnAdatbanyaszatDontesiFa() {
        String filename = "soybean.arff";
        int classIndex = 20;
        new DontesiFa(filename, classIndex);
        döntésiFaMsg.setText("OK!");
        setTimerForLabel(döntésiFaMsg);
    }
    @FXML protected void AdatbanyaszatAlgoritmusClick() {
    }
    @FXML protected void AdatbanyaszatAlgoritmus2Click() {
    }

    @FXML
    protected void EgyebParhuzamosClick() {
        ElemekTörlése();
        gp5.setVisible(true);
        gp5.setManaged(true);
    }
    @FXML
    protected void btnParhuzamosClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("parhuzamos.fxml"));
        Scene scene = new Scene(root, 400, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML protected void EgyebStreamClick() {
    }


    @FXML void bt2Click(){
        Create();
        ElemekTörlése();
        lb1.setVisible(true);
        lb1.setManaged(true);
        lb1.setText("Adatok beírva az adatbázisba");
    }

}
