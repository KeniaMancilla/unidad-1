
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;



public class DetallesContacto extends JPanel 
{

	private static final long serialVersionUID = 1L;
	private Agenda agenda;
	
	private JPanel jpPanel;
	
	
	private JLabel jlNombres;
	private JTextField jtfNombres;
	
	private JLabel jlApellidos;
	private JTextField jtfApellidos;
	
	private JLabel jlDireccion;
	private JTextField jtfDireccion;
	
	private JLabel jlNota;
	private JScrollPane jspNota;
	private JTextArea jtaNota;
	
	private JLabel jlFnac;
	private JTextField jtfFnac;
	
	private JLabel jlMail;
	private JTextField jtfMail;
	
	private JButton jbNuevo;
	private JButton jbEliminar;
	private JButton jbEditar;
	private JButton jbCancelar;
	private JButton jbGuardar;

	private NuevoContacto escuchaNuevoContacto;
	private Guardar Guardar;
	private Editar Editar;
	private Eliminar Eliminar;
	private Cancelar Cancelar;
	
	public static int NUEVO=1;
	public static int ELIMINAR=2;
	public static int EDITAR=3;
	
	
	private int operacion;
	
	private Color color=Colores.BackArea;
	
	private String filePath;
	
	public DetallesContacto()
	{
		super();
		init();
		colocaComponentes();
		bloqueaCampos();
		bloqueaBotonGuardar();
		restauraColor();
	}
	
	public DetallesContacto(Agenda a)
	{
		super();
		agenda=a;
		init();
		colocaComponentes();
		bloqueaCampos();
		bloqueaBotonGuardar();
		restauraColor();
	}
	
	private void init()
	{
		super.setLayout(new BorderLayout());
				
		jpPanel=new JPanel();
		jpPanel.setBackground(Colores.BackColor);
		
		jlNombres=new JLabel("Nombres:");
		jtfNombres=new JTextField();
				
		jlApellidos=new JLabel("Apellidos:");
		jtfApellidos=new JTextField();
		jtfApellidos.setPreferredSize(new Dimension(250, 19));
		
		jlDireccion=new JLabel("Dirección:");
		jtfDireccion=new JTextField();
		
		jlFnac=new JLabel("Fecha de Nacimiento:");
		jtfFnac=new JTextField();
		
		jlMail=new JLabel("Mail:");
		jtfMail=new JTextField();
		
		jlNota=new JLabel("Notas: ");
		jtaNota=new JTextArea();
		jtaNota.setLineWrap(true);
		jspNota=new JScrollPane(jtaNota);
		jspNota.setPreferredSize(new Dimension(250,100));
		
		jbNuevo=new JButton("Nuevo",new ImageIcon(getClass().getResource("/iconos/add.png")));
		NuevoContacto a =new NuevoContacto(this);
		jbNuevo.addActionListener(escuchaNuevoContacto);
		
		jbEliminar=new JButton("Eliminar",new ImageIcon(getClass().getResource("/iconos/Trash.png")));
		Eliminar=new Eliminar(this);
		jbEliminar.addActionListener(Eliminar);
		
		jbEditar=new JButton("Editar",new ImageIcon(getClass().getResource("/iconos/application_edit.png")));
		Editar=new Editar(this);
		jbEditar.addActionListener(Editar);
		
		jbCancelar=new JButton("Cancelar",new ImageIcon(getClass().getResource("/iconos/cancel.png")));
		Cancelar=new Cancelar(this);
		jbCancelar.addActionListener(Cancelar);
		
		jbGuardar=new JButton("Guardar",new ImageIcon(getClass().getResource("/iconos/save.png")));
		Guardar=new Guardar(agenda);
		jbGuardar.addActionListener(Guardar);
		
	}
	
	

