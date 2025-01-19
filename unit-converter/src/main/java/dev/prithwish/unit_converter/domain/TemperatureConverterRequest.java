package dev.prithwish.unit_converter.domain;

public class TemperatureConverterRequest {
    private double temperature;
    private TemperatureUnit unitFromConvert;
    private TemperatureUnit unitToConvert;

    public TemperatureConverterRequest() {
    }

    public TemperatureConverterRequest(double temperature, TemperatureUnit unitFromConvert, TemperatureUnit unitToConvert) {
        this.temperature = temperature;
        this.unitFromConvert = unitFromConvert;
        this.unitToConvert = unitToConvert;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public TemperatureUnit getUnitFromConvert() {
        return unitFromConvert;
    }

    public void setUnitFromConvert(TemperatureUnit unitFromConvert) {
        this.unitFromConvert = unitFromConvert;
    }

    public TemperatureUnit getUnitToConvert() {
        return unitToConvert;
    }

    public void setUnitToConvert(TemperatureUnit unitToConvert) {
        this.unitToConvert = unitToConvert;
    }
}
