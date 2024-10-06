
package noticeboardclient;


public class Adminwindow extends Userswindow // κλάση για το username και το password του admin.

{
    
    boolean admin;
    private String username="";
    private String password="";
    
    public Adminwindow (String username , String password){
        this.username=username;
        this.password=password;
        admin = true;
    }
    
}
