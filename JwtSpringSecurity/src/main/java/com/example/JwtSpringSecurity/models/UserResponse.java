package com.example.JwtSpringSecurity.models;

public class UserResponse {
    private String status;
    private String message;
    Payload PayloadObject;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Payload getPayload() {
        return PayloadObject;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPayload(Payload payloadObject) {
        this.PayloadObject = payloadObject;
    }

    public class Payload {
        User UserObject;
        private String token;

        public User getUser() {
            return UserObject;
        }

        public String getToken() {
            return token;
        }

        public void setUser(User userObject) {
            this.UserObject = userObject;
        }

        public void setToken(String token) {
            this.token = token;
        }
        public class User {
            private String id;
            private String firstName;
            private String lastName;
            private String location;
            private String email;
            private String role;


            public String getId() {
                return id;
            }

            public String getFirstName() {
                return firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public String getLocation() {
                return location;
            }

            public String getEmail() {
                return email;
            }

            public String getRole() {
                return role;
            }

            // Setter Methods

            public void setId(String id) {
                this.id = id;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public void setRole(String role) {
                this.role = role;
            }
        }//user closing
    }//payload closing
}//UserResponse closing
