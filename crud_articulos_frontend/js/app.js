// URL base de la API
const API_URL = "http://localhost:8080/api";

// Cuando se carga la página, mostramos el listado
document.addEventListener("DOMContentLoaded", listarArticulos);
document.addEventListener("DOMContentLoaded", listarPedidos);

// Manejador del formulario
document.getElementById("form-articulo").addEventListener("submit", guardarArticulo);

// Botón para cancelar edición
document.getElementById("cancelar").addEventListener("click", () => {
    // Limpiar todos los campos del formulario
    document.getElementById("form-articulo").reset();
    // Borrar el ID oculto del formulario
    document.getElementById("idArticulo").value = "";
});

// === Listar todos los artículos ===
function listarArticulos() {
    // Llamada GET a la API para obtener todos los artículos
    fetch(API_URL+"/articulos")
        .then(response => response.json()) // Convertimos la respuesta a JSON
        .then(data => {
            const tbody = document.getElementById("tabla-articulos"); // Obtenemos el cuerpo de la tabla
            tbody.innerHTML = ""; // Limpiar tabla antes de insertar nuevos datos
            data.forEach(articulo => {
                const fila = document.createElement("tr"); // Creamos una fila de tabla
                // Insertamos columnas con los datos del artículo y botones de acción
                fila.innerHTML = `
                    <td>${articulo.id}</td>
                    <td>${articulo.nombre}</td>
                    <td>${articulo.precio.toFixed(2)}</td>
                    <td>${articulo.cantidad}</td>
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="editarArticulo(${articulo.id})">Editar</button>
                        <button class="btn btn-danger btn-sm" onclick="eliminarArticulo(${articulo.id})">Eliminar</button>
                        <button class="btn btn-success btn-sm" onclick="agregarAlPedido(${articulo.id})">Agregar al pedido</button>
                    </td>
                `;
                tbody.appendChild(fila); // Agregamos la fila al cuerpo de la tabla
            });
        })
        .catch(error => console.error("Error al listar artículos:", error)); // Manejo de errores
}

// === Guardar o actualizar un artículo ===
function guardarArticulo(event) {
    event.preventDefault(); // Evitamos el comportamiento por defecto del formulario

    // Obtenemos los valores de los campos del formulario
    const id = document.getElementById("idArticulo").value;
    const nombre = document.getElementById("nombre").value.trim();
    const precio = parseFloat(document.getElementById("precio").value);
    const cantidad = parseInt(document.getElementById("cantidad").value);

    // Validación de campos
    if (!nombre || isNaN(precio) || precio < 0 || cantidad < 0) {
        alert("Por favor complete correctamente los campos.");
        return;
    }

    // Creamos un objeto artículo con los datos del formulario
    const articulo = { nombre, precio, cantidad };
    // Determinamos si es una edición (PUT) o creación (POST)
    const url = id ? `${API_URL+"/articulos"}/${id}` : API_URL+"/articulos";
    const metodo = id ? "PUT" : "POST";

    // Enviamos el artículo al backend usando fetch
    fetch(url, {
        method: metodo,
        headers: { "Content-Type": "application/json" }, // Indicamos que el cuerpo es JSON
        body: JSON.stringify(articulo) // Convertimos el objeto a JSON
    })
    .then(response => {
        if (!response.ok) throw new Error("Error al guardar"); // Verificamos respuesta exitosa
        return response.json();
    })
    .then(() => {
        // Limpiamos el formulario y recargamos la tabla
        document.getElementById("form-articulo").reset();
        document.getElementById("idArticulo").value = "";
        listarArticulos();
    })
    .catch(error => console.error("Error al guardar artículo:", error)); // Manejo de errores
}

// === Cargar artículo en el formulario para edición ===
function editarArticulo(id) {
    // Llamada GET para obtener los datos del artículo por su ID
    fetch(`${API_URL+"/articulos"}/${id}`)
        .then(response => response.json()) // Convertimos la respuesta a JSON
        .then(articulo => {
            // Cargamos los datos del artículo en el formulario
            document.getElementById("idArticulo").value = articulo.id;
            document.getElementById("nombre").value = articulo.nombre;
            document.getElementById("precio").value = articulo.precio;
            document.getElementById("cantidad").value = articulo.cantidad;
        })
        .catch(error => console.error("Error al obtener artículo:", error)); // Manejo de errores
}

