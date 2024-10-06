
package noticeboardclient;

import java.io.Serializable;
import java.util.Date;


public class Noticewindow implements Serializable //κλάση για τα notices.

{

        private String name;
	private String surname;
	private String job;
	private String phone;
	private String country;
	private String username;
	private Date date;
        private Date date2;
        
	public Noticewindow() {
		
	}
	
	public Noticewindow (String name,String surname,String phone,String job,String country,String username,Date date , Date date2) 
        
        {
		
		this.name=name;
		this.surname=surname;
		this.job=job;
		this.phone=phone;
		this.country=country;
		this.username=username;
                this.date=date;
                this.date2=date2;
	}
        
	public void setusername(String username){
            this.username=username;
                 
        }
        public void setsurname(String surname){
            this.surname=surname;
        }
        
        public String getname(){
            return name;
        }
        
        public String getusername(){
            return username;
        }
	public String getsurname(){
            return surname;
        }
        public String getjob(){
            return job;
        }
        public String getcountry(){
            return country;
        }
        public Date getdate(){
      return date;
            
        }
        
        public Date getdate2(){
            return date2;
        }
        public void setdate(Date date){
            this.date=date;
        }
        public void setdate1(Date date2){
            this.date2=date2;
        }

    public String getphone() {
       return phone;
    }
    
    
}
