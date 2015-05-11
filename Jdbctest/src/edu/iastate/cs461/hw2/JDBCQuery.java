package edu.iastate.cs461.hw2;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;



 
public class JDBCQuery {
    public static void main(String[] args) throws Exception {
    	//set different connection
        Connection conn = null;
        Connection conn2 = null;
        String sql;
        //url connect to homework2 database, url2 connect to ykhung database 
        String url = "jdbc:mysql://proj-461.cs.iastate.edu:3306/homework2?"
                + "user=ykhung&password=ykhung598&useUnicode=true&characterEncoding=UTF8";
        String url2 = "jdbc:mysql://proj-461.cs.iastate.edu:3306/ykhung?"
                + "user=ykhung&password=ykhung598&useUnicode=true&characterEncoding=UTF8";
 
        try {

            Class.forName("com.mysql.jdbc.Driver");

 
            System.out.println("success add MySQL");
            //connect to different database
            conn = DriverManager.getConnection(url);
            conn2 = DriverManager.getConnection(url2);
            //set different statement for 2 database
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn2.createStatement();

   
        		// if input is HibernateTest 1, Find distinct pids and pnames of parts for which the part has some supplier.
             if (args[0].equals("HibernateTest") &&args[1].equals("1")) {
            	 // sql statement for HibernateTest 1
                sql = "select DISTINCT p.pname,p.pid from parts p, catalog c where p.pid=c.pid;";
                ResultSet rs = stmt.executeQuery(sql);
                //output for HibernateTest 1
                while (rs.next()) {
                    System.out
                            .println(rs.getString(1) + "\t" + rs.getString(2));
                }
            }
          // if the begin of input is HibernateTest 2, Find pnames of all parts supplied by the supplier whose name is given by the user.
          //   Duplicate names are allowed. The supplier name is provided as the second command line
          //   argument of the program.
            else if (args[0].equals("HibernateTest") &&args[1].equals("2")) {
            	String str="";
            	str=args[2];
            	// sql statement for HibernateTest 2
            	sql = "select p.pid,p.pname from parts p, suppliers s, catalog c where p.pid=c.pid and s.sid=c.sid and s.sname =?;";
                java.sql.PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1,str);
                //Execute jdbc query
                ResultSet rs = ps.executeQuery();
                if(!rs.next()){
                	System.out.println("Please input correct name");
                	
                }
              //output for HibernateTest 2
                while (rs.next()) {
                    System.out
                            .println(rs.getString(1) + "\t" + rs.getString(2));

            }
            }
             // Find distinct sids and snames of suppliers who supply a red part or a green part and
             // insert the results into a new table called new in your own database.
            else if (args[0].equals("HibernateTest") &&args[1].equals("3")) {
            	ArrayList<String> List = new ArrayList<String>(); 
            	// sql statement for HibernateTest 3
                sql = "select distinct s.sid,s.sname from suppliers s, catalog c, parts p  where s.sid=c.sid and c.pid=p.pid and (p.color='Red' or p.color='Green');";
              //Execute jdbc query
                ResultSet rs = stmt.executeQuery(sql);
              //output for HibernateTest 3
                while (rs.next()) {
                	//save insert sql statement of each attributes in to list
                	sql = "insert into new values("+rs.getString(1)+","+"'"+rs.getString(2)+"'"+");";
                	List.add(sql);
                	System.out
                    .println(rs.getString(1) + "\t" + rs.getString(2));
                	
                    
                }
              //execute insert statement to ykhung database

                for(int i=0;i<List.size();i++){
                	stmt2.executeUpdate(List.get(i));
                	
                }

            }
             //  if the begin of input is HibernateTest 4,Find friends of friends of friends of a given person whose name is specified by the user.
            else if (args[0].equals("HibernateTest") &&args[1].equals("4")) {
            	String str="";
            	// get the person name after HibernateTest 4
            	str=args[2];
            	// sql statement for HibernateTest 3
                sql = "select f1.person from friends f1,friends f2, friends f3 where f1.friend=f2.person and f2.friend=f3.person and f3.friend=?";
                java.sql.PreparedStatement ps=conn.prepareStatement(sql);
              //Execute jdbc query
                ps.setString(1,str);
                ResultSet rs = ps.executeQuery();
              //output for HibernateTest 4
                if(!rs.next()){
                	System.out.println("Please input correct name");
                	
                }
                while (rs.next()) {
                    System.out
                            .println(rs.getString(1) + "\t");
                }

            }
            else {    System.out.println("Please input correct code");      }
            
            // SQLException
        } catch (SQLException e) {
            System.out.println("MySQL error");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	// close connection to database
            conn.close();
            conn2.close();

        }
 
    }
 
}