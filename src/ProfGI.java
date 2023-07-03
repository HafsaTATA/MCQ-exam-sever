import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProfGI extends JFrame {
	int width=700,height=300;
	String nom;
	Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\hp\\Documents\\info 1\\S2\\java\\MQC-server\\icon.png");
	Etudiant E;
	Professeur P;
public ProfGI(String nom) {
	P=new Professeur();
	E=new Etudiant();
	this.P.nom=nom;
	setTitle("Students results ");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(width,height);
	setLocationRelativeTo(null);
	setLayout(null);
	setIconImage(icon);
	
	 // Create the table
    JTable table = new JTable();
    fcts L=new fcts();
	Font customFont=L.myFont("C:\\Users\\hp\\Documents\\FONTS\\Poppins-Medium.ttf");
	
    // Set the table model
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new Object[] { "ID","Student name","Branch","Level", "MQC title", "Mark"});
    table.setModel(model);
    table.setFont(customFont.deriveFont(Font.BOLD,12));

    retrieveData(model);
	
 // Add the table to a scroll pane
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(20, 10, width - 50, height - 20);
    add(scrollPane);

    setVisible(true);
}

public void retrieveData(DefaultTableModel model ) {
	Connection conn=null ;
	try {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/javaprojet";
        conn = DriverManager.getConnection(url, "root", "");

        // Retrieve titles from qcms where NomProf = nom
        String query1 = "SELECT Titre FROM qcms WHERE ProfCreateur = ?";
        PreparedStatement statement1 = conn.prepareStatement(query1);
        statement1.setString(1, P.nom);
        ResultSet resultSet1 = statement1.executeQuery();

        /// Retrieve data from examspasse where titre = retrieved title
        String query2 = "SELECT idEtudiant,Note FROM examspasse WHERE titre = ?";
        PreparedStatement statement2 = conn.prepareStatement(query2);
            
        while (resultSet1.next()) {
            String titre = resultSet1.getString("Titre");

            statement2.setString(1, titre);
            ResultSet resultSet2 = statement2.executeQuery();

            // Retrieve name from Etudiant table where id = idEtudiant
            String query3 = "SELECT nom,filiere,niveau FROM Etudiant WHERE id = ?";
            PreparedStatement statement3 = conn.prepareStatement(query3);

            while (resultSet2.next()) {
                E.ID = resultSet2.getInt("idEtudiant");
                int resultat = resultSet2.getInt("Note");

                statement3.setInt(1,E.ID);
                ResultSet resultSet3 = statement3.executeQuery();
                if (resultSet3.next()) {
                    E.nom = resultSet3.getString("nom");
                    E.Niveau=resultSet3.getInt("Niveau");
                    E.filiere=resultSet3.getString("Filiere");
                    model.addRow(new Object[] { E.ID, E.nom,E.filiere,E.Niveau,titre, resultat });
                }
                resultSet3.close();
            }
            resultSet2.close();
        }

        conn.close(); // Closing the connection when no longer needed
    } catch (ClassNotFoundException e) {
        System.out.println("Driver not found!");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


}
