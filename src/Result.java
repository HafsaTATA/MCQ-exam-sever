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
	String nom,id;
	
public Result(String id,String nom) {
	this.nom=nom;
	this.id=id;
	setTitle(" TESTS RESULTS ");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(width,height);
	setLocationRelativeTo(null);
	setLayout(null);
	
	
	 // Create the table
    JTable table = new JTable();

    // Set the table model
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new Object[] { "Title", "Result" });
    table.setModel(model);

    retrieveData(model);
	
 // Add the table to a scroll pane
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(10, 10, width - 50, height - 20);
    add(scrollPane);

    setVisible(true);
}

public void retrieveData(DefaultTableModel model ) {
	Connection conn=null ;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/javaprojet";
        conn = DriverManager.getConnection(url, "root", "");
        String query = "SELECT titre, resultat FROM examspasse WHERE id ="+id+" AND nom ='"+nom+"'";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
        	String titre = resultSet.getString("titre");
            int resultat = resultSet.getInt("resultat");
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
