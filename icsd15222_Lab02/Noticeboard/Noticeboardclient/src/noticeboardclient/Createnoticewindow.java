
package noticeboardclient;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Createnoticewindow extends JFrame implements ActionListener //κλάση για παράθυρο εισαγωγής στοιχείων επιλογής μου.

{

	
	
	private JLabel name;
	private JLabel surname;
	private JLabel job;
	private JLabel country;
	private JLabel phone;
	private JLabel date;
        private JLabel date2;
	private JLabel username;
	
	private JTextField namee;
	private JTextField surnamee;
	private JTextField jobb;
	private JTextField countryy;
	private JTextField phonee;
	private JFormattedTextField datee;
        private JFormattedTextField datee2;
	private JTextField usernamee;
	
	private JButton create, b2;
	private JPanel createpanel;
	
	private DateFormat format;
	
	private Date date4;
        private Date date5;
	
	public Createnoticewindow() 
        
        {
		
		super("Create notice ");
		
		setSize(600,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		name = new JLabel("Name :");
		surname = new JLabel ("Surname :");
		phone = new JLabel("Phone :");
		job = new JLabel("Job :");
		country = new JLabel("Country :");
		username = new JLabel("Username :");
		date = new JLabel("Date :");
		date2 = new JLabel("Date last : ");
                
		namee = new JTextField(10);
		surnamee = new JTextField(10);
		phonee = new JTextField(10);
		jobb = new JTextField(10);
		countryy = new JTextField(10);
		usernamee = new JTextField(10);
		datee = new JFormattedTextField((new SimpleDateFormat("dd/MM/yyyy")));
		datee.setColumns(10);
                datee2 = new JFormattedTextField((new SimpleDateFormat("dd/MM/yyyy")));
		datee2.setColumns(10);
		create = new JButton("create ");
                b2=new JButton("Back");
		create.addActionListener(this);
		b2.addActionListener(this);
		createpanel = new JPanel();
		
		BoxLayout box = new BoxLayout(createpanel,BoxLayout.LINE_AXIS);
		
		createpanel.add(Box.createRigidArea(new Dimension(700,10)));
		createpanel.add(name);
		createpanel.add(namee);
		createpanel.add(Box.createRigidArea(new Dimension(700,10)));
		createpanel.add(surname);
		createpanel.add(surnamee);
                createpanel.add(Box.createRigidArea(new Dimension(700,10)));
		createpanel.add(job);
		createpanel.add(jobb);
		createpanel.add(Box.createRigidArea(new Dimension(700,10)));
		createpanel.add(phone);
		createpanel.add(phonee);
		createpanel.add(Box.createRigidArea(new Dimension(700,10)));
		createpanel.add(country);
		createpanel.add(countryy);
                createpanel.add(Box.createRigidArea(new Dimension(700,10)));
		createpanel.add(username);
		createpanel.add(usernamee);
                createpanel.add(Box.createRigidArea(new Dimension(700,10)));
		createpanel.add(date);
		createpanel.add(datee);
                createpanel.add(Box.createRigidArea(new Dimension(700,10)));
		createpanel.add(date2);
		createpanel.add(datee2);
		createpanel.add(Box.createRigidArea(new Dimension(700,10)));
		createpanel.add(create);
		createpanel.add(Box.createRigidArea(new Dimension(700,10)));
		createpanel.add(b2);
		
		this.add(createpanel);
		this.setVisible(true);
		
		
		
		
	
	
}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==create) {
			
			
				
				try {
                                    
                                    
				
                                    
                                    format = new SimpleDateFormat("dd/MM/yyyy");
				
                                    
                                    
				date4 = format.parse(datee.getText());
				date5 = format.parse(datee2.getText());
				
				Noticewindow objecttoserver = new Noticewindow(namee.getText(),surnamee.getText(),phonee.getText(),jobb.getText(),countryy.getText(),usernamee.getText(),date4,date5);
				
                                    
					JFrame f1=new JFrame();

					/*ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("notice.txt",true));
				        out.writeObject(objecttoserver);
				        out.flush();
				        out.close();*/
					JOptionPane.showMessageDialog(f1, "The notice has been created.","Message",JOptionPane.PLAIN_MESSAGE );
                                        
                                        //δημιουργία socket έτσι ώστε οι σημειώσεις με την χρήση socket να μεταφέρονται με μηνύματα με την χρήση της κλάσης Messagewindow στον server και να καταχωρούνται σε αρχείο που δημιουργείται το αρχείο στον server.
                                        
                                         Socket sock = new Socket("127.0.0.1",1111);
                                         
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
                                        
			                Messagewindow message = new Messagewindow();
                                        
                                        
			                message.setMessage("HELLOSERVER");               
			                os.writeObject(message);  
			                os.flush();
			                
			                System.out.println("The notice has been created.");
			                
			                
			                          
			                 message = (Messagewindow) is.readObject();
			                 
			                if(message.getMessage().equalsIgnoreCase("IAMWAITINGFORCLIENT")){
			                   
			                    message.setMessage("CREATENOTICE");
			                    os.writeObject(message); 
			                    os.flush();                                    
			             
			                    os.writeObject(objecttoserver); 
			                    os.flush();                     
			                    
			                    message = (Messagewindow) is.readObject();
			                    
			                   
			                    if(message.getMessage().equalsIgnoreCase("NOTICECREATED")){
			                     
			                       objecttoserver =(Noticewindow) is.readObject();
			                        
			                        os.close();
			                        is.close();
			                        sock.close();
                                                
                                                 
                                                this.dispose();
                                                Noticeboardmenuwindow nbwm = new Noticeboardmenuwindow();
			                       
                                            
			                    }
			                    
			                    else{
			                        System.out.println("USER DIDNT CREATE SUCCESSFULLY THE NOTICE");
			                        is.close();
			                        os.close();
			                        sock.close();
			                        
			                    }                                      
			                }
			                
			                else{
			                    System.out.println("CLIENT DIDNT RESPOND");
			                    os.close();
			                    is.close();
			                    sock.close();
			                }   
			                
			            } catch (IOException ex) {
			                Logger.getLogger(Createnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
			            }       catch (ClassNotFoundException ex) { 
                                                Logger.getLogger(Createnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (ParseException ex) { 
                        Logger.getLogger(Createnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                                        
					
				
				
			
			
                        
		
		
	}
		if(e.getSource()==b2) {
			
			try{
                            
                            //δημιουργία socket για back.
                        
                        	 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
			                
			               
						JOptionPane.showMessageDialog(this, "Back.","Message",JOptionPane.PLAIN_MESSAGE );
						
                                        
                                        
			                Messagewindow message = new Messagewindow();
                                        
                                        
			                message.setMessage("HELLOSERVER");               
			                os.writeObject(message);  
			                os.flush();
			                
			                System.out.println("Back.");
			                
			                
			                          
			                 message = (Messagewindow) is.readObject();
			                 
			                if(message.getMessage().equalsIgnoreCase("IAMWAITINGFORCLIENT")){
			                   
			                    message.setMessage("OPENBACKEXIT");
			                    os.writeObject(message); 
			                    os.flush();                                    
			             
			                    
			                    message = (Messagewindow) is.readObject();
			                    
			                   
			                    if(message.getMessage().equalsIgnoreCase("SUCCESS")){
			                     
			                       
			                        
			                        os.close();
			                        is.close();
			                        sock.close();
                                                
                                                 
                                               this.dispose();
	                                       Noticeboardmenuwindow nbmw = new Noticeboardmenuwindow();
			                       
                                            
			                    }
			                    
			                    else{
			                        System.out.println("USER DIDNT BACK SUCCESSFULLY.");
			                        is.close();
			                        os.close();
			                        sock.close();
			                        
			                    }                                      
			                }
			                
			                else{
			                    System.out.println("CLIENT DIDNT RESPOND.");
			                    os.close();
			                    is.close();
			                    sock.close();
			                }   
	               
	            }   catch (IOException ex) {
                            Logger.getLogger(Createnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Createnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
		}

	
	
}
}
