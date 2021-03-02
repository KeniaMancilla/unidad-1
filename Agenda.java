
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class Agenda extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	
	private JPanel jpPanel;
	private JSplitPane jsplitPanel;
	private JTable jtTabla;
	private Contacto Contacto;
	private BuscarContacto buscarContacto;
	private DetallesContacto detallesContacto;
	private TablaSeleccion TablaSeleccion;
	private ListSelectionModel listSelectionModel;

	private ConnDB connDb;
		
	public Agenda()
	{
		super();
		init();
		cargaDatos();
	 	
	}
			
	
	private void init()
	{
		super.setTitle("Agenda | frlx.wordpress.com");
		super.setIconImage(new ImageIcon(getClass().getResource("/iconos/User.png")).getImage());
		
			super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		
		
		
		super.setVisible(true);
		super.setSize(300,200);
		super.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		jpPanel=(JPanel)super.getContentPane();
					
		buscarContacto=new BuscarContacto(this);
		JMenuBar menu=new JMenuBar();
		menu.add(buscarContacto);
		buscarContacto.setFloatable(false);
		super.setJMenuBar(menu);
		
		Contacto=new Contacto();
		jtTabla=new JTable(Contacto);
		jtTabla.createDefaultColumnsFromModel();
		Contacto.columSize(jtTabla);
		
		jtTabla.setBackground(Colores.BackArea);
		JScrollPane jspPanel=new JScrollPane(jtTabla);	
		jtTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TablaSeleccion=new TablaSeleccion(this);
		
		listSelectionModel=jtTabla.getSelectionModel();
		listSelectionModel.addListSelectionListener(TablaSeleccion);
		
		detallesContacto=new DetallesContacto(this);
						
		jsplitPanel=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true, jspPanel, detallesContacto);
		jsplitPanel.setDividerLocation(690);
		jsplitPanel.setOneTouchExpandable(true);
		jpPanel.add(jsplitPanel);
		jsplitPanel.updateUI();
		jpPanel.updateUI();
		
	}
	

	public JTable getJtTabla() 
	{
		return jtTabla;
	}

	
	public void setJtTabla(JTable jtTabla) 
	{
		this.jtTabla = jtTabla;
	}

	public Contacto getContacto()
	{
		return Contacto;
	}
	
	public DetallesContacto getDetallesContacto()
	{
		return detallesContacto;
	}
	
	public void resetDatos()
	{
		jtTabla.removeAll();
		Contacto.getDataVector().removeAllElements();
		cargaDatos();
	}
	
	public void cargaDatos()
	{
		connDb=new ConnDB();
		String where="SELECT * FROM CONTACTO ORDER BY NOMBRES,APELLIDOS";
		connDb.query(where);
		Object[][] contactosSet=connDb.getResult();
    	if(contactosSet!=null)
		{
			for(int i=0;i<contactosSet.length;i++)
			{
				Object[] filtro=new Object[7];
				filtro[0]=contactosSet[i][0];
				filtro[1]=contactosSet[i][1];
				filtro[2]=contactosSet[i][2];
				filtro[3]=contactosSet[i][4];
				filtro[4]=contactosSet[i][6];
				filtro[5]=contactosSet[i][10];
								              
				Contacto.addRow(filtro);
			}
			if(contactosSet.length>0)
			{
				jtTabla.setRowSelectionInterval(0,0);
			}
		}
		
		connDb.closeConnection();
	}
	
	public void resetDatos(String whr)
	{
		jtTabla.removeAll();
		Contacto.getDataVector().removeAllElements();
		cargaDatos(whr);
	}
	
	public void cargaDatos(String whr)
	{
		connDb=new ConnDB();
		String where=whr;
		connDb.query(where);
		Object[][] contactosSet=connDb.getResult();
    	if(contactosSet!=null)
		{
			for(int i=0;i<contactosSet.length;i++)
			{
				Object[] filtro=new Object[7];
				filtro[0]=contactosSet[i][0];
				filtro[1]=contactosSet[i][1];
				filtro[2]=contactosSet[i][2];
				filtro[3]=contactosSet[i][4];
				filtro[4]=contactosSet[i][6];
				filtro[5]=contactosSet[i][10];
								              
				Contacto.addRow(filtro);
			}
			if(contactosSet.length>0)
			{
				jtTabla.setRowSelectionInterval(0,0);
			}
		}
		
		connDb.closeConnection();
	}
		
	
	public JPanel getJpPanel() 
	{
		return jpPanel;
	}

	
	public void setJpPanel(JPanel jpPanel) 
	{
		this.jpPanel = jpPanel;
	}
	
	
	public BuscarContacto getBuscarContacto() 
	{
		return buscarContacto;
	}

	
	public void setBuscarContacto(BuscarContacto buscarContacto) 
	{
		this.buscarContacto = buscarContacto;
	}
	

	public static void main(String[] args)
	{
		new Agenda();
      Contacto a = new Contacto();
      Cancelar b = new Cancelar();
      Editar c = new Editar();
      Eliminar d = new Eliminar();
      Guardar e = new Guardar();
      NuevoContacto f = new NuevoContacto();
      TablaSeleccion g = new TablaSeleccion();
      DetallesContacto h = new DetallesContacto();
      BuscarContacto i = new BuscarContacto();
      LimpiaConsulta j = new LimpiaConsulta();
      RWFichero k = new RWFichero();
      Colores l = new Colores();
      ConnDB m = new ConnDB();

	}
}
