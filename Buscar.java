
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Buscar implements KeyListener 
{

	private BuscarContacto buscarContacto;
	
	public Buscar() 
	{
		
	}
	
	public Buscar(BuscarContacto a) 
	{
		buscarContacto=a;
	}

	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		
		

	}

	
	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		String textoIntro=buscarContacto.getJtfBuscar().getText();
		String where="select * from contacto where nombres like '%"+textoIntro+"%'";
		buscarContacto.getAgenda().resetDatos(where);

	}

	
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		

	}

}
