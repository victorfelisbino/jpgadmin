package com.products;

 public class UserBean {
	
      private String username;
      private String password;
      private String firstName;
      private String lastName;
      private String email;
      private Integer id;
      public boolean valid;
	
	
      public String getFirstName() {
         return firstName;
	}

      public void setFirstName(String newFirstName) {
         firstName = newFirstName;
	}

	
      public String getLastName() {
         return lastName;
			}

      public void setLastName(String newLastName) {
         lastName = newLastName;
			}
			

      public String getPassword() {
         return password;
	}

      public void setPassword(String newPassword) {
         password = newPassword;
	}
	
			
      public String getUsername() {
         return username;
			}

      public void setUserName(String newUsername) {
         username = newUsername;
			}

				
      public boolean isValid() {
         return valid;
	}

      public void setValid(boolean newValid) {
         valid = newValid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
}
