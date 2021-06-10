package com.gerarifa.domain.web.controller;

import com.gerarifa.domain.model.Sorteio;
import com.gerarifa.domain.model.exception.NegocioException;
import com.gerarifa.domain.ports.services.SorteioService;
import com.gerarifa.domain.web.controller.form.SorteioForm;
import com.gerarifa.domain.web.controller.form.SorteioFormMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sorteio")
public class SorteioController {

    private final SorteioService sorteioService;

    private final SorteioFormMapper mapper;

    public SorteioController(SorteioService sorteioService, SorteioFormMapper mapper) {
        this.sorteioService = sorteioService;
        this.mapper = mapper;
    }

    @ModelAttribute("sorteioForm")
    public SorteioForm form() {
        return SorteioForm.builder().build();
    }

    @GetMapping("/list")
    public String list(ModelMap model) {
        List<Sorteio> sorteiosList = sorteioService.getAllAtivo();
        List<SorteioForm> sorteiosForm = sorteiosList.stream().map(mapper::fromModel).collect(Collectors.toList());
        model.put("sorteios", sorteiosForm);
        return "sorteio/list";
    }

    @GetMapping("/form")
    public String create(@ModelAttribute SorteioForm sorteioForm) {
        return "sorteio/form";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") String id, ModelMap model) {
        SorteioForm sorteioForm = mapper.fromModel(sorteioService.getById(id));
        model.put("sorteio", sorteioForm);
        return "sorteio/view";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") String id, ModelMap model) {
        SorteioForm sorteioForm = mapper.fromModel(sorteioService.getById(id));
        model.put("sorteioForm", sorteioForm);
        return "sorteio/form";
    }

    @PostMapping("/form")
    public String save(@Validated SorteioForm sorteioForm, BindingResult result) {
        if (result.hasErrors()) {
            return "sorteio/form";
        }
        try {
            if (sorteioForm.getId() == null)
                sorteioService.criarSoteio(mapper.toModel(sorteioForm));
            else
                sorteioService.atualizarSoteio(mapper.toModel(sorteioForm));

            return "redirect:/sorteio/list";
        }catch (NegocioException e){
            return "sorteio/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        sorteioService.cancelarSorteio(id);
        return "redirect:/sorteio/list";
    }
}
