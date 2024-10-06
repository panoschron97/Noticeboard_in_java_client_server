
package noticeboardclient;

import java.awt.event.ActionEvent;


import java.net.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;





public class Registerwindow extends JFrame  //κλάση δημιουργίας λογαριασμού 

{


	
	JTextField username;
	JTextField password;
        
	
        
	Registerwindow(){
		
		userinformation();
	}

	public void userinformation() {
		  JFrame f1=new JFrame("Register system");
	        JPanel p1=new JPanel();
	        p1.setLayout(null);
	        JLabel l1=new JLabel("username");
	        JLabel l2=new JLabel("password");
	        JButton b1=new JButton("register");
	    	b1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
				
						try {
						
                                                    
                                                    //δημιούργησα ένα txt για να μπορώ να κάνω επαλύθευση των στοιχείων username και password όταν ο χρήστης κάνει login.
						Userswindow uaaw = new Userswindow(username.getText(),password.getText());
                                                
                                                
						 BufferedWriter out = new BufferedWriter(new FileWriter("login.txt",true));
					    out.write(username.getText() + "," + password.getText());
					    out.newLine();
					    out.close();
                                            
                                            //δημιουργία socket me to local ip έτσι ώστε να μπορώ το username και το password να το αποθηκεύσω στο arraylist accounts στην μεριά του server μέσω μηνυμάτων μεταξύ client και server μέσω sockets.
							
						 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
			                
			               
						JOptionPane.showMessageDialog(f1, "The account has been created.","Message",JOptionPane.PLAIN_MESSAGE );
						
                                        
                                        
			                Messagewindow message = new Messagewindow();
                                        
                                        
			                message.setMessage("HELLOSERVER");               
			                os.writeObject(message);  
			                os.flush();
			                
			                System.out.println("The account has been created.");
			                
			                
			                          
			                 message = (Messagewindow) is.readObject();
			                 
			                if(message.getMessage().equalsIgnoreCase("IAMWAITINGFORCLIENT")){
			                   
			                    message.setMessage("REGISTERACCOUNT");
			                    os.writeObject(message); 
			                    os.flush();                                    
			             
			                    os.writeObject(uaaw); 
			                    os.flush();                     
			                    
			                    message = (Messagewindow) is.readObject();
			                    
			                   
			                    if(message.getMessage().equalsIgnoreCase("REGISTERED")){
			                     
			                       
			                        
			                        os.close();
			                        is.close();
			                        sock.close();
                                                
                                                 
                                                f1.dispose();
                                                Mainwindow  mw = new Mainwindow(); 
			                       
                                            
			                    }
			                    
			                    else{
			                        System.out.println("USER DIDNT CREATE SUCCESSFULLY THE REGISTRATION");
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
			                Logger.getLogger(Registerwindow.class.getName()).log(Level.SEVERE, null, ex);
			            }       catch (ClassNotFoundException ex) { 
                                                Logger.getLogger(Registerwindow.class.getName()).log(Level.SEVERE, null, ex);
                                            } 
				
                                               
			                       
				
                                }		
                
				
				
			});
	    	
	    	
	       
	        JButton b2=new JButton("Back");
	        b2.addActionListener(new ActionListener(
	     ){
	            @Override
	            public void actionPerformed(ActionEvent e) 
                            
	            {
                        try{
                            //δημιουργία socket για back.
                        
                        	 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
			                
			               
						JOptionPane.showMessageDialog(f1, "Back.","Message",JOptionPane.PLAIN_MESSAGE );
						
                                        
                                        
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
                                                
                                                 
                                               f1.dispose();
	                                       Mainwindow mw = new Mainwindow();
			                       
                                            
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
                            Logger.getLogger(Registerwindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Registerwindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
	        });
	        username=new JTextField(20);
	        password=new JTextField(20);
	        
	        l1.setBounds(50, 18, 100, 20);
	        username.setBounds(130, 20, 100, 20);
	        
	        l2.setBounds(50, 40, 100, 20);
	        password.setBounds(130, 42, 100, 20);
	        b1.setBounds(70,110,100,30);
	        b2.setBounds(170,110,100,30);
	        
	        p1.add(username);
	        p1.add(l1);
	        
	        p1.add(password);
	        p1.add(l2);
	        
	        p1.add(b1);
	        p1.add(b2);
	        f1.add(p1);
	        f1.setLocationRelativeTo(null);
	        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f1.setSize(400,200);
	        f1.setVisible(true);
	        
	    }

}