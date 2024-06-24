const BASE_URL = "http://localhost:8092/api";

// Función para obtener todos los perfiles
export const getAllProfiles = async () => {
    try {
        const response = await fetch(`${BASE_URL}/showAll`);
        return response.json();
    } catch (error) {
        console.error(error.message);
        throw error;
    }
};

// Función para eliminar un perfil por ID
export const deleteProfile = async (id) => {
    try {
        await fetch(`${BASE_URL}/delete/${id}`, {
            method: 'DELETE',
        });
        return true; // Retorna verdadero si la eliminación fue exitosa
    } catch (error) {
        console.error(error.message);
        throw error;
    }
};

export const createUser = async (usuarioDto) => {
    try {
        const response = await fetch(`${BASE_URL}/create`, {
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