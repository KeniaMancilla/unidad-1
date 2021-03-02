
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class RWFichero 
{
   
	private String fileName="default.conf";
	private File fichero=null;
				
	public RWFichero()
	{
		inicializaFichero(fileName);
	}
	
	public RWFichero(String fileName)
	{
		this.fileName=fileName;
		inicializaFichero(fileName);
	}
	
	private void inicializaFichero(String name)
	{
		fichero=new File(name);
		creaFichero();
	}
	
	public void creaFichero()
	{
		if(!fichero.exists())
		{
			try
			{
				fichero.createNewFile();
				
			}
			catch(IOException ex)
			{
				System.out.println("--->Error al crear fichero\n");
				ex.printStackTrace();
			}
		}
	}
	
	public void escribeEnFichero(String contenido,boolean append)
	{
		try
		{
			FileWriter fw=new FileWriter(fichero,append);
			fw.append(contenido+"\n");
			fw.close();
			
		}
		catch(IOException ex)
		{
			System.out.println("--->Error al escribir en fichero: "+fileName+"\n");
			ex.printStackTrace();
			
		}
	  	
	}
	
	public void escribeEnFichero(String[] contenido,boolean append)
	{
		for(int i=0;i<contenido.length;i++)
		{
		   escribeEnFichero(contenido[i],append);
		}
	}
	
	public String lecturaDeFichero()
	{
		String lecturaSc="";
		String temp=null;
		try
		{
	      BufferedReader br=new BufferedReader(new FileReader(fichero));
	      
	      temp=br.readLine(); 
	      while(temp!=null)
	      {
	    	if(!temp.startsWith("#"))
	    	    lecturaSc+=temp;
	    		    	
	    	temp=br.readLine(); 
	      }
	      
	      br.close();
		}
		
		catch(FileNotFoundException ex)
		{
			System.out.println("--->no se puede leer del fichero:"+fileName+"\n");
			ex.printStackTrace();
		}
		catch(IOException ex)
		{
			System.out.println("--->no se puede leer del fichero:"+fileName+"\n");
			ex.printStackTrace();
		}
		
		return lecturaSc;
	}
	
	public Object[] lecturaFichero()
	{
		Stack<String> lineas=new Stack<String>();
		String temp=null;
		try
		{
	      BufferedReader br=new BufferedReader(new FileReader(fichero));
	      
	      temp=br.readLine(); 
	      while(temp!=null)
	      {
	    	if(!temp.startsWith("#"))
	    	    lineas.push(temp);
	    		    	
	    	temp=br.readLine(); 
	      }
	      
	      br.close();
	      	      
		}
		
		catch(FileNotFoundException ex)
		{
			System.out.println("--->no se puede leer del fichero:"+fileName+"\n");
			ex.printStackTrace();
		}
		catch(IOException ex)
		{
			System.out.println("--->no se puede leer del fichero:"+fileName+"\n");
			ex.printStackTrace();
		}
		
		return lineas.toArray();
	}
	
		
	public void borraFichero()
	{
		fichero.delete();
	}
	
	public String getPath()
	{
		if(fichero!=null)
			return fichero.getAbsolutePath();
		else
			return "path unknow";
		
	}
		
	public void setFileName(String fileName)
	{
	  this.fileName=fileName;  
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public File getFile()
	{
		return fichero;
	}
	
	
	
}
