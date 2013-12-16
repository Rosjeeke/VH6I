/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ETL.GUI;

import ETL.ExcelExtract;
import ETL.Koffie;
import ETL.MysqlDatabase;
import Rommel.Klant;
import Rommel.Transform;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Liannie
 */
public class MainFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JComboBox comboBox;
    private JButton btnBrowse;
    private JButton clean;
    private JFileChooser fc;
    private JPanel panel;
    private JLabel lblCurrentfile;
    private JLabel lblStatus;
    private File file = null;
    private MainFrame mf = this;
    private String path;
    private ArrayList<Klant> klanten;
    private MysqlDatabase db;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
//					MainFrame frame = new MainFrame();
//					frame.setVisible(true);
                    ExcelExtract transform = new ExcelExtract();
                    transform.infoScanRead(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Opdracht 6 BCG Matrix\\datadump1.xls", 3);
                    transform.extractSwitch(System.getProperty("user.home") + "\\Dropbox\\DVP6IB2 - junior BI-consultant\\Opdracht 6 BCG Matrix\\datadump1.xls", 2);
                    ArrayList<Koffie> albert = transform.getAlbert();
                    ArrayList<Koffie> superboer = transform.getSuperboer();
                    ArrayList<Koffie> c1000 = transform.getC1000();
                    ArrayList<Koffie> superunie = transform.getSuperunie();
                    ArrayList<Koffie> coop = transform.getCoop();
                    ArrayList<Koffie> hoogvliet = transform.getHoogvliet();
                    ArrayList<Koffie> jumbo = transform.getJumbo();
                    ArrayList<Koffie> vomar = transform.getVomar();
                    ArrayList<Koffie> jan = transform.getJan();
                    ArrayList<Koffie> deen = transform.getDeen();
                    ArrayList<Koffie> bonimarkt = transform.getBonimarkt();
                    ArrayList<Koffie> plus = transform.getPlus();
                    //db.sqlUpdate("INSERT INTO Tijd Values()");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
//    public void insertInto(ArrayList<Koffie> koffieSupermarkt){
//        int i = 0;
//        String koffie = "";
//        for(Koffie kof : koffieSupermarkt){
//            
//            if(i<1){
//            koffie = "INSERT INTO koffie VALUES('"+kof.getMajorBrand()+"', '"+kof.getApparaat()+"', '"+k.getTelefoon()+"',"
//                        + " '"+k.getMobiel()+"', '"+k.getPlaats()+"', '"+k.getLand()+"', '"+k.getPercentage()+"' )";
//            i++;}
//            else{
//                query += ",('"+k.getKlantNummer()+"', '"+k.getKlantNaam()+"', '"+k.getTelefoon()+"'," + " '"+k.getMobiel()+"', '"+k.getPlaats()+"', '"+k.getLand()+"', '"+k.getPercentage()+"' )";
//            }
//        }
//        db.sqlUpdate(query);
//    }
    
    public MainFrame() {
        db = new MysqlDatabase("koffie");
        db.sqlUpdate("INSERT INTO Tijd Values('1', '2007', '1'),('2', '2007', '2'),('3', '2007', '3'),('4', '2007', '4'),('5', '2008', '1'),('6', '2008', '2'),('7', '2008', '3'),('8', '2008', '4')");
        db.sqlUpdate("INSERT INTO Verkoper Values('1','Albert Heijn'),('2','Super de Boer'),('3','C1000'),('4','Superunie'),('5','COOP Totaal'),('6','Hoogvliet'),('7','Jumbo'),('8','Vomar'),('9','Jan Linders'),('10','Deen'),('11','Bonimarkt'),('12','Plus')");
        //db.sqlUpdate("INSERT INTO Tijd_Jaar Values('1', '2007'),('2','2008')");
        klanten = new ArrayList<Klant>();
        setResizable(false);
        setTitle("VH6B2 - ECTL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Select File", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(12, 10, 450, 300);
        contentPane.add(panel);
        panel.setLayout(null);

        textField = new JTextField();
        textField.setBounds(12, 23, 140, 20);
        panel.add(textField);
        textField.setColumns(10);

        btnBrowse = new JButton("Browse");
        fc = new JFileChooser();
        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Handle open button action.
                if (e.getSource() == btnBrowse) {
                    fc = new JFileChooser();
                    int returnVal = fc.showOpenDialog(contentPane);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file = fc.getSelectedFile();
                        //This is where the application would open the file.
                        textField.setText(file.getAbsolutePath());
                        path = textField.getText();
                        //setText currentfile
                        int dotPos = file.getName().lastIndexOf(".");
                        String extension = file.getName().substring(dotPos);
                        System.out.println("Opening: " + file.getName() + ".");

                    } else {
                        System.out.println("Open command cancelled by user.");
                    }
                }
            }
        });
        btnBrowse.setBounds(160, 23, 80, 20);
        panel.add(btnBrowse);

        JButton btnExtract = new JButton("Extract");
        btnExtract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
//				Extract extract = new Extract(file, mf, path);
//                                klanten = extract.getKlanten();
            }
        });
        btnExtract.setBounds(160, 70, 80, 20);
        panel.add(btnExtract);

        JButton btnClean = new JButton("Clean");
        btnClean.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnClean.setBounds(160, 110, 80, 20);
        panel.add(btnClean);

        JButton btnTransform = new JButton("Transform");
        btnTransform.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Transform transform = new Transform();
            }
        });
        btnTransform.setBounds(160, 150, 80, 20);
        panel.add(btnTransform);

        JButton btnLoad = new JButton("Load");
        btnClean.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnLoad.setBounds(160, 190, 80, 20);
        panel.add(btnLoad);


    }
}
