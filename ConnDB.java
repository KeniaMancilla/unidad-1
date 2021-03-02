
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;



public class ConnDB 
{
    private String cadenaConn;
    private static final String fileDbPath="dbConn";
    private RWFichero fichero;
    private ResultSet resultado=null;
    private Connection connection = null;
	private Statement statement = null;
	private String query;
    private String mensaje="";
    private boolean connOk=true;
    
    public ConnDB()
    {
       	fichero=new RWFichero(fileDbPath);
       	cadenaConn=fichero.lecturaDeFichero();
       	conn();
       
    }
    
    public ConnDB(String path)
    {
    	cadenaConn=path;
    	
    }
    
    public String getDbConn()
    {
    	return cadenaConn;
    }
    
    public void setCadenaConn(String c,boolean guardar)
    {
    	if(guardar)
    	{
    		String msg="#Cadena de conecci�n BD\n"+"jdbc:sqlite:"+c;
    		fichero.escribeEnFichero(msg, false);
    	}
    	cadenaConn="jdbc:sqlite:"+c;	
    	conn();
    }
    
    public void query(String query)
    {
    	
		try 
		{
			resultado = statement.executeQuery(query);
			this.query=query;
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
			mensaje+=e.getMessage()+"\n";
		}
		
		
    }
    
    public ResultSet getResultSet()
    {
    	return resultado;
    }
    
    public Statement getStatement()
    {
    	return statement;
    }
    
    public void conn()
    {
    	try
    	{
    		Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(cadenaConn);
			statement = connection.createStatement();
					
			connOk=true;
    	}
    	catch(ClassNotFoundException ex)
    	{
    		ex.printStackTrace();
    		mensaje+=ex.getMessage()+"\n";
    		connOk=false;
    	}
    	catch(SQLException ex)
    	{
    		ex.printStackTrace();
    		mensaje+=ex.getMessage()+"\n";
    		connOk=false;
    	}
    }
     
    
    public Object[][] getResult()
	{
    	Object[][] result=null;
    	int filas=0;
		try
		{
			if(resultado!=null)
			{
				while(resultado.next())
				{
					filas++; 
				}
				
				resultado = statement.executeQuery(query);
				ResultSetMetaData metaDatos = resultado.getMetaData();
				int numeroColumnas = metaDatos.getColumnCount();
				result=new String[filas][numeroColumnas];
				
				filas=0;
				while(resultado.next())
				{
					 for(int i=0;i<numeroColumnas;i++)
					 {
						 result[filas][i]=resultado.getString(i+1);
					 }
					filas++;
				}
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			mensaje+=e.getMessage()+"\n";
		}
				
		return result;
	}
    
    public Object[][] getResult(String[] campos)
	{
    	Object[][] result=null;
    	int filas=0;
		try
		{
			ResultSet rss=resultado;
			while(rss.next())
			{
				filas++; 
			}
			
			resultado = statement.executeQuery(query);
			ResultSetMetaData metaDatos = rss.getMetaData();
			int numeroColumnas = metaDatos.getColumnCount();
			result=new Object[filas][numeroColumnas-1];
			
			filas=0;
			while(rss.next())
			{
				 for(int i=0;i<campos.length;i++)
				 {
					 result[filas][i]=rss.getString(campos[i]); 
				 }
				 filas++;
			}
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			mensaje+=e.getMessage()+"\n";
		}
		
		return result;
	}
    
    public Object[][] getResult(int[] campos)
	{
    	Object[][] result=null;
    	int filas=0;
		try
		{
			ResultSet rss=resultado;
			while(rss.next())
			{
				filas++; 
			}
			
			resultado = statement.executeQuery(query);
			ResultSetMetaData metaDatos = rss.getMetaData();
			int numeroColumnas = metaDatos.getColumnCount();
			result=new Object[filas][numeroColumnas-1];
			
			filas=0;
			while(rss.next())
			{
				 for(int i=0;i<campos.length;i++)
				 {
					 result[filas][i]=rss.getString(campos[i]);
				 }
				 filas++;
			}
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			mensaje+=e.getMessage()+"\n";
		}
		
			
		return result;
	}
    
    public void closeConnection()
    {
		try 
		{
			
			statement.close();
			connection.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			mensaje+=e.getMessage()+"\n";
		}
    }
    
    public int[] operaciones(String op)
    {
    	int res[]=new int[2];
    	
    	mensaje+=op+"\n";
    	
    	try
    	{
    		Statement insert=connection.createStatement();
    		res[0]=insert.executeUpdate(op);
    	}
    	catch(SQLException ex)
    	{
    		ex.printStackTrace();
    		res[1]=1;
    		mensaje+=ex.getMessage()+"\n";
    		connOk=false;
    	}
    	   	
    	
    	return res;
    }
   
    public void putImage(File file,String where)
    {
    	
    	try
    	{
    		 byte[] escritura=new byte[1024];
    		 FileInputStream fis = new FileInputStream(file);
    		 ByteArrayOutputStream bos = new ByteArrayOutputStream();
    		 byte[] buf = new byte[1024];
    		 for (int readNum; (readNum = fis.read(buf)) != -1;) 
    	     {
    	            bos.write(buf, 0, readNum); 
    	     }
    		 
    		 escritura=bos.toByteArray();
    		 PreparedStatement ps =connection.prepareStatement(where);
    		 ps.setBytes(1, escritura);
             ps.executeUpdate();
             ps.close();

    	}
    	catch(FileNotFoundException ex)
    	{
    		ex.printStackTrace();
    		mensaje+=ex.getMessage()+"\n";
    	}
    	catch(IOException ex)
    	{
    		ex.printStackTrace();
    		mensaje+=ex.getMessage()+"\n";
    	}
    	catch(SQLException ex)
    	{
    		ex.printStackTrace();
    		mensaje+=ex.getMessage()+"\n";
    	}

    	
    }
    
    public ImageIcon getImage(String where,String campo)
    {
    	ImageIcon imagen;
    	byte[] lectura=new byte[1024];
    	try
    	{
		    Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(where);
			
			lectura=rs.getBytes(campo);
			rs.close();
    	}
    	catch(SQLException ex)
    	{
    		ex.printStackTrace();
    		mensaje+=ex.getMessage()+"\n";
    	}
    	imagen=new ImageIcon(lectura);
    	return imagen;
    }
    
    public String[] getTablesName()
    {
    	
    	String[] tipoTablas={"TABLE"};
    	String recuperaTablas="";
    	
    	
    	
    	try
    	{
    		ResultSet resultadoTablas=connection.getMetaData().getTables(null, null, null, tipoTablas);
    	
    		while(resultadoTablas.next())
    		{
    			recuperaTablas+=resultadoTablas.getString(3)+",";
    		}
    	}
    	catch(SQLException ex)
    	{
    		ex.printStackTrace();
    		mensaje+=ex.getMessage()+"\n";
    	}
    	
    	return recuperaTablas.split(",");
    }
    
    public String getMensaje()
    {
    	return mensaje;
    }
    
    public boolean getconnOk()
    {
    	return connOk;
    }
    
}
