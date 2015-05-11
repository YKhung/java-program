
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;



 
public class JDBCQuery {
	private static Connection setConnection(String name) {
		Connection conn =null;
		
		try{
	        	
	        String userName = "ykhung"; //username of DBMS; modified to use your username
	        String password = "ykhung598"; //password of DBMS; modified to use your password
	        String url = "jdbc:mysql://proj-461.cs.iastate.edu:3306/"+name; //hostname and database name modified to use your database
	        Class.forName("com.mysql.jdbc.Driver"); //Load JDBC driver for MySQL 
	        conn = DriverManager.getConnection (url, userName, password); //connect to the Database
	        System.out.println("Database connection successed."); 
	    }
	    catch (Exception e){
	        System.err.println ("error"+e.fillInStackTrace());
	        conn = null;
	    }
		return conn;
	}
	
    public static void main(String[] args) throws Exception {

        String sql;

 
        try {
   
             if (args[0].equals("HibernateTest") &&args[1].equals("1")) {

             	Connection conn = setConnection("homework2");
                 Statement stmt = conn.createStatement();
   
                sql = "select DISTINCT p.pname,p.pid from parts p, catalog c where p.pid=c.pid;";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    System.out
                            .println(rs.getString(1) + "\t" + rs.getString(2));
                }
                conn.close();
            }

            else if (args[0].equals("HibernateTest") &&args[1].equals("2")) {
             	Connection conn = setConnection("homework2");
            	String str="";
            	str=args[2];
            	sql = "select p.pid,p.pname from parts p, suppliers s, catalog c where p.pid=c.pid and s.sid=c.sid and s.sname =?;";
                java.sql.PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1,str);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out
                            .println(rs.getString(1) + "\t" + rs.getString(2));

            }
                conn.close();
            }

            else if (args[0].equals("HibernateTest") &&args[1].equals("3")) {
             	Connection conn = setConnection("homework2");
             	Connection conn2 = setConnection("ykhung");
                Statement stmt = conn.createStatement();
                Statement stmt2 = conn2.createStatement();
            	ArrayList<String> List = new ArrayList<String>(); 

                sql = "select distinct s.sid,s.sname from suppliers s, catalog c, parts p  where s.sid=c.sid and c.pid=p.pid and (p.color='Red' or p.color='Green');";

                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                	sql = "insert into new values("+rs.getString(1)+","+"'"+rs.getString(2)+"'"+");";
                	List.add(sql);
                	System.out
                    .println(rs.getString(1) + "\t" + rs.getString(2));                   
                }
                for(int i=0;i<List.size();i++){
                	stmt2.executeUpdate(List.get(i));
                	
                }
                conn.close();
                conn2.close();

            }
            else if (args[0].equals("HibernateTest") &&args[1].equals("4")) {
             	Connection conn = setConnection("homework2");
            	String str="";
            	str=args[2];
                sql = "select f1.person from friends f1,friends f2, friends f3 where f1.friend=f2.person and f2.friend=f3.person and f3.friend=?";
                java.sql.PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1,str);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    System.out
                            .println(rs.getString(1) + "\t");
                }
                conn.close();

            }
            else {    System.out.println("Please input correct name");      }
            

        }catch (Exception e) {
        	System.out.println("error");
            e.printStackTrace();
        } 
 
    }
    
 
}