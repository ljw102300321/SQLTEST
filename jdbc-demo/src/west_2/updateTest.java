package west_2;

import java.sql.SQLException;

public class updateTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Update.updateStock("苹果",200,"set");
        Update.updateStock("苹果",200,"increase");
        Update.updateStock("苹果",200,"decrease");
        Update.updateNeed(10,"苹果",2,"increase");
    }
}
