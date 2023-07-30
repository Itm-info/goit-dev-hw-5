package itm.dbWorks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLFromFile {
    private Connection conn = Database.getInstance().getConnection();
    private ArrayList<PreparedStatement> pSL = new ArrayList<>();
    private String[] queries;
    public String[] getQueries() { return this.queries; }
    public Connection getConnection() { return this.conn; }

    public SQLFromFile(String fileName) throws IOException {
        this.queries = new String(Files.readAllBytes(Paths.get("sql\\"+ fileName + ".sql")))
                .split("\\s*;\\n*\\s*(?=([^']*'[^']*')*[^']*$)");
    }

    public PreparedStatement preparedStatementFill(PreparedStatement ps, String[] params) throws SQLException {
        for( int i=0 ; i < ps.getParameterMetaData().getParameterCount() ; ++i ) {
            System.out.println("Count: " + ps.getParameterMetaData().getParameterCount() + ", params length:" + params.length);
            ps.setString(i+1, params[i]);
        }
        return ps;
    }

    public void preparePSList(ArrayList<String[]> paramsArray) throws SQLException {
        PreparedStatement ps;
        int p=0;
        for(String query : queries) {
            ps = conn.prepareStatement(query);
            preparedStatementFill(ps, paramsArray.get(p++));
            pSL.add(ps);
        }
    }

    public void execPSList() throws SQLException {
        for(PreparedStatement ps : pSL) {
            if ( ! ps.execute() ) { System.out.println("OK"); }
            else                  { System.out.println("NOT OK"); }
        }
    }
}
