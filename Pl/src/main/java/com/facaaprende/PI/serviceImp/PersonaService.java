package com.facaaprende.PI.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.facaaprende.PI.DTO.IPersonaBestDTO;
import com.facaaprende.PI.entity.NucleoTematico;
import com.facaaprende.PI.entity.Persona;
import com.facaaprende.PI.repository.INucleoTRepo;
import com.facaaprende.PI.repository.IPersonaRepo;
import com.facaaprende.PI.service.IPersonaService;


@Service
public class PersonaService implements IPersonaService, UserDetailsService{

	@Autowired
	private IPersonaRepo repo;
	
	@Autowired
	private INucleoTRepo repo2;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public Persona CrearPersona(Persona persona) {
		String clave = this.bcrypt.encode(persona.getClave());
		persona.setClave(clave);
		return repo.save(persona);
	}

	@Override
	public Persona Editarpersona(Persona persona) {
		Persona p = this.repo.findById(persona.getId()).orElse(null);
		if(p != null) {
			if (persona.getNombre() != null) {
				p.setNombre(persona.getApellido());	
			}
			if (persona.getApellido() != null) {
				p.setApellido(persona.getApellido());
			}
			
			if (persona.getCorreo() != null) {
				p.setClave(persona.getClave());
			}
			if (persona.getClave() != null) {
				p.setClave(persona.getClave());
			}
			if (persona.getUrl_foto() != null) {
				p.setUrl_foto(persona.getUrl_foto());
			}
		}
		
		return this.repo.save(p);
	}

	@Override
	public Persona EliminarPersona(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona PersonaXId(int id) {
		return this.repo.findById(id).orElse(null);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Persona p = this.repo.findOneByCorreo(username);
		if(p == null)
			throw new UsernameNotFoundException("Usuario no encontrado");
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(p.getRol().getRol()));
		
		UserDetails ud = new User(p.getCorreo(), p.getClave(), roles);
		
		return ud;
	}

	@Override
	public Persona PersonaXCorreo(String correo) {
		return this.repo.findOneByCorreo(correo);
	}

	@Override
	public Persona AgregarNucleoPersona(Integer id, List<NucleoTematico> nucleo) {
		Persona p = this.repo.findById(id).orElse(null);
		
		List<NucleoTematico> nu = new ArrayList<NucleoTematico>();
		
		if (p != null) {
			for (NucleoTematico nucleoTematico : nucleo) {
				NucleoTematico nt = this.repo2.findById(nucleoTematico.getId()).orElse(null);
				if(nt != null) {
					nu.add(nt);
				}
			}
		}
		
		for (NucleoTematico nucleoTematico2 : nu) {
			p.getNucleo().add(nucleoTematico2);
			nucleoTematico2.getPersona().add(p);
		}
		
		return this.repo.save(p);
	}

	@Override
	public Persona eliminarRelacion(Integer idP, Integer IdN) {
		Persona p = this.repo.findById(idP).orElse(null);
		NucleoTematico nt = this.repo2.findById(IdN).orElse(null);
		
		if(p != null && nt != null) {
			p.getNucleo().remove(nt);
			nt.getPersona().remove(p);
			/*
			 p.getArchivo().forEach(pe -> {
				if (pe.getNucleo_archivo() == nt) {
					p.getArchivo().remove(pe);
				}
			});
			 */
		}
		return this.repo.save(p);
	}

	@Override
	public List<IPersonaBestDTO> obetenrTodas() {
		return this.repo.listarMejores();
	}

	@Override
	public Persona editCalificacion(Integer id, Double total) {
		Persona p = this.repo.findById(id).orElse(null);
		
		if(p != null) {
			p.setTotal(total);
		}
		
		return this.repo.save(p);
	}

	@Override
	public Page<Persona> obtenerPersonasXRol(Integer rol, Integer pagina) {
		return this.repo.personasXRol(rol, PageRequest.of(pagina, 5, Sort.by("nombre").ascending()));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
