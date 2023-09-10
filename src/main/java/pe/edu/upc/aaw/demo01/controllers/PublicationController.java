package pe.edu.upc.aaw.demo01.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.aaw.demo01.dtos.PublicationDTO;
import pe.edu.upc.aaw.demo01.entities.Publication;
import pe.edu.upc.aaw.demo01.servicesinterfaces.IPublicationService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publication")
public class PublicationController {

    @Autowired
    private IPublicationService pS;

    @PostMapping
    public void registrar(@RequestBody PublicationDTO dto){
        ModelMapper m=new ModelMapper();
        Publication t=m.map(dto, Publication.class);
        pS.insert(t);
    }


    @GetMapping
    public List<PublicationDTO> listar(){
        return pS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,PublicationDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){
        pS.delete(id);
    }


    @GetMapping("/{id}")
    public PublicationDTO listarId(@PathVariable("id") Integer id){
        ModelMapper m=new ModelMapper();
        PublicationDTO dto=m.map(pS.listId(id), PublicationDTO.class);
        return dto;
    }
    
    @PutMapping
    public void modificar(@RequestBody PublicationDTO dto){
        ModelMapper m=new ModelMapper();
        Publication c=m.map(dto,Publication.class);
        pS.insert(c);
    }
}
