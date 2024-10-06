//Chronopoulos Panagiotis 321/2015222
package noticeboardserver;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


import noticeboardclient.Messagewindow;
import noticeboardclient.Userswindow;
import noticeboardclient.Noticewindow;


public class Noticeboardserverwindow {
  
    public static void main(String[] args) throws ClassNotFoundException, IOException 
    //ο server μας που επικοινωνεί με τον χρήστη για tην εγγραφή χρηστών καταχωρώντας στο arraylist τα accounts των χρηστών  , τα notices των χρηστών σε αρχείο , τις διαγραφές των notices μέσα από το αρχείο , και τα αποτελέσματα των ανακοινώσεων που θέλει ο χρήστης να δει τα οποία ο server στέλνει μέσω των scokets και ο χρήστης μπορεί να τα δει μέσω της εφαρμογής με την χρήση της arraylist results. 
    {
      
        try {
           
            ArrayList <Userswindow> accounts = new ArrayList();
             ArrayList <Noticewindow> notices = new ArrayList();
             ArrayList <Noticewindow> results = new ArrayList();
        
            ServerSocket ServerSock = new ServerSocket(1111);           
            
            System.out.println("Server is up and running...");
            
            while(true){
                
              
              
                Socket sock = ServerSock.accept();
                 
              
                
                ObjectOutputStream os = new ObjectOutputStream(sock.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(sock.getInputStream());
                
               
                         
                Messagewindow message = (Messagewindow) is.readObject();   
                
                Userswindow uaaw = new Userswindow();
                Noticewindow objecttoserver = new Noticewindow();
               
                 
                if(message.getMessage().equalsIgnoreCase("HELLOSERVER")){
                  
                    message.setMessage("IAMWAITINGFORCLIENT");
                    os.writeObject(message);
                    os.flush();
                    
                  
                    message = (Messagewindow) is.readObject();                   
                    
                    if(message.getMessage().equalsIgnoreCase("REGISTERACCOUNT")){
                        uaaw = (Userswindow) is.readObject();
                     
                        accounts.add(uaaw);
                        
                        
                        message.setMessage("REGISTERED");
                        os.writeObject(message);
                        os.flush();
                        
                        os.writeObject(uaaw);
                        os.flush();
                        os.close();
                       
                        System.out.println("The approval for registration successfully completed."+ " , "+uaaw.toString());
                   
                      
                                           
                    }
                    
                    else if(message.getMessage().equalsIgnoreCase("LOGINACCOUNT")){
                       
                        
                        message.setMessage("LOGGED");
                        os.writeObject(message);
                        os.flush();
                        
                       
                        os.close();
                       
                        System.out.println("The approval for log in successfully completed.");
                   
                      
                                           
                    }
                  
                    //απλώς αυτό το socket δεν το έχω χρησιμοποιήσει μόνο για back και Exit αλλά και για τα ανοίγματα των windows : registerwindow,loginwindow,createnoticewindow,modify_deletewindow και Searchnoticewindow καιResultsnoticewindow
                      else if(message.getMessage().equalsIgnoreCase("OPENBACKEXIT")){
                       
                        
                        message.setMessage("SUCCESS");
                        os.writeObject(message);
                        os.flush();
                        
                       
                        os.close();
                       
                        System.out.println("The approval for open window , back or exit successfully completed.");
                   
                      
                                           
                    }
                    
                
                
                  
                    else if(message.getMessage().equalsIgnoreCase("CREATENOTICE")){
                        objecttoserver = (Noticewindow) is.readObject();
                     
                        notices.add(objecttoserver);
                        
                        
                        message.setMessage("NOTICECREATED");
                        os.writeObject(message);
                        os.flush();
                        
                        
                        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("notice.txt",true));
		        out.writeObject(objecttoserver);
		        out.flush();
		        out.close();
                        
                       os.writeObject(objecttoserver);
                       os.flush();
                       
                        
                        
                        
                       
                        
                       
                        System.out.println("The approval for creating a notice successfully completed."+" , "+objecttoserver.toString());
                        
                        
                        
                      
                                           
                    }
                      
			
                       
                                           
                    
                  
                    
                
                else  if(message.getMessage().equalsIgnoreCase("DELETENOTICE")){
                
                  
                  objecttoserver = (Noticewindow) is.readObject();
                        
                        for(int i=0;i<notices.size();i++){
                            
                            System.out.println("Username : " + objecttoserver.getusername() + " , " + "username : " + notices.get(i).getusername());
                            
                            if(objecttoserver.getusername().equals(notices.get(i).getusername())){
                               
                                
                                notices.remove(i);
                                message.setMessage("NOTICEDELETED");
                                os.writeObject(message);
                                os.flush();           
                                PrintWriter writer = new PrintWriter("notice.txt");
                                writer.print("");

                                writer.close();
                                 
                            }
                           
                        }
                        message.setMessage("OK");
                        os.writeObject(message);
                        os.flush();
                   
                          
                                             
                        
                        
                        
                        
                        System.out.println("The approval for deleting a notice succssfully completed."+ " , "+objecttoserver.toString());
                       
                }
                      else if (message.getMessage().equalsIgnoreCase("SEARCHNOTICE")){
                    
                    objecttoserver  = (Noticewindow) is.readObject();
                    
                    for(int i=0; i<notices.size(); i++){
                    
                        
                        System.out.println("date 1 : "+objecttoserver.getdate()+" , "+notices.get(i).getdate()+" , " +"date 2 : "+objecttoserver.getdate2()+" , "+notices.get(i).getdate2());
                        if((objecttoserver.getdate().equals(notices.get(i).getdate()) && objecttoserver.getdate2().equals(notices.get(i).getdate2()))){
                            
                            results.add(notices.get(i));
                            System.out.println("The notice is showed in yout screen.");
                        }
                     System.out.println("The approval for searching a notice successfully completed."+" , "+notices.get(i).toString());
                            
                }   
                   
                  
                    os.writeObject(results);
                    os.flush();
                    message.setMessage("SEARCHED");
                    os.writeObject(message);
                    os.flush();
                    
                    
                }
                    
                
                    
                      
                }   else{
                    System.out.println("SERVER DIDNT RESPOND.");
                    os.close();
                    is.close();   
                    sock.close();
                }      

            }
            
        } catch (IOException ex) {
            Logger.getLogger(Noticeboardserverwindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

