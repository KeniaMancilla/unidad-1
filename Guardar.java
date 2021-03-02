
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import javax.swing.JOptionPane;


public class Guardar implements ActionListener 
{

	private Agenda agenda;
	private ConnDB connDb;
	private File file;
	
	public Guardar()
	{
		
	}
	
	public Guardar(Agenda a)
	{
		agenda=a;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent ev) 
	{
		connDb=new ConnDB();
		
		if(agenda.getDetallesContacto().getOperacion()==DetallesContacto.NUEVO)
		{
			if(validateNuevo())
			{
				String nombres=agenda.getDetallesContacto().getJtfNombres().getText().toUpperCase();
				String apellidos=agenda.getDetallesContacto().getJtfApellidos().getText().toUpperCase();
				
				String direccion=agenda.getDetallesContacto().getJtfDireccion().getText().toUpperCase();
				if(direccion.equals(""))
					direccion="NULL";
				else
					direccion="'"+direccion+"'";
				
				
				String mail=agenda.getDetallesContacto().getJtfMail().getText();
				if(mail.equals(""))
					mail="NULL";
				else
					mail="'"+mail+"'";
				
				
				String nota=agenda.getDetallesContacto().getJtaNota().getText();
				if(nota.equals(""))
					nota="NULL";
				else
					nota="'"+nota+"'";
				
				String fnac=agenda.getDetallesContacto().getJtfFnac().getText();
				if(fnac.equals(""))
					fnac="NULL";
				else
					fnac="'"+fnac+"'";
								
				
				String insert="insert into contacto"+
				"(NOMBRES,APELLIDOS,DIRECCION,MAIL,NOTA,FECHA DE NACIMIENTO)"+
				"values " +
				"('"+nombres+"',"+
				"'"+apellidos+"',"+
				direccion+","+
				mail+","+
				nota+","+
				fnac+
				")";
								
				int[] resOp=connDb.operaciones(insert);
								
				if((agenda.getDetallesContacto().getFilePath()!=null))
				{
					connDb.query("Select idcontacto from contacto where nombres='"+nombres+"' and apellidos='"+apellidos+"'");
					file=new File(agenda.getDetallesContacto().getFilePath());
					connDb.query("select max(idcontacto) from contacto");
					Object[][] idContacto=connDb.getResult();
				}
				else
				{
					
					connDb.query("select max(idcontacto) from contacto");
					Object[][] idContacto=connDb.getResult();
				}
						
				agenda.getDetallesContacto().setFilePath(null);
				
				if(resOp[1]==1)
				{
					JOptionPane.showMessageDialog(agenda, "No se ha podido agregar al contacto.\n Error al guardar", "Error al guardar", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(agenda, "Contacto agregado correctamente", "Guardar", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			agenda.resetDatos();
			agenda.getDetallesContacto().bloqueaCampos();
			agenda.getDetallesContacto().habilitaBotones();
			agenda.getDetallesContacto().bloqueaBotonGuardar();
			agenda.getDetallesContacto().restauraColor();
		}
		
		else if(agenda.getDetallesContacto().getOperacion()==DetallesContacto.EDITAR)
		{
				if((validateNuevo())&&(agenda.getContacto().getDataVector().size()>0))
				{
					String nombres=agenda.getDetallesContacto().getJtfNombres().getText().toUpperCase();
					String apellidos=agenda.getDetallesContacto().getJtfApellidos().getText().toUpperCase();
					
					String direccion=agenda.getDetallesContacto().getJtfDireccion().getText().toUpperCase();
					if(direccion.equals(""))
						direccion="NULL";
					else
						direccion="'"+direccion+"'";
			
					
					String mail=agenda.getDetallesContacto().getJtfMail().getText();
					if(mail.equals(""))
						mail="NULL";
					else
						mail="'"+mail+"'";
					
					
					String nota=agenda.getDetallesContacto().getJtaNota().getText();
					if(nota.equals(""))
						nota="NULL";
					else
						nota="'"+nota+"'";
					
					String fnac=agenda.getDetallesContacto().getJtfFnac().getText();
					if(fnac.equals(""))
						fnac="NULL";
					else
						fnac="'"+fnac+"'";
									

					
					Vector<Object> datos=(Vector) agenda.getContacto().getDataVector().get(agenda.getJtTabla().getSelectedRow());
					Object[] d=datos.toArray();
					
					System.out.println(d[0]);
					
					String update="update contacto set "+
					"NOMBRES='"+nombres+"',"+
					"APELLIDOS='"+apellidos+"',"+
					"DIRECCION="+direccion+","+
					"MAIL="+mail+","+
					"NOTA="+nota+","+
					"FNACIMIENTO="+fnac+
					" where idcontacto='"+d[0]+"'";
					
					int[] resOp=connDb.operaciones(update);
					
					if((agenda.getDetallesContacto().getFilePath()!=null))
					{
						file=new File(agenda.getDetallesContacto().getFilePath());
						connDb.query("select * from contacto where idcontacto='"+d[0]+"'");
					}
					

					if(resOp[1]==1)
					{
						JOptionPane.showMessageDialog(agenda, "No se ha podido actualizar al contacto.", "Error al actualizar", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(agenda, "Contacto actualizado correctamente", "Actualizar", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				agenda.resetDatos();
				agenda.getDetallesContacto().bloqueaCampos();
				agenda.getDetallesContacto().habilitaBotones();
				agenda.getDetallesContacto().bloqueaBotonGuardar();
				agenda.getDetallesContacto().restauraColor();
			
		}
		
		else
		{
			JOptionPane.showMessageDialog(agenda, "No se ha realizado ninguna operación \nno se guardará ningún cambio", "Guardar", JOptionPane.INFORMATION_MESSAGE);
		}
		
		agenda.getBuscarContacto().habilitaBusqueda();
		connDb.closeConnection();
	}
	
	public Boolean validateNuevo()
	{
		Boolean regresa=true;
		String mensaje="No se puede agregar un nuevo contacto.\nLos siguientes campos son obligatorios\n";
		
		if(agenda.getDetallesContacto().getJtfNombres().getText().equals("")||agenda.getDetallesContacto().getJtfNombres().getText().equals(null))
		{
			regresa=false;
			mensaje+="- Nombres\n";
		}
		
		if(agenda.getDetallesContacto().getJtfApellidos().getText().equals("")||agenda.getDetallesContacto().getJtfApellidos().getText().equals(null))
		{
			regresa=false;
			mensaje+="- Apellidos";
		}
		if(!regresa)
		{
			JOptionPane.showMessageDialog(agenda, mensaje, "Error al guardar", JOptionPane.ERROR_MESSAGE);
		}
		
		return regresa;
	}

}
