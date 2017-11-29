import java.sql.*;
import java.util.ArrayList;

public final class MySqlCon {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin";
	private static final String CON_STRING = "jdbc:mysql://localhost:3306/db_middleware?useSSL=false";
	
	private MySqlCon(){
		
	}
	
	public static int executeInsert(String query){
		int autoGenKey = -1;
		
		try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(CON_STRING,USERNAME,PASSWORD);  
            //here db_middleware is database name  
            Statement stmt=con.createStatement();  
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                autoGenKey = rs.getInt(1);
            } else {
            	throw new Exception("Falha na recuperacao de key auto gerada.");
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
		
		return autoGenKey;
	}
	
	public static void excuteUpdate(String query){
		try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(CON_STRING,USERNAME,PASSWORD);  
            //here db_middleware is database name  
            Statement stmt=con.createStatement();  
            stmt.executeUpdate(query);
            con.close();
        }catch(Exception e){ System.out.println(e);}
	}
	
	public static void confirmReceived(int id){
		try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(CON_STRING,USERNAME,PASSWORD);  
            //here db_middleware is database name  
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("update `db_middleware`.`message_client` set received = 1 where id = "+id);
            
            con.close();
        }catch(Exception e){ System.out.println(e);}
	}
	
	public static ArrayList<MensagemParaEnviar> getMsgsToSend(){
		ArrayList<MensagemParaEnviar> ans = new ArrayList<MensagemParaEnviar>();
		try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(CON_STRING,USERNAME,PASSWORD);  
            //here db_middleware is database name  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select mc.id, mc.id_client, c.address, m.content, mc.received from `db_middleware`.`message_client` as mc join `db_middleware`.`client` as c on mc.id_client = c.id join `db_middleware`.`message` as m on mc.id_message = m.id where mc.received = 0;");           
            while(rs.next()) 
            	ans.add(new MensagemParaEnviar(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5)));  
            con.close();  
        }catch(Exception e){ System.out.println(e);}
		
		return ans;
	}
	
	public static ArrayList<Client> getClients(){
		ArrayList<Client> ans = new ArrayList<Client>();
		try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(CON_STRING,USERNAME,PASSWORD);  
            //here db_middleware is database name  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from `db_middleware`.`client`");           
            while(rs.next())
            	ans.add(new Client(rs.getInt(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(2)));  
            con.close();  
        }catch(Exception e){ System.out.println(e);}
		
		return ans;
	}

}
