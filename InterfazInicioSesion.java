import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class InterfazInicioSesion {
    private JFrame ventanaInicio;
    private Map<String, String> usuariosRegistrados;
    private List<String> enviosSolicitados;

    public InterfazInicioSesion() {
        usuariosRegistrados = new HashMap<>();
        enviosSolicitados = new ArrayList<>();
    }

    public void mostrarVentanaInicioSesion() {
        ventanaInicio = new JFrame("Iniciar Sesión");
        ventanaInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaInicio.setSize(350, 200);
        ventanaInicio.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null); // Usaremos un diseño absoluto

        JLabel etiquetaCorreo = new JLabel("Correo: ");
        etiquetaCorreo.setBounds(20, 20, 80, 25);

        JTextField campoCorreo = new JTextField(20);
        campoCorreo.setBounds(120, 20, 200, 25);

        JLabel etiquetaContraseña = new JLabel("Contraseña: ");
        etiquetaContraseña.setBounds(20, 50, 80, 25);

        JPasswordField campoContraseña = new JPasswordField(20);
        campoContraseña.setBounds(120, 50, 200, 25);

        JButton botonIniciarSesion = new JButton("Iniciar Sesión");
        botonIniciarSesion.setBounds(20, 100, 150, 30);

        JButton botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setBounds(200, 100, 120, 30);

        botonIniciarSesion.addActionListener(e -> {
            String correo = campoCorreo.getText();
            String contraseña = new String(campoContraseña.getPassword());

            if (usuariosRegistrados.containsKey(correo) && usuariosRegistrados.get(correo).equals(contraseña)) {
                JOptionPane.showMessageDialog(null, "¡Inicio de sesión exitoso!");
                mostrarMenuPrincipal();
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Por favor, regístrese si es un nuevo usuario.");
            }
        });

        botonRegistrarse.addActionListener(e -> {
            String correo = campoCorreo.getText();
            String contraseña = new String(campoContraseña.getPassword());
            usuariosRegistrados.put(correo, contraseña);
            JOptionPane.showMessageDialog(null, "¡Registro exitoso! Por favor, inicie sesión con las nuevas credenciales.");
        });

        panel.add(etiquetaCorreo);
        panel.add(campoCorreo);
        panel.add(etiquetaContraseña);
        panel.add(campoContraseña);
        panel.add(botonIniciarSesion);
        panel.add(botonRegistrarse);

        ventanaInicio.add(panel);
        ventanaInicio.setVisible(true);
    }

    public void mostrarMenuPrincipal() {
        ventanaInicio.getContentPane().removeAll();
        ventanaInicio.repaint();

        JButton botonEnviarPaquete = new JButton("Enviar Paquete");
        JButton botonObservaciones = new JButton("Observaciones");
        botonEnviarPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaEnviarPaquete();
            }
        });
    
        botonObservaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaObservaciones();
            }
        });
        JPanel panelMenu = new JPanel(new GridLayout(3, 1)); // 3 filas y 1 columna
        JLabel labelPregunta = new JLabel("¿Qué deseas realizar hoy?");
        labelPregunta.setHorizontalAlignment(SwingConstants.CENTER); // Centrar la pregunta
        panelMenu.add(labelPregunta);
        panelMenu.add(botonEnviarPaquete);
        panelMenu.add(botonObservaciones);
    
        // Añadir el panel al centro de la ventana y hacerla visible
        ventanaInicio.add(panelMenu, BorderLayout.CENTER);
        ventanaInicio.setVisible(true);
    }
    public void mostrarVentanaEnviarPaquete() {
        JFrame ventanaEnviarPaquete = new JFrame("Enviar Paquete");
        ventanaEnviarPaquete.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaEnviarPaquete.setSize(600, 400);
        ventanaEnviarPaquete.setLocationRelativeTo(null);
    
        JTextField campoDepartamento = new JTextField(20);
        JTextField campoProvincia = new JTextField(20);
        JTextField campoDistrito = new JTextField(20);
        JTextField campoDestino = new JTextField(20);
        JTextField campoPeso = new JTextField(10);
        JTextField campoLargo = new JTextField(10);
        JTextField campoAncho = new JTextField(10);
        JTextField campoAlto = new JTextField(10);
    
        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double montoAPagar = calcularMontoAPagar();
                mostrarVentanaPago(montoAPagar);
                ventanaEnviarPaquete.dispose();
            }
        });
    
        JPanel panelEnviarPaquete = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        panelEnviarPaquete.add(new JLabel("Departamento:"), gbc);
        panelEnviarPaquete.add(campoDepartamento, gbc);
        panelEnviarPaquete.add(new JLabel("Provincia:"), gbc);
        panelEnviarPaquete.add(campoProvincia, gbc);
        panelEnviarPaquete.add(new JLabel("Distrito:"), gbc);
        panelEnviarPaquete.add(campoDistrito, gbc);
        panelEnviarPaquete.add(new JLabel("Destino:"), gbc);
        panelEnviarPaquete.add(campoDestino, gbc);
        panelEnviarPaquete.add(new JLabel("Peso (kg):"), gbc);
        panelEnviarPaquete.add(campoPeso, gbc);
        panelEnviarPaquete.add(new JLabel("Largo:"), gbc);
        panelEnviarPaquete.add(campoLargo, gbc);
        panelEnviarPaquete.add(new JLabel("Ancho:"), gbc);
        panelEnviarPaquete.add(campoAncho, gbc);
        panelEnviarPaquete.add(new JLabel("Alto:"), gbc);
        panelEnviarPaquete.add(campoAlto, gbc);
        panelEnviarPaquete.add(botonEnviar, gbc);
    
        ventanaEnviarPaquete.add(panelEnviarPaquete);
        ventanaEnviarPaquete.setVisible(true);
    }
    
    private double calcularMontoAPagar() {
        // Lógica para calcular el monto a pagar
        // Esta es una implementación de ejemplo, puedes modificarla según tus requerimientos
        return 20.50; // Simulación de monto
    }
    
    

    public void mostrarVentanaPago(double montoAPagar) {
        JFrame ventanaPago = new JFrame("Pago");
        ventanaPago.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaPago.setSize(400, 300);
        ventanaPago.setLocationRelativeTo(null);

        JLabel labelMontoAPagar = new JLabel("Monto a pagar: " + montoAPagar);

        JComboBox<String> bancos = new JComboBox<>();
        bancos.addItem("Bbva");
        bancos.addItem("BPC");
        bancos.addItem("Yape");
        // Agregar otros bancos según necesites

        JComboBox<String> monedas = new JComboBox<>();
        monedas.addItem("Soles");
        monedas.addItem("Dólares");
        // Agregar otros tipos de moneda según necesites

        JButton botonGenerarCodigo = new JButton("Generar Código de Pago");
        botonGenerarCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bancoSeleccionado = (String) bancos.getSelectedItem();
                String monedaSeleccionada = (String) monedas.getSelectedItem();

                int codigoPago = codigoPagoAleatorio();
                JOptionPane.showMessageDialog(null, "Código de Pago: " + codigoPago);
                int codigoIngresado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Código de Pago"));
                if (validarCodigo(codigoIngresado, codigoPago)) {
                    mostrarVentanaConfirmacionPedido(codigoPago);
                    ventanaPago.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Código incorrecto. Por favor, inténtelo de nuevo.");
                }
            }
        });

        JPanel panelPago = new JPanel();
        panelPago.add(labelMontoAPagar);
        panelPago.add(bancos);
        panelPago.add(monedas);
        panelPago.add(botonGenerarCodigo);

        ventanaPago.add(panelPago);
        ventanaPago.setVisible(true);
    }

    private int codigoPagoAleatorio() {
        Random rand = new Random();
        return 10000000 + rand.nextInt(90000000);
    }

    private boolean validarCodigo(int codigoIngresado, int codigoGenerado) {
        return codigoIngresado == codigoGenerado;
    }

    public void mostrarVentanaConfirmacionPedido(int codigoPago) {
        JFrame ventanaConfirmacion = new JFrame("Confirmación de Pedido");
        ventanaConfirmacion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaConfirmacion.setSize(300, 200);
        ventanaConfirmacion.setLocationRelativeTo(null);

        JTextField campoCodigoConfirmacion = new JTextField(10);
        JButton botonConfirmarPedido = new JButton("Confirmar Pedido");
        botonConfirmarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigoIngresado = Integer.parseInt(campoCodigoConfirmacion.getText());
                if (validarCodigo(codigoIngresado, codigoPago)) {
                    JOptionPane.showMessageDialog(null, "Pedido confirmado. Código: " + codigoIngresado);
                    ventanaConfirmacion.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Código de confirmación incorrecto. Por favor, inténtelo de nuevo.");
                }
            }
        });

        JPanel panelConfirmacion = new JPanel();
        panelConfirmacion.add(new JLabel("Ingrese el código de confirmación: "));
        panelConfirmacion.add(campoCodigoConfirmacion);
        panelConfirmacion.add(botonConfirmarPedido);

        ventanaConfirmacion.add(panelConfirmacion);
        ventanaConfirmacion.setVisible(true);
    }
