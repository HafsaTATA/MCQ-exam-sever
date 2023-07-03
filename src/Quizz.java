import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;


public class Quizz extends JFrame implements ActionListener{
	int count=0,width=1000,height=350,current;
	String[] options;
	String[] questions;
	JLabel qstn;
	JRadioButton opt1,opt2,opt3,initialize;
	JButton next;
	ButtonGroup bg;
	String title;
	Etudiant E;
	Connection conn =null;
	Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\hp\\Documents\\info 1\\S2\\java\\MQC-server\\icon.png");
	public Quizz(String title,int id,String nom) {
//je dois d'abords afficher les qtsns et au fur et mesure calculer la note
//finalement stocker la note dan ma variablec count;
//inserer dans ma table les chamsps: id-nom-titre(title)-profcreateur(prof)-resultat(count)
		E=new Etudiant();
		this.title=title;
		this.E.ID=id;
		this.E.nom=nom;
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width,height);
		setLocationRelativeTo(null);
		setLayout(null);
		setIconImage(icon);
		
		//declaration
		fcts L=new fcts();
		Font customFont=L.myFont("C:\\Users\\hp\\Documents\\FONTS\\Poppins-Medium.ttf");
		options=new String[4*15];
		questions=new String[15];
		qstn=new JLabel();
		qstn.setFont(customFont.deriveFont(Font.BOLD,15));
		next=new JButton("next");
		next.setFont(customFont.deriveFont(Font.BOLD,15));
		opt1=new JRadioButton();
		opt1.setFont(customFont.deriveFont(Font.BOLD,15));
		opt2=new JRadioButton();
		opt2.setFont(customFont.deriveFont(Font.BOLD,15));
		opt3=new JRadioButton();
		opt3.setFont(customFont.deriveFont(Font.BOLD,15));
		initialize=new JRadioButton();
		bg=new ButtonGroup();
		bg.add(opt1);
        bg.add(opt2);
        bg.add(opt3);
        bg.add(initialize);
		
		//emplacement:
		 qstn.setBounds(30,20,width-50,30);  
	     opt1.setBounds(50,80,width-50,20);  
	     opt2.setBounds(50,110,width-50,20);  
	     opt3.setBounds(50,140,width-50,20); 
	     next.setBounds(100,240,150,30);
	     
	     
	     
	     
		//action:
	     opt1.addActionListener(this);
	     opt2.addActionListener(this);
	     opt3.addActionListener(this);
	     next.addActionListener(this);
	   
	    retrieveData("SELECT Question, Ans1, Ans2, Ans3, AnsRight FROM `q&a`  WHERE Titre='"+title+"'") ;
	    add(qstn);
        add(next);

        current = 0;
        displayQuestion();

        setVisible(true);
        
	    
 }
	public void displayQuestion() {
		initialize.setSelected(true);
        qstn.setText(questions[current]);
        opt1.setText(options[current*4]);
        opt2.setText(options[current*4+1]);
        opt3.setText(options[current*4+2]);
        add(opt1);add(opt2);add(opt3);
        setVisible(true);
        
        
    }
	
	public void checkAnswer() {
        String correctAnswer = options[current*4+3];
        JRadioButton selectedButton = null;
            if (opt1.isSelected()) 
                selectedButton = opt1;
            else if (opt2.isSelected()) 
                selectedButton = opt2;
            else if (opt3.isSelected()) 
                selectedButton = opt3;
            

        if (selectedButton != null && selectedButton.getText().equals(correctAnswer))
        	count++ ;
        else if (selectedButton==null)
			;
		else 
			count--;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
            checkAnswer();
            current++;
		
            if (current < questions.length) {
                displayQuestion();
            } else {
                JOptionPane.showMessageDialog(this, "Your score is: " + count);
                LoadExamsPasseTable ();
                System.exit(0);
                
            }
        }
	
}
	
	public void retrieveData(String query) {
		
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/javaprojet";
            conn = DriverManager.getConnection(url, "root", "");
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            int index = 0;
            while (resultSet.next() && index < 15) {
            	questions[index]= resultSet.getString("question");
            	options[index * 4]= resultSet.getString("ans1");
            	options[index * 4 + 1]= resultSet.getString("ans2");
            	options[index * 4 + 2] = resultSet.getString("ans3");
            	options[index * 4 + 3] = resultSet.getString("ansRight");

                index++;
            }

            //conn.close(); // Closing the connection when no longer needed
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	
	
	public void LoadExamsPasseTable (){
				
				try {

	            // Requête SQL pour insérer des données dans la table "examsPasse"
	            String query = "INSERT INTO examspasse (idEtudiant, titre, Note) VALUES (?, ?, ?)";

	            // Création de l'instruction préparée
	            PreparedStatement statement= conn.prepareStatement(query);

	            // Assignation des valeurs aux paramètres de l'instruction préparée
	            statement.setInt(1,E.ID);
	            statement.setString(2, title);
	            statement.setInt(3, count);
	            statement.executeUpdate();
	            statement.close();
	           
	        }  
				
				catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	

}
