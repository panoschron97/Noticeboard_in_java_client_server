
package noticeboardclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Mainwindow 

{
	
	

		
		
	public Mainwindow() // η mainwindow η οποία τρέχει την Firstwindow κλάση για login , register ή exit.
        
        {
		
		Firstwindow();
		
	}
	
	private void Firstwindow () 
        
        {
		
		JFrame f = new JFrame("Notice board");
		JPanel p = new JPanel();
		JButton b1 =new JButton("Login");
		JButton b2 = new JButton("Register");
		JButton b3 = new JButton("Exit");
		
		p.setLayout(null);
		
		b1.addActionListener(new ActionListener() //ActionListener όταν ο χρήστης πατάει το κουμπί login για σύνδεση στην εφαρμογή με scoket.
                {
			@Override
			public void actionPerformed(ActionEvent e) {
                            
				 try{
                        
                        	 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
			                
			               
						JOptionPane.showMessageDialog(f, "Login window will open.","Message",JOptionPane.PLAIN_MESSAGE );
						
                                        
                                        
			                Messagewindow message = new Messagewindow();
                                        
                                        
			                message.setMessage("HELLOSERVER");               
			                os.writeObject(message);  
			                os.flush();
			                
			                System.out.println("Login window will open.");
			                
			                
			                          
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
                                                
                                                 
                                               
	                                       f.dispose();
                                               Loginwindow lw = new Loginwindow();
			                       
                                            
			                    }
			                    
			                    else{
			                        System.out.println("USER DIDNT WENT TO LOGIN WINDOW SUCCESSFULLY.");
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
                            Logger.getLogger(Mainwindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Mainwindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
			}
			
			});
			
		
		b2.addActionListener(new ActionListener() //ActionListener όταν ο χρήστης πατάει το κουμπί register για δημιουργία λογαριασμού στην εφαρμογή με socket.
                {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			 try{
                        
                        	 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
			                
			               
						JOptionPane.showMessageDialog(f, "Register window will open.","Message",JOptionPane.PLAIN_MESSAGE );
						
                                        
                                        
			                Messagewindow message = new Messagewindow();
                                        
                                        
			                message.setMessage("HELLOSERVER");               
			                os.writeObject(message);  
			                os.flush();
			                
			                System.out.println("Registration window will open.");
			                
			                
			                          
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
                                                
                                                 
                                               
	                                       f.dispose();
                                               Registerwindow rw = new Registerwindow();
			                       
                                            
			                    }
			                    
			                    else{
			                        System.out.println("USER DIDNT REGISTER SUCCESSFULLY.");
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
                            Logger.getLogger(Mainwindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Mainwindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
		}
		
		});
		
		b3.addActionListener(new ActionListener() //ActionListener όταν ο χρήστης πατάει το κουμπί exit για κλείσιμο τηις εφαρμογής με socket.
                {
			@Override
			public void actionPerformed(ActionEvent e) {
				 try{
                        
                        	 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
			                
			               
						JOptionPane.showMessageDialog(f, "Exit.","Message",JOptionPane.PLAIN_MESSAGE );
						
                                        
                                        
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
			                        System.out.println("USER EXIT SUCCESSFULLY.");
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
                            Logger.getLogger(Mainwindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Mainwindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
			}
			
			});
		
		b1.setBounds(150,40,100,20);
		b2.setBounds(150,70,100,20);
		b3.setBounds(150,100,100,20);
		p.add(b1);
		p.add(b2);
		p.add(b3);
		f.add(p);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400,300);
		f.setVisible(true);
		
		
		
	}


}