# CS3560 Final Project
### Group Members: Brandon Trieu, Amanda Wong

## Library Management System

*Description*: This project aims to develop a Library Management System, focusing on Object-Oriented Programming (OOP) principles for modularity, reusability, and maintainability. The system will support user management, book management, borrowing and returning processing, and reporting, ensuring efficient performance and scalability.

*Key Features*: 
- **User Authentication:** Implement secure login system for library users and library employees
- **Book Management:** Allows library employees to add, remove, and update book/movie catalog
- **Borrowing Transaction Processing:** Documents/tracks what/when users borrow a library item, which items are borrowed, return dates of items, and late return fees
- **Reporting:** Generate borrowing history and overdue items

*OOP Principles Applied:*
- **Encapsulation:** Secure handling of user and library item data with private/protected attributes and controlled access via getter and setter methods
- **Inheritance:** 
  - General User class with subclasses like Librarian, Member, Guest
  - General libraryItem class with subclasses like PrintedBook, eBook, Movie
- **Polymorphism:** Implement display methods that behave differently based on media type 
- **Abstraction:** Use an interface to define a general structure of storage of media types provided by the library 
  - MediaType interface with implementations for Book, Movie
 
*Technologies:*
- **Programming Languages:** Java
- **GUI:** Swing
- **Database:** Internal data structures
- **Development Tools:**
  - **IDE:** IntelliJ
  - **Version Control:** Git
 
*Expected Outcomes:*
- A fully functional Library Management System following best OOP practices
- Modular, scalable, and reusable design allowing future extensions
- Efficient handling of book transactions, user data, and security
- User-friendly GUI with an intuitive workflow

*How to Run*: In order to run, open the source code in an IDE of choice. If  

*Features Implemented*
- Admin & User Menus
- Admin Features
  - Reset Data
  - View Items
  - View Users
  - View Issued Items
  - Add User
  - Add Item
  - Issue Item
  - Return Item  
- User Features
  - View Item
  - My Items
  - Borrow Item
  - Return Item 

*Future Work* 
- SQL database connection
- Implementing additional functions for users
- Search function 






