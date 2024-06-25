import React, { useState, useEffect } from 'react';
import { getAllProfiles, deleteProfile, createUser } from './api';
import './App.css';

function App() {
  const [profiles, setProfiles] = useState([]); // Almacena los resgitros que llegan del backend
  const [loading, setLoading] = useState(true); // Decide si el loader se debe mostrar o no
  const [error, setError] = useState(null); // Almmacena el mensaje de error que pueda llegar desde el backend
  const [showCreate, setShowCreate] = useState(false); // Decide si el popup para la creación de un nuevo perfil debe mostrarse o no
  const [newUserData, setNewUserData] = useState({
    nombre: '',
    apellidos: '',
    curso: '',
    anoIngreso: '',
    notaMedia: '',
  }); // Almacena el nuevo usuario creado

  // Función que lee todos los perfiles existentes y los almacena en profiles
  const fetchData = async () => {
    try {
      setLoading(true);
      const data = await getAllProfiles();
      setProfiles(data.object);
      setLoading(false);
    } catch (error) {
      setError(error.message);
      setLoading(false);
    }
  };

  // Función que elimina el perfil con el id pasado como argumento
  const handleDelete = async (id) => {
    try {
      setLoading(true);
      await deleteProfile(id);
      const updatedProfiles = profiles.filter(profile => profile.id !== id);
      setProfiles(updatedProfiles);
      setLoading(false);
    } catch (error) {
      setError(error.message);
      setLoading(false);
    }
  };

  // Función que almacena en newUserData el usuario nuevo a crear
  const handleCreate = async () => {
    try {
      setLoading(true);
      await createUser(newUserData);
      setShowCreate(false);
      setNewUserData({
        nombre: '',
        apellidos: '',
        curso: 0,
        anoIngreso: 0,
        notaMedia: 0,
      });
      setLoading(false);
    } catch (error) {
      setError(error.message);
      setLoading(false);
    }
  };

  /* Al iniciar la aplicación deben mostrarse los perfiles existentes,
  para lo cual debemos traerlos de la base de datos */
  useEffect(() => {
    fetchData();
  }, []);

  // Cierra el popup que muestra los errores
  const closeErrorPopup = () => {
    setError(null);
  };

  // Abre el popup para crear un perfil
  const openCreatePopup = () => {
    setShowCreate(true);
  };

  // Cierra el popup para crear un perfil
  const closeCreatePopup = () => {
    setShowCreate(false);
  };

  /* Almacena en newUserData el valor de cada input del 
  formulario de creación de un nuevo perfil en el 
  campo correspondiente de newUserData */
  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewUserData(prevData => ({
      ...prevData,
      [name]: value,
    }));
  };

  // Código HTML
  return (
    <div className="app-container">
      {/*LOADER*/}
      {loading && <p>Cargando...</p>}
      {/*SI NO HAY PERFILES*/}
      {profiles.length === 0 && (
        <div className="noProfiles">
          <p className="warning">No hay registros</p>
          <button className="create-button" onClick={openCreatePopup}>Crear Usuario</button>
        </div>
      )}
      {/*SI HAY PERFILES*/}
      {profiles.length > 0 && (
        <>
          <div className="yesProfile">
            <button className="create-button" onClick={openCreatePopup}>Crear Usuario</button>
          </div>
          {/*LISTA DE PERFILES*/}
          {profiles.map(profile => (
            <div key={profile.id} className="profile-card">
              <div className="data">
                <p className="profile-name">{profile.nombre} {profile.apellidos}</p>
                <p>Curso: {profile.curso}</p>
                <p>Año de Ingreso: {profile.anoIngreso}</p>
                <p>Nota Media: {profile.notaMedia}</p>
              </div>
              <button className="delete-button" onClick={() => handleDelete(profile.id)}>Eliminar</button>
            </div>
          ))}
        </>
      )}


      {/* POPUP PARA CREAR USUARIOS */}
      {showCreate && (
        <div className="bg-popup">
          <div className="create-popup">
            <div className="create-popup-content">
              <h2 style={{ textAlign: 'center' }}>Crear Usuario</h2>
              <form onSubmit={handleCreate}>
                <div className="formData">
                  <div>
                    <div className="campo">
                      <label>Nombre:</label>
                      <input type="text" name="nombre" value={newUserData.nombre} onChange={handleChange} required />
                    </div>

                    <div className="campo">
                      <label>Apellidos:</label>
                      <input type="text" name="apellidos" value={newUserData.apellidos} onChange={handleChange} required />
                    </div>
                  </div>

                  <div>
                    <div className="campo">
                      <label>Curso:</label>
                      <input type="number" name="curso" value={newUserData.curso} onChange={handleChange} required />
                    </div>

                    <div className="campo">
                      <label>Año de Ingreso:</label>
                      <input type="nunmber" name="anoIngreso" value={newUserData.anoIngreso} onChange={handleChange} required />
                    </div>

                    <div className="campo">
                      <label>Nota Media:</label>
                      <input type="number" name="notaMedia" value={newUserData.notaMedia} onChange={handleChange} required />
                    </div>
                  </div>

                </div>


                <div className="buttons-form">
                  <button type="submit" className="create-button">Crear</button>
                  <button type="button" className="delete-button" onClick={closeCreatePopup}>Cancelar</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      )}

      {/* POPUP DE ERROR */}
      {error && (
        <div className="popUp">
          <div className="popUp-content">
            <span className="close" onClick={closeErrorPopup}>&times;</span>
            <p className="error-message">{error}</p>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
