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
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
@Tag(name = "Véhicules", description = "Gestion du parc de véhicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @Operation(summary = "Créer un vehicule", description = "Crée un vehicule à partir du corps JSON fourni.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vehicule créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    @Operation(summary = "Consulter un vehicule", description = "Retourne un vehicule par son identifiant.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule trouvé"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    @GetMapping("/{id}")
    public Vehicule findById(@Parameter(description = "Identifiant de un vehicule") @PathVariable Long id) {
        return vehiculeService.findById(id);
    }

    @Operation(summary = "Lister les vehicules", description = "Retourne la liste de tous les vehicules.")
    @ApiResponse(responseCode = "200", description = "Liste retournée (éventuellement vide)")
    @GetMapping
    public List<Vehicule> findAll() {
        return vehiculeService.findAll();
    }

    @Operation(summary = "Modifier un vehicule", description = "Met à jour un vehicule existant.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule modifié"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    @PutMapping("/{id}")
    public Vehicule update(@Parameter(description = "Identifiant de un vehicule") @PathVariable Long id,
                           @RequestBody Vehicule vehicule) {
        return vehiculeService.update(id, vehicule);
    }

    @Operation(summary = "Supprimer un vehicule", description = "Supprime un vehicule par son identifiant.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vehicule supprimé"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant de un vehicule") @PathVariable Long id) {
        vehiculeService.deleteById(id);
    }
}