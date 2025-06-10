1. Nombre del proyecto: Pastelería Lulu’s

2. Integrantes:

Berardo José Márquez Velázquez – 192479 ROL: Diseño – Arrays
Paula Andrea Echavez Vargas – 192587 ROL: Diseño – Entrada y Salida de Datos
Keiner Josué Barbosa Calderón – 192502 ROL: Diseño – Lógica
María Laura Contreras Trillos – 192521 ROL: Diseño – Métodos

3. Descripción del problema y solución propuesta.

El emprendimiento Pastelería lulu's surgió ante la necesidad de organizar eficientemente los
pedidos personalizados de una pastelería artesanal, así como facilitar la gestión de productos,
clientes y entregas. 
Actualmente, el proceso de pedidos es manual y genera errores frecuentes, pérdida de información
o retrasos, ademas generaba confusiones para saber que persona habia pedido cierto producto generando entregas incorrectas.

Solución: se desarrolló un programa que permite:

- Registrar productos.
- Tomar y especificar el pedido del cliente.
- Hacer las entregas en la ubicación que ingreso el cliente.
- Llevar un control constante de inventario.
- Dar un informe total de ventas que se llevan al dia para una mejor organización.

4. Explicación de clases y métodos principales.

| Clase              | Rol                                                        |
| ------------------ | ---------------------------------------------------------- |
| `Producto`         | Representa un producto general de la pastelería            |
| `VariedadProducto` | Representa una versión específica de ese producto          |
| `Pedido`           | Administra los productos y datos de un pedido específico   |
| `Utilidades`       | Contiene métodos para **leer y validar** datos del usuario |
| `PasteleriaLulu`   | Ejecuta el programa y gestiona el flujo principal          |

---
## 🧁 Clase `Producto`

### Propósito: Representa un producto con nombre, precio y cantidad máxima.

### Atributos:

* `nombre`: nombre del producto (ej. "Tortas").
* `precio`: precio base del producto.
* `cantidadMaxima`: número máximo permitido (fijo en 25).

### Métodos:

* **Constructor** `Producto(String nombre, double precio)`: inicializa nombre y precio.
* `getNombre()`: retorna el nombre del producto.
* `getPrecio()`: retorna el precio del producto.

---

## 🍪 Clase `VariedadProducto` (Extiende `Producto`)

### Propósito: Representa una **variedad específica** de un producto (ej. "Torta de chocolate").

### Atributo adicional:

* `variedad`: especificación de tipo (ej. "Chocolate", "Vainilla").

### Métodos:

* **Constructor** `VariedadProducto(String nombre, String variedad, double precio)`: Constructor que usa `super()` para heredar atributos de `Producto`.
* `getVariedad()`: retorna la variedad del producto.

---

## 📦 Clase `Pedido`

### Propósito: Manejar un pedido realizado por un cliente.

### Atributos:

* `nombreCliente`: nombre del cliente.
* `productos`: lista de productos pedidos (tipo `VariedadProducto`).
* `cantidades`: lista de cantidades correspondientes a cada producto.
* `paraLlevar`: si el pedido es para llevar o no.
* `telefono`, `direccion`: si es para llevar, se guarda la info.
* `total`: acumulador del costo total del pedido.

### Métodos:

* **Constructor** `Pedido(String nombreCliente)`: recibe el nombre del cliente.
* `agregarProducto()`: agrega un producto al pedido con su cantidad, y actualiza el total.
* `setParaLlevar()`, `setTelefono()`, `setDireccion()`: setters.
* `getTotal()`: retorna el total del pedido.
* `getNombreCliente()`: retorna el nombre del cliente.
* `mostrarPedido()`: imprime el detalle del pedido (productos, totales, info de entrega).

---

## 🛠 Clase `Utilidades`

### Propósito: Proporciona funciones auxiliares para leer datos del usuario desde consola.

### Atributo:

