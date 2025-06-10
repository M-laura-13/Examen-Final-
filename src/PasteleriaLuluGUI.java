import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PasteleriaLuluGUI {
    private JFrame frame;
    private JTextField campoNombre, campoCantidad, campoTelefono, campoDireccion;
    private JComboBox<String> comboCategoria, comboVariedad;
    private JCheckBox checkParaLlevar;
    private JTextArea areaResumen;
    private JLabel labelTotal;
    private double total = 0;
    private List<String> historial = new ArrayList<>();

    private static final String[] categorias = {"🎂 Tortas", "🍪 Galletas", "🍮 Postres", "🧁 Cupcakes", "🍫 Brownies"};
    private static final String[][] variedades = {
        {"Chocolate", "Vainilla", "Zanahoria", "Red Velvet"},
        {"Chispas", "Avena", "Mantequilla", "Coco"},
        {"Tres Leches", "Flan", "Cheesecake", "Gelatina"},
        {"Chocolate", "Frambuesa", "Fresa", "Oreo"},
        {"Clásico", "Nuez", "Chocolate Blanco", "Doble Chocolate"}
    };
    private static final double[][] precios = {
        {55, 42, 56, 94},
        {7, 5, 5, 10},
        {15, 20, 34, 10},
        {8, 7, 9, 11},
        {5, 9, 11, 15}
    };

    public PasteleriaLuluGUI() {
        frame = new JFrame("🧁 Pastelería Lulu's 🍰");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 580);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 245, 235)); // fondo pastel

        JPanel panelTop = new JPanel(new GridLayout(8, 2, 5, 5));
        panelTop.setBorder(BorderFactory.createTitledBorder("📝 Datos del Pedido"));
        panelTop.setBackground(new Color(255, 245, 235));

        campoNombre = new JTextField();
        campoCantidad = new JTextField();
        campoTelefono = new JTextField();
        campoDireccion = new JTextField();
        checkParaLlevar = new JCheckBox("📦 Para llevar");
        comboCategoria = new JComboBox<>(categorias);
        comboVariedad = new JComboBox<>();
        labelTotal = new JLabel("💵 Total: $0.00");

        comboCategoria.addActionListener(e -> actualizarVariedades());
        actualizarVariedades();

        panelTop.add(new JLabel("👤 Nombre del Cliente:"));
        panelTop.add(campoNombre);
        panelTop.add(new JLabel("📂 Categoría:"));
        panelTop.add(comboCategoria);
        panelTop.add(new JLabel("🍽️ Variedad:"));
        panelTop.add(comboVariedad);
        panelTop.add(new JLabel("🔢 Cantidad:"));
        panelTop.add(campoCantidad);
        panelTop.add(new JLabel("📱 Teléfono:"));
        panelTop.add(campoTelefono);
        panelTop.add(new JLabel("🏡 Dirección:"));
        panelTop.add(campoDireccion);
        panelTop.add(checkParaLlevar);
        panelTop.add(new JLabel());

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(255, 235, 245));

        JButton btnAgregar = new JButton("➕ Agregar Producto");
        JButton btnFinalizar = new JButton("✅ Finalizar Pedido");
        JButton btnHistorial = new JButton("📜 Ver Historial");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnFinalizar);
        panelBotones.add(btnHistorial);

        areaResumen = new JTextArea();
        areaResumen.setEditable(false);
        areaResumen.setBorder(BorderFactory.createTitledBorder("🧾 Resumen del Pedido"));
        areaResumen.setBackground(new Color(255, 250, 240));
        JScrollPane scrollPane = new JScrollPane(areaResumen);

        labelTotal.setFont(new Font("Arial", Font.BOLD, 16));
        labelTotal.setForeground(new Color(40, 90, 50));
        JPanel totalPanel = new JPanel();
        totalPanel.setBackground(new Color(255, 245, 235));
        totalPanel.add(labelTotal);

        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panelBotones, BorderLayout.SOUTH);
        frame.add(totalPanel, BorderLayout.WEST);

        btnAgregar.addActionListener(e -> agregarProducto());
        btnFinalizar.addActionListener(e -> finalizarPedido());
        btnHistorial.addActionListener(e -> mostrarHistorial());

        frame.setVisible(true);
    }

    private void actualizarVariedades() {
        int categoriaIndex = comboCategoria.getSelectedIndex();
        comboVariedad.removeAllItems();
        for (String v : variedades[categoriaIndex]) {
            comboVariedad.addItem(v);
        }
    }

    private void agregarProducto() {
        String nombre = campoNombre.getText().trim();
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            JOptionPane.showMessageDialog(frame, "❌ El nombre solo puede contener letras.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int categoriaIndex = comboCategoria.getSelectedIndex();
        int variedadIndex = comboVariedad.getSelectedIndex();
        String variedad = variedades[categoriaIndex][variedadIndex];
        double precio = precios[categoriaIndex][variedadIndex];

        int cantidad;
        try {
            cantidad = Integer.parseInt(campoCantidad.getText());
            if (cantidad < 1 || cantidad > 25) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "⚠️ Cantidad inválida (1-25).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double subtotal = cantidad * precio;
        total += subtotal;
        labelTotal.setText("💵 Total: $" + String.format("%.2f", total));

        areaResumen.append("🍰 " + categorias[categoriaIndex] + " (" + variedad + ") x" + cantidad + " = $" + subtotal + "\n");
    }

    private void finalizarPedido() {
        if (areaResumen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "⚠️ Agregue al menos un producto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String resumen = "📋 Pedido de: " + campoNombre.getText().trim() +
                "\n" + areaResumen.getText() +
                "💰 Total a pagar: $" + String.format("%.2f", total) + "\n";

        if (checkParaLlevar.isSelected()) {
            String tel = campoTelefono.getText().trim();
            if (!tel.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(frame, "❌ Teléfono inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String dir = campoDireccion.getText().trim();
            if (dir.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "❌ Dirección requerida para pedidos a domicilio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            resumen += "📦 Para llevar a: " + dir + "\n📞 Tel: " + tel + "\n";
        } else {
            resumen += "🏪 Para recoger en tienda.\n";
        }

        JOptionPane.showMessageDialog(frame, resumen, "✅ Resumen del Pedido", JOptionPane.INFORMATION_MESSAGE);
        historial.add(resumen);
        areaResumen.setText("");
        campoCantidad.setText("");
        labelTotal.setText("💵 Total: $0.00");
        total = 0;
    }

    private void mostrarHistorial() {
        if (historial.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "📭 No hay pedidos en el historial.", "Historial", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String h : historial) {
            sb.append(h).append("\n------------------\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(frame, scroll, "📜 Historial de Pedidos", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PasteleriaLuluGUI::new);
    }
}
