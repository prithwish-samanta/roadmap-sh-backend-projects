package dev.prithwish.unit_converter.service;

import dev.prithwish.unit_converter.domain.LengthUnit;
import dev.prithwish.unit_converter.domain.TemperatureUnit;
import dev.prithwish.unit_converter.domain.WeightUnit;
import org.springframework.stereotype.Service;

@Service
public class UnitConverterServiceImpl implements UnitConverterService {

    @Override
    public double convertLength(double length, LengthUnit from, LengthUnit to) {
        // Conversion factors to meters
        double meters = switch (from) {
            case MILLIMETER -> length / 1000.0;
            case CENTIMETER -> length / 100.0;
            case METER -> length;
            case KILOMETER -> length * 1000.0;
            case INCH -> length * 0.0254;
            case FOOT -> length * 0.3048;
            case YARD -> length * 0.9144;
            case MILE -> length * 1609.34;
        };

        // Convert from meters to the target unit
        return switch (to) {
            case MILLIMETER -> meters * 1000.0;
            case CENTIMETER -> meters * 100.0;
            case METER -> meters;
            case KILOMETER -> meters / 1000.0;
            case INCH -> meters / 0.0254;
            case FOOT -> meters / 0.3048;
            case YARD -> meters / 0.9144;
            case MILE -> meters / 1609.34;
        };
    }

    @Override
    public double convertWeight(double weight, WeightUnit from, WeightUnit to) {
        // Conversion factors to grams
        double grams = switch (from) {
            case MILLIGRAM -> weight / 1000.0;
            case GRAM -> weight;
            case KILOGRAM -> weight * 1000.0;
            case OUNCE -> weight * 28.3495;
            case POUND -> weight * 453.592;
        };

        // Convert from grams to the target unit
        return switch (to) {
            case MILLIGRAM -> grams * 1000.0;
            case GRAM -> grams;
            case KILOGRAM -> grams / 1000.0;
            case OUNCE -> grams / 28.3495;
            case POUND -> grams / 453.592;
        };
    }

    @Override
    public double convertTemperature(double temperature, TemperatureUnit from, TemperatureUnit to) {
        // Convert to Celsius first
        double celsius = switch (from) {
            case CELSIUS -> celsius = temperature;
            case FAHRENHEIT -> celsius = (temperature - 32) * 5 / 9;
            case KELVIN -> celsius = temperature - 273.15;
        };

        // Convert from Celsius to the target unit
        return switch (to) {
            case CELSIUS -> celsius;
            case FAHRENHEIT -> celsius * 9 / 5 + 32;
            case KELVIN -> celsius + 273.15;
        };
    }
}
