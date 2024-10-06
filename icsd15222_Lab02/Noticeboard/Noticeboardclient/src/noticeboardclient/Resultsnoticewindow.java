
package noticeboardclient;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
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

/**
 *
 * @author panos
 */
public class Resultsnoticewindow extends JFrame implements ActionListener //κλάση με τα αποτελέσματα των σημειώσεων έχοντας ο client με τον εξυπηρετή δώσει μεταξύ τους τα σωστά μηνύματα και έχει γίνει με επιτυχία η επικοινωνία μεταξύ τους.

{

    
   
    private JPanel panel;
    private JButton back;
    
    public Resultsnoticewindow(ArrayList <Noticewindow> results){
        
        super("Results");
        
        panel = new JPanel();
        BoxLayout box = new BoxLayout(panel, BoxLayout.LINE_AXIS);
        
        try{
                        //δημιουργία socket για εμφάνιση αποτελεσμάτων με sockeτ.
                        	 Socket sock = new Socket("127.0.0.1",1111);
                                            
			               
			                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
			                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
			                
			               
						JOptionPane.showMessageDialog(this, "Now the results will appear to you.","Message",JOptionPane.PLAIN_MESSAGE );
						
                                        
                                        
			                Messagewindow message = new Messagewindow();
                                        
                                        
			                message.setMessage("HELLOSERVER");               
			                os.writeObject(message);  
			                os.flush();
			                
			                System.out.println("Now the results will appear to you.");
			                
			                
			                          
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
                                                
                                                 
                                                 for(int i=0; i<results.size(); i++){
            
            
                                                 panel.add(Box.createRigidArea(new Dimension(1000,10)));
                                                 panel.add(new JLabel("Name : "+results.get(i).getname()));
            
                                                 panel.add(Box.createRigidArea(new Dimension(1000,10)));
                                                 panel.add(new JLabel("Surname : "+results.get(i).getsurname()));
            
                                                 panel.add(Box.createRigidArea(new Dimension(1000,10)));
                                                 panel.add(new JLabel("Job : "+results.get(i).getjob()));
            
            
                                                 panel.add(Box.createRigidArea(new Dimension(1000,10)));
                                                 panel.add(new JLabel("Phone : "+results.get(i).getphone()));
            
                                                  panel.add(Box.createRigidArea(new Dimension(1000,10)));
                                                  panel.add(new JLabel("Country : "+results.get(i).getcountry()));
            
                                                  panel.add(Box.createRigidArea(new Dimension(1000,10)));
                                                  panel.add(new JLabel("Username : "+results.get(i).getusername()));
            
                                                  panel.add(Box.createRigidArea(new Dimension(1000,10)));
                                                  panel.add(new JLabel("Date : "+results.get(i).getdate().toString()));
            
                                                  panel.add(Box.createRigidArea(new Dimension(1000,10)));
                                                  panel.add(new JLabel("Date last : "+results.get(i).getdate2().toString()));
    
        }
      
                                       
			                    }
			                    
			                    else{
			                        System.out.println("USER DIDNT SAW RESULTS SUCCESSFULLY.");
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
                            Logger.getLogger(Resultsnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Resultsnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
        
        
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        back = new JButton("back");
        back.addActionListener(this);
        panel.add(back);
        this.add(panel);
        this.setVisible(true);
        
        
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==back){
            
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
                            Logger.getLogger(Resultsnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Resultsnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
        }
        
        
        
    }
    
}
