package pe.edu.upc.aaw.demo01.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.aaw.demo01.dtos.AcknowledgmentDTO;
import pe.edu.upc.aaw.demo01.dtos.CategoryDTO;
import pe.edu.upc.aaw.demo01.entities.Acknowledgment;
import pe.edu.upc.aaw.demo01.entities.Category;
import pe.edu.upc.aaw.demo01.servicesinterfaces.IAcknowledgmentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/acknowledgment")
public class AcknowledgmentController {

    @Autowired
    private IAcknowledgmentService ackC;

    @PostMapping
    public void registrar(@RequestBody AcknowledgmentDTO dto){
        ModelMapper m=new ModelMapper();
        Acknowledgment t=m.map(dto, Acknowledgment.class);
        ackC.insert(t);
    }


    @GetMapping
    public List<AcknowledgmentDTO> listar(){
        return ackC.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,AcknowledgmentDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){
        ackC.delete(id);
    }


    @GetMapping("/{id}")
    public AcknowledgmentDTO listarId(@PathVariable("id") Integer id){
        ModelMapper m=new ModelMapper();
        AcknowledgmentDTO dto=m.map(ackC.listId(id), AcknowledgmentDTO.class);
        return dto;
    }

    @PutMapping
    public void modificar(@RequestBody AcknowledgmentDTO dto){
        ModelMapper m=new ModelMapper();
        Acknowledgment c=m.map(dto,Acknowledgment.class);
        ackC.insert(c);
    }
}
