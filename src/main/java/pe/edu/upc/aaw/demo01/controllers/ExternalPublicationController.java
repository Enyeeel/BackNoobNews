package pe.edu.upc.aaw.demo01.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.aaw.demo01.dtos.ExternalPublicationDTO;
import pe.edu.upc.aaw.demo01.dtos.InteractionDTO;
import pe.edu.upc.aaw.demo01.entities.ExternalPublication;
import pe.edu.upc.aaw.demo01.entities.Interaction;
import pe.edu.upc.aaw.demo01.servicesinterfaces.IExternalPublicationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/externalPublication")
public class ExternalPublicationController {

    @Autowired
    private IExternalPublicationService extP;

    @PostMapping
    public void registrar(@RequestBody ExternalPublicationDTO dto){
        ModelMapper m=new ModelMapper();
        ExternalPublication t=m.map(dto, ExternalPublication.class);
        extP.insert(t);
    }


    @GetMapping
    public List<ExternalPublicationDTO> listar(){
        return extP.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,ExternalPublicationDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){
        extP.delete(id);
    }


    @GetMapping("/{id}")
    public ExternalPublicationDTO listarId(@PathVariable("id") Integer id){
        ModelMapper m=new ModelMapper();
        ExternalPublicationDTO dto=m.map(extP.listId(id), ExternalPublicationDTO.class);
        return dto;
    }

    @PutMapping
    public void modificar(@RequestBody ExternalPublicationDTO dto){
        ModelMapper m=new ModelMapper();
        ExternalPublication c=m.map(dto,ExternalPublication.class);
        extP.insert(c);
    }
}
