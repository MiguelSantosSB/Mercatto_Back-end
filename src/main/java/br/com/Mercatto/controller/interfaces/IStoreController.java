package br.com.Mercatto.controller.interfaces;

import br.com.Mercatto.dto.message.MessageError;
import br.com.Mercatto.dto.request.StoreRequest;
import br.com.Mercatto.dto.response.StoreResponse;
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

@Tag(name = "Lojas")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/store")
@Validated
public interface IStoreController {

    @Operation(summary = "Salvar uma nova loja.")
    @ApiResponses(value = {
           @ApiResponse(
                   responseCode = "201",
                   description = "Caso a loja seja armazenado com sucesso.",
                   content = @Content(
                           mediaType = "application/jason",
                           examples = @ExampleObject(
                                   value = """
                                           {
                                             "storeId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                             "name": "Loja Teste",
                                             "address": 10.00,
                                             "telephone": "11 23333-4444",
                                             "cnpj": "16546056/0001"
                                           }
                                           """
                           )
                )

        ),
        @ApiResponse(
                responseCode = "403",
                description = "Caso o usuário não tenha a permissão necessária para realizar a operação",
                content = @Content(
                        mediaType = "application/jason",
                        examples = @ExampleObject(
                                value = """
                                        {
                                            "timestamp": "15-12-2024 10:32:25",
                                            "status": 403,
                                            "type": "http://localhost:8080/store/create",
                                            "tilte": "Não autorizado.",
                                            "detail": "Usuário não tem permissão para acessar esse recurso"
                                        }
                                        """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Caso a loja já esteja armazenada no sistema.",
                content = @Content(
                        mediaType = "application/jason",
                        examples = @ExampleObject(
                                value = """
                                        {
                                            "timestamp": "15-12-2024 15:42:35",
                                            "status": 400,
                                            "type": "http://localhost:8080/store/create",
                                            "tilte": "Dado único já cadastrado.",
                                            "detail": "Loja já está armazenada no sistema."
                                        }
                                        """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "401",
                description = "Caso o usuário não esteja autenticado",
                content = @Content(
                        mediaType = "application/jason",
                        examples = @ExampleObject(
                                value = """
                                        {
                                            "path": "http://localhost:8080/store/create",
                                            "message": "The Token has expired on Sunday December 15 20:42:47 BRT 2024.",
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
                    Dados para salvar em nova loja.
                    """,
            content = @Content(
                    mediaType = "application/jason",
                    examples = @ExampleObject(
                            value = """
                                            {
                                                "name": "Loja test",
                                                "address": "rua de test",
                                                "telephone": "11 23333-4444",
                                                "cnpj": "16546056/0001"
                                            }
                                    """
                    )
            )
    )
    @PostMapping("/create")
    ResponseEntity<StoreResponse> save(@RequestBody StoreRequest request);


    @Operation(summary = "Atualiza a loja por ID.", description = """
            Atualiza a loja passando o ID na URL.
            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a loja seja alterada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "storeId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "name": "Loja Teste",
                                              "address": 10.00,,
                                              "telephone": "11 23333-4444",
                                              "cnpj": "16546056/0001"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma loja.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "15-12-2024 11:56:30",
                                                 "status": 404,
                                                 "type": "http://localhost:8080/store/update/{id}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Loja não está armazenada no sistema."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Caso a loja já esteja armazenada no sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "15-12-2024 12:42:53",
                                                 "status": 400,
                                                 "type": "http://localhost:8080/store/update/{id}",",
                                                 "title": "Dado único já cadastrado.",
                                                 "detail": "Já existe uma loja armazenada com esse código."
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
                                                "type": "http://localhost:8080/store/update/{id}",",
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
                                                 "path": "http://localhost:8080/store/update/{id}",",
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
                    Dados para atualizar uma loja.
                    """,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                                            {
                                              "name": "Loja Teste",
                                              "address": 10.00,
                                              "telephone": "11 23333-4444",
                                              "cnpj": "16546056/0001"
                                            }
                                    """
                    )
            )
    )
    @PutMapping("/update/{id}")
    ResponseEntity<StoreResponse> update(@PathVariable Long id, @RequestBody StoreRequest request);


    @Operation(summary = "Consulta todas as lojas.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a consulta seja efetuada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "storeId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "name": "Loja Teste",
                                              "address": 10.00,
                                              "telephone": "11 23333-4444",
                                              "cnpj": "16546056/0001"
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
                                                "type": "http://localhost:8080/store/findAll",
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
                                                 "path": "http://localhost:8080/store/findAll",
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
    ResponseEntity<List<StoreResponse>> findAll();


    @Operation(summary = "Consulta a loja por ID.", description = """
            Consulta a loja passando o ID na URL.
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
                                              "storeId": "a47e4fbe-7a27-4fb3-a0e4-16c10fc31d8a",
                                              "name": "Loja Teste",
                                              "address": 10.00,
                                              "telephone": "11 23333-4444",
                                              "cnpj": "16546056/0001"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma loja.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "",
                                                 "status": 404,
                                                 "type": "http://localhost:8080/store/findById/{id}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Loja não está armazenada no sistema."
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
                                                "type": "http://localhost:8080/store/findById/{id}",
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
                                                 "path": "http://localhost:8080/store/findById/{id}",
                                                 "message": "The Token has expired on Sunday December 15 17:39:42 BRT 2024.",
                                                 "error": "Unauthorized",
                                                 "status": 401
                                            }
                                            """
                            )
                    )
            )
    })

    @GetMapping("/findById/{id}")
    ResponseEntity<StoreResponse> findById(@PathVariable Long id);


    @Operation(summary = "Deleta uma loja por ID.", description = """
            Deleta a loja passando o ID na URL.

            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Caso a loja seja deletada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "message": "Loja deletada com sucesso."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caso o ID enviado na URL não seja de nenhuma loja.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                 "timestamp": "15-12-2024 11:56:30",
                                                 "status": 404,
                                                 "type": "http://localhost:8080/store/delete/{id}",
                                                 "title": "Recurso não encontrado.",
                                                 "detail": "Loja não armazenada no sistema."
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
                                                "type": "http://localhost:8080/store/delete/{id}",
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
                                                 "path": "http://localhost:8080/store/delete/{id}",
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