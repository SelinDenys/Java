import database.DBManager;
import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;

import static org.testng.Assert.*;

public class DBManagerTest {

    @Test()
    public void testSetData()
    {
        DBManager dbManager = new DBManager("jdbc:h2:mem:test", "username", "password");
        assertFalse(dbManager.setData("creat table test"));
    }

    @Test()
    public void testGetData()
    {
        DBManager dbManager = new DBManager("jdbc:h2:mem:test", "username", "password");
        dbManager.setData("CREATE TABLE Test " +
                "(int INTEGER PRIMARY KEY, " +
                "string TEXT, " +
                "day DATE, " +
                "float REAL)");

        dbManager.setData("INSERT INTO Test (int, string, day, float) " +
                "VALUES ('1', 'str', '1980-11-11', '11.11')");

        ResultSet set = dbManager.getData("SELECT * FROM Test");
        LocalDate localDate = LocalDate.of(1980, 11, 11);

        try {
            if (set.next()) {
                assertEquals(set.getInt("int"), 1);
                assertEquals(set.getString("string"), "str");
                assertEquals(set.getDate("day"), Date.valueOf(localDate));
                assertEquals(set.getFloat("float"), 11.11f);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            fail();
        }

    }
}
