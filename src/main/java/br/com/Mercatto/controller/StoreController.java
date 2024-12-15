package br.com.Mercatto.controller;


import br.com.Mercatto.controller.interfaces.IStoreController;
import br.com.Mercatto.dto.message.MessageError;
import br.com.Mercatto.dto.request.StoreRequest;
import br.com.Mercatto.dto.response.StoreResponse;
import br.com.Mercatto.mapper.StoreMapper;
import br.com.Mercatto.service.impl.StoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/store")
public class StoreController implements IStoreController {

    private final StoreServiceImpl service;

    @PostMapping("/create")
    public ResponseEntity<StoreResponse> save(StoreRequest request){
        return ResponseEntity.ok().body(StoreMapper.toResponse(service.save(StoreMapper.toModel(request))));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StoreResponse> update(Long id, StoreRequest request) {
        return ResponseEntity.ok().body(StoreMapper.toResponse(service.update(id, StoreMapper.toModel(request))));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<StoreResponse>> findAll() {
        return ResponseEntity.ok().body(StoreMapper.storeResponseList(service.findAll()));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<StoreResponse> findById(Long id) {
        return ResponseEntity.ok().body(StoreMapper.toResponse(service.findById(id)));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<MessageError> delete(Long id) {
       service.delete(id);
       var messageError = new MessageError();
       messageError.setMessage("Loja deletada com sucesso!");
       messageError.setStatusCode(Integer.valueOf(200));
       return ResponseEntity.ok().body(messageError);
    }
}
