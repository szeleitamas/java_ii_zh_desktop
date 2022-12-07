package grafikus;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
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
import java.util.Iterator;
import java.util.List;

public class GrafikusController {
    @FXML private Label lb1;
    @FXML private GridPane gp1;
    @FXML private TableView tv1;
    @FXML private TextField tfnev, tfigazgato, tfkinevezes;
    @FXML private TableColumn<Np, String> IDCol;
    @FXML private TableColumn<Np, String> nevCol;
    @FXML private TableColumn<Np, String> igazgatoCol;
    @FXML private TableColumn<Np, String> kinevezesCol;
    @FXML
    public ComboBox cb1;
    @FXML
    private ComboBox cb2;
    @FXML private Label lb2;
    @FXML private GridPane gp2, gp6, gp7, gp8, gp9;
    @FXML public TextArea ta1, ta2, ta3, ta4;
    @FXML private TextField tf6, tf7, tf8, tf9, tf10, tf11, tf12, tf13, tf14, tf15, tf16;


    @FXML private TextField unev, uigazgato, ukinevezes;
    SessionFactory factory;
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    final String token = "83edfa60bcf34796df2da5e4eed53785a49f2539408e95ca23e241bfdef64dec";
    HttpsURLConnection httpsURLConnection;
    @FXML void initialize(){
        ElemekTörlése();
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
    }
    void ElemekTörlése(){
        lb1.setVisible(false);
        lb1.setManaged(false);
        gp1.setVisible(false);
        gp1.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);
        lb2.setVisible(false);
        lb2.setManaged(false);
        gp2.setVisible(false);
        gp2.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);

        gp6.setVisible(false);
        gp6.setManaged(false);
        gp8.setVisible(false);
        gp8.setManaged(false);
        gp9.setVisible(false);
        gp9.setManaged(false);

        tv1.setVisible(false);
        tv1.setManaged(false);

        gp7.setVisible(false);
        gp7.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);
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

    @FXML protected void menuUpdateClick() {
    }
    @FXML protected void menuDeleteClick() {
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
    }
    @FXML protected void Rest2CreateClick() {
    }
    @FXML protected void Rest2ReadClick() {
    }
    @FXML protected void Rest2UpdateClick() {
    }

    @FXML protected void SoapLetoltesClick() {
    }
    @FXML protected void SoapLetoltes2Click() {
    }
    @FXML protected void SoapGrafikonClick() {
    }
    @FXML protected void AdatbanyaszatDontesifaClick() {
    }
    @FXML protected void AdatbanyaszatAlgoritmusClick() {
    }
    @FXML protected void AdatbanyaszatAlgoritmus2Click() {
    }
    @FXML protected void EgyebParhuzamosClick() {
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
