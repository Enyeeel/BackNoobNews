package pe.edu.upc.aaw.demo01.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.aaw.demo01.dtos.ConfigurationDTO;
import pe.edu.upc.aaw.demo01.dtos.ConnectedDeviceDTO;
import pe.edu.upc.aaw.demo01.entities.Configuration;
import pe.edu.upc.aaw.demo01.entities.ConnectedDevice;
import pe.edu.upc.aaw.demo01.servicesinterfaces.IConfigurationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {

    @Autowired
    private IConfigurationService conS;

    @PostMapping
    public void registrar(@RequestBody ConfigurationDTO dto){
        ModelMapper m=new ModelMapper();
        Configuration t=m.map(dto, Configuration.class);
        conS.insert(t);
    }


    @GetMapping
    public List<ConfigurationDTO> listar(){
        return conS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,ConfigurationDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){
        conS.delete(id);
    }


    @GetMapping("/{id}")
    public ConfigurationDTO listarId(@PathVariable("id") Integer id){
        ModelMapper m=new ModelMapper();
        ConfigurationDTO dto=m.map(conS.listId(id), ConfigurationDTO.class);
        return dto;
    }

    @PutMapping
    public void modificar(@RequestBody ConfigurationDTO dto){
        ModelMapper m=new ModelMapper();
        Configuration c=m.map(dto,Configuration.class);
        conS.insert(c);
    }
}
