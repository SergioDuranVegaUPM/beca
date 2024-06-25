// URL para conectar con la API
const API_URL = "http://localhost:8092/api";

/* 
Función para obtener todos los perfiles del backend
Devuelve el json con los campos 
object (la lista de perfiles) y mensaje
o, en caso de error, el mensaje de error
*/
export const getAllProfiles = async () => {
    try {
        const response = await fetch(`${API_URL}/showAll`);
        return response.json();
    } catch (error) {
        console.error(error.message);
        throw error;
    }
};

/* 
Función para eliminar un perfil por ID
Devuelve true si tuvo éxito
o, en caso de error, el mensaje de error
*/
export const deleteProfile = async (id) => {
    try {
        await fetch(`${API_URL}/delete/${id}`, {
            method: 'DELETE',
        });
        return true;
    } catch (error) {
        console.error(error.message);
        throw error;
    }
};

/* 
Función para crear un nuevo usuario pasado como argumento
Devuelve el json con los campos 
object (el usuario creado) y mensaje.
o, en caso de error, el mensaje de error
*/
export const createUser = async (usuarioDto) => {
    try {
        const response = await fetch(`${API_URL}/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(usuarioDto),
        });
        return response.json();
    } catch (error) {
        console.error('Error al crear el usuario:', error.message);
        throw error;
    }
};