* `sc`: un único `Scanner` para entrada por teclado.

### Métodos:

* `leerEntero(mensaje, min, max)`: pide un número entero entre `min` y `max`, con validación.
* `leerTexto(mensaje, soloLetras)`: pide texto, y si `soloLetras` es true, valida que no tenga números o símbolos.
* `leerTelefono()`: pide un teléfono de 10 dígitos.
* `leerDireccion()`: solicita la dirección de entrega.
* `leerSiNo(mensaje)`: pregunta sí o no y retorna boolean (`true` para "si").

---

## 🎂 Clase `PasteleriaLulu` (Contiene `main`)

### Propósito: Punto de entrada del programa. Maneja el menú y el flujo general de pedidos.

### Atributos estáticos:

* `categorias`: tipos de productos (Tortas, Galletas...).
* `variedades`: subtipos por categoría.
* `precios`: precios por subcategoría.
* `clientesAtendidos`: contador de clientes atendidos.
* `totalDia`: acumulador de ventas del día.

### Métodos:

#### `main()`

* Muestra mensaje de bienvenida.
* Repite el proceso de atención a clientes hasta que no haya más.
* Al final muestra un resumen del día.

#### `procesarCliente()`

* Pide el nombre del cliente.
* Permite agregar varios productos.
* Decide si es para llevar (y pide dirección/teléfono).
* Muestra el resumen del pedido.
* Suma al total del día y cuenta al cliente.

#### `mostrarMenu()`

* Muestra las categorías de productos.

#### `mostrarVariedades(int categoria)`

* Muestra las variedades y precios disponibles según la categoría elegida.

---
| Método                                  | Función principal                              |
| --------------------------------------- | ---------------------------------------------- |
| `agregarProducto()`                     | Agrega productos al pedido                     |
| `mostrarPedido()`                       | Muestra el resumen del pedido del cliente      |
| `leerEntero()`, `leerTexto()`           | Validan entradas del usuario                   |
| `leerTelefono()`                        | Valida número con 10 dígitos                   |
| `leerSiNo()`                            | Respuestas tipo "sí o no"                      |
| `mostrarMenu()` / `mostrarVariedades()` | Presentan menú visual al cliente               |
| `procesarCliente()`                     | Maneja todo el flujo de atención de un cliente |
---

5. Intruciones básicas para ejecutar el código.

---

## 🖥️ Opción 1: **Desde una extencion de visual studio code**

### ✅ Importante:

* Tener instalado la ultima version de **Extension pack for java**.
* Tener el archivo guardado como `PasteleriaLulu.java`.

### 📌 Pasos:

1. **Abre GitBash** para ejecutar los comandos.

2. **Navega a la carpeta** donde guardaste el archivo:

   ```bash
   cd ruta/del/archivo
   ```

   (ejemplo: `cd C:\Users\TuUsuario\Documentos\Java`)

3. **Compila el código**:

   ```bash
   javac PasteleriaLulu.java
   ```

   Esto generará un archivo `PasteleriaLulu.class`.

4. **Ejecuta el programa**:

   ```bash
   java PasteleriaLulu
   ```

---

## 💻 Opción 2: **Usando un entorno de desarrollo (IDE)** como por ejemplo IntelliJ IDEA, Eclipse o NetBeans

### 📌 Pasos generales:

1. **Abre tu IDE favorito**. En nuestro caso visual studio code 
2. **Crea un nuevo proyecto Java**.
3. **Agrega una nueva clase** llamada `PasteleriaLulu`.
4. **Pega todo tu código** (incluyendo todas las clases) dentro del archivo.
5. **Ejecuta el programa** haciendo clic en el botón de "Run" (▶️).

---

## 📂 Archivos sugeridos

Tu archivo principal debe llamarse:

```
PasteleriaLulu.java
```

Y puedes tener todas las clases en este mismo archivo porque ninguna es pública excepto la clase principal.
---