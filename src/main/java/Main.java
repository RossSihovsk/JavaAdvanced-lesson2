import java.time.LocalDate;


public class Main {

    public static void main(String[] args) throws Exception {

        UserDAO userDAO = new UserDAO();

        User newUser = new User("Діма", "Тетеренко", "dima@gmail.com", "111111", "USER");
        User newUser2 = new User("Остап", "Нечипуренко", "ostap@gmail.com", "111111", "USER");
        User newUser3 = new User("Руслан", "Ярич", "rus@gmail.com", "111111", "USER");

        userDAO.insert(newUser.getFirstName(),newUser.getLastName(),newUser.getEmail(),newUser.getPassword(),newUser.getAccessLevel());
        userDAO.insert(newUser2.getFirstName(),newUser2.getLastName(),newUser2.getEmail(),newUser2.getPassword(),newUser2.getAccessLevel());
        userDAO.insert(newUser3.getFirstName(),newUser3.getLastName(),newUser3.getEmail(),newUser3.getPassword(),newUser3.getAccessLevel());
        System.out.println(userDAO.readByID(2));
        userDAO.delete(2);


        JournalDAO journalDAO = new JournalDAO();
        System.out.println(
                journalDAO.insert("MAXIM", "Standatr description",
                        LocalDate.parse("2019-04-01"), 500));

        SubscribeDAO subscribeDAO = new SubscribeDAO();
        System.out.println(subscribeDAO.insert(2, 1, true, LocalDate.parse("2019-04-26"), 12));

    }
}