	private void colocaComponentes()
	{
			
		GroupLayout layout = new GroupLayout(jpPanel);
		jpPanel.setLayout(layout);
			  
		layout.setAutoCreateGaps(true);
		 		  
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		
		hGroup.addGroup(layout.createParallelGroup().addComponent(jlNombres)
  													.addComponent(jlDireccion)
				   									.addComponent(jlMail)	   									
				   									.addComponent(jlNota)
				   									);

		hGroup.addGroup(layout.createParallelGroup().addComponent(jtfNombres)
													.addComponent(jtfDireccion)													
				   									.addComponent(jtfMail)
				   									.addComponent(jspNota)
				   									);
		
		hGroup.addGroup(layout.createParallelGroup().addComponent(jlApellidos)
													.addComponent(jlFnac)
													
												    );
		
		hGroup.addGroup(layout.createParallelGroup().addComponent(jtfApellidos)
													.addComponent(jtfFnac)
													
												    );

				       
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jlNombres).addComponent(jtfNombres).addComponent(jlApellidos).addComponent(jtfApellidos));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jlDireccion).addComponent(jtfDireccion).addComponent(jlFnac).addComponent(jtfFnac));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jlMail).addComponent(jtfMail));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jlNota).addComponent(jspNota));
				
		
		layout.setVerticalGroup(vGroup);
		
		super.add(jpPanel, BorderLayout.NORTH);
	
			
		
		JPanel jpBotones=new JPanel();
		jpBotones.setBackground(Colores.BackColor);
		jpBotones.add(jbNuevo);
		jpBotones.add(jbEditar);
		jpBotones.add(jbEliminar);
		jpBotones.add(jbCancelar);
		jpBotones.add(jbGuardar);
		
		super.add(jpBotones,BorderLayout.SOUTH);
			
		super.updateUI();
		
	}
	
	public void bloqueaCampos()
	{
		jtfNombres.setEditable(false);
		jtfApellidos.setEditable(false);
		jtfDireccion.setEditable(false);
		jtfFnac.setEditable(false);
		jtaNota.setEditable(false);
		jtfMail.setEditable(false);
		
	}
	
	
	public void restauraColor()
	{
		jtfNombres.setBackground(color);
		jtfApellidos.setBackground(color);
		jtfDireccion.setBackground(color);
		jtfFnac.setBackground(color);
		jtaNota.setBackground(color);
		jtfMail.setBackground(color);

		
	}
	public void cambiaColor()
	{
		jtfNombres.setBackground(Colores.BackContact);
		jtfApellidos.setBackground(Colores.BackContact);
		jtfDireccion.setBackground(Colores.BackContact);
		jtfFnac.setBackground(Colores.BackContact);
		jtaNota.setBackground(Colores.BackContact);
		jtfMail.setBackground(Colores.BackContact);
		
	}
	public void bloqueaBotones()
	{
		jbNuevo.setEnabled(false);
		jbEliminar.setEnabled(false);
		jbEditar.setEnabled(false);
	}
	
	public void habilitaBotones()
	{
		jbNuevo.setEnabled(true);
		jbEliminar.setEnabled(true);
		jbEditar.setEnabled(true);
	}
	
	public void bloqueaBotonGuardar()
	{
		jbGuardar.setEnabled(false);
	}
	
	public void habilitaBotonGuardar()
	{
		jbGuardar.setEnabled(true);
	}
	
	public void setFilePath(String a)
	{
		filePath=a;
	}
	
	public String getFilePath()
	{
		return filePath;
	}
	
	public void habilitaCampos()
	{
		jtfNombres.setEditable(true);
		jtfApellidos.setEditable(true);
		jtfDireccion.setEditable(true);
		jtfFnac.setEditable(true);
		jtaNota.setEditable(true);
		jtfMail.setEditable(true);
		
	}
	
	public void limpiaCampos()
	{
		jtfNombres.setText("");
		jtfApellidos.setText("");
		jtfDireccion.setText("");
		jtfFnac.setText("");
		jtaNota.setText("");
		jtfMail.setText("");
		
	}
	
	public DetallesContacto getDetallesContacto()
	{
		return this;
	}
	
	
	public JTextField getJtfMail() {
		return jtfMail;
	}

	public void setJtfMail(JTextField jtfMail) {
		this.jtfMail = jtfMail;
	}
	
	public int getOperacion() 
	{
		return operacion;
	}

	public void setOperacion(int operacion) 
	{
		this.operacion = operacion;
	}

	public Agenda getAgenda(){
		return agenda;
	}

	
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	
	public JPanel getJpPanel() {
		return jpPanel;
	}

	
	public void setJpPanel(JPanel jpPanel) {
		this.jpPanel = jpPanel;
	}

	
	public JLabel getJlNombres() {
		return jlNombres;
	}

	
	public void setJlNombres(JLabel jlNombres) {
		this.jlNombres = jlNombres;
	}

	
	public JTextField getJtfNombres() {
		return jtfNombres;
	}

	
	public void setJtfNombres(JTextField jtfNombres) {
		this.jtfNombres = jtfNombres;
	}

	
	public JLabel getJlApellidos() {
		return jlApellidos;
	}

	
	public void setJlApellidos(JLabel jlApellidos) {
		this.jlApellidos = jlApellidos;
	}

	
	public JTextField getJtfApellidos() {
		return jtfApellidos;
	}

	
	public void setJtfApellidos(JTextField jtfApellidos) {
		this.jtfApellidos = jtfApellidos;
	}

	
	public JLabel getJlDireccion() {
		return jlDireccion;
	}

	
	public void setJlDireccion(JLabel jlDireccion) {
		this.jlDireccion = jlDireccion;
	}

	public JTextField getJtfDireccion() {
		return jtfDireccion;
	}

	
	public void setJtfDireccion(JTextField jtfDireccion) {
		this.jtfDireccion = jtfDireccion;
	}

	public JLabel getJlNota() {
		return jlNota;
	}

	public void setJlNota(JLabel jlNota) {
		this.jlNota = jlNota;
	}

	public JScrollPane getJspNota() {
		return jspNota;
	}

	public void setJspNota(JScrollPane jspNota) {
		this.jspNota = jspNota;
	}

	public JTextArea getJtaNota() {
		return jtaNota;
	}

	public void setJtaNota(JTextArea jtaNota) {
		this.jtaNota = jtaNota;
	}

	public JLabel getJlFnac() {
		return jlFnac;
	}

	public void setJlFnac(JLabel jlFnac) {
		this.jlFnac = jlFnac;
	}

	public JTextField getJtfFnac() {
		return jtfFnac;
	}

	public void setJtfFnac(JTextField jtfFnac) {
		this.jtfFnac = jtfFnac;
	}

	public JButton getJbNuevo() {
		return jbNuevo;
	}

	public void setJbNuevo(JButton jbNuevo) {
		this.jbNuevo = jbNuevo;
	}

	public JButton getJbEliminar() {
		return jbEliminar;
	}

	public void setJbEliminar(JButton jbEliminar) {
		this.jbEliminar = jbEliminar;
	}

	public JButton getJbEditar() {
		return jbEditar;
	}

	public void setJbEditar(JButton jbEditar) {
		this.jbEditar = jbEditar;
	}


	public JButton getJbGuardar() {
		return jbGuardar;
	}

	public void setJbGuardar(JButton jbGuardar) {
		this.jbGuardar = jbGuardar;
	}

	
	
}
