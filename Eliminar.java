
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;




public class Eliminar implements ActionListener 
{

	private DetallesContacto detallesContacto;
	private ConnDB connDb;
	
	public Eliminar() 
	{
		
	}
	
	public Eliminar(DetallesContacto a) 
	{
		detallesContacto=a;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent ev) 
	{
		
			if(detallesContacto.getAgenda().getContacto().getDataVector().size()>0)
			{
				if(detallesContacto.getAgenda().getJtTabla().getSelectedRow()!=-1)
				{
			
					int res=JOptionPane.showConfirmDialog(detallesContacto, "¿Seguro que quiere eliminar el contacto?", "Eliminar contacto", JOptionPane.YES_NO_OPTION);
					if(res==0)
					{
						connDb=new ConnDB();
						
							
							Vector<Object> datos=(Vector) detallesContacto.getAgenda().getContacto().getDataVector().get(detallesContacto.getAgenda().getJtTabla().getSelectedRow());
							
							Object[] d=datos.toArray();
							String delete="delete from contacto where idcontacto='"+d[0]+"'";		
							int[] op=connDb.operaciones(delete);
							if(op[1]==1)
							{
								JOptionPane.showMessageDialog(detallesContacto, "Error, no se ha eliminado", "Eliminar", JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(detallesContacto, "Contacto eliminado correctamente", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
							}
							 connDb.closeConnection();
							 detallesContacto.limpiaCampos();
							 detallesContacto.getAgenda().resetDatos();
							 detallesContacto.bloqueaCampos();
							 detallesContacto.habilitaBotones();
							 detallesContacto.bloqueaBotonGuardar();							 
							 detallesContacto.updateUI();
							 detallesContacto.getAgenda().getJpPanel().updateUI();
						}
				}
				else
				{
					JOptionPane.showMessageDialog(detallesContacto, "No hay un contacto seleccionado actualmente", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		
		else
		{
			JOptionPane.showMessageDialog(detallesContacto, "No hay contactos actualmente", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
