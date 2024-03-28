<div align="center">
  <h1 align="center"><a href="https://github.com/ASJordi/webapp-todo">TODO App</a></h1>

  <p align="center">TODO App with Java and Jakarta EE</p>
</div>

## About :computer:

TODO App is a web application to manage tasks and users. It is developed with Java and Jakarta EE. It has a login system, user management, and task management. Tomcat is used as a server.

<img src="src/main/resources/app01.png" alt="app01" width="400"/>
<img src="src/main/resources/app02.png" alt="app01" width="400"/>
<img src="src/main/resources/app03.png" alt="app01" width="400"/>

## Stack :hammer_and_wrench:

* Java SE
* Jakarta EE
* JSP
* JSTL
* CDI
* Tomcat
* MySQL
* Bootstrap

## Installation :gear:

- Clone the repository

  ```bash
  git@github.com:ASJordi/webapp-todo.git
  ```

- Create a MySQL database with the script in `src/main/resources/db/db.sql`

- Configure the database connection in `src/main/webapp/META-INF/context.xml`

- Install dependencies with Maven

- Configure tomcat to use the MySQL connector

- Run the application with:

  ```bash
  mvn tomcat7:redeploy
  ```


## License :page_facing_up:

Distributed under the MIT License. See `LICENSE` for more information.

## Contact :email:

Jordi Ayala - [@ASJordi](https://twitter.com/ASJordi)

Project Link: [https://github.com/ASJordi/webapp-todo](https://github.com/ASJordi/webapp-todo)
