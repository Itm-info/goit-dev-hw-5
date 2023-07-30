package itm.dbWorks;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseInitService {
    public static void main(String[] args) throws SQLException, IOException {
        SQLFromFile init = new SQLFromFile("init_db");

        ArrayList<String[]> paramsArray = new ArrayList<>();
        for (int i = 0; i <= 7;) { paramsArray.add(i++,null); } // Initialize

        paramsArray.set(4, new String[] {"1", "1000", "1900-12-31", "100", "100000"} );
        paramsArray.set(5, new String[] {"1", "1000"} );

        init.preparePSList(paramsArray);
        init.execPSList();
    }
}
