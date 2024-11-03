package br.com.Mercatto.service;

import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {

    public String cadastroCliente(String nome){
        return "Teste do cliente "+ nome;
    }

}
