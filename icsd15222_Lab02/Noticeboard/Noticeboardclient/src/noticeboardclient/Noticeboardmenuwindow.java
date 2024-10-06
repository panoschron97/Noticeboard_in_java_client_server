
package noticeboardclient;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Noticeboardmenuwindow extends JFrame implements ActionListener // αφού ο χρήστης συνδέθηκε στην εφαρμογή τον μεταβιβάζει στο menu επιλογών.

{
	

	String username;
	
	private JPanel panel;
	private JButton createnotice;
	private JButton modify_deletenotice;
	private JButton searchnotice;
	private JButton exit;
	
	
	public Noticeboardmenuwindow() 
        
        {
		
		super("Welcome user");
		
		createnotice = new JButton("Create notice");
		createnotice.addActionListener(this);
		modify_deletenotice = new JButton("Modify/Delete notice");
		modify_deletenotice.addActionListener(this);
		searchnotice = new JButton("Search notice");
		searchnotice.addActionListener(this);
		exit = new JButton("Exit ");
		exit.addActionListener(this);
		panel = new JPanel();
		
		
		BoxLayout box = new BoxLayout(panel,BoxLayout.LINE_AXIS);
		panel.add(Box.createRigidArea(new Dimension(400,50)));
		panel.add(createnotice);
		panel.add(Box.createRigidArea(new Dimension(400,50)));
		panel.add(modify_deletenotice);
		panel.add(Box.createRigidArea(new Dimension(400,50)));
		panel.add(searchnotice);
		panel.add(Box.createRigidArea(new Dimension(400,50)));
		panel.add(exit);
		panel.add(Box.createRigidArea(new Dimension(400,50)));
		
		setSize(400,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) //το menu επιλογών create,delete,search.
        
        {
		// TODO Auto-generated method stub
		
		if(e.getSource()==createnotice) {
			try{
                        //δημιουργία socket για createnotice window.
                        	 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
			                
			               
						JOptionPane.showMessageDialog(this, "Createnoticewindow will open.","Message",JOptionPane.PLAIN_MESSAGE );
						
                                        
                                        
			                Messagewindow message = new Messagewindow();
                                        
                                        
			                message.setMessage("HELLOSERVER");               
			                os.writeObject(message);  
			                os.flush();
			                
			                System.out.println("Createnoticewindow will open.");
			                
			                
			                          
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
	                                       Createnoticewindow cnw = new Createnoticewindow();
			                       
                                            
			                    }
			                    
			                    else{
			                        System.out.println("USER DIDNT WENT TO CREATENOTICEWINDOW SUCCESSFULLY.");
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
                            Logger.getLogger(Noticeboardmenuwindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Noticeboardmenuwindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
		}
		if(e.getSource()==modify_deletenotice) {
			try{
                        //δημιουργία socket για modify_deletenoticewindow.
                        	 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
			                
			               
						JOptionPane.showMessageDialog(this, "Modify_deletenoticewindow will open.","Message",JOptionPane.PLAIN_MESSAGE );
						
                                        
                                        
			                Messagewindow message = new Messagewindow();
                                        
                                        
			                message.setMessage("HELLOSERVER");               
			                os.writeObject(message);  
			                os.flush();
			                
			                System.out.println("Modify_deletenoticewindow will open.");
			                
			                
			                          
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
	                                       Modify_deletenoticewindow mdnw = new Modify_deletenoticewindow ();
			                       
                                            
			                    }
			                    
			                    else{
			                        System.out.println("USER DIDNT WENT TO MODIFY_DELETENOTICEWINDOW SUCCESSFULLY.");
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
                            Logger.getLogger(Noticeboardmenuwindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Noticeboardmenuwindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
		}
		if(e.getSource()==searchnotice) {
			  try{
                        //δημιουργία socket για Searchnoticewindow.
                        	 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
			                
			               
						JOptionPane.showMessageDialog(this, "Searchnoticewindow will open.","Message",JOptionPane.PLAIN_MESSAGE );
						
                                        
                                        
			                Messagewindow message = new Messagewindow();
                                        
                                        
			                message.setMessage("HELLOSERVER");               
			                os.writeObject(message);  
			                os.flush();
			                
			                System.out.println("Searchnoticewindow will open.");
			                
			                
			                          
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
	                                       Searchnoticewindow snw = new Searchnoticewindow();
			                       
                                            
			                    }
			                    
			                    else{
			                        System.out.println("USER DIDNT WENT TO SEARCHNOTICEWINDOW SUCCESSFULLY.");
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
                            Logger.getLogger(Noticeboardmenuwindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Noticeboardmenuwindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
		}
                if(e.getSource()==exit) {
			
                         try{
                             //δημιουργία socket για Exit.
                        
                        	 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
			                
			               
						JOptionPane.showMessageDialog(this, "Exit the application.","Message",JOptionPane.PLAIN_MESSAGE );
						
                                        
                                        
			                Messagewindow message = new Messagewindow();
                                        
                                        
			                message.setMessage("HELLOSERVER");               
			                os.writeObject(message);  
			                os.flush();
			                
			                System.out.println("Exit.");
			                
			                
			                          
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
	                                       Mainwindow mw = new Mainwindow();
			                       
                                            
			                    }
			                    
			                    else{
			                        System.out.println("USER DIDNT EXIT SUCCESSFULLY.");
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
                            Logger.getLogger(Noticeboardmenuwindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Noticeboardmenuwindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
		}
        }
}