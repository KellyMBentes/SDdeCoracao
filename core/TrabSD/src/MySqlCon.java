import java.sql.*;
import java.util.ArrayList;

public final class MySqlCon {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin";
	
	private MySqlCon(){
		
	}
	
	public static int executeInsert(String query){
		int autoGenKey = -1;
		
		try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/db_middleware",USERNAME,PASSWORD);  
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
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/db_middleware",USERNAME,PASSWORD);  
            //here db_middleware is database name  
            Statement stmt=con.createStatement();  
            stmt.executeUpdate(query);
            con.close();
        }catch(Exception e){ System.out.println(e);}
	}
	
	public static void confirmReceived(int id){
		try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/db_middleware",USERNAME,PASSWORD);  
            //here db_middleware is database name  
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("UPDATE `db_middleware`.`message_client` SET received = 1 WHERE id = "+id);
            
            con.close();
        }catch(Exception e){ System.out.println(e);}
	}
	
	public static ArrayList<MensagemParaEnviar> getMsgsToSend(){
		ArrayList<MensagemParaEnviar> ans = new ArrayList<MensagemParaEnviar>();
		try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/db_middleware",USERNAME,PASSWORD);  
            //here db_middleware is database name  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT MC.id, MC.id_client, C.address, M.content, MC.received FROM DB_MIDDLEWARE.message_client AS MC JOIN DB_MIDDLEWARE.client AS C ON MC.id_client = C.id JOIN DB_MIDDLEWARE.message AS M ON MC.id_message = M.id WHERE MC.received = 0;");           
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
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/db_middleware",USERNAME,PASSWORD);  
            //here db_middleware is database name  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT * FROM DB_MIDDLEWARE.client");           
            while(rs.next())
            	ans.add(new Client(rs.getInt(1), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(2)));  
            con.close();  
        }catch(Exception e){ System.out.println(e);}
		
		return ans;
	}

}
