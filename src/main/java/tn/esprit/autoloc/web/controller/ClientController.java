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
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    private final IClientService clientService;

    @Operation(summary = "Créer un client", description = "Crée un client à partir du corps JSON fourni.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        return clientService.create(client);
    }

    @Operation(summary = "Consulter un client", description = "Retourne un client par son identifiant.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouvé"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @GetMapping("/{id}")
    public Client findById(@Parameter(description = "Identifiant de un client") @PathVariable Long id) {
        return clientService.findById(id);
    }

    @Operation(summary = "Lister les clients", description = "Retourne la liste de tous les clients.")
    @ApiResponse(responseCode = "200", description = "Liste retournée (éventuellement vide)")
    @GetMapping
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @Operation(summary = "Modifier un client", description = "Met à jour un client existant.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client modifié"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @PutMapping("/{id}")
    public Client update(@Parameter(description = "Identifiant de un client") @PathVariable Long id,
                         @RequestBody Client client) {
        return clientService.update(id, client);
    }

    @Operation(summary = "Supprimer un client", description = "Supprime un client par son identifiant.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprimé"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant de un client") @PathVariable Long id) {
        clientService.deleteById(id);
    }
}