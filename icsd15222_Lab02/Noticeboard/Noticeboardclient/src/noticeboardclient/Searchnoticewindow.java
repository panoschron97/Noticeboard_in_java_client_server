
package noticeboardclient;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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

class Searchnoticewindow extends JFrame implements ActionListener //κλάση για παράθυρο εμφάνισης στοιχείων επιλογής μου.

{
private JLabel date;
private JLabel date1;
private JFormattedTextField datee;
private JFormattedTextField datee1;
private JPanel panel;
private JButton searching ,back;

    
    private ArrayList <Noticewindow> results;
    
    public Searchnoticewindow()
    
    {
        
        super("Search notice");
        
        date = new JLabel("Date : " );
        date1 = new JLabel("Date last : " );
        
        datee = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        datee.setColumns(10);
        datee1 = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        datee1.setColumns(10);
        
        searching = new JButton("Search");
        searching.addActionListener(this);
        back = new JButton("Back");
        back.addActionListener(this);
        
        panel = new JPanel();
        
        BoxLayout box = new BoxLayout(panel, BoxLayout.LINE_AXIS);
        
        
        
        
        panel.add(Box.createRigidArea(new Dimension(600,10)));
        panel.add(date);
        panel.add(datee);
        panel.add(Box.createRigidArea(new Dimension(600,10)));
        panel.add(date1);
        panel.add(datee1);
        panel.add(Box.createRigidArea(new Dimension(600,10)));
        panel.add(searching);
        panel.add(Box.createRigidArea(new Dimension(600,10)));
        panel.add(back);
        
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(panel);
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    
    {
        
        if(e.getSource()==searching){
            
            try{
                
            results = new ArrayList();
            Date date4,date5;
            
            
            Noticewindow objecttoserver = new Noticewindow();
            
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            
           
            date4 = format.parse(datee.getText());
            date5 = format.parse(datee1.getText());
            
            
           objecttoserver.setdate(date4);
            objecttoserver.setdate1(date5);
            
              //δημιουργία socket έτσι ώστε οι σημειώσεις με την χρήση socket να εμφανιστούν οι σημειώσεις που απαιτεί ο client με βάση τις ημερομηνίες που είσάγει στο application ο χρήστης και ο server ενημερώνεται και μας στέλνει τα στοιχεία των σημειώσεων.
            
            Socket sock = new Socket("127.0.0.1", 1111);
            
            ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
            
            
            Messagewindow message = new Messagewindow();
            message.setMessage("HELLOSERVER");
            os.writeObject(message);
            os.flush();
            message = (Messagewindow) is.readObject();
            
            if(message.getMessage().equalsIgnoreCase("IAMWAITINGFORCLIENT")){
                
                message.setMessage("SEARCHNOTICE");
                
                os.writeObject(message);
                os.flush();
                os.writeObject(objecttoserver);
                os.flush();
                results = (ArrayList<Noticewindow>) is.readObject();
                message = (Messagewindow) is.readObject();
                
                if(message.getMessage().equalsIgnoreCase("SEARCHED"))
                
                {
                    System.out.println("Searching...");
                    os.close();
                    is.close();
                    sock.close();
                    
                    this.dispose();
                    
                    //παράθυρο με τα αποτελέσματα των σημειώσεων με βάση τις ημερομηνίες που εισάγει ο χρήστης και αφού έχει τελειώσει το πρωτόκολλο χρήστη -εξυπηρετή με σωστά μηνύματα με την χρήση του Messagewindow.
                    
                    Resultsnoticewindow r = new Resultsnoticewindow(results);
                    
                }
                else{
                    System.out.println("CLIENT DIDNT RESPOND");
                    os.close();
                    is.close();
                    sock.close();
                }
            }
            os.flush();
            os.close();
            is.close();
            sock.close();
            
        }   catch (ParseException ex) {
                Logger.getLogger(Searchnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Searchnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Searchnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
      
    }
        
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
                            Logger.getLogger(Searchnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Searchnoticewindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
        }
}
}