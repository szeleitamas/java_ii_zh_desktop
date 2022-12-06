package grafikus;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
    @FXML private GridPane gp2;
    @FXML private TableView tv2;

    @FXML private TextField unev, uigazgato, ukinevezes;
    SessionFactory factory;
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
        tv2.setVisible(false);
        tv2.setManaged(false);
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






    @FXML protected void Rest1CreateClick() {
    }
    @FXML protected void Rest1ReadClick() {
    }
    @FXML protected void Rest1UpdateClick() {
    }
    @FXML protected void Rest2DeleteClick() {
    }
    @FXML protected void Rest2CreateClick() {
    }
    @FXML protected void Rest2ReadClick() {
    }
    @FXML protected void Rest2UpdateClick() {
    }
    @FXML protected void Rest1DeleteClick() {
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
