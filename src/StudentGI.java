import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentGI extends JFrame implements ActionListener{
	//Declaration :
	ArrayList<String> qcmsDispo;
    JButton[] qcmTitles;
    int width=500,height=500;
    JLabel intro;
	JButton result;
	String id,nom;
	public StudentGI (String nom,String id,String filiere,String niveau) {
		this.id=id;//pcq je l'utiliserai par la suite comme parametre pr constructeurs
		this.nom=nom;
		
		setTitle(" Student interface ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width,height);
		setLocationRelativeTo(null);
		setLayout(null);
	//creation:
		intro=new JLabel("les QCMS disponibles sont :");
		result=new JButton("NOTES");
		qcmsDispo= new ArrayList<>();
		retrieveData("SELECT titre FROM QCMS WHERE filiere = '"+filiere+"' AND niveau ="+niveau);
		qcmTitles = new JButton [qcmsDispo.size()];
		
    //display :
        
        intro.setBounds(20,20,width,30);
        add(intro);
        result.setBounds(width/2-100,height-100,200,30);
        add(result);
        int y = 60;
        for (int i = 0; i < qcmsDispo.size(); i++) {
            qcmTitles[i] = new JButton(qcmsDispo.get(i));
            qcmTitles[i].setBounds(20, y, width-50, 30);
            add(qcmTitles[i]);
            qcmTitles[i].addActionListener(this);
            y += 40;
        }
		
		setVisible (true);
		
		//actions :
		result.addActionListener(this);
		
		
	}
	public void retrieveData(String query) {
		Connection conn ;
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/javaprojet";
	        conn = DriverManager.getConnection(url, "root", "");
	        PreparedStatement statement = conn.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String title = resultSet.getString("titre");
	            qcmsDispo.add(title);
	        }
	        	
	        conn.close(); // Closing the connection when no longer needed
	    } catch (ClassNotFoundException e) {
	        System.out.println("Driver not found!");
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//the results button :
		if (e.getSource() == result) {
			new Result(id,nom);
			dispose();
		}
		for (int i = 0; i < qcmsDispo.size(); i++) {
            if (e.getSource()==qcmTitles[i]) {
            	dispose();
            	new Quizz(qcmsDispo.get(i),id,nom);
            	
            }
        }
	}

}
