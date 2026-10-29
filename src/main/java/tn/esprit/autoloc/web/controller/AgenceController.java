package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
@Tag(name = "Agences", description = "Gestion des agences de location")
public class AgenceController {

    private final IAgenceService agenceService;

    @Operation(summary = "Créer une agence", description = "Crée une agence à partir du corps JSON fourni.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Agence créée"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agence create(@RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    @Operation(summary = "Consulter une agence", description = "Retourne une agence par son identifiant.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence trouvée"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @GetMapping("/{id}")
    public Agence findById(@Parameter(description = "Identifiant de une agence") @PathVariable Long id) {
        return agenceService.findById(id);
    }

    @Operation(summary = "Lister les agences", description = "Retourne la liste de tous les agences.")
    @ApiResponse(responseCode = "200", description = "Liste retournée (éventuellement vide)")
    @GetMapping
    public List<Agence> findAll() {
        return agenceService.findAll();
    }

    @Operation(summary = "Modifier une agence", description = "Met à jour une agence existante.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence modifiée"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @PutMapping("/{id}")
    public Agence update(@Parameter(description = "Identifiant de une agence") @PathVariable Long id,
                         @RequestBody Agence agence) {
        return agenceService.update(id, agence);
    }

    @Operation(summary = "Supprimer une agence", description = "Supprime une agence par son identifiant.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Agence supprimée"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant de une agence") @PathVariable Long id) {
        agenceService.deleteById(id);
    }
}