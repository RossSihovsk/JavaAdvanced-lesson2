import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscribeDAO {

    public Subscribe insert(int userID, int magazineID, boolean subscribeStatus, LocalDate subscribeDate,
                            int subscribePeriod) throws Exception {
        String query = "insert into subscribe(`user_id`, `magazine_id`, `subscribe_status`, `subscribe_date`, `subscribe_period`) values (?, ?, ?, ?, ?)";

        Subscribe subscribe = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DaoConnection.getInstance().getConnection();

            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userID);
            statement.setInt(2, magazineID);
            statement.setBoolean(3, subscribeStatus);
            statement.setDate(4, Date.valueOf(subscribeDate));
            statement.setInt(5, subscribePeriod);
            int rows = statement.executeUpdate();
            System.out.printf("%d row(s) added!\n", rows);

            if (rows == 0) {
                throw new Exception("Creating subscribe failed, no rows affected!");
            } else {
                resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    subscribe = new Subscribe(resultSet.getInt(1), userID, magazineID, subscribeStatus, subscribeDate,
                            subscribePeriod);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Creating subscribe failed!", e);
        } catch (Exception exception) {
            exception.printStackTrace();
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

        System.out.println(subscribe + " is added to database!");
        return subscribe;
    }

    public boolean delete(int id) throws  Exception {
        String sqlQuery = "delete from subscribe where id = ?";

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
                throw new Exception("Deleting subscribe failed, no rows affected!");
            } else {
                result = true;
            }
        } catch (SQLException e) {
            throw new Exception("Deleting subscribe failed!", e);
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
            System.err.println("Deleting subscribe failed, no rows affected!");
        } else {
            System.out.println("Subscribe with ID#" + id + " is deleted from database!");
        }
        return result;
    }

    public List<Subscribe> readAll() throws Exception {
        String sqlQuery = "select * from subscribe";

        List<Subscribe> subscribeList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DaoConnection.getInstance().getConnection();
            statement = connection.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                subscribeList.add(new Subscribe(resultSet.getInt("id"), resultSet.getInt("user_id"),
                        resultSet.getInt("magazine_id"), resultSet.getBoolean("subscribe_status"),
                        resultSet.getDate("subscribe_date").toLocalDate(), resultSet.getInt("subscribe_period")));
            }
        } catch (SQLException e) {
            throw new Exception("Getting list of subscribes failed!", e);
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

        return subscribeList;
    }
}
