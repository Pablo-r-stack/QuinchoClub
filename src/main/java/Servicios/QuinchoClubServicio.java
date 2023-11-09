/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author lauta
 */
public class QuinchoClubServicio implements UserDetailsService {
 
    
  @Autowired
  private UsuarioRepositorio ur;
  
  @Transactional
  public void crearUsuario(String nombre, String apellido, String email, String password, String password2, String dni, String edad, String telefono) throws MiException {
        validar(nombre, apellido, email, password, password2, dni, edad, telefono);
        if(ur.buscarPorEmail(email) != null){
            throw new MiException("Ese email ya se encuentra registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email); 
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setDni(dni);
        usuario.setEdad(edad);
        usuario.setTelefono(telefono);
        usuario.setFechaAlta(new Date());
        usuario.setRoles(Roles.USUARIO);
        ur.save(usuario);
    }
  
  public void actualizar(Integer idUsuario, String nombre, String apellido, String email, String password, String password2, String dni, String edad, String telefono) throws Exception {

        Optional<Usuario> respuesta = ur.findById(idUsuario);
        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email); 
            usuario.setPassword(new BCryptPasswordEncoder().encode(password));
            usuario.setDni(dni);
            usuario.setEdad(edad);
            usuario.setTelefono(telefono);
            usuario.setRole(usuario.getRole());
            ur.save(usuario);
        }
    }
  public List<Usuario> listarUsuarios() {
    
    List<Usuario> usuario = new ArrayList();
    
    usuario = ur.findAll();
    
    return usuario;
  }
    
  private void validar(String nombre, String apellido, String email, String password, String password2, String dni, String edad, String telefono) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede ser nulo");
        }
        if (apellido.isEmpty() || apellido == null) {
            throw new MiException("El apellido no puede ser nulo");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("El email no puede ser nulo");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacía, y debe tener mas de 5 dígitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
        if (dni.isEmpty() || dni == null) {
            throw new MiException("El dni no puede ser nulo");
        }
        if (edad.isEmpty() || edad == null) {
            throw new MiException("La edad no puede ser nulo");
        }
        if (telefono.isEmpty() || telefono == null) {
            throw new MiException("El telefono no puede ser nulo");
        }

    }
   @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {  //autorizacin de seguridad para el usuario
        
        Usuario usuario = ur.buscarPorEmail(email);
        if (usuario != null){
           List<GrantedAuthority> permisos = new ArrayList<>();
           
           GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRole().toString());
           
           permisos.add(p);
           
           ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                   
           HttpSession session = attr.getRequest().getSession(true);
                   
           session.setAttribute("usuariosession", usuario);
           
           return new User(usuario.getEmail(), usuario.getPassword(), permisos);
                              
        }else{
                throw new UsernameNotFoundException("Usuario invalido");
        }
    }
}
