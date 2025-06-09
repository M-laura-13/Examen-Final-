 import java.util.*;
class Producto {
    protected String nombre;
    protected double precio;
    protected int cantidadMaxima = 25;

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

class VariedadProducto extends Producto {
    private String variedad;

    public VariedadProducto(String nombre, String variedad, double precio) {
        super(nombre, precio);
        this.variedad = variedad;
    }

    public String getVariedad() {
        return variedad;
    }
}

class Pedido {
    private String nombreCliente;
    private List<VariedadProducto> productos = new ArrayList<>();
    private List<Integer> cantidades = new ArrayList<>();
    private boolean paraLlevar;
    private String telefono;
    private String direccion;
    private double total;

    public Pedido(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void agregarProducto(VariedadProducto producto, int cantidad) {
        productos.add(producto);
        cantidades.add(cantidad);
        total += producto.getPrecio() * cantidad;
    }

    public void setParaLlevar(boolean paraLlevar) {
        this.paraLlevar = paraLlevar;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getTotal() {
        return total;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void mostrarPedido() {
        System.out.println("\nPedido a nombre de: " + nombreCliente);
        System.out.println("----------------------------------------");
        for (int i = 0; i < productos.size(); i++) {
            VariedadProducto p = productos.get(i);
            System.out.println("- " + p.getNombre() + " (" + p.getVariedad() + ") x" + cantidades.get(i) + " = $" + (p.getPrecio() * cantidades.get(i)));
        }
        System.out.println("----------------------------------------");
        System.out.printf("Total a pagar: $" + total + "\n");
        System.out.println("----------------------------------------");
        if (paraLlevar) {
            System.out.println("Para llevar a: ");
            System.out.println("Direccion: " + direccion);
            System.out.println("Telefono: " + telefono);
            System.out.println("----------------------------------------");
        } else {
            System.out.println("Para recoger en tienda.");
            System.out.println("----------------------------------------");
        }
    }
}

class Utilidades {
    private static Scanner sc = new Scanner(System.in);

    public static int leerEntero(String mensaje, int min, int max) {
        int valor = -1;
        while (true) {
            System.out.print(mensaje);
            String input = sc.nextLine();
            try {
                valor = Integer.parseInt(input);
                if (valor < min || valor > max) {
                    System.out.println("Caracteres inválidos, intente de nuevo");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Caracteres inválidos, intente de nuevo");
            }
        }
        return valor;
    }

    public static String leerTexto(String mensaje, boolean soloLetras) {
        while (true) {
            System.out.print(mensaje);
            String input = sc.nextLine();
            if (soloLetras) {
                if (!input.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                    System.out.println("Caracteres inválidos, intente de nuevo");
                    continue;
                }
            } else {
                if (input.trim().isEmpty()) {
                    System.out.println("Caracteres inválidos, intente de nuevo");
                    continue;
                }
            }
            return input;
        }
    }

    public static String leerTelefono() {
        while (true) {
            System.out.println("================================================");
            System.out.print("Ingrese número de teléfono (10 dígitos): ");
            String input = sc.nextLine();
            if (input.matches("\\d{10}")) {
                return input;
            } else {
                System.out.println("Caracteres inválidos, intente de nuevo");
            }
        }
    }

    public static String leerDireccion() {
        System.out.println("===================================================");
        System.out.print("Ingrese dirección de entrega: ");
        return sc.nextLine();
    }

    public static boolean leerSiNo(String mensaje) {
        while (true) {
            System.out.print(mensaje + " (si/no): ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("si")) return true;
            if (input.equals("no")) return false;
            System.out.println("Caracteres inválidos, intente de nuevo");
        }
    }
}

public class PasteleriaLulu {
    private static String[] categorias = {"Tortas", "Galletas", "Postres", "Cupcakes", "Brownies"};
    private static String[][] variedades = {
        {"Chocolate", "Vainilla", "Zanahoria", "Red Velvet"},
        {"Chispas", "Avena", "Mantequilla", "Coco"},
        {"Tres Leches", "Flan", "Cheesecake", "Gelatina"},
        {"Chocolate", "Frambuesa", "Limón", "Oreo"},
        {"Clásico", "Nuez", "Chocolate Blanco", "Doble Chocolate"}
    };
    private static double[][] precios = {
        {250, 230, 240, 260},
        {50, 55, 52, 54},
        {70, 65, 80, 60},
        {35, 38, 36, 40},
        {45, 48, 50, 55}
    };

    private static int clientesAtendidos = 0;
    private static double totalDia = 0;

    public static void main(String[] args) {
        System.out.println("|======================================|");
        System.out.println("|   ¡Bienvenido a Pastelería Lulu's!   |");
        System.out.println("|======================================|");
        boolean otroCliente = true;
        while (otroCliente) {
            procesarCliente();
            otroCliente = Utilidades.leerSiNo("¿Hay otro cliente?");
        }
        System.out.println("\n================== RESUMEN DEL DIA ==================");
        System.out.println("Clientes atendidos hoy: " + clientesAtendidos);
        System.out.println("Total vendido hoy: $" + totalDia);
        System.out.println("=====================================================");
    }

    private static void procesarCliente() {
        System.out.println("===========================================");
        String nombreCliente = Utilidades.leerTexto("Ingrese el nombre del cliente: ", true);
        System.out.println("===========================================\n");
        Pedido pedido = new Pedido(nombreCliente);

        boolean agregarOtroProducto = true;
        while (agregarOtroProducto) {
            mostrarMenu();
            System.out.println("==============================");
            int categoria = Utilidades.leerEntero("Seleccione una categoría (1-5): ", 1, 5) - 1;
            System.out.println("==============================\n");
            mostrarVariedades(categoria);
            System.out.println("==============================");
            int variedad = Utilidades.leerEntero("Seleccione una variedad (1-4): ", 1, 4) - 1;
            System.out.println("==============================\n");
            System.out.println("==============================");
            int cantidad = Utilidades.leerEntero("Ingrese cantidad (máx 25): ", 1, 25);
            System.out.println("==============================\n");

            VariedadProducto prod = new VariedadProducto(
                categorias[categoria],
                variedades[categoria][variedad],
                precios[categoria][variedad]
            );
            pedido.agregarProducto(prod, cantidad);
            System.out.println("============================================");
            agregarOtroProducto = Utilidades.leerSiNo("¿Desea agregar otro producto?");
            System.out.println("============================================");
        }
        System.out.println("============================================");
        boolean paraLlevar = Utilidades.leerSiNo("¿El pedido es para llevar?");
        System.out.println("============================================\n");
        pedido.setParaLlevar(paraLlevar);
        if (paraLlevar) {
            String telefono = Utilidades.leerTelefono();
            String direccion = Utilidades.leerDireccion();
            pedido.setTelefono(telefono);
            pedido.setDireccion(direccion);
        }
        System.out.println("\n============================================");
        System.out.println("Resumen del pedido:");
        System.out.println("----------------------------------------------");
        pedido.mostrarPedido();

        totalDia += pedido.getTotal();
        clientesAtendidos++;
    }

    private static void mostrarMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println("Menú de la tienda:");
        System.out.println("----------------------------------------");
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }
    }

    private static void mostrarVariedades(int categoria) {
        System.out.println("----------------------------------------");
        System.out.println("\nVariedades de " + categorias[categoria] + ":");
        System.out.println("----------------------------------------");
        for (int i = 0; i < variedades[categoria].length; i++) {
            System.out.println((i + 1) + ". " + variedades[categoria][i] + " ($" + precios[categoria][i] + ")");
        }
    }
}