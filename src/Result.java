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

public class Result extends JFrame {
	int width=700,height=300;
	Etudiant E;
	Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\hp\\Documents\\info 1\\S2\\java\\MQC-server\\icon.png");
	
public Result(int id) {
	E=new Etudiant();
	this.E.ID=id;
	setTitle(" TESTS RESULTS ");
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
    model.setColumnIdentifiers(new Object[] { "MQC's title", "Mark" });
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
        String query = "SELECT titre,Note FROM examspasse WHERE idEtudiant ="+E.ID;
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
        	String titre = resultSet.getString("titre");
            int resultat = resultSet.getInt("Note");
            model.addRow(new Object[] { titre, resultat });
        }
        	
        conn.close(); // Closing the connection when no longer needed
    } catch (ClassNotFoundException e) {
        System.out.println("Driver not found!");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


}
