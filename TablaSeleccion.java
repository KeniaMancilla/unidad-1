
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TablaSeleccion implements ListSelectionListener 
{

	private Agenda agenda;
	private ConnDB connDb;
	
	public TablaSeleccion() 
	{
	}
	
	public TablaSeleccion(Agenda a) 
	{
		agenda=a;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent ev) 
	{
		if(agenda.getJtTabla().getSelectedRow()<agenda.getContacto().getDataVector().size())
		{
			
			
			connDb=new ConnDB();
			
			Object[][] contacto=connDb.getResult();
			
			if(contacto.length>0)
			{
				agenda.getDetallesContacto().getJtfNombres().setText((String)contacto[0][1]);
				agenda.getDetallesContacto().getJtfApellidos().setText((String)contacto[0][2]);
				agenda.getDetallesContacto().getJtfDireccion().setText((String)contacto[0][3]);
				agenda.getDetallesContacto().getJtfMail().setText((String)contacto[0][4]);
				agenda.getDetallesContacto().getJtaNota().setText((String)contacto[0][5]);
				agenda.getDetallesContacto().getJtfFnac().setText((String)contacto[0][6]);

				agenda.getDetallesContacto().updateUI();
			}
			connDb.closeConnection();
		}
		
				
	}

	
	

}
