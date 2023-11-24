package ttpsentregable5.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttpsentregable5.DTO.GrupoCrearDTO;
import ttpsentregable5.Mapper.GrupoCrearMapper;
import ttpsentregable5.model.Categoria;
import ttpsentregable5.model.Grupo;
import ttpsentregable5.model.Usuario;
import ttpsentregable5.service.GrupoService;

@RestController
@RequestMapping(value = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoRestController {

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private GrupoCrearMapper grupoMapper;

	@GetMapping
	public ResponseEntity<List<Grupo>> listAllGrupos() {
		List<Grupo> grupos = grupoService.listarGrupos();
		if (grupos.isEmpty()) {
			return new ResponseEntity<List<Grupo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Grupo>>(grupos, HttpStatus.OK);
	}

	@PostMapping("/crearGrupo")
	public ResponseEntity<String> crearGrupo(@RequestBody GrupoCrearDTO grupodto) {
		try {
			Categoria cat = grupoMapper.nomCategoriaACategoria(grupodto.getCategoria());
			List<Usuario> usus = grupoMapper.agregarUsuario(grupodto.getNombreUsuario());
			Grupo grupo = new Grupo();
			this.grupoService.crear(null);
			return new ResponseEntity<>("Grupo Creado", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
