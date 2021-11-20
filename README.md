#  Expense Reimbursement System - (ERS) 
### Java, Javalin, Hibernate, JavaScript

## Project Description

The Expense Reimbursement System will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Technologies Used

Java 8, SQL, Bootstrap 5, CSS,  HTML, JavaScript, JUnit, Hibernate, AWS RDS, Log4J, Javalin

## Features
<ul>
<li>Single Page Application (SPA) functionality utilizing JavaScript</li>
<li>User Login</li>
<li>Persisted encrypted passwords using AES256</li></li>
<li>Two groups/permissions ; employee and finance manager</li>
<li>All accounts can add new reimbursement requests</li>
<li>All accounts can view their past reimbursement requests and the status of these requests</li>
<li>Finance managers can view all reimbursement requests</li>
<li>Finance managers can approve or deny reimbursement requests</li>
<li>Data is persisted in a PostgreSQL database</li>
<li>Javalin/Jetty Web Server</li>
<li>85% test coverage of Service Layer</li>
<li>Different levels of logging (error, warn, info, debug)</li>
<li>DAO design pattern</li></li>

</ul>
## Getting Started/ Setting Up

1. Navigate to the desired directory on your system, to host the project inside a Terminal or console
2. Use Git Bash by right clicking (please refer to this link if Git is not installed on your system: https://git-scm.com/book/en/v2/Getting-Started-Installing-Git )
3. Use the following command to obtain project files: 
>  git clone https://github.com/210927-JavaFS/project1-umi729.git
4. Navigate to project1-umi729 / src / main / resources / hibernate.cfg.xml
5. Modify database properties under the comment "JDBC Properties TO Use JDBC In Hibernate"
> - property name="hibernate.connection.username"
> - property name="hibernate.connection.password"
> - property name="hibernate.connection.url"
> _Note : Regarding the connection url - if using a jdbc postgreSQL database you can use the format jdbc:postgresql://\<Host Database URL\>:\<Desired Port\>/\<Database Name\>_
6. Save hibernate.cfg.xml changes
7. Open an IDE such as Eclipse or Spring Tool Suite
8. Import the project from the chosen host directory which it was cloned into
9. Run project as a Java Application

## Contributors

Umer Zahid

