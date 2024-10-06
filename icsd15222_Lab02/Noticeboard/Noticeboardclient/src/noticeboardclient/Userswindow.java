
package noticeboardclient;

import java.io.Serializable;


public class Userswindow implements Serializable //κλάση για όλα τα usernames και passwords των χρηστών.

{

   	private String username;
	private String password;
	
	
	public Userswindow() {
		
	}
	
	
	public Userswindow(String username , String password) {
		
		this.username=username;
		this.password=password;
	}
    
}
