
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NuevoContacto implements ActionListener 
{

	private DetallesContacto detallesContacto;
	
	public NuevoContacto() 
	{
		
	}
	
	public NuevoContacto(DetallesContacto d) 
	{
		detallesContacto=d;
	}

	
	@Override
	public void actionPerformed(ActionEvent ev) 
	{
		detallesContacto.limpiaCampos();
		detallesContacto.habilitaCampos();
		detallesContacto.setOperacion(DetallesContacto.NUEVO);
		detallesContacto.bloqueaBotones();
		detallesContacto.habilitaBotonGuardar();
		detallesContacto.cambiaColor();
		detallesContacto.getAgenda().getBuscarContacto().bloqueBusqueda();
	}

}
