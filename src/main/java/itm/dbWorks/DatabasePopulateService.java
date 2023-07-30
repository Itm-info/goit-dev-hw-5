package itm.dbWorks;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

public class DatabasePopulateService {
    public static void main(String[] args) throws SQLException, IOException {
        SQLFromFile populate = new SQLFromFile("populate_db");
        Connection conn = populate.getConnection();
        String[] queries = populate.getQueries();
        Random rnd = new Random();


        PreparedStatement ps = conn.prepareStatement(queries[0]);
        for ( int i=0; i<10; ++i ) {
            populate.preparedStatementFill(ps, new String[] {
                    i%2 == 0 ? "Gregor" : "Max",
                    i%2 == 0 ? "2000-01-01" : "1988-11-30",
                    Arrays.asList("Trainee", "Junior", "Middle", "Senior").get(rnd.nextInt(4)),
                    i%2 == 0 ? "100000" : "100"
                    } )
                    .execute();
        }

        ps = conn.prepareStatement(queries[1]);
        String[] params = new String[5];
        for ( int i=0; i<=4; ++i ) {
            params[i] = (Arrays.asList("Gregor", "Max", "Tony", "Sarah").get(rnd.nextInt(4)));
        }
        populate.preparedStatementFill(ps, params);
        ps.execute();

        ps = conn.prepareStatement(queries[2]);
        for ( int i=0; i<10; ++i ) {
            populate.preparedStatementFill(ps, new String[] {
                            String.valueOf(rnd.nextInt(3)),
                            "2000-01-01",
                            rnd.nextInt(2) == 0 ? "2000-03-01" : "2000-05-01"
                    } )
                    .execute();
        }

        conn.prepareStatement(queries[3]).execute();
    }
}
