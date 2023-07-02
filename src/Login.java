import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.jdi.IntegerValue;

import java.awt.Toolkit;

public class Login extends JFrame implements ActionListener{

	//Declaration:
		JTextField nomBox,IDBox,specialiteBox;
		JComboBox<String> filiereBox,niveauBox;
		int width=400,height=500;
		JLabel lblNom,lblID,lblFiliere,lblNiveau,lblSpecialite;
		JButton button=new JButton("Submit");
		String metier="";
		Image icon=Toolkit.getDefaultToolkit().getImage("C:\\Users\\hp\\Documents\\info 1\\S2\\java\\MQC-server\\icon.png");
		
		 
		public Login(){	
			
		String[] options = {"Student","Teacher"};
	 	int w=625,h=495;
        JFrame frame = new JFrame("StudyQuest");
        frame.setIconImage(icon);
        JPanel heading=new JPanel();
        JLayeredPane layeredPane = new JLayeredPane();
        JLabel label1 = new JLabel(" Welcome ");
        JLabel label3 = new JLabel(" to StudyQuest ");
        JLabel label2=new JLabel(" I am : ");
        ImageIcon background=new ImageIcon("C:\\Users\\hp\\Documents\\info 1\\S2\\java\\MQC-server\\background.jpg");
        JLabel backgroundLabel = new JLabel(background);
        JComboBox<String> comboBox = new JComboBox<>(options);
        JButton submitButton = new JButton("Next");
        
       // Icon buttonIcon= new ImageIcon("C:\\Users\\hp\\Documents\\info 1\\S2\\java\\MQC-server\\right.png");
        
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(w,h);
        frame.setLocationRelativeTo(null);
		frame.setLayout(null);
        
		heading.setBackground(new Color(0,0,0,0));
		heading.setBounds(0,100,w,200);
		heading.setLayout(null);
		
		backgroundLabel.setBounds(0, 0, 612, 459);
		
        label1.setBounds(w/2-150,-3,300,100);
        label1.setForeground(new Color(245,75,217,100));
        label3.setBounds(w/2-20,15,300,100);
        label3.setForeground(new Color(3,5,217,100));
        label3.setBackground(new Color(0,0,0,0));
        label2.setBounds(w/2-50,80,300,100);
        label2.setForeground(new Color(0,0,0,150));
        heading.add(label1);
        heading.add(label2);
        heading.add(label3);
        
        comboBox.setBounds(w/2-50,140,100,20);
        comboBox.setBackground(Color.white);
        comboBox.setForeground(new Color(0,0,0,80));
        heading.add(comboBox);
       
        submitButton.setBounds(420,330, 90,30);
        //submitButton.setBackground(Color.white);
        submitButton.setBorderPainted(false);
        submitButton.setForeground(new Color(0,0,0,170));
        //***********************************Font area**************************
        fcts f=new fcts();
        Font customFont=f.myFont("C:\\Users\\hp\\Documents\\FONTS\\RemachineScript_Personal_Use.ttf");
        label3.setFont(customFont.deriveFont(Font.BOLD,40));
        
        customFont=f.myFont("C:\\Users\\hp\\Documents\\FONTS\\Poppins-Medium.ttf");
        submitButton.setFont(customFont.deriveFont(Font.BOLD,12));
        label1.setFont(customFont.deriveFont(Font.BOLD,50));
        label2.setFont(customFont.deriveFont(Font.BOLD,13));		            
        comboBox.setFont(customFont.deriveFont(Font.BOLD,12));
        
       
        
        //*****************************************************
        
        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(heading, Integer.valueOf(2));
        layeredPane.add(submitButton, Integer.valueOf(1));
        frame.setContentPane(layeredPane);
        frame.setVisible(true);
     
		
		setIconImage(icon);
		setTitle("Login");//donne le nom de la fennetre
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width,height);
		setLocationRelativeTo(null);//pr centrer ds l'ecran
		setLayout(null);
		JLayeredPane layeredPane2 = new JLayeredPane();
		
        JLabel background2 = new JLabel(new ImageIcon("C:\\Users\\hp\\Documents\\info 1\\S2\\java\\MQC-server\\login.png"));
        background2.setBounds(0,0, width,height);
        layeredPane2.add(background2,Integer.valueOf(0));
		//creation:
		lblNom=new JLabel("Enter your full name");
		nomBox=new JTextField();
	
		lblID=new JLabel("Enter your ID number: ");
		IDBox=new JTextField();
		lblNiveau=new JLabel("Pick your level :");
		niveauBox = new JComboBox<>(new String[] {"1", "2", "3"});
		lblFiliere=new JLabel("Pick your studies field ");
		String[] filiereOptions={"GINFO", "GTR", "GSEII","GMSA","GESI","GINDUS"};
		filiereBox = new JComboBox<>(filiereOptions);
		
