package com.example.thithuchanhmd4.controller;


import com.example.thithuchanhmd4.model.City;
import com.example.thithuchanhmd4.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityService cityService;


    @GetMapping("")
    public ModelAndView viewListCity() {
        List<City> cityList = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("list", cityList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formcreat() {
        ModelAndView formcreat = new ModelAndView("create");
        City city = new City();
        formcreat.addObject("city", city);
        return formcreat;
    }

    @PostMapping("/create")
    public ModelAndView createCity(@ModelAttribute("city") City city) {
        cityService.save(city);
        return viewListCity();
    }

    @GetMapping("/delete/{id}")
    public ModelAndView formdelete(@PathVariable Long id) {
        City city = cityService.findById(id);
        ModelAndView formDelete = new ModelAndView("delete");
        formDelete.addObject("city", city);
        return formDelete;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute("city") City city) {
        cityService.remove(city.getId());
        return viewListCity();
    }

    @GetMapping("/view/{idCity}")
    public ModelAndView viewDetail(@PathVariable("idCity") Long id) {
        City city = cityService.findById(id);
        ModelAndView viewDetail = new ModelAndView("detail");
        viewDetail.addObject("city", city);
        return viewDetail;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView formedit(@PathVariable Long id) {
        City city = cityService.findById(id);
        ModelAndView formEdit = new ModelAndView("edit");
        formEdit.addObject("city", city);
        return formEdit;
    }

    @PostMapping("/edit")
    public ModelAndView formedit(@ModelAttribute("city") City city) {
        cityService.save(city);
        return viewListCity();
    }
}
