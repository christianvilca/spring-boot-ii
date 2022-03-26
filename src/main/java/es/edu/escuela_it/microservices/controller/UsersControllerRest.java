package es.edu.escuela_it.microservices.controller;

import es.edu.escuela_it.microservices.model.AccountDTO;
import es.edu.escuela_it.microservices.model.UserDTO;
import es.edu.escuela_it.microservices.services.UserService;
import es.edu.escuela_it.microservices.validators.GroupValidatorOnCreate;
import es.edu.escuela_it.microservices.validators.GroupValidatorOnUpdate;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.Link;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Api(tags = "User API Rest")
public class UsersControllerRest {

    @Autowired
    //@Qualifier("BD")
    private UserService userService;

    @GetMapping("/{id}")
    @ApiOperation(notes="Retrieve one user system by id", value="Get user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Response ok if the operation was successful"),
            @ApiResponse(code = 404,message = "Response not found if the resource could not be found")
    })
    public ResponseEntity<UserDTO> getUserById(
            @ApiParam(
                    example = "1",
                    value = "Identifier for User",
                    allowableValues = "1,2,3,4",  // Esto no se hace solo para ver la flexibilidad de manipulacion
                    required = true)
            @PathVariable Integer id){ // Otra forma @PathVariable("id") Integer idUser
        System.out.println("Recovery user by id");

        UserDTO userDTO = userService.getUserById(id);

        // Devuelve informacion de donde encontrar este recurso
        Link withSelfRel = linkTo(methodOn(UsersControllerRest.class).getUserById(userDTO.getId())).withSelfRel();
        userDTO.add(withSelfRel);

        /*if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }*/

        // Spring traduce el objeto java a json
        // Jackson (libreria) esta trabajando para la serializacion y descerializacion
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<UserDTO>> listAllUsers( @RequestParam(required = false) String name,
                                       @RequestParam(required = false) String lastName,
                                       @RequestParam(required = false) Integer age) {

        List<UserDTO> list = List.of(new UserDTO(1, "Rafael"),
                new UserDTO(2, "Miguel"),
                new UserDTO(3, "Alvaro"));

        for (UserDTO userDTO : list) {
            Link withSelfRel = linkTo(methodOn(UsersControllerRest.class).getUserById(userDTO.getId())).withSelfRel();
            userDTO.add(withSelfRel);

            Link accountsRel = linkTo(methodOn(UsersControllerRest.class).getUserAccounts(userDTO.getId())).withRel("accounts");
            userDTO.add(accountsRel);
        }

        Link link = linkTo(methodOn(UsersControllerRest.class).listAllUsers("", "", 0)).withSelfRel();
        CollectionModel<UserDTO> result = CollectionModel.of(list, link);
        return ResponseEntity.ok(result);

        //list = list.stream().filter(u -> u.getName().contains(name)).collect(Collectors.toList());

        //return ResponseEntity.ok(list);
    }

    // @Valid -> Indica que valide y que tome las validaciones que estan indicadas en el UserDTO
    // sino que haga caso omiso
    @PostMapping
    public ResponseEntity<String> createUser(@Validated(value = GroupValidatorOnCreate.class) @RequestBody UserDTO userDTO){
        System.out.println("Recovery user by id " + userDTO.getName());

        // Recupera la ruta actual
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();

        return ResponseEntity.created(location).build() ;
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Validated(value = GroupValidatorOnUpdate.class) @RequestBody UserDTO userDTO) {
        System.out.println("Updating data");
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        System.out.println("Delete user by id");
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<AccountDTO>> getUserAccounts(@PathVariable Integer id){
        List<AccountDTO> list = List.of(new AccountDTO("Google"),
                                    new AccountDTO("Twitter"),
                                    new AccountDTO("EscuelaIT"));
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}/accounts/{idAccount}")
    public ResponseEntity<AccountDTO> getUserAccountById(@PathVariable Integer id, @PathVariable Integer idAccount){

        return ResponseEntity.ok(new AccountDTO("Google"));
    }
}