		lblSpecialite=new JLabel("Enter your specialty");
		specialiteBox=new JTextField();
       
		//placements for both:
		lblNom.setBounds(20,20,width,20);
		nomBox.setBounds(40,40,width-90,25);
		button.setBounds(width-130,height-90,100,30);
		button.setBorderPainted(false);
		button.setForeground(new Color(0,0,0,170));
        
        
		//placements for student
		lblID.setBounds(20,90,width,20);
		IDBox.setBounds(40,110,width-90,25);
		lblNiveau.setBounds(20,230,width,20);
		niveauBox.setBounds(40,250,width-90,25);
		lblFiliere.setBounds(20,160,width,20);
		filiereBox.setBounds(40,180,width-90,25);
		//placements for prof:
		lblSpecialite.setBounds(20,90,width,20);
		specialiteBox.setBounds(40,110,width-90,25);
		
		button.setFont(customFont.deriveFont(Font.BOLD,12));
		lblNom.setFont(customFont.deriveFont(Font.BOLD,12));
		nomBox.setFont(customFont.deriveFont(Font.BOLD,12));
		lblID.setFont(customFont.deriveFont(Font.BOLD,12));
		IDBox.setFont(customFont.deriveFont(Font.BOLD,12));
		lblNiveau.setFont(customFont.deriveFont(Font.BOLD,12));
		niveauBox.setFont(customFont.deriveFont(Font.BOLD,12));
		lblFiliere.setFont(customFont.deriveFont(Font.BOLD,12));
		filiereBox.setFont(customFont.deriveFont(Font.BOLD,12));
		lblSpecialite.setFont(customFont.deriveFont(Font.BOLD,12));
		specialiteBox.setFont(customFont.deriveFont(Font.BOLD,12));
		//display:
		submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String selectedOption = (String) comboBox.getSelectedItem();
               metier=selectedOption;
               frame.dispose(); // Close the interface
               if (metier.equals("Student")) {
       			// Add the components to the layered pane with the appropriate z-order
       			 layeredPane2.add(lblNom, Integer.valueOf(1));
       			 layeredPane2.add(nomBox, Integer.valueOf(1));
       			 layeredPane2.add(button, Integer.valueOf(1));
       			 layeredPane2.add(lblID, Integer.valueOf(1));
       			 layeredPane2.add(IDBox, Integer.valueOf(1));
       			 layeredPane2.add(lblNiveau, Integer.valueOf(1));
       			 layeredPane2.add(niveauBox, Integer.valueOf(1));
       			 layeredPane2.add(lblFiliere, Integer.valueOf(1));
       			 layeredPane2.add(filiereBox, Integer.valueOf(1));
       			 setContentPane(layeredPane2);
       			 setVisible(true);
       			}
       		else if(metier.equals("Teacher")){
       		// Add the components to the layered pane with the appropriate z-order
       		 layeredPane2.add(button, Integer.valueOf(1));
       		 layeredPane2.add(lblNom, Integer.valueOf(1));
       		 layeredPane2.add(nomBox, Integer.valueOf(1));
       		 layeredPane2.add(specialiteBox,Integer.valueOf(1));
       		 layeredPane2.add(lblSpecialite,Integer.valueOf(1));
       		 setContentPane(layeredPane2);
       		 setVisible(true);
       			}
       	
       		
       		
            }
        });
		
		//actions:
   		button.addActionListener(this);
		
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button) { 
				if (metier.equals("Student")) {
					Etudiant E=new Etudiant();
					E.nom=nomBox.getText();
					String tempId=IDBox.getText();//can't use the E.ID directy because what if no valu is entered then how can i Integer.parseint(a null or empty value)!!?
					E.filiere = (String) filiereBox.getSelectedItem();
					String tempNiveau =(String) niveauBox.getSelectedItem();//(string) cuz the getSelectedItem() returns an object
					if (E.nom.isEmpty() || tempId.isEmpty() || E.filiere.isEmpty() || tempNiveau.isEmpty()) {
						JOptionPane.showMessageDialog(this, "Please complete all fields");
					}
					else {
						E.ID=Integer.parseInt(tempId);
						E.Niveau=Integer.parseInt(tempNiveau);
						new StudentGI(E.nom,E.ID,E.filiere,E.Niveau); // Create an instance of the Student class (assumed to be another JFrame)
						dispose(); // Close the current Login interface
					}
				}
				else if (metier.equals("Teacher")){
					Professeur P=new Professeur();
					P.nom=nomBox.getText();
					P.Specialite=specialiteBox.getText();
					if (P.nom.isEmpty() || P.Specialite.isEmpty()) {
						JOptionPane.showMessageDialog(this, "Please complete all fields");
					}
					else {
						new ProfGI(P.nom);
						dispose();
						}
				}
			}
		}

		public static void main(String[] args) {
			//welcome interface: returns
			 new Login();
		}
	}
