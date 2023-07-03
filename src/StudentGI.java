import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentGI extends JFrame implements ActionListener{
	//Declaration :
	ArrayList<String> qcmTitles;
    JButton[] qcmDispo;
    int width=500,height=500;
    JLabel intro;
	JButton result;
	String nom;
	int id;
	Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\hp\\Documents\\info 1\\S2\\java\\MQC-server\\icon.png");
	
	public StudentGI (String nom,int id,String filiere,int niveau) {
		
		this.id=id;//pcq je l'utiliserai par la suite comme parametre pr constructeurs
		this.nom=nom;
		JLayeredPane layeredPane = new JLayeredPane();
		ImageIcon backgroundImage=new ImageIcon("C:\\Users\\hp\\Documents\\info 1\\S2\\java\\MQC-server\\login2.jpg");
		JLabel background=new JLabel(backgroundImage);
		fcts L=new fcts();
		Font customFont=L.myFont("C:\\Users\\hp\\Documents\\FONTS\\Poppins-Medium.ttf");
		background.setBounds(0, 0, 700, 1000);
		setTitle(" Student space ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width,height);
		setLocationRelativeTo(null);
		setLayout(null);
		setIconImage(icon);
		
	//creation:
		intro=new JLabel("The available MQCs are :");
		intro.setForeground(new Color(255,255,255,230));
		intro.setFont(customFont.deriveFont(Font.BOLD,25));
		result=new JButton("You can find your marks HERE !! ");
		qcmTitles= new ArrayList<>();
		retrieveData("SELECT Titre FROM QCMS WHERE filiere = '"+filiere+"' AND niveau ="+niveau);
		qcmDispo = new JButton [qcmTitles.size()];
		
    //display :
        
        intro.setBounds(width/2-165,45,width,30);
        result.setBounds(width/2-150,height-125,300,40);
        result.setBorder(BorderFactory.createLineBorder(Color.WHITE,5));
        result.setForeground(Color.white);
        result.setFont(customFont.deriveFont(Font.BOLD,17));
        result.setBackground(new Color(242, 208, 241));
        add(result);
        int y = 100;
        customFont=L.myFont("C:\\Users\\hp\\Documents\\FONTS\\BaksoSapi.otf");
        for (int i = 0; i < qcmTitles.size(); i++) {
            qcmDispo[i] = new JButton(qcmTitles.get(i));
            qcmDispo[i].setBounds(width/2-250, y, width, 30);
            qcmDispo[i].setBorderPainted(false);
            qcmDispo[i].setForeground(new Color(0, 0, 0,170));
            qcmDispo[i].setBackground(new Color(242, 208, 241));
            qcmDispo[i].addActionListener(this);
            qcmDispo[i].setFont(customFont.deriveFont(Font.BOLD,20));
            y += 50;
            layeredPane.add(qcmDispo[i], Integer.valueOf(1));
        }
        layeredPane.add(background, Integer.valueOf(0));
        layeredPane.add(intro, Integer.valueOf(1));
        layeredPane.add(result, Integer.valueOf(1));
        setContentPane(layeredPane);
       
		setVisible (true);
		
		//actions :
		result.addActionListener(this);
		
		
	}
	 void retrieveData(String query) {
		Connection conn ;
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/javaprojet";
	        conn = DriverManager.getConnection(url, "root", "");
	        PreparedStatement statement = conn.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String title = resultSet.getString("titre");
	            qcmTitles.add(title);
	        }
	        	
	        conn.close(); // Closing the connection when no longer needed
	    } catch (ClassNotFoundException e) {
	        System.out.println("Driver not found!");
	    } catch (SQLException ex) {
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//the results button :
		if (e.getSource() == result) {
			new Result(id);
			dispose();
		}
		for (int i = 0; i < qcmTitles.size(); i++) {
            if (e.getSource()==qcmDispo[i]) {
            	dispose();
            	new Quizz(qcmTitles.get(i),id,nom);
            	
            }
        }
	}
}
