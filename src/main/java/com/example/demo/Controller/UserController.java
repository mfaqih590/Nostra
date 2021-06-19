package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.MainService;
import com.example.demo.mapper.userMapper;
import com.example.demo.util.AppUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/user/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(description = "API untuk Simpan User")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MainService mainService;

    @Autowired
    private userMapper mapper;

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Post User")
    public ResponseEntity posRole(@RequestBody User modelInput) {
        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        try {
            userRepository.save(modelInput);
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

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get list dari semua user (dengan paging)")
    public ResponseEntity getListed(){

        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        try {

            List<User> list = mapper.testFind();

            if (AppUtil.isObjectEmpty(list)) {
                res.put("status", false);
                res.put("message", "Data tidak ditemukan");

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
}
