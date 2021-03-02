
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Contacto extends DefaultTableModel 
{

	private static final long serialVersionUID = 1L;
	
	private static Object[] columnas={"Id","Nombres","Apellidos","Telf","Móvil","Mail"};

	
	public Contacto()
	{
		super(columnas,0);
	}
	
	public Class<?> getColumnClass(int columnIndex)
	{
		return super.getColumnClass(columnIndex);
	}
	
	public void columSize(JTable tabla)
    {
    	 TableColumn column = null;
	     column =tabla.getColumnModel().getColumn(0);
	     column.setPreferredWidth(5);
	     column =tabla.getColumnModel().getColumn(1);
	     column.setPreferredWidth(150);
	     column =tabla.getColumnModel().getColumn(2);
	     column.setPreferredWidth(150);
	     column =tabla.getColumnModel().getColumn(3);
	     column.setPreferredWidth(50);
	     column =tabla.getColumnModel().getColumn(4);
	     column.setPreferredWidth(50);
	     column =tabla.getColumnModel().getColumn(5);
	     column.setPreferredWidth(100);
	     
	     
    }
	

}
