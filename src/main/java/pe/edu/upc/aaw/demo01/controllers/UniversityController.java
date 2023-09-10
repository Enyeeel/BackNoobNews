package pe.edu.upc.aaw.demo01.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.aaw.demo01.dtos.UniversityDTO;
import pe.edu.upc.aaw.demo01.entities.University;
import pe.edu.upc.aaw.demo01.servicesinterfaces.IUniversityService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private IUniversityService uS;

    @PostMapping
    public void registrar(@RequestBody UniversityDTO dto){
        ModelMapper m=new ModelMapper();
        University u=m.map(dto, University.class);
        uS.insert(u);
    }

    @GetMapping
    public List<UniversityDTO> listar(){
        return uS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,UniversityDTO.class);
        }).collect(Collectors.toList());
    }

    @PutMapping
    public void modificar(@RequestBody UniversityDTO dto){
        ModelMapper m=new ModelMapper();
        University c=m.map(dto,University.class);
        uS.insert(c);
    }
}
