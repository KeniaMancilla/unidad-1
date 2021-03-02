
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Cancelar implements ActionListener 
{

	private DetallesContacto detallesContacto;
	
	public Cancelar() 
	{
		
	}
	
	public Cancelar(DetallesContacto a) 
	{
		detallesContacto=a;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		detallesContacto.habilitaBotones();
		detallesContacto.bloqueaCampos();
		detallesContacto.getAgenda().resetDatos();
		detallesContacto.bloqueaBotonGuardar();
		detallesContacto.restauraColor();
		detallesContacto.getAgenda().getBuscarContacto().habilitaBusqueda();
	}

}
