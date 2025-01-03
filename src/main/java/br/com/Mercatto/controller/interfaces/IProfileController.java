package br.com.Mercatto.controller.interfaces;

import br.com.Mercatto.dto.message.MessageError;
import br.com.Mercatto.dto.request.ProfileRequest;
import br.com.Mercatto.dto.response.ProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Perfil")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/profile")
@Validated
public interface IProfileController {
    @Operation(summary = "Salva um novo perfil.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Caso o perfil seja armazenado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "profileId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "profile": "ADMIN"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Caso o perfil não tenha a permissão necessária para realizar a operação.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "timestamp": "15-12-2024 17:38:25",
                                                "status": 403,
                                                "type": "http://localhost:8080/profile/create",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso a perfil já esteja armazenado no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "15-12-2024 12:41:33",
                                                 "status": 400,
                                                 "type": "http://localhost:8080/profile/create",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Perfil já está armazenado no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "http://localhost:8080/profile/create",
                                                 "message": "The Token has expired on Sunday December 15 17:39:42 BRT 2024.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = """
                    Dados para salvar um novo perfil.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                            {
                                              "profile": "ADMIN"
                                            }
                                    """
                    )
            )
    )
    @PostMapping("/create")
    ResponseEntity<ProfileResponse> save(@RequestBody ProfileRequest request);

    @Operation(summary = "Atualiza o perfil por ID.", description = """
            Atualiza o perfil passando o ID na URL.
            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a perfil seja alterado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "profileId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "profile": "ADMIN"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum perfil.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "15-12-2024 11:56:30",
                                                 "status": 404,
                                                 "type": "http://localhost:8080/profile/update/{id}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Perfil não está armazenado no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso a perfil já esteja armazenado no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "15-12-2024 12:42:53",
                                                 "status": 400,
                                                 "type": "http://localhost:8080/profile/update/{id}",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Já existe um perfil armazenado com esse código."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Caso o perfil não tenha a permissão necessária para realizar a operação.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "timestamp": "15-12-2024 17:38:25",
                                                "status": 403,
                                                "type": "http://localhost:8080/profile/update/{id}",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "http://localhost:8080/profile/update/{id}",
                                                 "message": "The Token has expired on Sunday December 15 17:39:42 BRT 2024.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = """
                    Dados para atualizar um perfil.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                            {
                                              "profile": "ADMIN"
                                            }
                                    """
                    )
            )
    )
    @PutMapping("/update/{id}")
    ResponseEntity<ProfileResponse> update(@PathVariable Long id, @RequestBody ProfileRequest request);


    @Operation(summary = "Consulta todos os perfis.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "profileId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "profile": "ADMIN"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Caso o usuário não tenha a permissão necessária para realizar a operação.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "timestamp": "15-12-2024 17:38:25",
                                                "status": 403,
                                                "type": "http://localhost:8080/profile/findAll",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "http://localhost:8080/profile/findAll",
                                                 "message": "The Token has expired on Sunday December 15 17:39:42 BRT 2024.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/findAll")
    ResponseEntity<List<ProfileResponse>> findAll();


    @Operation(summary = "Consulta o perfil por ID.", description = """
            Consulta o perfil passando o ID na URL.
            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "profileId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "profile": "ADMIN"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum perfil.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "",
                                                 "status": 404,
                                                 "type": "http://localhost:8080/profile/findById/{id}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Perfil não está armazenado no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Caso o usuário não tenha a permissão necessária para realizar a operação.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "timestamp": "15-12-2024 17:38:25",
                                                "status": 403,
                                                "type": "http://localhost:8080/profile/findById/{id}",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "http://localhost:8080/profile/findById/{uuid}",
                                                 "message": "The Token has expired on Mon May 23 17:39:42 BRT 2022.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })

    @GetMapping("/findById/{id}")
    ResponseEntity<ProfileResponse> findById(@PathVariable Long id);

    @Operation(summary = "Deleta um usuário por ID.", description = """
            Deleto o usuário passando o ID na URL.

            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso o perfil seja deletado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "message": "Perfil deletado com sucesso."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhum perfil.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "15-12-2024 11:56:30",
                                                 "status": 404,
                                                 "type": "http://localhost:8080/profile/delete/{id}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "usuário não armazenado no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Caso o usuário não tenha a permissão necessária para realizar a operação.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "timestamp": "15-12-2024 17:38:25",
                                                "status": 403,
                                                "type": "http://localhost:8080/profile/delete/{uuid}",
                                                "title": "Não autorizado.",
                                                "detail": "Usuário não tem permissão para acessar esse recurso!"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Caso o usuário não esteja autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "path": "http://localhost:8080/profile/delete/{id}",
                                                 "message": "The Token has expired on Sunday December 15 17:39:42 BRT 2024.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping("/delete/{id}")
    ResponseEntity<MessageError> delete(@PathVariable Long id);
}
