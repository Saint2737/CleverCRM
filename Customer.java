public class Customer{
	
   private int id;
   private String name;
   private String email;
   private int number;
	
   public Customer(int id, String name, String email, int number) {
	   this.id = id;
	   this.name = name;
	   this.email = email;
	   this.number = number;
   }
   
   public int getId() {
	   return id;
   }
   public void setId(int id) {
	   this.id = id;
   }
   public String getName() {
	   return name;
   }
   public void setName(String name) {
	   this.name = name;
   }
   public String getEmail() {
	   return email;
   }
   public void setEmail(String email) {
	   this.email = email;
   }
   public int getNumber() {
	   return number;
   }
   public void setNumber(int number) {
	   this.number = number;
   }
}