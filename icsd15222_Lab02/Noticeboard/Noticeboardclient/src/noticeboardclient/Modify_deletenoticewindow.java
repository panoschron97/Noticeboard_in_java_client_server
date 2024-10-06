
package noticeboardclient;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Modify_deletenoticewindow extends JFrame implements ActionListener //κλάση για παράθυρο διαγραφής στοιχείων.

{

	
	private JPanel panel;
	private JButton delete;
	private JButton back;
	private JLabel username;
	private JTextField usernamee;
	
	public Modify_deletenoticewindow() 
        
        {
		
		super("Modify_delete notice");
		
		
		username = new JLabel("Give the username to delete: ");
		usernamee = new JTextField(10);
		delete = new JButton("Delete notice");
		delete.addActionListener(this);
		back = new JButton("Back ");
		back.addActionListener(this);
		panel=new JPanel();
		
		
		BoxLayout box = new BoxLayout(panel,BoxLayout.LINE_AXIS);
		
		panel.add(Box.createRigidArea(new Dimension(400,50)));
		panel.add(username);
		panel.add(Box.createRigidArea(new Dimension(400,50)));
		panel.add(usernamee);
		panel.add(Box.createRigidArea(new Dimension(400,50)));
		panel.add(delete);
		panel.add(Box.createRigidArea(new Dimension(400,50)));
		panel.add(back);
		panel.add(Box.createRigidArea(new Dimension(400,50)));
		
		this.add(panel);
		this.setVisible(true);
		
		setSize(400,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==back) {
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
                            Logger.getLogger(Modify_deletenoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Modify_deletenoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
		}
		
		if(e.getSource()==delete) {
			
                    

	         try {
		
                    
                    Noticewindow objecttoserver = new Noticewindow();
                    objecttoserver.setusername(usernamee.getText());
                    
                    
                    
		JFrame f1=new JFrame();

					/*ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("notice.txt",true));
				        out.writeObject(objecttoserver);
				        out.flush();
				        out.close();*/
					
                                          //δημιουργία socket έτσι ώστε οι σημειώσεις με την χρήση socket να μεταφέρονται με μηνύματα με την χρήση της κλάσης Messagewindow στον server και να διαγράφονται από το αρχείο στην μεριά του server.
                                        	
						 Socket sock = new Socket("127.0.0.1",1111);
                                                 
                                                  
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
                                                 
                                          Messagewindow message = new Messagewindow();
                                        
                                      
			                message.setMessage("HELLOSERVER");               
			                os.writeObject(message);  
			                os.flush();
			                
			                System.out.println("The notice has been deleted.");
			                
			                
			                          
			                 message = (Messagewindow) is.readObject();
			                 
			                if(message.getMessage().equalsIgnoreCase("IAMWAITINGFORCLIENT")){
			                   
			                    message.setMessage("DELETENOTICE");
			                    os.writeObject(message); 
			                    os.flush();                                    
			             
			                    os.writeObject(objecttoserver); 
			                    os.flush();                     
			                    
			                    
			                    
                                             
			                    message = (Messagewindow) is.readObject();
                                            
                                            
			                    if(message.getMessage().equalsIgnoreCase("NOTICEDELETED")){
                                                
                                                JOptionPane.showMessageDialog(this, "The notice has been deleted.");
                                                
                                            
                                            
                                            message = (Messagewindow) is.readObject();
                                            
                                        
                                                
                                            }
                                             if(message.getMessage().equalsIgnoreCase("OK")){
                                                 
                                                is.close();
                                                os.close();
                                                sock.close();
                                             }
                                              
                                               this.dispose();
					     Noticeboardmenuwindow nbw = new Noticeboardmenuwindow ();
                                        }
			                } 
                                           
			                    
                                                
                                                
                                                catch (IOException ex) {
                        Logger.getLogger(Modify_deletenoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Modify_deletenoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
{
			                  
			           
		

	
}
        }}}