package uol.compass.partidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.partidos.dto.AssociadoDTO;
import uol.compass.partidos.dto.form.AssociadoFormDTO;
import uol.compass.partidos.service.AssociadoService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    private AssociadoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<AssociadoDTO> saveAssociado(@RequestBody @Valid AssociadoFormDTO body) {
        AssociadoDTO associado = this.service.save(body);
        return ResponseEntity.ok(associado);
    }

    @GetMapping
    public ResponseEntity<List<AssociadoDTO>> getAssociados() {
        List<AssociadoDTO> associados = this.service.getAssociados();
        return ResponseEntity.ok(associados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDTO> searchAssociado(@PathVariable Long id) {
        AssociadoDTO associado = this.service.searchAssociado(id);
        return ResponseEntity.ok(associado);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AssociadoDTO> updateAssociado(@PathVariable Long id,
                                                        @RequestBody @Valid AssociadoFormDTO body) {
        AssociadoDTO associado = this.service.updateAssociado(id, body);
        return ResponseEntity.ok(associado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<AssociadoDTO> deleteAssociado(@PathVariable Long id) {
        AssociadoDTO associado = this.service.deleteAssociado(id);
        return ResponseEntity.ok(associado);
    }
}
