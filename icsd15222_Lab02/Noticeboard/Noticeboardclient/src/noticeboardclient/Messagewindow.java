


package noticeboardclient;

import java.io.Serializable;

/**
 *
 * @author panos
 */
public class Messagewindow implements Serializable //κλάση για τα μηνύματα μέσω των sockets που φτιάξαμε για να μπορεί ο server να επικοινωνεί με τον χρήστη και το αντίθετο.

{

   private String Message;
    
    public Messagewindow(){
        
    }
    
    public String getMessage(){
        return Message;
    }
    
    public void setMessage(String Message){
        this.Message=Message;
    }
    
}
