const API_URL = "http://localhost:8080/api";

// Función para registrar usuarios
async function registrarUsuario(usuario) {
    const respuesta = await fetch(`${API_URL}/usuarios/registrar`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuario)
    });
    return respuesta;
}

// Función para obtener películas
async function obtenerPeliculas() {
    const respuesta = await fetch(`${API_URL}/peliculas`);
    return await respuesta.json();
}