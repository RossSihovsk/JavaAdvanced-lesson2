import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JournalDAO {

    public Journal insert(String title, String description, LocalDate publishDate, int subscribePrice)
            throws Exception {
        String query = "insert into magazine(`title`, `description`, `publish_date`, `subscribe_price`) values (?, ?, ?, ?)";

        Journal journal = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DaoConnection.getInstance().getConnection();

            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setDate(3, Date.valueOf(publishDate));
            statement.setInt(4, subscribePrice);
            int rows = statement.executeUpdate();
            System.out.printf("%d row(s) added!\n", rows);

            if (rows == 0) {
                throw new Exception("Creating magaziner failed, no rows affected!");
            } else {
                resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    journal = new Journal(resultSet.getInt(1), title, description, publishDate, subscribePrice);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Creating magazine failed!", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.err.println("Result set can't be closed!" + e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Prepared statement can't be closed!" + e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Connection can't be closed!" + e);
            }
        }

        System.out.println(journal + " is added to database!");
        return journal;
    }

    public boolean delete(int id) throws Exception {
        String sqlQuery = "delete from magazine where id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = false;

        try {
            connection = DaoConnection.getInstance().getConnection();

            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            System.out.printf("%d row(s) deleted!\n", rows);

            if (rows == 0) {
                throw new Exception("Deleting magazine failed, no rows affected!");
            } else {
                result = true;
            }
        } catch (SQLException e) {
            throw new Exception("Deleting magazine failed!", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Prepared statement can't be closed!" + e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Connection can't be closed!" + e);
            }
        }

        if (result == false) {
            System.err.println("Deleting magazine failed, no rows affected!");
        } else {
            System.out.println("Magazine with ID#" + id + " is deleted from database!");
        }
        return result;
    }

    public List<Journal> readAll() throws Exception {
        String sqlQuery = "select * from magazine";

        List<Journal> journalList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DaoConnection.getInstance().getConnection();
            statement = connection.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                journalList.add(new Journal(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("description"), resultSet.getDate("publish_date").toLocalDate(),
                        resultSet.getInt("subscribe_price")));
            }
        } catch (SQLException e) {
            throw new Exception("Getting list of magazines failed!", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.err.println("Result set can't be closed!" + e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Prepared statement can't be closed!" + e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Connection can't be closed!" + e);
            }
        }

        return journalList;
    }

    public Journal readByID(int id) throws Exception {
        String sqlQuery = "select * from magazine where id = ?";

        Journal journal = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DaoConnection.getInstance().getConnection();
            statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                journal = new Journal(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("description"), resultSet.getDate("publish_date").toLocalDate(),
                        resultSet.getInt("subscribe_price"));
            }
        } catch (SQLException e) {
            throw new Exception("Getting magazine by id failed!", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.err.println("Result set can't be closed!" + e);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Prepared statement can't be closed!" + e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Connection can't be closed!" + e);
            }
        }

        System.out.println(journal + " is getted from database!");
        return journal;
    }

}
