# Codecool Shop


Codecool Shop is an online shop, where users can buy different items available, as in a real store. The main interest of the app is that it can have its persistence either in memory or in a database. The site admin can switch the persistence just by pressing a button.

---

![homePage](https://i.imgur.com/QSncvll.png) 
![homePage2](https://user-images.githubusercontent.com/89275915/171204162-40637ac4-8a21-460a-ada7-f3c277c51a1a.png)
![homePage2](https://i.imgur.com/LdRE5X9.png)
![homePage2](https://i.imgur.com/a6gQxDn.png)



## Tech

Codecool Shop uses a number of open source projects to work properly:

- [Maven]
- [Servlets]
- [PostgreSql]
- [JS]
- [CSS]
- [HTML]

## Installation

1. Clone the repo
     ```sh
    git clone https://github.com/R0bert196/CodecoolShop
    ```

2.  Create a new postgresql database for the project.
    ```sh
    create db codecoolshop
    ```

3. Remove the 'template' from application.properties.template and enter your databse connection in appliaction.properites.


4. Run the init_db.sql on your new database connection. The admin user will be inserted in the database.
 
5. Run the project from intelij/ eclipse and enjoy!


[JS]: https://www.javascript.com/
[Maven]: https://maven.apache.org/
[Servlets]: https://www.geeksforgeeks.org/introduction-java-servlets/
[PostgreSQL]: https://www.postgresql.org/
[CSS]: https://developer.mozilla.org/en-US/docs/Web/CSS
[HTML]: https://developer.mozilla.org/en-US/docs/Web/HTML


## License

[MIT](https://choosealicense.com/licenses/mit/)


