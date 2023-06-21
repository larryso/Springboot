# Spring Transaction Management: @Transactional In-Depth

## Basic Knowledge about JDBC Transaction management

Plain JDBC transaction management code looks like this:
```java
import java.sql.Connection;

Connection connection = dataSource.getConnection(); // (1)

try (connection) {
    connection.setAutoCommit(false); // (2)
    // execute some SQL statements...
    connection.commit(); // (3)

} catch (SQLException e) {
    connection.rollback(); // (4)
}
```
1. You need a connection to the database to start transactions. DriverManager.getConnection(url, user, password) would work as well, though in most enterprise-y applications you will have a data source configured and get connections from that.

2. This is the only way to 'start' a database transaction in Java, even though the name might sound a bit off. setAutoCommit(true) makes sure that every single SQL statement automatically gets wrapped in its own transaction and setAutoCommit(false) is the opposite: You are the master of the transaction(s) and you’ll need to start calling commit and friends. Do note, the autoCommit flag is valid for the whole time your connection is open, which means you only need to call the method once, not repeatedly.

3. Let’s commit our transaction…​

4. Or, rollback our changes, if there was an exception.

## Understanding Isolcation Levels in a Database Transaction


## Reference
[https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth](https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth)