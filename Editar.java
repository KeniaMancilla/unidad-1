

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class Editar implements ActionListener 
{

	private DetallesContacto detallesContacto;
	
	public Editar() 
	{
		
	}
	
	public Editar(DetallesContacto a) 
	{
		detallesContacto=a;
	}

	
	@Override
	public void actionPerformed(ActionEvent ev) 
	{
		if(detallesContacto.getAgenda().getContacto().getDataVector().size()>0)
		{
			detallesContacto.habilitaCampos();
			detallesContacto.setOperacion(DetallesContacto.EDITAR);
			detallesContacto.bloqueaBotones();
			detallesContacto.habilitaBotonGuardar();
			detallesContacto.cambiaColor();
			detallesContacto.getAgenda().getBuscarContacto().bloqueBusqueda();
		}
		else
		{
			JOptionPane.showMessageDialog(detallesContacto, "No hay ningún contacto disponible.", "Error al actualizar", JOptionPane.ERROR_MESSAGE);
		}
	}

}
