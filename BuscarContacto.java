
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;




public class BuscarContacto extends JToolBar 
{

	private static final long serialVersionUID = 1L;
	
	private Agenda agenda;
	
	private JLabel jlBuscar;
	private JTextField jtfBuscar;
	private JButton jbBuscar;
	private JButton jbLimpiar;
	private Buscar Buscar;
	private LimpiaConsulta LimpiaConsulta;
	
	public BuscarContacto(Agenda a)
	{ 
		agenda=a;
		init();
		colocaComponentes();
	}

	public BuscarContacto()
	{ 
		init();
		colocaComponentes();
	}
	
	private void init()
	{
		jlBuscar=new JLabel("Buscar contacto:");
		
		jtfBuscar=new JTextField();
		Buscar=new Buscar(this);
		jtfBuscar.addKeyListener(Buscar);
		
		jbBuscar=new JButton("Buscar",new ImageIcon(getClass().getResource("/iconos/Search.png")));
		jbBuscar.setEnabled(false);
		
		jbLimpiar=new JButton(new ImageIcon(getClass().getResource("/iconos/editclear.png")));
		jbLimpiar.setToolTipText("Limpiar consulta");
		LimpiaConsulta=new LimpiaConsulta(this);
		jbLimpiar.addActionListener(LimpiaConsulta);
	}
	
	private void colocaComponentes()
	{
		super.add(jlBuscar);
		super.add(jtfBuscar);
		super.add(jbBuscar);
		super.addSeparator();
		super.add(jbLimpiar);
		
	}
		
	public BuscarContacto getBuscarContacto()
	{
		return this;
	}
	
	public Agenda getAgenda()
	{
		return agenda;
	}

	
	public JLabel getJlBuscar() {
		return jlBuscar;
	}

	public void setJlBuscar(JLabel jlBuscar) {
		this.jlBuscar = jlBuscar;
	}

	public JTextField getJtfBuscar() {
		return jtfBuscar;
	}

	public void setJtfBuscar(JTextField jtfBuscar) {
		this.jtfBuscar = jtfBuscar;
	}

	public JButton getJbBuscar() {
		return jbBuscar;
	}

	public void setJbBuscar(JButton jbBuscar) {
		this.jbBuscar = jbBuscar;
	}

	public JButton getJbLimpiar() {
		return jbLimpiar;
	}

	public void setJbLimpiar(JButton jbLimpiar) {
		this.jbLimpiar = jbLimpiar;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
	public void bloqueBusqueda()
	{
		jtfBuscar.setEnabled(false);
		jbLimpiar.setEnabled(false);
	}
	
	public void habilitaBusqueda()
	{

		jtfBuscar.setEnabled(true);
		jbLimpiar.setEnabled(true);
	}
	
}
