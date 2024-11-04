package br.com.Mercatto.restImpl;

import br.com.Mercatto.rest.UserRest;
import br.com.Mercatto.service.UserService;
import br.com.Mercatto.utils.MercattoUtils;
import br.com.Mercatto.controller.MecattoController;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return MercattoUtils.getResponseEntity(MecattoController.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            return userService.login(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return MercattoUtils.getResponseEntity(MecattoController.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