//pipipip
    public void mostrarVentanaObservaciones() {
        JFrame ventanaObservaciones = new JFrame("Observaciones");
        ventanaObservaciones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaObservaciones.setSize(300, 200);
        ventanaObservaciones.setLocationRelativeTo(null);

        JButton botonMisProductos = new JButton("Mis productos");
        JButton botonRegistrarIncidente = new JButton("Registrar incidente");
        JButton botonGastosAnuales = new JButton("Gastos anuales");
        JButton botonReembolso = new JButton("Reembolso");

        botonMisProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEnviosSolicitados();
            }
        });

        botonRegistrarIncidente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaRegistrarIncidente();
            }
        });

        botonGastosAnuales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarGastosAnuales();
            }
        });

        botonReembolso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaReembolso();
            }
        });

        JPanel panelObservaciones = new JPanel();
        panelObservaciones.add(botonMisProductos);
        panelObservaciones.add(botonRegistrarIncidente);
        panelObservaciones.add(botonGastosAnuales);
        panelObservaciones.add(botonReembolso);

        ventanaObservaciones.add(panelObservaciones);
        ventanaObservaciones.setVisible(true);
    }

    public void mostrarEnviosSolicitados() {
        JFrame ventanaEnviosSolicitados = new JFrame("Mis Pedidos");
        ventanaEnviosSolicitados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaEnviosSolicitados.setSize(400, 300);
        ventanaEnviosSolicitados.setLocationRelativeTo(null);

        JList<String> listaPedidos = new JList<>(enviosSolicitados.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(listaPedidos);

        ventanaEnviosSolicitados.add(scrollPane);

        ventanaEnviosSolicitados.setVisible(true);
    }

    public void mostrarVentanaRegistrarIncidente() {
        JFrame ventanaRegistrarIncidente = new JFrame("Registrar Incidente");
        ventanaRegistrarIncidente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaRegistrarIncidente.setSize(300, 200);
        ventanaRegistrarIncidente.setLocationRelativeTo(null);
    
        String[] opciones = {
            "Retraso de entrega",
            "Pérdida de paquete",
            "Daño de la mercancía",
            "Entrega incorrecta",
            "Paquete abierto o violado",
            "Problemas de facturación",
            "Rechazo de entrega",
            "Falta de comunicación",
            "Otros"
        };
    
        JComboBox<String> opcionesIncidente = new JComboBox<>(opciones);
    
        JTextField campoEspecificar = new JTextField(20);
    
        JButton botonRegistrar = new JButton("Registrar");
    
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String incidenteSeleccionado = (String) opcionesIncidente.getSelectedItem();
                String detallesIncidente = "";
    
                if (incidenteSeleccionado.equals("Otros")) {
                    detallesIncidente = campoEspecificar.getText();
                } else {
                    detallesIncidente = incidenteSeleccionado;
                }
    
                // Aquí puedes usar 'detallesIncidente' para registrar el incidente en tu sistema
                // Por ejemplo, imprimirlo en consola para propósitos de prueba
                System.out.println("Incidente registrado: " + detallesIncidente);
    
                // Puedes añadir más lógica aquí, como guardar el incidente en una lista, base de datos, etc.
    
                ventanaRegistrarIncidente.dispose();
            }
        });
    
        JPanel panelIncidentes = new JPanel();
        panelIncidentes.add(new JLabel("Tipo de incidente:"));
        panelIncidentes.add(opcionesIncidente);
        panelIncidentes.add(new JLabel("Especificar (si es 'Otros'):"));
        panelIncidentes.add(campoEspecificar);
        panelIncidentes.add(botonRegistrar);
    
        ventanaRegistrarIncidente.add(panelIncidentes);
        ventanaRegistrarIncidente.setVisible(true);
    }
    public void mostrarGastosAnuales() {
        // Lógica para mostrar los gastos anuales
    }

    public void mostrarVentanaReembolso() {
        JFrame ventanaReembolso = new JFrame("Solicitud de Reembolso");
        ventanaReembolso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaReembolso.setSize(400, 300);
        ventanaReembolso.setLocationRelativeTo(null);

        JLabel labelCodigoEntrega = new JLabel("Ingrese el código de 8 dígitos:");
        JTextField campoCodigoEntrega = new JTextField(8);

        String[] razones = {
            "Daño de la mercancía",
            "Paquete abierto o violado",
            "Problema con la facturación",
            "Pérdida de paquetes",
            "Otros"
        };

        JComboBox<String> opcionesRazon = new JComboBox<>(razones);
        JTextField campoEspecificarRazon = new JTextField(20);
        campoEspecificarRazon.setEnabled(false);

        opcionesRazon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String razonSeleccionada = (String) opcionesRazon.getSelectedItem();
                campoEspecificarRazon.setEnabled(razonSeleccionada.equals("Otros"));
            }
        });

        JButton botonSolicitarReembolso = new JButton("Solicitar Reembolso");

        botonSolicitarReembolso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoEntrega = campoCodigoEntrega.getText();
                String razonSeleccionada = (String) opcionesRazon.getSelectedItem();
                String detallesRazon = "";

                if (razonSeleccionada.equals("Otros")) {
                    detallesRazon = campoEspecificarRazon.getText();
                } else {
                    detallesRazon = razonSeleccionada;
                }

                System.out.println("Solicitud de reembolso - Código: " + codigoEntrega + ", Razón: " + detallesRazon);

                ventanaReembolso.dispose();
            }
        });

        JPanel panelReembolso = new JPanel();
        panelReembolso.add(labelCodigoEntrega);
        panelReembolso.add(campoCodigoEntrega);
        panelReembolso.add(new JLabel("Razón del reembolso:"));
        panelReembolso.add(opcionesRazon);
        panelReembolso.add(campoEspecificarRazon);
        panelReembolso.add(botonSolicitarReembolso);

        ventanaReembolso.add(panelReembolso);
        ventanaReembolso.setVisible(true);
    }
}
