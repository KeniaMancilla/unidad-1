
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LimpiaConsulta implements ActionListener 
{

	private BuscarContacto buscarContacto;
	
	public LimpiaConsulta(BuscarContacto b) 
	{
		buscarContacto=b;
	}

	public LimpiaConsulta() 
	{
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		buscarContacto.getAgenda().resetDatos();
		buscarContacto.getJtfBuscar().setText("");
	}

}
