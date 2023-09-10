package pe.edu.upc.aaw.demo01.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.aaw.demo01.dtos.InteractionDTO;
import pe.edu.upc.aaw.demo01.dtos.PublicationDTO;
import pe.edu.upc.aaw.demo01.entities.Interaction;
import pe.edu.upc.aaw.demo01.entities.Publication;
import pe.edu.upc.aaw.demo01.serviceimplements.InteractionServiceImplement;
import pe.edu.upc.aaw.demo01.servicesinterfaces.IInteractionService;
import pe.edu.upc.aaw.demo01.servicesinterfaces.IPublicationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/interaction")
public class InteractionController {

    @Autowired
    private IInteractionService iS;

    @PostMapping
    public void registrar(@RequestBody InteractionDTO dto){
        ModelMapper m=new ModelMapper();
        Interaction t=m.map(dto, Interaction.class);
        iS.insert(t);
    }


    @GetMapping
    public List<InteractionDTO> listar(){
        return iS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,InteractionDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){
        iS.delete(id);
    }


    @GetMapping("/{id}")
    public InteractionDTO listarId(@PathVariable("id") Integer id){
        ModelMapper m=new ModelMapper();
        InteractionDTO dto=m.map(iS.listId(id), InteractionDTO.class);
        return dto;
    }

    @PutMapping
    public void modificar(@RequestBody InteractionDTO dto){
        ModelMapper m=new ModelMapper();
        Interaction c=m.map(dto,Interaction.class);
        iS.insert(c);
    }

}
