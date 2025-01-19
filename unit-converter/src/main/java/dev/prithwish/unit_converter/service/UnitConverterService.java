package dev.prithwish.unit_converter.service;

import dev.prithwish.unit_converter.domain.LengthUnit;
import dev.prithwish.unit_converter.domain.TemperatureUnit;
import dev.prithwish.unit_converter.domain.WeightUnit;

public interface UnitConverterService {
    double convertLength(double length, LengthUnit from, LengthUnit to);

    double convertWeight(double weight, WeightUnit from, WeightUnit to);

    double convertTemperature(double temperature, TemperatureUnit from, TemperatureUnit to);
}
