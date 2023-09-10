package pe.edu.upc.aaw.demo01.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.aaw.demo01.dtos.ConnectedDeviceDTO;
import pe.edu.upc.aaw.demo01.dtos.ExternalPublicationDTO;
import pe.edu.upc.aaw.demo01.entities.ConnectedDevice;
import pe.edu.upc.aaw.demo01.entities.ExternalPublication;
import pe.edu.upc.aaw.demo01.servicesinterfaces.IConnectedDeviceService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/connectedDevice")
public class ConnectedDeviceController {

    @Autowired
    private IConnectedDeviceService connD;

    @PostMapping
    public void registrar(@RequestBody ConnectedDeviceDTO dto){
        ModelMapper m=new ModelMapper();
        ConnectedDevice t=m.map(dto, ConnectedDevice.class);
        connD.insert(t);
    }


    @GetMapping
    public List<ConnectedDeviceDTO> listar(){
        return connD.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,ConnectedDeviceDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){
        connD.delete(id);
    }


    @GetMapping("/{id}")
    public ConnectedDeviceDTO listarId(@PathVariable("id") Integer id){
        ModelMapper m=new ModelMapper();
        ConnectedDeviceDTO dto=m.map(connD.listId(id), ConnectedDeviceDTO.class);
        return dto;
    }

    @PutMapping
    public void modificar(@RequestBody ConnectedDeviceDTO dto){
        ModelMapper m=new ModelMapper();
        ConnectedDevice c=m.map(dto,ConnectedDevice.class);
        connD.insert(c);
    }
}
