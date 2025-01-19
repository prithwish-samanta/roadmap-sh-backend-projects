package dev.prithwish.unit_converter.controller;

import dev.prithwish.unit_converter.domain.*;
import dev.prithwish.unit_converter.service.UnitConverterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    private final UnitConverterService unitConverterService;

    public AppController(UnitConverterService unitConverterService) {
        this.unitConverterService = unitConverterService;
    }

    @GetMapping("/length")
    public String lengthConverter(Model model) {
        model.addAttribute("activeNav", "length");
        model.addAttribute("units", LengthUnit.values());
        model.addAttribute("request", new LengthConverterRequest());
        return "length";
    }

    @PostMapping("/length/result")
    public String lengthConverterResult(Model model, @ModelAttribute LengthConverterRequest request) {
        double res = unitConverterService.convertLength(request.getLength(), request.getUnitFromConvert(), request.getUnitToConvert());
        model.addAttribute("result", String.format("%.2f %s = %.2f %s", request.getLength(), request.getUnitFromConvert(), res, request.getUnitToConvert()));
        model.addAttribute("activeNav", "length");
        model.addAttribute("reset", "/length");
        return "result";
    }

    @GetMapping("/weight")
    public String widthConverter(Model model) {
        model.addAttribute("activeNav", "weight");
        model.addAttribute("units", WeightUnit.values());
        model.addAttribute("request", new WeightConverterRequest());
        return "weight";
    }

    @PostMapping("/weight/result")
    public String widthConverterResult(Model model, @ModelAttribute WeightConverterRequest request) {
        double res = unitConverterService.convertWeight(request.getWeight(), request.getUnitFromConvert(), request.getUnitToConvert());
        model.addAttribute("result", String.format("%.2f %s = %.2f %s", request.getWeight(), request.getUnitFromConvert(), res, request.getUnitToConvert()));
        model.addAttribute("activeNav", "weight");
        model.addAttribute("reset", "/weight");
        return "result";
    }

    @GetMapping("/temperature")
    public String temperatureConverter(Model model) {
        model.addAttribute("activeNav", "temperature");
        model.addAttribute("units", TemperatureUnit.values());
        model.addAttribute("request", new TemperatureConverterRequest());
        return "temperature";
    }

    @PostMapping("/temperature/result")
    public String temperatureConverterResult(Model model, @ModelAttribute TemperatureConverterRequest request) {
        double res = unitConverterService.convertTemperature(request.getTemperature(), request.getUnitFromConvert(), request.getUnitToConvert());
        model.addAttribute("result", String.format("%.2f %s = %.2f %s", request.getTemperature(), request.getUnitFromConvert(), res, request.getUnitToConvert()));
        model.addAttribute("activeNav", "temperature");
        model.addAttribute("reset", "/temperature");
        return "result";
    }
}
