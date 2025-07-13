    package com.graphql.project.entity;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import jakarta.persistence.*;

    import java.math.BigDecimal;
    import java.util.List;

    @Entity
    @NamedQueries(
            {
                    @NamedQuery(name = "Customer.findCustomerByName", query = "SELECT c FROM Customer c WHERE c.firstName = :firstName AND c.lastName = :lastName"),
                    @NamedQuery(name = "Customer.findCustomerByState", query = "SELECT c FROM Customer c WHERE c.state = :state"),
                    @NamedQuery(name = "Customer.findCustomerByCity", query = "SELECT c FROM Customer c WHERE c.city = :city" ),
                    @NamedQuery(name = "Customer.findCustomerAll", query = "SELECT C FROM Customer c LEFT JOIN FETCH e.payments LEFT JOIN FETCH e.customerOrders")
            }
    )
    public class Customer {
        @Id
        @GeneratedValue
        private int customerId;

        @ManyToOne
        @JsonBackReference
        @JoinColumn(name = "employeeId")
        private Employee salesRepEmployeeNum;

        private String lastName;
        private String firstName;
        private String phone;
        private String address1;
        private String address2;
        private String city;
        private String state;
        private int postalCode;
        private String country;
        @Column(precision = 19, scale = 0)
        private BigDecimal creditLimit;

        @OneToMany(mappedBy = "CustomerId", fetch = FetchType.LAZY)
        @JsonManagedReference
        private List<Payment> payments;


        @OneToMany(mappedBy = "CustomerId", fetch = FetchType.LAZY)
        @JsonManagedReference
        private List<Order> customerOrders;


        public Customer(int customerId,
                        Employee salesRepEmployeeNum,
                        String lastName, String firstName,
                        String phone, String address1,
                        String address2, String city,
                        String state, int postalCode,
                        String country,
                        BigDecimal creditLimit,
                        List<Payment> payments,
                        List<Order> customerOrders) {
            this.customerId = customerId;
            this.salesRepEmployeeNum = salesRepEmployeeNum;
            this.lastName = lastName;
            this.firstName = firstName;
            this.phone = phone;
            this.address1 = address1;
            this.address2 = address2;
            this.city = city;
            this.state = state;
            this.postalCode = postalCode;
            this.country = country;
            this.creditLimit = creditLimit;
            this.payments = payments;
            this.customerOrders = customerOrders;
        }

        public Customer(Employee salesRepEmployeeNum, String lastName,
                        String firstName, String phone, String address1,
                        String address2, String city, String state,
                        int postalCode, String country, BigDecimal creditLimit) {
            this.salesRepEmployeeNum = salesRepEmployeeNum;
            this.lastName = lastName;
            this.firstName = firstName;
            this.phone = phone;
            this.address1 = address1;
            this.address2 = address2;
            this.city = city;
            this.state = state;
            this.postalCode = postalCode;
            this.country = country;
            this.creditLimit = creditLimit;
        }

        public Customer() {
        }


        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public Employee getSalesRepEmployeeNum() {
            return salesRepEmployeeNum;
        }

        public void setSalesRepEmployeeNum(Employee salesRepEmployeeNum) {
            this.salesRepEmployeeNum = salesRepEmployeeNum;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(int postalCode) {
            this.postalCode = postalCode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public BigDecimal getCreditLimit() {
            return creditLimit;
        }

        public void setCreditLimit(BigDecimal creditLimit) {
            this.creditLimit = creditLimit;
        }

        public List<Payment> getPayments() {
            return payments;
        }

        public void setPayments(List<Payment> payments) {
            this.payments = payments;
        }

        public List<Order> getCustomerOrders() {
            return customerOrders;
        }

        public void setCustomerOrders(List<Order> customerOrders) {
            this.customerOrders = customerOrders;
        }
    }
