package edu.iastate.cs461.hw2;
import org.hibernate.*;
import org.hibernate.criterion.Expression;

import events.Event;
import events.EventManager;

import java.sql.SQLException;
import java.util.*;

import javax.swing.JOptionPane;

import util.HibernateUtil;
/**
 * @author Yin Kam Hung
 *
 */
/**
*accepts command line arguments in the following format where
*<subquestion number> is replaced by 1 for sub-question a, 2 for sub-question b, and so on. Replace
*<argument list> by the list of arguments required for that question. The program shows the query
*results on console. The first line shows the names of the attributes separated by a comma. The result
*for each row is shown in a subsequent line, one line per row
*@return The result of every Test.
*/
public class Query {
    public static void main(String[] args) {
    	boolean flag=true;
    	Query query = new Query();
    	try {
      
    		//Execute HibernateTest 1 query
        if (args[0].equals("HibernateTest") &&args[1].equals("1")) {
        	List results = query.HibernateTest1();
            Iterator it=results.iterator();
          //output for HibernateTest 1
            while (it.hasNext()){
               Object[] Row = (Object[])it.next();
               System.out.println(Row[0]+":"+Row[1]);
            }
        }  //Execute HibernateTest 2 query
        else if (args[0].equals("HibernateTest") &&args[1].equals("2")) {
        	//get the supplier name
        	String str="";
        	str=args[2];
        	List results = query.HibernateTest2(str);
            Iterator it=results.iterator();
          //output for HibernateTest 2
            if(!it.hasNext()){
            	System.out.println("Please input correct supplier");
            	
            }
            while (it.hasNext()){
               Object[] Row = (Object[])it.next();
               System.out.println(Row[0]+":"+Row[1]);
            }

            
        }//Execute HibernateTest 3 query
        else if (args[0].equals("HibernateTest") &&args[1].equals("3")) {
        	List results = query.HibernateTest3();
            Iterator it=results.iterator();
          //output for HibernateTest 3
            while (it.hasNext()){
               Object[] Row = (Object[])it.next();
               System.out.println(Row[0]+":"+Row[1]);
               query.createAndStoreNewSuppier((int)Row[0], Row[1].toString());
            }
        	
        }//Execute HibernateTest 4 query
        else if (args[0].equals("HibernateTest") &&args[1].equals("4")) {
        	//get person name.
        	String str="";
        	str=args[2];
        	List results = query.HibernateTest4(str);
            Iterator it=results.iterator();
            if(!it.hasNext()){
            	System.out.println("Please input correct name");
            	
            }
            while (it.hasNext()){
              // Object[] Row = (Object[])it.next();
               System.out.println(it.next());
            }
        }
        else
        {    System.out.println("Please input correct code");      }

    	HibernateUtil.getSessionFactory().close();
        
    	}
         catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        } finally {


        }
    }
/**
*Find distinct pids and pnames of parts for which the part has some supplier
*@return distinct pids and pnames.
*/   
    private List HibernateTest1() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // HQL statement for HibernateTest1
        List result = session.createQuery("select distinct p.pname,p.pid from Parts p, Catalog c where p.pid=c.parts").list();

        session.getTransaction().commit();

        return result;
    }
/**
*Find pnames of all parts supplied by the supplier whose name is given by the user.
*@return pnames.
*/   
    private List HibernateTest2(String str) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
     // HQL statement for HibernateTest2
        org.hibernate.Query query = session.createQuery("select p.pid,p.pname from Parts p, Suppliers s, Catalog c where p.pid=c.parts and s.sid=c.suppliers and s.sname =:str");
        query.setString("str",str);
        List result=query.list();
        session.getTransaction().commit();

        return result;
    }
    /**
    *insert the results into a new table called new in your own database
    *@return null.
    */ 
    private void createAndStoreNewSuppier(int sid, String sname) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Catalog a=new Catalog();
        New n = new New();
        NewId id=new NewId();
        id.setSid(sid);
        id.setSname(sname);
        n.setId(id);

        session.save(n);

        session.getTransaction().commit();

    }
    /**
    *Find distinct sids and snames of suppliers who supply a red part or a green part and
    *@return distinct pids and pnames.
    */

    private List HibernateTest3() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
         

     // HQL state ment for HibernateTest3
        List result = session.createQuery("select distinct s.sid,s.sname from Suppliers s, Catalog c, Parts p where s.sid=c.suppliers and c.parts=p.pid and (p.color='Red' or p.color='Green')").list();

        session.getTransaction().commit();

        return result;
    }
    /**
    *Find friends of friends of friends of a given person whose name is specified by the
    *user
    *@return friends.
    */ 
    private List HibernateTest4(String str) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        // HQL statement for HibernateTest4
        //List result = session.createQuery("SELECT f.friend FROM Friends f WHERE f.person in (SELECT f1.friend FROM Friends f1 WHERE f1.person like 'Ed')").list();
        
        org.hibernate.Query query = session.createQuery("select f1.id.person from Friends f1,Friends f2, Friends f3 where f1.id.friend=f2.id.person and f2.id.friend=f3.id.person and f3.id.friend=:str");
        query.setString("str",str);
        List result=query.list();
        session.getTransaction().commit();

        return result;
    }
}
