import React, { useState, useEffect } from 'react';
import { getAllProfiles, deleteProfile, createUser } from './api';
import './App.css'; // Archivo CSS para los estilos

function App() {
  const [profiles, setProfiles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [showCreate, setShowCreate] = useState(false);
  const [newUserData, setNewUserData] = useState({
    nombre: '',
    apellidos: '',
    curso: '',
    anoIngreso: '',
    notaMedia: '',
  });

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

  useEffect(() => {
    fetchData();
  }, []);

  const closeErrorPopup = () => {
    setError(null);
  };

  const openCreatePopup = () => {
    setShowCreate(true);
  };

  const closeCreatePopup = () => {
    setShowCreate(false);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewUserData(prevData => ({
      ...prevData,
      [name]: value,
    }));
  };

  return (
    <div className="app-container">
      {loading && <p>Cargando...</p>}
      {profiles.length === 0 && (
        <div className="noProfiles">
          <p className="warning">No hay registros</p>
          <button className="create-button" onClick={openCreatePopup}>Crear Usuario</button>
        </div>
      )}
      {profiles.length > 0 && (
        <>
          <div className="yesProfile">
            <button className="create-button" onClick={openCreatePopup}>Crear Usuario</button>
          </div>
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


      {/* Popup para crear usuario */}
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

      {/* Popup de error */}
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
