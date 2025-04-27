package west_2;

import java.sql.SQLException;

public class updateTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       try {
           Update.updateStock("苹果",200,"set");
       }catch (Exception e){

       }
        try {
            Update.updateStock("苹果",200,"increase");
        }catch (Exception e){

        }
        try {
            Update.updateStock("苹果",200,"decrease");
        }catch (Exception e){

        }
        try {
            Update.updateNeed(10,"苹果",2,"increase");
        }catch (Exception e){

        }
    }
}