// === Eliminar un artículo ===
function eliminarArticulo(id) {
    // Confirmación antes de eliminar
    if (confirm("¿Deseás eliminar este artículo?")) {
        // Llamada DELETE al backend
        fetch(`${API_URL+"/articulos"}/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (!response.ok) throw new Error("Error al eliminar"); // Verificamos que la respuesta sea exitosa
            listarArticulos(); // Actualizamos la lista de artículos
        })
        .catch(error => console.error("Error al eliminar artículo:", error)); // Manejo de errores
    }
}


// ------------------- PEDIDOS ---------------------------------- //

// === Listar todos los artículos ===
function listarPedidos() {
    // Llamada GET a la API para obtener todos los artículos
    fetch(API_URL+"/pedidos")
        .then(response => response.json()) // Convertimos la respuesta a JSON
        .then(data => {
            const tbody = document.getElementById("tabla-pedidos"); // Obtenemos el cuerpo de la tabla
            tbody.innerHTML = ""; // Limpiar tabla antes de insertar nuevos datos
            data.forEach(pedido => {
                const fila = document.createElement("tr"); // Creamos una fila de tabla
                // Insertamos columnas con los datos del artículo y botones de acción
                fila.innerHTML = `
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="seleccionarPedido(${pedido.idPedido})">Seleccionar Pedido</button>
                        <button class="btn btn-danger btn-sm" onclick="eliminarPedido(${pedido.idPedido})">Eliminar Pedido</button>
                    </td>
                    <td>${pedido.idPedido}</td>
                    <td>${pedido.idArticulo}</td>
                    <td>${pedido.cantidad}</td>
                `;
                tbody.appendChild(fila); // Agregamos la fila al cuerpo de la tabla
            });
        })
        .catch(error => console.error("Error al listar artículos:", error)); // Manejo de errores
}

// === Seleccionar pedido ===
function seleccionarPedido(idPedido) {
    const elem = document.getElementById("idPedido");
    elem.textContent = idPedido; 
}

// === Eliminar todo el pedido ===
function eliminarPedido(idPedido) {
    // Confirmación antes de eliminar
    if (confirm("¿Deseás eliminar todo el pedido?")) {
        // Llamada DELETE al backend
        fetch(`${API_URL+"/pedidos"}/${idPedido}`, {
            method: "DELETE"
        })
        .then(response => {
            if (!response.ok) throw new Error("Error al eliminar"); // Verificamos que la respuesta sea exitosa
            listarArticulos(); // Actualizamos la lista de artículos
            listarPedidos(); // Actualizamos la lista de pedidos
        })
        .catch(error => console.error("Error al eliminar pedido:", error)); // Manejo de errores
    }
}

// === Actualizar un pedido ===
function agregarAlPedido(idArticulo) {
    //event.preventDefault(); // Evitamos el comportamiento por defecto del formulario

    // Obtenemos los valores de los campos del formulario
    const idPedido = document.getElementById("idPedido").textContent;
    //const idArticulo = document.getElementById("idArticulo").value;

    // Creamos un objeto artículo con los datos del formulario
    const pedido = { idPedido, idArticulo };

    // Determinamos si es una edición (PUT) o creación (POST)
    const url = idPedido ? `${API_URL+"/pedidos"}/${idPedido}` : API_URL+"/pedidos";
    const metodo = idPedido ? "PUT" : "POST";

    // Enviamos el artículo al backend usando fetch
    fetch(url, {
        method: metodo,
        headers: { "Content-Type": "application/json" }, // Indicamos que el cuerpo es JSON
        body: JSON.stringify(pedido) // Convertimos el objeto a JSON
    })
    .then(response => {
        if (!response.ok) throw new Error("Error al guardar"); // Verificamos respuesta exitosa
        return response.json();
    })
    .then((data) => {
        // Limpiamos el formulario y recargamos la tabla
        const elem = document.getElementById("idPedido");
        elem.textContent = data.idPedido; 
        
        listarArticulos();
        listarPedidos();
    })
    .catch(error => console.error("Error al guardar pedido:", error)); // Manejo de errores
}

// Botón para nuevo pedido
document.getElementById("nuevo_pedido").addEventListener("click", () => {
    const elem = document.getElementById("idPedido");
    elem.textContent = ""; 
});