package uol.compass.partidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.partidos.dto.PartidoComAssociadosDTO;
import uol.compass.partidos.dto.PartidoDTO;
import uol.compass.partidos.dto.form.PartidoFormDTO;
import uol.compass.partidos.service.PartidoService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    private PartidoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<PartidoDTO> savePartido(@RequestBody @Valid PartidoFormDTO body) {
        PartidoDTO partido = this.service.save(body);
        return new ResponseEntity<>(partido, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PartidoDTO>> getPartidos(@RequestParam(name = "ideologia", required = false) String ideologia) {
        List<PartidoDTO> partidos = this.service.getPartidos(ideologia);
        return ResponseEntity.ok(partidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidoDTO> searchPartido(@PathVariable Long id) {
        PartidoDTO partido = this.service.searchPartido(id);
        return ResponseEntity.ok(partido);
    }

    @GetMapping("/{id}/associados")
    public ResponseEntity<PartidoComAssociadosDTO> listFiliados(@PathVariable Long id) {
        PartidoComAssociadosDTO partido = this.service.getAssociadosPartido(id);
        return ResponseEntity.ok(partido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartidoDTO> updatePartido(@PathVariable Long id,
                                                    @RequestBody @Valid PartidoFormDTO body) {
        PartidoDTO partido = this.service.updatePartido(id, body);
        return ResponseEntity.ok(partido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PartidoDTO> deletePartido(@PathVariable Long id) {
        PartidoDTO partido = this.service.deletePartido(id);
        return ResponseEntity.ok(partido);
    }
}
