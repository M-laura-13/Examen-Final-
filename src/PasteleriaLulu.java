import java.util.Scanner;

// Clase Producto.java
class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}

// Clase Pedido.java
class Pedido {
    private Producto producto;
    private int cantidad;
    private String nombreCliente;

    public Pedido(Producto producto, int cantidad, String nombreCliente) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.nombreCliente = nombreCliente;
    }

    public double calcularTotal() {
        return producto.getPrecio() * cantidad;
    }

    public void mostrarPedido() {
        System.out.println("Pedido a nombre de: " + nombreCliente);
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Total a pagar: $" + calcularTotal());
    }
}

// Clase Entrega.java
class Entrega {
    private String telefono;
    private String direccion;

    public Entrega(String telefono, String direccion) {
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public void mostrarEntrega() {
        System.out.println("--- Datos de entrega ---");
        System.out.println("Teléfono: " + telefono);
        System.out.println("Dirección: " + direccion);
    }
}

// Clase Cupcake (Hereda de Producto)
class Cupcake extends Producto {
    private boolean tieneDecoracion;

    public Cupcake(String nombre, double precio, boolean tieneDecoracion) {
        super(nombre, precio);
        this.tieneDecoracion = tieneDecoracion;
    }

    public boolean isTieneDecoracion() {
        return tieneDecoracion;
    }
}

// Clase Main.java
public class PasteleriaLulu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[][] menu = {
            {"Torta de chocolate", "Torta de vainilla", "Torta de fresa", "Torta tres leches"},
            {"Galleta de avena", "Galleta de chispas", "Galleta de mantequilla", "Galleta de coco"},
            {"Postre de limón", "Postre de maracuyá", "Postre de fresa", "Postre de chocolate"},
            {"Cupcake de red velvet", "Cupcake de vainilla", "Cupcake de chocolate", "Cupcake arcoiris"},
            {"Brownie clásico", "Brownie con nuez", "Brownie de chocolate blanco", "Brownie de dulce de leche"}
        };

        double[][] precios = {
            {15000, 14000, 14500, 16000},
            {3000, 3500, 3200, 3600},
            {5000, 5200, 5100, 5300},
            {4500, 4400, 4600, 5000},
            {6000, 6200, 6300, 6500}
        };

        System.out.println("Bienvenido a la Pastelería Delicia Dulce");
        System.out.println("Menú disponible:");

        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + obtenerCategoria(i));
        }

        System.out.print("Seleccione una categoría (1-5): ");
        int categoria = sc.nextInt() - 1;

        if (categoria >= 0 && categoria < menu.length) {
            System.out.println("Variaciones disponibles:");
            for (int j = 0; j < 4; j++) {
                System.out.println((j + 1) + ". " + menu[categoria][j] + " ($" + precios[categoria][j] + ")");
            }

            System.out.print("Seleccione el producto (1-4): ");
            int seleccion = sc.nextInt() - 1;

            System.out.print("Cantidad del producto: ");
            int cantidad = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            System.out.print("Nombre del cliente: ");
            String cliente = sc.nextLine();

            Producto producto = new Producto(menu[categoria][seleccion], precios[categoria][seleccion]);
            Pedido pedido = new Pedido(producto, cantidad, cliente);
            pedido.mostrarPedido();

            System.out.print("\n¿Desea que el pedido sea para entrega? (si/no): ");
            String entrega = sc.nextLine();

            if (entrega.equalsIgnoreCase("si")) {
                System.out.print("Número de teléfono: ");
                String tel = sc.nextLine();
                System.out.print("Dirección de entrega: ");
                String dir = sc.nextLine();

                Entrega datosEntrega = new Entrega(tel, dir);
                datosEntrega.mostrarEntrega();
            } else {
                System.out.println("Puede recoger su pedido en 30 minutos.");
            }
        } else {
            System.out.println("Opción inválida.");
        }
        
        sc.close();
    }

    public static String obtenerCategoria(int i) {
        switch (i) {
            case 0: return "Tortas";
            case 1: return "Galletas";
            case 2: return "Postres";
            case 3: return "Cupcakes";
            case 4: return "Brownies";
            default: return "Desconocido";
        }
    }
}