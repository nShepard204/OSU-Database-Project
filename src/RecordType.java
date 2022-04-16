import java.sql.Connection;

public abstract class RecordType {

    public RecordType(){
    }

    public abstract void add(Connection conn);
    public abstract void edit();
    public abstract void delete();
 }
