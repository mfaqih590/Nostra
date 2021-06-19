package com.example.demo.Controller;

import com.example.demo.Model.ModelRole;
import com.example.demo.Repository.RolesRepository;
import com.example.demo.Service.MainService;
import com.example.demo.mapper.rolesMapper;
import com.example.demo.util.AppUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/cuba/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(description = "API untuk Simpan COBADOK")
public class RolesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RolesController.class);
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private rolesMapper mapper;

    @Autowired
    private MainService mainService;

    @GetMapping("Cuba")
    public List<ModelRole> listAll(Model model) {
        List<ModelRole> listStudents = rolesRepository.findAll();
        model.addAttribute("List Role", listStudents);

        return listStudents;
    }


    @PostMapping(path = "/Roles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Post Role")
    public ResponseEntity posRole(@RequestBody ModelRole modelInput) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        try {

            rolesRepository.save(modelInput);
            String msg = "Data Berhasil ditambahkan";
            res.put("status", "Ok");
            res.put("message", msg);
            res.put("data", modelInput);
            return ResponseEntity.ok(res);

        } catch (Exception e) {
            res.put("status", "Error");
            res.put("message", "Data gagal ditambahkan");
            res.put("keterangan", e.getMessage());
            e.printStackTrace();
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get list dari semua absen (dengan paging)")
    public ResponseEntity getListed(){

        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        try {

            List<ModelRole> list = mapper.testFind();

            if (AppUtil.isObjectEmpty(list)) {
                res.put("status", false);
                res.put("message", "Data EMANG ditemukan");

                return ResponseEntity.status(404).body(res);

            } else {

                res.put("status", true);
                res.put("message", "success");
                res.put("total", list.size());
                res.put("data", list);

                return ResponseEntity.ok().body(res);
            }

        } catch (Exception e) {
            res.put("status", false);
            res.put("message", mainService.getMessageError(e.getMessage()));
            e.printStackTrace();


            return ResponseEntity.badRequest().body(res);
        }

    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "delete aja")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        try {
            rolesRepository.deleteById(id);
            res.put("status", "OK");
            res.put("message", "Berhasil Menghapus Data");
            return ResponseEntity.status(200).body(res);
        } catch (Exception e) {
            res.put("status", "false");
            res.put("message", e.getStackTrace());
            return ResponseEntity.badRequest()
                    .body(res);
        }
    }

}
