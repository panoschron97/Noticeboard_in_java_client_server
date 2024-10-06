
package noticeboardclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Loginwindow 

{

	JTextField username,password;
	
	Loginwindow() //σύνδεση στην εφαρμογή με username και password.
        
        {
		
	
		Logininformation();
	}


	private void Logininformation() 
        
        {
		JFrame f1=new JFrame("Login system");
        JPanel p1=new JPanel();
        p1.setLayout(null);
        JLabel l1=new JLabel("username");
        JLabel l2=new JLabel("password");
        JButton b1=new JButton("login");
        
    	b1.addActionListener(new ActionListener() //επαλήθευση στοιχείων για σύνδεση στην εφαρμογή. 
        
        {
			@Override
			public void actionPerformed(ActionEvent e) {
				 
			     try {
                                 
                  
                                 
                                  //δημιουργία socket για σύνδεση του χρήστη στην εφαρμογή έτσι ώστε ο server να μπορεί να εγκρίνει την σύνδεση του.
                                 
                                 	 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
      
                                        
			                Messagewindow message = new Messagewindow();
                                        
                                        
			                message.setMessage("HELLOSERVER");               
			                os.writeObject(message);  
			                os.flush();
			                
			                
			                          
			                 message = (Messagewindow) is.readObject();
			                 
			                if(message.getMessage().equalsIgnoreCase("IAMWAITINGFORCLIENT")){
			                   
			                    message.setMessage("LOGINACCOUNT");
			                    os.writeObject(message); 
			                    os.flush();                                    
			             
			                                       
			                    
			                    message = (Messagewindow) is.readObject();
			                    
			                   
			                    if(message.getMessage().equalsIgnoreCase("LOGGED")){
			                     
			                            Scanner input = new Scanner(new File("login.txt"));
			            while (input.hasNextLine())
			            {
			              String s = input.nextLine();  
			              String[] sArray = s.split(",");
			              
			              if (username.getText().equals(sArray[0]) && password.getText().equals(sArray[1]))
			              {
			            	  JOptionPane.showMessageDialog(f1,"You are successfully logged in the application.", "Message", JOptionPane.INFORMATION_MESSAGE);
                                          System.out.println("You are logged in succsefully.");
			            	  f1.dispose();
			            	   Noticeboardmenuwindow aw = new Noticeboardmenuwindow();
			              }
                                      else{
                                          JOptionPane.showMessageDialog(f1,"Try again.", "Message", JOptionPane.INFORMATION_MESSAGE);
                                      }
			             
			            }
			            
			            input.close();
			                        
			                        os.close();
			                        is.close();
			                        sock.close();
                        
			                    }
			                    
			                    else{
			                        System.out.println("USER DIDNT LOGGED IN SUCCESSFULLY TO THE APPLICATION.");
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
 
			            
			        } catch (FileNotFoundException e1) {
			            JOptionPane.showMessageDialog(f1,"Wrong username or password.", "Message",JOptionPane.INFORMATION_MESSAGE);
			        } catch (IOException ex) {
                                Logger.getLogger(Loginwindow.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Loginwindow.class.getName()).log(Level.SEVERE, null, ex);
                            }
			        
			}	
			
		});
    	
       
        JButton b2=new JButton("Exit");
        b2.addActionListener(new ActionListener(
     ){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                     try{
                         
                         //δημιουργία socket για Exit.
                        
                        	 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
			                
			               
						JOptionPane.showMessageDialog(f1, "Exit the application.","Message",JOptionPane.PLAIN_MESSAGE );
						
                                        
                                        
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
                                                
                                                 
                                               
	                                       System.exit(0);
			                       
                                            
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
                            Logger.getLogger(Loginwindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Loginwindow.class.getName()).log(Level.SEVERE, null, ex);
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